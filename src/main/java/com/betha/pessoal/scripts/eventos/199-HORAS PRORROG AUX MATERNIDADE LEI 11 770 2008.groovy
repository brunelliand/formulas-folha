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
def evento = [codigo: 199, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()

if (!Sexo.FEMININO.equals(servidor.sexo)) {
  suspender 'Calcular apenas para sexo feminino'
}


def vaux = Lancamentos.valor(evento)
if (vaux >= 0) {
  valorReferencia = vaux
} else {
  def afas = Funcoes.afasprorroglicmatlei11770()
  if (afas == 0) {
    suspender "Sem dias de licença"
  }
  vaux = Funcoes.cnvdpbase(afas)
  valorReferencia = vaux
}
valorCalculado = Funcoes.calcprop(funcionario.salario, vaux)
if (valorCalculado > 0) {
  Bases.compor(valorReferencia, Bases.PAGAPROP, Bases.MEDAUXMATPR)

  if ( servidor.sexo == Sexo.FEMININO){
    Bases.compor(valorCalculado,Bases.INSS)
  }

  Bases.compor(valorCalculado,
          Bases.SALBASE, Bases.PERIC,
          Bases.SIND,
          Bases.FGTS,
          Bases.IRRF,
          Bases.PREVEST,
          Bases.FUNDASS,
          Bases.FUNDOPREV,
          Bases.COMPHORAMES,
          Bases.FUNDFIN)
}
