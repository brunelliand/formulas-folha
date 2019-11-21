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
def evento = [codigo: 97, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

def vvar = Lancamentos.valor(evento)
if (vvar >= 0) {
    valorReferencia = vvar
    valorCalculado = vvar
} else {
    double vtot
    double vinf
    valorCalculado = 0
    def vencidos = PeriodosAquisitivos.buscaVencidos();
    if (vencidos.size() <= 0) suspender "Sem períodos de férias vencidos";
    vencidos.each { feriasvenc ->
        def diasferias = feriasvenc.saldo
        vinf++

        if (diasferias > 0) {
            vtot = Funcoes.remuneracao(matricula.tipo).valor * diasferias / feriasvenc.configuracaoFerias.diasParaAdquirirNoPeriodo
            valorCalculado += vtot
            PeriodosAquisitivos.quitaPorRescisao(feriasvenc, vtot)
        }
    }
    valorReferencia = vinf
}

imprimir(valorReferencia)
imprimir(valorCalculado)