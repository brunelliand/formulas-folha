package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.EncargosSociais
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.Numeros
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 50, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

if (!TipoMatricula.FUNCIONARIO.equals(matricula.tipo) && matricula.tipo != TipoMatricula.AUTONOMO) {
    suspender 'Calculo somente para funcionarios e autônomos '
}

def vaux = 0
def vmat = 0
def base = 0
def basefer = 0
def inssfer = 0
def abatinss = 0;

def dtrescisao
boolean possuiPrevidenciaFederal

if (TipoMatricula.FUNCIONARIO.equals(matricula.tipo)) {
    dtrescisao = calculo.dataRescisao
    possuiPrevidenciaFederal = funcionario.possuiPrevidenciaFederal
} else {
    possuiPrevidenciaFederal = true;
}

def salario = Funcoes.remuneracao(matricula.tipo).valor
def diafin
if (dtrescisao == null) {
    if (calculo.quantidadeDiasCompetencia > 30 || Funcoes.afasservmil() > 0 || Funcoes.afasacidtrab() > 0) {
        diafin = 30
    } else {
        diafin = calculo.quantidadeDiasCompetencia
    }
} else {
    diafin = Datas.dia(dtrescisao)
}

def diassd = Funcoes.afasservmil() + Funcoes.afasacidtrab() //dias sem direito
def dias = diafin - diassd

if (diassd > 0) {
    abatinss = Numeros.arredonda(Funcoes.calcprop(salario, Funcoes.cnvdpbase(diassd)), 2)
}

if (dias > 0) {
    vaux = Lancamentos.valor(evento);
    if (!possuiPrevidenciaFederal) {
        suspender 'Calculo somente sera realizado para matriculas com previdencia federal'
    }

    basefer = Bases.valor(Bases.INSSFER);
    imprimir 'Base de INSS de férias ' + basefer
    if (basefer > 0) {
        inssfer = Funcoes.getInssFerias().valor;
        imprimir 'INSS proporcional de férias ' + inssfer
    } else {
        basefer = 0
        inssfer = 0
    }

    def baseprev = Bases.valor(Bases.INSS) + Bases.valor(Bases.INSSOUTRA)
    imprimir 'Base de previdencia : ' + baseprev

    if (diassd == 0) {
        base = baseprev
    } else {
        base = baseprev - abatinss;
    }
    base += basefer
    if (base > 0) {
        if (vaux >= 0) {
            valorReferencia = vaux
            valorCalculado = vaux
        } else {
            def baseint = baseprev //avaliar se deve mudar para base ao inves de baseprev
            def aux = Funcoes.afasadocao()

            if (aux > 0) {
                vmat = Eventos.valor(124);
                baseint -= vmat
            }
            def max = EncargosSociais.RGPS.buscaMaior(1)
            if (baseint > max) {
                vaux = baseint - max
                Bases.compor(vaux, Bases.EXCEINSS)
            }
            vaux = base
            if (vaux > max) {
                vaux = max
            }
            if (TipoMatricula.FUNCIONARIO.equals(matricula.tipo)) {
                vaux2 = Numeros.trunca(vaux, 2);
                valorReferencia = EncargosSociais.RGPS.buscaContribuicao(vaux2, 2)
            } else {
                if (EncargosSociaisFpas.ENTIDADE_BENEFICENTE.equals(EncargosSociais.RGPS.codigoFpas) && (TipoMatricula.AUTONOMO.equals(matricula.tipo))) {
                    valorReferencia = 20
                } else {
                    valorReferencia = EncargosSociais.RGPS.buscaMaior(2)
                }
            }

            if (vmat > 0) {
                base -= vmat
                if (base > max) {
                    base = max
                }
            } else {
                base = vaux
            }

            vaux = (base * valorReferencia) / 100
            valorCalculado = Numeros.trunca(vaux, 2) - inssfer - Eventos.valor(169)

            if (calculo.subTipoProcessamento == SubTipoProcessamento.COMPLEMENTAR) {
                valorCalculado += Eventos.valorCalculado(169, TipoValor.CALCULADO, TipoProcessamento.MENSAL, SubTipoProcessamento.INTEGRAL)
            }
        }
        Bases.compor(valorCalculado, Bases.IRRF, Bases.ABATIRRF)
    }
}


diassemdireito = Funcoes.afasacidtrab() + Funcoes.afasservmil()

if ((base != 0) || ((base == 0) && (diassemdireito > 0) && (Funcoes.diastrab() == 0))) {
    Bases.compor(valorCalculado, Bases.ABATINSS)
}

imprimir valorReferencia
imprimir valorCalculado