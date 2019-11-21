package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 33, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

if (calculo.tipoProcessamento == TipoProcessamento.FERIAS) {
    suspender "Calculo do evento cancelado"
}
vaux = Lancamentos.valor(evento)
if (vaux > 0) {
    valorReferencia = vaux
    valorCalculado = vaux
    Bases.compor(valorCalculado, Bases.SALBASE, Bases.FGTS, Bases.IRRF, Bases.INSS)
    Bases.compor(valorCalculado, Bases.FUNDOPREV, Bases.FUNDASS, Bases.PREVEST, Bases.SALAFAM, Bases.MEDIAUXMAT, Bases.FUNDFIN)
}
