package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.Servidor.servidor
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Folhas.folha
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 44, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

if (!Funcoes.pagapensao()) {
    suspender "Cálculo do evento ${evento.codigo} cancelado"
}

if (((folha.diasGozo > 0) && ((calculo.tipoProcessamento == TipoProcessamento.MENSAL && calculo.subTipoProcessamento == SubTipoProcessamento.INTEGRAL)))) {
    suspender "Cálculo do evento ${evento.codigo} cancelado"
}

vvar = Lancamentos.valor(evento);

if (vvar > 0) {
    valorReferencia = vvar
    valorCalculado = vvar
} else {
    def salario = Funcoes.remuneracao(matricula.tipo).valor;
    double valorTotalPensao;
    def quantidadePensionistas = servidor.buscaDependentes.sum(0, { it.pensao ? 1 : 0 })
    def valorPensaoPorDependente
    servidor.buscaDependentes.each { dependente ->
        if (dependente.pensao) {
            if (dependente.aplicacaoDesconto.toString().equals('VALOR_PERCENTUAL')) {
                valorPensaoPorDependente = (salario * dependente.percentualDesconto / 100) / quantidadePensionistas
            } else {
                valorPensaoPorDependente = dependente.valorDesconto
            }
            dependente.aplicaRateio(valorPensaoPorDependente)
            valorTotalPensao += valorPensaoPorDependente;
        }
    }
    valorCalculado = valorTotalPensao
}
Bases.compor(valorCalculado, Bases.IRRF)

