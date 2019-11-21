package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.EncargosSociais
import com.betha.pessoal.scripts.padrao.Folhas.folha
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Numeros

import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 90, taxa: 3, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()
if (!funcionario.possuiPrevidencia(TipoPrevidencia.PREVIDENCIA_PROPRIA)) {
  suspender "Apenas para fundo de assistência"
}

forma = 1; //1-Aplicar %, 2-Usar tabela previdencia Munic.
if (folha.folhaPagamento) {
  def vvar = Lancamentos.valor(evento)
  def vaux
  def taxa
  if (vvar > 0) {
    valorReferencia = vvar
    valorCalculado = vvar
  } else {
    double baseprev = Bases.valor(Bases.FUNDOPREV)
    if (!baseprev <= 0) {
      suspender "Sem base de fundo de assistência"
    }

    if (forma == 1) {
      vaux = baseprev
      taxa = evento.taxa
    } else {
      vaux = EncargosSociais.RPPS.PrevidenciaPropria.buscaMaior(1)
      if (baseprev <= vaux) {
        vaux = baseprev
      }
      def vaux2 = Numeros.trunca(vaux, 2)
      taxa = EncargosSociais.RPPS.PrevidenciaPropria.buscaContribuicao(vaux2, 2)
    }
    valorReferencia = taxa
    valorCalculado = (vaux * taxa) / 100
  }
  if (valorCalculado > 0) {
    Bases.compor(valorCalculado, Bases.IRRF, Bases.ABATIRRF)
  }
}