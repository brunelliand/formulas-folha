package com.betha.pessoal.scripts.eventos
   
import com.betha.pessoal.scripts.padrao.DadosPensionista.pensionista
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*
   
/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 118 , taxa: 0 , tipo: TipoEvento.VENCIMENTO , unidade: UnidadeEvento.AUTOMATICO]; 
   
/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

if( TipoMatricula.PENSIONISTA.equals(matricula.tipo) ){
  def inicioCompetencia = Datas.data(Datas.ano(calculo.competencia),Datas.mes(calculo.competencia),1)
  def dataCessacaoBeneficio = pensionista.dataCessacaoBeneficio

  if( ! dataCessacaoBeneficio ){
    if( dataCessacaoBeneficio <= inicioCompetencia ){
      suspender "Data de cessação do beneficio menor que a competência atual"
    }
  }

  def vvar = Lancamentos.valor(evento)
  if( vvar > 0 ){
    valorCalculado = vvar
  }else{
    valorCalculado = pensionista.valorBeneficio
  }
  if( valorCalculado > 0 ){
    Bases.compor(valorCalculado,
            Bases.IRRF,
            Bases.INSS,
            Bases.PREVEST,
            Bases.FUNDASS,
            Bases.FUNDOPREV,
            Bases.FUNDFIN)
  }
}
