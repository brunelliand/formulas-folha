package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 119, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

def vvar = Lancamentos.valor(evento)

if (vvar > 0) {
    def ferias = Funcoes.replicaEventoVariavel(evento.codigo)
    def valor = 0
    def diferenca = 0

    if (ferias.valor > 0) {
        ferias.valor
        diferenca = vvar - ferias.valor
        if (diferenca > 0) {
            valorCalculado = ferias.valor + diferenca
        } else {
            valorCalculado = ferias.valor
        }
    } else {
        valorReferencia = vvar
        valorCalculado = vvar
    }
}

imprimir(valorCalculado)
