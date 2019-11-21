package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
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
def evento = [codigo: 100, taxa: 33.3333, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

vvar = Lancamentos.valor(evento)
if (vvar >= 0) {
    valorReferencia = vvar
    valorCalculado = vvar
} else {
    double vlrPer
    int vinf
    valorCalculado = 0
    PeriodosAquisitivos.buscaVencidos() each { feriasvenc ->
        def diasferias = feriasvenc.saldo
        vinf++

        if (diasferias > 0) {
            vlrPer += feriasvenc.valorCalculadoPago * diasferias / feriasvenc.configuracaoFerias.diasParaAdquirirNoPeriodo
            vlrPer = vlrPer * evento.taxa / 100
            vlrPer = Numeros.arredonda(vlrPer, 2)
            valorCalculado += vlrPer
        }
    }
    valorReferencia = vinf
}

imprimir(valorReferencia)
imprimir(valorCalculado)