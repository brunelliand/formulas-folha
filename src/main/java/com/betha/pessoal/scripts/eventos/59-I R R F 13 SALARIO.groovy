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
def evento = [codigo: 59 , taxa: 0 , tipo: TipoEvento.VENCIMENTO , unidade: UnidadeEvento.AUTOMATICO]; 
   
/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

double base = Bases.valor(Bases.IRRF13) - Eventos.valor(136) - Eventos.valor(190) - Eventos.valor(193) + Bases.valor(Bases.IRRFOUTRA13) + Eventos.valor(200)

def menorFaixa = EncargosSociais.IRRF.buscaContribuicao(0,1)

if(base < menorFaixa){
  suspender 'abaixo da faixa minima tributavel'
}

vaux = Lancamentos.valor(evento)
def desc = 0;
if (vaux >= 0){
  vlrcalc = vaux
}else{
  def vaux2 = base.trunc(2);
  desc = EncargosSociais.IRRF.buscaContribuicao(vaux2,3)
  valorReferencia = EncargosSociais.IRRF.buscaContribuicao(vaux2,2)
  valorCalculado = ((vaux2 * valorReferencia) / 100) - desc - Eventos.valor(173)
}

if (valorCalculado < EncargosSociais.IRRF.minimoIrrfDarf){
  valorCalculado = 0
}else{
  Bases.compor(desc, Bases.DESCIRRF13)
}

imprimir(valorReferencia)
imprimir(valorCalculado)

