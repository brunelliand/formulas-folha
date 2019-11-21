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
def evento = [codigo: 98, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

if (matricula.tipo == TipoMatricula.FUNCIONARIO) {
    def vaux = Lancamentos.valor(evento)
    if (vaux > 0) {
        valorReferencia = vaux
        valorCalculado = vaux
    } else {
        double vinf
        double vint
        double vger
        valorCalculado = 0
        def vencidos = PeriodosAquisitivos.buscaVencidos();
        if (vencidos.size() < 2) suspender "Não possui mais que uma férias vencida"
        double vlrProporcional
        vencidos.each { feriasvenc ->
            vinf++
            def valorDiasFeriasVencidos = feriasvenc.saldo
            vlrProporcional = 0
            if (vinf > 1) {
                vlrProporcional = Funcoes.remuneracao(matricula.tipo).valor * valorDiasFeriasVencidos / 30;
            }
        }
        valorCalculado += vlrProporcional
        valorReferencia = vinf - 1
        if (valorCalculado < 0) {
            valorCalculado = 0
        }
    }
}

imprimir(valorReferencia)
imprimir(valorCalculado)