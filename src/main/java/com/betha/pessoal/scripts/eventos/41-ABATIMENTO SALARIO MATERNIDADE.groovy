package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.Numeros
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import com.betha.pessoal.scripts.padrao.Servidor.servidor
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 41, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

if (TipoMatricula.FUNCIONARIO.equals(matricula.tipo)) {
    if (!servidor.sexo.equals(Sexo.FEMININO)) {
        suspender 'Calculo realizado apenas para sexo feminino'
    }

    if (!funcionario.possuiPrevidenciaFederal) {
        suspender 'Calculo realizado apenas para previdencia federal'
    }

    def vsmataux = 0

    def datainicial = Datas.data(Datas.ano(calculo.competencia), Datas.mes(calculo.competencia), 1)
    def datafinal = Datas.data(Datas.ano(calculo.competencia), Datas.mes(calculo.competencia), calculo.quantidadeDiasCompetencia)

    def classificacoes = [ClassificacaoTipoAfastamento.LICENCA_MATERNIDADE,
                          ClassificacaoTipoAfastamento.PRORROGACAO_DA_LICENCA_MATERNIDADE,
                          ClassificacaoTipoAfastamento.ABORTO_NAO_CRIMINOSO]

    def dias = Funcoes.diasafast(datainicial, datafinal, classificacoes)

    if (dias <= 0) {
        suspender 'Sem dias de direito '
    }


    def vaux = Lancamentos.valor(evento)
    if (vaux >= 0) {
        valorCalculado = vaux
    } else {
        def ultdia = datafinal
        def diaaf = Funcoes.diasafast(ultdia, ultdia, classificacoes)

        if (diaaf == 0 && calculo.subTipoProcessamento == SubTipoProcessamento.COMPLEMENTAR) {
            suspender "Cálculo do evento ${evento.codigo} cancelado"
        }


        def base = (Bases.valor(Bases.INSS) +
                Eventos.valor(40)) - (
                Eventos.valor(22) +
                        Eventos.valor(23) +
                        Eventos.valor(24) +
                        Eventos.valor(178) +
                        Eventos.valor(179) +
                        Eventos.valor(180)
        )

        def aux = Funcoes.cnvdpbase(Funcoes.afaslicmat()) + Funcoes.cnvdpbase(Funcoes.afasprorroglicmat()) + Funcoes.cnvdpbase(Funcoes.afasaborto())

        if (base == funcionario.salario) {
            vsmataux = funcionario.salario
        } else {
            def baseaux
            def hrsmesaux
            if (funcionario.unidadePagamento == UnidadePagamento.DIARISTA) {
                baseaux = (funcionario.quantidadeHorasMes / 30) * Bases.valor(Bases.PAGAPROP)
                baseaux = Numeros.arredonda(baseaux, 12)
                hrsmesaux = Numeros.arredonda(funcionario.quantidadeHorasMes, 12)
            } else {
                baseaux = Bases.valor(Bases.PAGAPROP)
                hrsmesaux = funcionario.quantidadeHorasMes
            }

            if (baseaux != hrsmesaux) {
                vsmataux = (Eventos.valor(3) + Eventos.valor(161) + Eventos.valor(162))
            }
        }
        if ((vsmataux != funcionario.salario) && (Eventos.valor(195) == 0)) {
            base = (Bases.valor(Bases.INSS) +
                    Eventos.valor(40)) - (
                    Eventos.valor(22) +
                            Eventos.valor(23) +
                            Eventos.valor(24) +
                            Eventos.valor(178) +
                            Eventos.valor(179) +
                            Eventos.valor(180))

            vsmataux = (Eventos.valor(3) +
                    Eventos.valor(161) +
                    Eventos.valor(162))


            if (dias < calculo.quantidadeDiasCompetencia) {
                if (datainicial >= Datas.data(2003, 8, 1)) {
                    def diafin = Bases.valor(Bases.PAGAPROP)

                    if ((funcionario.unidadePagamento == UnidadePagamento.DIARISTA) ||
                            (funcionario.unidadePagamento == UnidadePagamento.HORISTA)) {
                        diafin = diafin + (funcionario.quantidadeHorasMes / 30)
                    }

                    def dtrescisao = calculo.dataRescisao
                    diaresc = 0
                    if (dtrescisao) {
                        diaresc = dtrescisao.dia
                    }
                    if ((dtrescisao) && (Funcoes.cnvdpbase(diaresc) < diafin)) {
                        diafin = Funcoes.cnvdpbase(diaresc)
                    }
                    base = (base - Bases.valor(Bases.COMPHORAMES))
                    base = (((base * aux) / diafin) + vsmataux)
                } else {
                    if (dtrescisao) {
                        base /= Datas.dia(dtrescisao)
                        base = Numeros.trunca(base, 2) * calculo.quantidadeDiasCompetencia
                    }
                    base /= calculo.quantidadeDiasCompetencia
                    base = Numeros.trunca(base, 2) * dias
                }
            }
            valorCalculado = base
        } else {
            valorCalculado =
                    Eventos.valor(3) + Eventos.valor(161) + Eventos.valor(162) + Eventos.valor(195)
        }
    }
}








