package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Folhas.folhas
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 38, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

//Este evento deve ser configurado para calcular por último
def vaux = Lancamentos.valor(evento)

double calc;
if (vaux > 0) {
    valorReferencia = vaux
    valorCalculado = vaux
} else {
    vaux = 0

    folhas.buscaFolhas().each { folha ->
        folha.eventos.each { e ->

            if (e.tipo == TipoEvento.VENCIMENTO) {
                imprimir 'Evento : ' + e.codigo + ' - ' + e.tipo + ' - ' + e.valor
                calc += e.valor.abs()
            }
            if (e.tipo == TipoEvento.DESCONTO) {
                calc -= e.valor.abs()
            }
        }
    }

    imprimir 'Líquido: ' + calc
    if (calc < 0) {
        valorReferencia = 0
        valorCalculado = calc
    }
}
