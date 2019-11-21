package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.evento
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*


/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()
def vlraux = Lancamentos.valor(evento)

if (vlraux <= 0) {
    def cptproc = calculo.competencia
    def dataInicial = Datas.data(Datas.ano(cptproc), Datas.mes(cptproc), 1)
    def dataFinal = Datas.data(Datas.ano(cptproc), Datas.mes(cptproc), calculo.quantidadeDiasCompetencia)
    vlraux = Funcoes.faltas(false, dataInicial, dataFinal, 0, true)

}
valorReferencia = vlraux
if (valorReferencia > 0) {
    valorCalculado = Funcoes.calcprop(funcionario.salario, valorReferencia)
    Bases.compor(valorReferencia, Bases.PAGAPROP)
    Bases.compor(valorCalculado,
            Bases.SALBASE,
            Bases.PERIC,
            Bases.SIND,
            Bases.FGTS,
            Bases.IRRF,
            Bases.INSS,
            Bases.PREVEST,
            Bases.FUNDASS,
            Bases.FUNDOPREV,
            Bases.COMPHORAMES,
            Bases.FUNDFIN)
}
