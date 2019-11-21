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
def evento = [codigo: 14, taxa: 30, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()
valorReferencia = Lancamentos.valor(evento)
if (valorReferencia > 0) {
    def base = funcionario.salario + Eventos.valor(11) + Eventos.valor(12) + Eventos.valor(13)
    valorCalculado = (base / funcionario.quantidadeHorasMes) * (evento.taxa / 100) * valorReferencia
    Bases.compor(valorCalculado, Bases.SALBASE, Bases.FGTS, Bases.IRRF, Bases.INSS, Bases.PREVEST, Bases.FUNDASS, Bases.FUNDOPREV, Bases.SALAFAM, Bases.MEDIAUXMAT)
}
