package com.betha.pessoal.scripts.eventos
   
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.evento
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*
   

   
/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/
   
fgts = Funcoes.getfgtsFerias();
imprimir 'referencia : '+ fgts.referencia
imprimir 'valor : ' + fgts.valor
valorReferencia = fgts.referencia; 
valorCalculado = fgts.valor;

if ( valorCalculado > 0 ){
    evento.replicado(true)
}