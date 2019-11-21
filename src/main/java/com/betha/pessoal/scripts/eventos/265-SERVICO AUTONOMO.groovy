package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosAutonomo.autonomo
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 265, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

if (matricula.tipo == TipoMatricula.AUTONOMO) {
    def vaux = Lancamentos.valor(evento)
    if (vaux >= 0) {
        valorReferencia = vaux
        valorCalculado = vaux
    } else {
        vaux = autonomo.totalServicosAutonomo
        valorReferencia = vaux
        valorCalculado = vaux
    }
    if (valorCalculado > 0) {
        Bases.compor(valorCalculado, Bases.SALBASE, Bases.IRRF, Bases.COMPHORAMES)
    }
}
