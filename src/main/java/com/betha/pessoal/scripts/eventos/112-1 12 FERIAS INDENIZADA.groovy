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
def evento = [codigo: 112, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()
if (!Funcoes.avisoPrevioIndenizado()) {
    suspender "Sem aviso prévio indenizado"
}

def vvar = Lancamentos.valor(evento)
valorReferencia = 1
if (vvar > 0) {
    valorCalculado = vvar
} else {
    def periodoProporcional = PeriodosAquisitivos.buscaProporcional()
    if (!periodoProporcional) suspender "Sem férias proporcionais"
    def feriasprop = periodoProporcional.saldo
    def mesesaquis = periodoProporcional.configuracaoFerias.mesesParaAquisicao
    def diasdirfer = periodoProporcional.configuracaoFerias.diasParaAdquirirNoPeriodo
    double vlorfer

    if (feriasprop > 0) {
        vlorfer = Eventos.valor(96) + Eventos.valor(102) + Eventos.valor(103) + Eventos.valor(104)
        valorCalculado = vlorfer / feriasprop * (diasdirfer / mesesaquis);
    } else {
        int feriasVencidas
        PeriodosAquisitivos.buscaVencidos().each { p ->
            feriasVencidas++
            mesesaquis = p.configuracaoFerias.mesesParaAquisicao
        }
        if (feriasVencidas > 0) {
            def nofer = feriasVencidas
            if (nofer > 5) {
                nofer = 5
            }
            vlorfer = Eventos.valor(97) + Eventos.valor(105) + Eventos.valor(106) + Eventos.valor(107)
            valorCalculado = vlorfer / nofer / mesesaquis
        } else {
            valorCalculado = 0
            valorReferencia = 0
        }
    }
}
