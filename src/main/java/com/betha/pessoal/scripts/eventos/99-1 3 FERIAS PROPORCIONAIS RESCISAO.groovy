package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.PeriodosAquisitivos

import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 99, taxa: 33.333, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()
def vaux = Lancamentos.valor(evento)
if (vaux >= 0) {
    valorCalculado = vaux
    valorReferencia = vaux
} else {
    def feriasprop = PeriodosAquisitivos.buscaProporcional();
    vaux = Eventos.valor(96) + Eventos.valor(102) + Eventos.valor(103) + Eventos.valor(104) + Eventos.valor(112) + Eventos.valor(235)

    valorReferencia = evento.taxa
    valorCalculado = vaux * evento.taxa / 100
    if (valorCalculado > 0) {
        PeriodosAquisitivos.quitaPorRescisao(feriasprop, valorCalculado)
    }

}
