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
def evento = [codigo: 151, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()
def vaux = Lancamentos.valor(evento)
if (vaux > 0) {
    valorReferencia = vaux
} else {
    def afasauxdoencemp = Funcoes.afasauxdoencemp()
    if (afasauxdoencemp == 0) {
        suspender 'Sem dias de afastamento'
    }
    vaux = Funcoes.cnvdpbase(afasauxdoencemp)
    valorReferencia = vaux
}
valorCalculado = Funcoes.calcprop(funcionario.salario, vaux)
if (valorCalculado > 0) {
    if (Eventos.valor(1) == 0) {
        Bases.compor(funcionario.salario, Bases.HORAEXTRA, Bases.SIND)
    }
    Bases.compor(valorReferencia, Bases.PAGAPROP)
    Bases.compor(valorCalculado,
            Bases.PERIC,
            Bases.IRRF,
            Bases.INSS,
            Bases.PREVEST,
            Bases.FUNDASS,
            Bases.FUNDOPREV,
            Bases.COMPHORAMES,
            Bases.FUNDFIN)
}
