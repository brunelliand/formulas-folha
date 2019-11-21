package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.Servidor.servidor
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 149, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()
if (!funcionario.possuiPrevidenciaFederal) {
    suspender 'Apenas para previdencia federal'
}
if (!Sexo.FEMININO.equals(servidor.sexo)) {
    suspender 'Apenas para sexo feminino'
}
base = Bases.valor(Bases.INSS13) + Bases.valor(Bases.DESC13REINT)
if (base <= 0) {
    suspender 'Sem base de calculo'
}
vlrCalc = 0
if (funcionario.dataRescisao != null) {
    vlrCalc = Funcoes.deducauxmat13(base, Funcoes.avos13(calculo.competencia.mes))
} else {
    vlrCalc = Funcoes.deducauxmat13(base, Funcoes.avos13(12))
}
def vlrDeduz = Funcoes.acumula(evento.codigo, TipoValor.CALCULADO, Datas.data(Datas.ano(calculo.competencia), 1, 1), Datas.data(Datas.ano(calculo.competencia), 12, 1), ['MENSAL', 'FERIAS'])
vlrCalc -= vlrDeduz
if (vlrCalc > 0) {
    valorCalculado = vlrCalc
}

