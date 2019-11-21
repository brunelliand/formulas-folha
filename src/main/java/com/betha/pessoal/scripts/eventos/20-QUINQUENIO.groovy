package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.Numeros
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import com.betha.pessoal.scripts.padrao.Bases
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 20, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

if (TipoMatricula.FUNCIONARIO.equals(matricula.tipo)) {
    int forma = 1 //1-Normal , 2-Composto
    def cptproc = Datas.data(Datas.ano(calculo.competencia), Datas.mes(calculo.competencia), 1)
    def dtbase = funcionario.dataBase
    if (dtbase == null) {
        dtbase = funcionario.dataAdmissao
    }
    def vvar = Lancamentos.valor(evento)
    def vvarFgts = Lancamentos.valor(evento)
    boolean somenteFgts = false
    int rh = 0
    int novant = 0;
    if (vvar < 0 || vvarFgts < 0) {
        novant = 0 //Implementar posteriormente a rotina de controle de adicionais
    } else {
        if (vvar < 0) {
            somenteFgts = true
        }
        def dfin = Datas.data(calculo.competencia.ano, calculo.competencia.mes, calculo.quantidadeDiasCompetencia)
        def afastamentos = [ClassificacaoTipoAfastamento.AUXILIO_DOENCA_PREVIDENCIA, ClassificacaoTipoAfastamento.LICENCA_SEM_VENCIMENTOS]
        int meses = Funcoes.mesesafast(dtbase, dfin, afastamentos)
        if (meses > 0) {
            dfin = Datas.removeMeses(cptproc, meses)
            int dia = calculo.quantidadeDias(Datas.mes(dfin), Datas.ano(dfin))
            if (Datas.mes(dfin).equals(2)) {
                if (Datas.ano(dfin)) {
                    if (Numeros.resto(Datas.ano(dfin), 4) == 0) {
                        dia = 29
                    } else {
                        dia = 28
                    }
                }
            }
            dfin = Datas.data(Datas.ano(dfin), Datas.mes(dfin), dia)
        }
        novant = Datas.diferencaAnos(dtbase, dfin) / evento.taxa
        novant = Numeros.trunca(novant, 0)
    }
    if (novant < 1) {
        suspender 'Sem adicional adquirido!'
    }
    if (vvar > 0) {
        valorReferencia = vvar
    } else {
        if (vvarFgts > 0) {
            valorReferencia = vvarFgts
        } else {
            if (rh == 1) {
                valorReferencia = 0 //Alterar quando implementar controle de adicionais
            } else {
                def taxa = evento.taxa
                def vaux
                if (forma == 1 || novant == 1) {
                    vaux = novant * taxa
                } else {
                    taxa /= 100
                    vaux = 1 + taxa
                    vaux = ((vaux ^ novant) - 1) / taxa
                    vaux *= taxa * 100
                }
                if (vaux > 35) {
                    vaux = 35 //Vai para de gerar em 35%
                }
                valorReferencia = vaux
            }
        }
    }
    def vcal = (funcionario.salario * valorReferencia) / 100
    if (vcal > 0) {
        if (calculo.tipoProcessamento == TipoProcessamento.DECIMO_TERCEIRO_SALARIO) {
            vcal *= Funcoes.avos13(12) / 12
            if (calculo.subTipoProcessamento == SubTipoProcessamento.ADIANTAMENTO) {
                vcal *= 50 / evento.getTaxa(26)
            }
            valorCalculado = vcal
        } else {
            valorCalculado = vcal
            BigDecimal base;
            if (Eventos.valor(195) > 0) {
                base = Bases.valor(Bases.PAGAPROP) - Bases.valor(Bases.MEDAUXMATPR)
            } else {
                base = Bases.valor(Bases.PAGAPROP)
            }
            if (base > 0 && !somenteFgts) {
                Bases.compor(vcal, Bases.HORAEXTRA, Bases.SALAFAM)
            }
            valorCalculado = Funcoes.calcprop(vcal, base)
        }
        afastamentos = [ClassificacaoTipoAfastamento.LICENCA_COM_VENCIMENTOS,
                        ClassificacaoTipoAfastamento.LICENCA_MATERNIDADE,
                        ClassificacaoTipoAfastamento.ACIDENTE_DE_TRABALHO_EMPREGADOR,
                        ClassificacaoTipoAfastamento.DOENCA_DO_TRABALHO_EMPREGADOR,
                        ClassificacaoTipoAfastamento.PRORROGACAO_DA_LICENCA_MATERNIDADE,
                        ClassificacaoTipoAfastamento.ABORTO_NAO_CRIMINOSO,
                        ClassificacaoTipoAfastamento.ADOCAO_GUARDA_JUDICIAL_DE_CRIANCA,
                        ClassificacaoTipoAfastamento.CEDENCIA,
                        ClassificacaoTipoAfastamento.ACIDENTE_DE_TRABALHO_PREVIDENCIA,
                        ClassificacaoTipoAfastamento.SERVICO_MILITAR]

        dias = Funcoes.diastrab() + Funcoes.diasafastcalc30(1, calculo.competencia, afastamentos)
        valBaseFgts = Funcoes.calcprop(vcal, Funcoes.cnvdpbase(dias))
        if (!somenteFgts) {
            Bases.compor(valorCalculado, Bases.SALBASE, Bases.PERIC, Bases.IRRF, Bases.INSS, Bases.MEDIAUXMAT)
            Bases.compor(valorCalculado, Bases.PREVEST, Bases.FUNDASS, Bases.FUNDOPREV, Bases.FUNDFIN)
        }
        Bases.compor(valorCalculado, Bases.FGTS)
    }
}
