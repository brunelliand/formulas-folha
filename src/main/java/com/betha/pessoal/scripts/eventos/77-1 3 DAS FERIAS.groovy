package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.Calculo
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Numeros
import com.betha.pessoal.scripts.padrao.evento
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.Folhas.folha
import com.betha.pessoal.scripts.padrao.Folhas.folhas
import com.betha.pessoal.scripts.padrao.PeriodosAquisitivos.periodoAquisitivo
import com.betha.pessoal.scripts.padrao.PeriodosAquisitivos.periodoConcessao
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/


def valorFerias = Funcoes.replicaFeriasNaFolhaMensal(evento.codigo,[Bases.INSSFER])
valorCalculado = valorFerias.valor
valorReferencia = valorFerias.referencia

if (TipoProcessamento.FERIAS.equals(calculo.tipoProcessamento)) {
    if (folha.folhaPagamento) {
        imprimir 'Calculo da folha de pagamento das ferias'
        valorReferencia = 0
        valorCalculado = 0
        folhas.buscaFolhas().each { f ->
            f.eventos.each { e ->
                if (e.codigo == evento.codigo) {
                    valorReferencia += e.referencia
                    valorCalculado += e.valor
                }
            }
        }
        if (valorCalculado > 0) {
            Bases.compor(valorCalculado,
                    Bases.FGTS,
                    Bases.DESCTERFER,
                    Bases.IRRFFER,
                    Bases.INSS,
                    Bases.PREVEST,
                    Bases.FUNDASS,
                    Bases.FUNDOPREV,
                    Bases.FUNDFIN,
                    Bases.MEDIAUXMAT)
        }
    } else {
        def vaux = Lancamentos.valor(evento)
        if (vaux >= 0) {
            valorCalculado = vaux
            valorReferencia = vaux
        } else {
            if (periodoAquisitivo.pagouUmTercoIntegral) {
                suspender 'O 1/3 de férias já foram pagas no gozo anterior (ferias fracionadas)!'
            }
            vaux = Eventos.valor(75) + Eventos.valor(80) + Eventos.valor(81) + Eventos.valor(82) + Eventos.valor(234)

            if (calculo.pagarUmTercoIntegral) {
                vaux = ((vaux * (periodoConcessao.diasGozo / folha.diasGozo)) * (periodoAquisitivo.saldo - periodoConcessao.diasAbono) / periodoConcessao.diasGozo)
                vaux = (vaux / periodoConcessao.diasGozo) * folha.diasGozo
                vaux = Numeros.arredonda(vaux, 2)
            }

            valorReferencia = evento.taxa
            valorCalculado = vaux * evento.taxa / 100
        }
    }
}

imprimir(valorCalculado)