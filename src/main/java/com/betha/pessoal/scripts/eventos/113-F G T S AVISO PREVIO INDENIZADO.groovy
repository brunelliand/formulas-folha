package com.betha.pessoal.scripts.eventos
   
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Numeros

import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*
   
/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 113 , taxa: 8 , tipo: TipoEvento.VENCIMENTO , unidade: UnidadeEvento.AUTOMATICO];
   
/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

if (!Funcoes.optanteFgts(matricula.tipo)){
  suspender "Não é optante de FGTS"
}

if (!Funcoes.avisoPrevioIndenizado()) {
  suspender "Sem aviso prévio indenizado"
}

def vaux = Lancamentos.valor(evento)
if ( vaux > 0 ){
  valorCalculado = vaux
}else{
  if ( funcionario.categoriaSefipVinculo.toString() == 'MENOR_APRENDIZ' ){
    valorReferencia = 2
  }else{
    valorReferencia = evento.taxa
  }
  vaux = Bases.valor(Bases.FGTSAVISO) * valorReferencia / 100
  valorCalculado = Numeros.arredonda(vaux, 2)
}
