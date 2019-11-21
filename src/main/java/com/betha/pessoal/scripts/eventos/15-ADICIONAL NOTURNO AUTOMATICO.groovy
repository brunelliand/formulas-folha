package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 15, taxa: 30, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

vvar = Lancamentos.valor(evento)
if(vvar > 0) {
    valorReferencia = vvar
    valorCalculado = vvar
} else {
    valorReferencia = evento.taxa
    valorCalculado = Eventos.valor(2) * evento.taxa / 100
}
if(valorCalculado < 0) {
    valorCalculado = 0
}
Bases.compor(valorCalculado,
        Bases.SALBASE,
        Bases.FGTS,
        Bases.IRRF,
        Bases.INSS,
        Bases.PREVEST,
        Bases.FUNDASS,
        Bases.FUNDOPREV,
        Bases.SALAFAM,
        Bases.MEDIAUXMAT,
        Bases.FUNDFIN)


