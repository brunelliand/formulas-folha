package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 45, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

def vaux = Lancamentos.valor(evento)
if (vaux > 0) {
    valorReferencia = vaux
    valorCalculado = vaux
} else {
    vaux = 0
    if (calculo.tipoProcessamento == TipoProcessamento.MENSAL && calculo.subTipoProcessamento == SubTipoProcessamento.INTEGRAL) {
        vaux = Eventos.valorCalculado(67, TipoValor.CALCULADO, TipoProcessamento.MENSAL, SubTipoProcessamento.ADIANTAMENTO)
    }
    valorReferencia = vaux
    valorCalculado = vaux
}
