package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.*
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Servidor.servidor
import com.betha.pessoal.scripts.padrao.Folhas.folha
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*
   
/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 92 , taxa: 0 , tipo: TipoEvento.VENCIMENTO , unidade: UnidadeEvento.AUTOMATICO]; 
   
/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

if (! TipoProcessamento.FERIAS.equals(Calculo.calculo.tipoProcessamento)){
  suspender "Este evento deve ser calculado apenas no processamento férias"
}

Funcoes.somenteFuncionarios()

if(folha.folhaPagamento){
  def desc
  def base = Bases.valor(Bases.IRRFFER)
  if(servidor.possuiMolestiaGrave){
    base += Eventos.valor(198) + Eventos.valor(228)
  }
  if( base < EncargosSociais.IRRF.buscaContribuicao(0,1)){
    suspender "Sem I.R.R.F. a ser calculado!"
  }
  def vaux = Lancamentos.valor(evento)
  if(vaux>=0){
    valorCalculado=vaux
  }else{
    desc = EncargosSociais.IRRF.buscaContribuicao(base,3)
    valorReferencia = EncargosSociais.IRRF.buscaContribuicao(base,2)
    valorCalculado = (base * valorReferencia / 100) - ( desc + Eventos.valor(173))

  }
  if(valorCalculado < EncargosSociais.IRRF.minimoIrrfDarf){
    valorCalculado=0
  }else{
    Bases.compor(desc,Bases.DESCIRRF)
  }
}

