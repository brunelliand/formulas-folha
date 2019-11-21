package com.betha.pessoal.scripts.eventos
   
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.EncargosSociais
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Numeros

import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*
   
/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 55 , taxa: 11 , tipo: TipoEvento.VENCIMENTO , unidade: UnidadeEvento.AUTOMATICO];
   
/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()

if(funcionario.possuiPrevidencia(TipoPrevidencia.ASSISTENCIA_MUNICIPAL)){
  def baseprev = Bases.valor(Bases.FUNDASS13) - Eventos.valor(200)
  if(baseprev <= 0) {
    suspender "Sem valor de base de assistência municipal"
  }

  int forma = 1 //1-Aplicar %, 2-Usar tabela assistencia Munic.

  def vvar = Lancamentos.valor(evento)
  def vaux
  def taxa
  if(vvar >= 0){
    valorReferencia = vvar
    valorCalculado = vvar
  }else{
    if(forma == 1) {
      vaux = baseprev
      taxa = evento.taxa
    }else{
      vaux = EncargosSociais.RPPS.FundoAssistencia.buscaMaior(1)
      if(baseprev <= vaux){
        vaux = baseprev
      }
      vaux2 = Numeros.trunca(vaux, 2)
      taxa = EncargosSociais.RPPS.FundoAssistencia.buscaContribuicao(vaux2,2)
    }
    valorReferencia = taxa
    valorCalculado = (vaux * taxa) / 100
  }

  Bases.compor(valorCalculado, Bases.IRRF13, Bases.ABATIRRF13)
}



