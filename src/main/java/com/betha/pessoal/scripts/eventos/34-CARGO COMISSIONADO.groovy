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
def evento = [codigo: 34, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

if (TipoMatricula.FUNCIONARIO.equals(matricula.tipo)) {
    def vvar = valorReferencia = Lancamentos.valor(evento)

    if (vvar > 0) {
        valorReferencia = vvar
        valorCalculado = Funcoes.calcprop(valorReferencia, Bases.valor(Bases.PAGAPROP))

        Bases.compor(valorReferencia, Bases.SALAFAM)
        Bases.compor(valorCalculado, Bases.SALBASE, Bases.PERIC, Bases.SIND, Bases.FGTS, Bases.IRRF)
        Bases.compor(valorCalculado, Bases.INSS, Bases.PREVEST, Bases.FUNDASS, Bases.FUNDOPREV, Bases.COMPHORAMES, Bases.FUNDFIN)
    }
}






