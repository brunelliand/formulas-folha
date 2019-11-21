package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.Calculo
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.PeriodosAquisitivos
import com.betha.pessoal.scripts.padrao.PeriodosAquisitivos.periodoAquisitivo
import com.betha.pessoal.scripts.padrao.PeriodosAquisitivos.periodoConcessao
import com.betha.pessoal.scripts.padrao.evento
import com.betha.pessoal.scripts.padrao.Folhas.folha
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import com.betha.pessoal.scripts.padrao.Folhas.folhas
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*


/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

def valorFerias = Funcoes.replicaFeriasNaFolhaMensal(evento.codigo, [Bases.INSSFER])
valorCalculado = valorFerias.valor
valorReferencia = valorFerias.referencia

if (Calculo.calculo.tipoProcessamento.equals(TipoProcessamento.FERIAS)) {
    if (folha.folhaPagamento) {
        imprimir 'Folha de pagamento das ferias'
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
            Bases.compor(valorCalculado, Bases.IRRFFER)
        }
    } else {
        long diasfer = 0
        def datafin = Datas.adicionaDias(periodoAquisitivo.dataFinal, 1)
        def mesesconces = periodoAquisitivo.configuracaoFerias.mesesParaConcessao
        datafin = Datas.adicionaMeses(datafin, mesesconces)
        datafin = Datas.removeDias(datafin, 1)

        if (periodoConcessao.dataInicioGozo > datafin) {
            diasfer = Datas.diferencaDias(periodoConcessao.dataInicioGozo, periodoConcessao.dataFimGozo) + 1
        } else {
            if (periodoConcessao.dataFimGozo > datafin) {
                diasfer = Datas.diferencaDias(datafin, periodoConcessao.dataFimGozo)
            }
        }
        def vlrrefaux
        def vaux = Lancamentos.valor(evento)
        if (vaux >= 0) {
            valorCalculado = vaux
        } else {
            def diasdirfer = periodoAquisitivo.configuracaoFerias.diasParaAdquirirNoPeriodo
            vlrrefaux = diasfer / diasdirfer

            if (vlrrefaux > 2) {
                vlrrefaux = 2
            }
            def vlrcalcux = Funcoes.remuneracao(matricula.tipo).valor * diasdirfer / 30
            valorCalculado = vlrcalcux * vlrrefaux
            valorReferencia = vlrrefaux
        }


    }
}
imprimir(valorReferencia)
imprimir(valorCalculado)





