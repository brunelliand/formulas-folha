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
def evento = [codigo: 60 , taxa: 1 , tipo: TipoEvento.DESCONTO , unidade: UnidadeEvento.AUTOMATICO];
   
/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/
   
Funcoes.somenteFuncionarios()
def vvar=Lancamentos.valor(evento)
if(vvar > 0){
    valorCalculado = vvar
}else{
    valorReferencia = evento.taxa
    valorCalculado = Funcoes.remuneracao(matricula.tipo).valor * evento.taxa / 100
}
