package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import com.betha.pessoal.scripts.padrao.PeriodosAquisitivosDecimoTerceiro

import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 111, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()

if (!Funcoes.recebeDecimoTerceiro(matricula.tipo)) {
    suspender "Matrícula não recebe décimo terceiro salário"
}

if (!Funcoes.avisoPrevioIndenizado()) {
    suspender "Sem aviso prévio indenizado"
}

def vvar = Lancamentos.valor(evento)
valorReferencia = 1
if (vvar > 0) {
    valorCalculado = vvar
} else {
    int mesproc
    def avosAdquiridos
    double valorPeriodo
    PeriodosAquisitivosDecimoTerceiro.buscaPeriodosAquisitivosBySituacao(SituacaoPeriodoAquisitivoDecimoTerceiro.EM_ANDAMENTO).each { periodo ->
        mesproc = Datas.mes(calculo.competencia)
        avosAdquiridos = periodo.avosAdquiridos

        valorPeriodo = valorPeriodo + Funcoes.remuneracao(matricula.tipo).valor / 12
    }
    valorCalculado = valorPeriodo
}

if (valorCalculado > 0) {
    Bases.compor(valorCalculado, Bases.IRRF13, Bases.INSS13, Bases.FGTS13)
}