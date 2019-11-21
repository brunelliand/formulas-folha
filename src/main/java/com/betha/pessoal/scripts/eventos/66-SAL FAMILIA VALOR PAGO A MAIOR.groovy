package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 66, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

if (calculo.tipoProcessamento == TipoProcessamento.MENSAL &&
        calculo.subTipoProcessamento == SubTipoProcessamento.COMPLEMENTAR) {
    def vvar = Lancamentos.valor(evento)
    if (vvar > 0) {
        valorCalculado = vvar
    } else {
        def valorIntegral = Eventos.valorCalculado(126, TipoValor.CALCULADO, TipoProcessamento.MENSAL, SubTipoProcessamento.INTEGRAL) + Eventos.valorCalculado(127,
                TipoValor.CALCULADO, TipoProcessamento.MENSAL, SubTipoProcessamento.INTEGRAL) +
                Eventos.valorCalculado(128, TipoValor.CALCULADO, TipoProcessamento.MENSAL, SubTipoProcessamento.INTEGRAL) +
                Eventos.valorCalculado(129, TipoValor.CALCULADO, TipoProcessamento.MENSAL, SubTipoProcessamento.INTEGRAL)

        def valorComplementar = Eventos.valor(126) + Eventos.valor(127) + Eventos.valor(128) + Eventos.valor(129)
        if (valorComplementar < valorIntegral) {
            valorCalculado = valorIntegral - valorComplementar
        }
    }
}
