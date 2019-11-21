package com.betha.pessoal.scripts.eventos
   
import com.betha.pessoal.scripts.padrao.MediasVantagens.mediaVantagem
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.PeriodosAquisitivos

import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*
   
/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 102 , taxa: 0 , tipo: TipoEvento.VENCIMENTO , unidade: UnidadeEvento.AUTOMATICO]; 
   
/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/
   
Funcoes.somenteFuncionarios()

def vaux = Lancamentos.valor(evento)
  if ( vaux > 0 ){
    valorReferencia = vaux
    valorCalculado = vaux
  }else{
    def base = Eventos.valor(103) + Eventos.valor(104) + Eventos.valor(235) //apenas para gerar dependência

    def perProp = PeriodosAquisitivos.buscaProporcional()
    if(! perProp){
   	 suspender 'Sem dias de ferias proporcionais.' 
  	}
    def diasferias = perProp.saldo

    dataRescisao = calculo.dataRescisao

    valorReferencia = diasferias
    def vint = mediaVantagem.calcular(perProp.dataInicial, dataRescisao)

    if ( vint <= 0 ){
      suspender "Sem valor de média/vantagem"
    }
    vint = vint * diasferias / 30
    valorCalculado = vint
    PeriodosAquisitivos.quitaPorRescisao(perProp, valorReferencia, valorCalculado , true )

  }    

