package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.PeriodosAquisitivosDecimoTerceiro.periodoAquisitivoDecimoTerceiro
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 26, taxa: 33.3333, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

boolean recebeDecimoTerceiro = Funcoes.recebeDecimoTerceiro()

if (!recebeDecimoTerceiro) {
    suspender "Cálculo do evento ${evento.codigo} cancelado"
}

boolean periodoEmAndamento;
periodoEmAndamento = periodoAquisitivoDecimoTerceiro.situacao.equals(SituacaoPeriodoAquisitivoDecimoTerceiro.EM_ANDAMENTO) ||
        periodoAquisitivoDecimoTerceiro.situacao.equals(SituacaoPeriodoAquisitivoDecimoTerceiro.ATRASADO)

if (!periodoEmAndamento) {
    suspender "Cálculo do evento ${evento.codigo} cancelado"
}

vvar = Lancamentos.valor(evento)

if (calculo.pagarDecimoTerceiroFerias && TipoProcessamento.FERIAS.equals(calculo.tipoProcessamento)) {
    if (periodoAquisitivoDecimoTerceiro.totalMovimentacoes > 0) {
        suspender "Cálculo do evento ${evento.codigo} cancelado"
    }
}

valorReferencia = Funcoes.avos13(12)
imprimir "Avos : " + valorReferencia

if (vvar > 0) {
    valorCalculado = vvar
} else {
    def salario = Funcoes.remuneracao(matricula.tipo).valor
    valorCalculado = salario * valorReferencia / 12 * evento.taxa / 100
    if (valorCalculado < 0) {
        valorCalculado = 0
    }
}

Bases.compor(valorCalculado, Bases.FGTS13)

