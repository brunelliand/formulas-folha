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

/* Este evento demonstra na folha mensal o valor calculado de INSS de fÃ©rias de formar proporcional conforme os dias de fÃ©rias em cada competÃªncia */
inssFerias = Funcoes.getInssFerias();
imprimir 'referencia : ' + inssFerias.referencia
imprimir 'valor : ' + inssFerias.valor
valorReferencia = inssFerias.referencia;
valorCalculado = inssFerias.valor; 

if ( valorCalculado > 0 ){
    evento.replicado(true)
}