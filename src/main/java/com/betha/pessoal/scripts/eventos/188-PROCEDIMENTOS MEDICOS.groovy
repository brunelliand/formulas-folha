package com.betha.pessoal.scripts.eventos
   
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.PlanoSaudeDespesas

import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*
   
/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 188 , taxa: 0 , tipo: TipoEvento.VENCIMENTO , unidade: UnidadeEvento.AUTOMATICO]; 
   
/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/
   
def vaux = Lancamentos.valor(evento)
if ( vaux >= 0 ){
  valorCalculado = vaux
  valorReferencia = vaux
}else{
  vaux = 0
  PlanoSaudeDespesas.buscaDespesasPlanoSaude().each{ despesa ->

    if ( despesa.tipo == TipoDespesaPlanoSaude.PROCEDIMENTOS ){
      vaux += despesa.valor  
    }
  }
  valorCalculado = vaux
}

imprimir(valorCalculado)