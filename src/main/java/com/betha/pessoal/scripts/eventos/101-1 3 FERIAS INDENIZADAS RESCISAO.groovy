package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Numeros
import com.betha.pessoal.scripts.padrao.PeriodosAquisitivos

import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 101, taxa: 33.3333, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/


vaux = Lancamentos.valor(evento)
if (vaux > 0) {
    valorReferencia = vaux
    valorCalculado = vaux
} else {
    double vlrPer
    double vlrAux
    valorCalculado = 0
    if (Eventos.valor(98) <= 0) {
        suspender 'Sem evento de ferias em dobro calculado'
    }
    PeriodosAquisitivos.buscaVencidos().each { feriasvenc ->
        vlrPer = Funcoes.remuneracao(matricula.tipo).valor * evento.taxa / 100;
        vlrPer = Numeros.arredonda(vlrPer, 2)
        PeriodosAquisitivos.quitaPorRescisao(feriasvenc, vlrPer)
        vlrAux += vlrPer
    }

    valorCalculado += vlrAux
    valorReferencia = evento.taxa

}

