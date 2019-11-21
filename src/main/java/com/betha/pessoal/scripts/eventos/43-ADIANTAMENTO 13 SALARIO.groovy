package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.PeriodosAquisitivosDecimoTerceiro
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 43, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

boolean recebeDecimoTerceiro = Funcoes.recebeDecimoTerceiro()

if (!recebeDecimoTerceiro) {
    suspender "Cálculo do evento ${evento.codigo} cancelado"
}

if (!Funcoes.permitecalc13integral()) {
    suspender "Cálculo do evento ${evento.codigo} cancelado"
}

vaux = Lancamentos.valor(evento)

if (vaux >= 0) {
    valorCalculado = vaux
} else {

    double valorAdiantado = PeriodosAquisitivosDecimoTerceiro.buscaPeriodosAquisitivosBySituacao(SituacaoPeriodoAquisitivoDecimoTerceiro.QUITADO_PARCIALMENTE).sum(0, {
        it.anoExercicio == calculo.anoDecimoTerceiro || calculo.dataRescisao ? it.totalMovimentacoes : 0
    })
    if (valorAdiantado == 0) {
        suspender "Cálculo do evento ${evento.codigo} cancelado "
    }

    valorCalculado = valorAdiantado - Eventos.valor(200) - Eventos.valor(184)
    if (Eventos.valor(25) > 0) {
        Bases.compor(valorCalculado, Bases.FGTS13)
    }
}

