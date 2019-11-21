package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.PeriodosAquisitivos

import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 96, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

vvar = Lancamentos.valor(evento)
if (vvar >= 0) {
    valorReferencia = vvar
    valorCalculado = vvar
} else {
    vtot = 0
    vinf = 0
    valorCalculado = 0
    def feriasprop = PeriodosAquisitivos.buscaProporcional();
    if (feriasprop != null) {
        suspender 'Sem dias de ferias proporcionais.'
    }

    if (feriasprop.saldo > 0) {
        def diasferias = feriasprop.saldo
        valorCalculado = Funcoes.remuneracao(matricula.tipo).valor * diasferias / feriasprop.configuracaoFerias.diasParaAdquirirNoPeriodo
        valorReferencia = diasferias
        PeriodosAquisitivos.quitaPorRescisao(feriasprop, valorCalculado)
    }
}

imprimir(valorReferencia)
imprimir(valorCalculado)