package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.evento
import com.betha.pessoal.scripts.padrao.Folhas.folha
import com.betha.pessoal.scripts.padrao.Folhas.folhas
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

def valorFerias = Funcoes.replicaFeriasNaFolhaMensal(evento.codigo, [Bases.INSSFER])
valorCalculado = valorFerias.valor
valorReferencia = valorFerias.referencia

def remuneracao
if (TipoProcessamento.FERIAS.equals(calculo.tipoProcessamento)) {
    remuneracao = Funcoes.remuneracao(matricula.tipo).valor
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
            Bases.compor(remuneracao, Bases.SIND)
            Bases.compor(valorReferencia, Bases.PAGAPROP)
            Bases.compor(valorCalculado, Bases.FGTS, Bases.IRRFFER, Bases.INSS, Bases.PREVEST, Bases.FUNDASS, Bases.FUNDOPREV)
            Bases.compor(valorCalculado, Bases.COMPHORAMES, Bases.FUNDFIN)
        }
    } else {
        imprimir 'Folha interna de ferias'

        if (TipoMatricula.FUNCIONARIO.equals(matricula.tipo) ) {

            remuneracao = Funcoes.remuneracao(matricula.tipo).valor
            def diasferias = folha.diasGozo

            def vaux = Lancamentos.valor(evento)

            if (vaux >= 0) {
                valorReferencia = vaux
            } else {
                if (diasferias > 0) {
                    vaux = Funcoes.cnvdpbase(diasferias);
                    valorReferencia = vaux
                }
            }
            if (vaux > 0) {
                valorCalculado = Funcoes.calcprop(remuneracao, vaux)
            }
        }
    }
}



