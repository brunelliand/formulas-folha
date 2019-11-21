package com.betha.pessoal.scripts.eventos
   
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.EncargosSociais
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*
   
/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 53 , taxa: 0 , tipo: TipoEvento.VENCIMENTO , unidade: UnidadeEvento.AUTOMATICO]; 
   
/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()

if(funcionario.possuiPrevidencia(TipoPrevidencia.PREVIDENCIA_ESTADUAL)){
  def baseprev = Bases.valor(Bases.PREVEST13) + Eventos.valor(200)

  if(baseprev <= 0) {
    suspender "Sem base de previdência estadual"
  }

  int forma = 1 //1-Aplica %, 2-Considera Minimo e Maximo Tabela
  def minprevest = EncargosSociais.RPPS.PrevidenciaEstadual.minimo
  def maxprevest = EncargosSociais.RPPS.PrevidenciaEstadual.maximo

  def vvar = Lancamentos.valor(evento)
  if(vvar >= 0){
    valorReferencia = vvar
    valorCalculado = vvar
  }else{
    valorReferencia = evento.taxa
    def vaux = baseprev * valorReferencia / 100
    if(forma == 2){
      if(vaux < minprevest){
        vaux = minprevest
      }else{
        if(vaux > maxprevest){
          vaux = maxprevest
        }
      }
    }
    valorCalculado = vaux
  }
  Bases.compor(valorCalculado, Bases.IRRF13, Bases.ABATIRRF13)
}

