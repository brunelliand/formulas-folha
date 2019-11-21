package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 47, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

def vvar = Lancamentos.valor(evento)
if (vvar > 0) {
    valorCalculado = vvar
} else {
    def mesAnterior = Datas.removeMeses(calculo.competencia, 1)
    def soma = Eventos.valorCalculado(38, TipoValor.CALCULADO, TipoProcessamento.MENSAL, SubTipoProcessamento.ADIANTAMENTO, mesAnterior) +
            Eventos.valorCalculado(38, TipoValor.CALCULADO, TipoProcessamento.MENSAL, SubTipoProcessamento.COMPLEMENTAR, mesAnterior) +
            Eventos.valorCalculado(38, TipoValor.CALCULADO, TipoProcessamento.DECIMO_TERCEIRO_SALARIO, SubTipoProcessamento.ADIANTAMENTO, mesAnterior) +
            Eventos.valorCalculado(38, TipoValor.CALCULADO, TipoProcessamento.DECIMO_TERCEIRO_SALARIO, SubTipoProcessamento.INTEGRAL, mesAnterior) +
            Eventos.valorCalculado(38, TipoValor.CALCULADO, TipoProcessamento.FERIAS, SubTipoProcessamento.INTEGRAL, mesAnterior) + Eventos.valorCalculado(38, TipoValor.CALCULADO, TipoProcessamento.FERIAS, SubTipoProcessamento.ADIANTAMENTO, mesAnterior)
    valorCalculado = soma
}
