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
def evento = [codigo: 71, taxa: 50, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

def vvar = Lancamentos.valor(evento)
def base = Bases.valor(Bases.SALBASE)

if (vvar > 0) {
    valorReferencia (vvar * 100) / base
    valorCalculado = vvar
} else {
    valorReferencia = evento.taxa
    valorCalculado = base * valorReferencia / 100
}
if (valorCalculado > 0) {
    Bases.compor(valorCalculado, Bases.IRRF)
}

