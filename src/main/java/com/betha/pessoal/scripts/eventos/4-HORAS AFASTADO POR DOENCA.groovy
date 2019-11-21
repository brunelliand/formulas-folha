package com.betha.pessoal.scripts.eventos
   
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*
   
/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 4 , taxa: 0 , tipo: TipoEvento.VENCIMENTO , unidade: UnidadeEvento.AUTOMATICO]; 
   
/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/
   
if(matricula.tipo != TipoMatricula.FUNCIONARIO){
  suspender "Calculo do evento ${evento.codigo} cancelado"
}
vaux=Lancamentos.valor(evento)
if(vaux>=0){
  valorReferencia=vaux
}else{
  if((Funcoes.afasauxdoenc()==0)){
    suspender''
  }
  vaux=Funcoes.cnvdpbase(Funcoes.afasauxdoenc())
  valorReferencia=vaux
}
valorCalculado=Funcoes.calcprop(funcionario.salario,vaux)
