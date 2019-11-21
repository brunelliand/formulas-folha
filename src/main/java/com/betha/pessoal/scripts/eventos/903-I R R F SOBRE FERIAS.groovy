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

/* Este evento demonstra na folha mensal o valor calculado de IRRF de fÃ©rias de formar proporcional conforme os dias de fÃ©rias em cada competÃªncia */
irrfFerias = Funcoes.getIrrfFerias();
imprimir 'referencia : ' + irrfFerias.referencia
imprimir 'valor : ' + irrfFerias.valor
valorReferencia = irrfFerias.referencia;
valorCalculado = irrfFerias.valor;

if ( valorCalculado > 0 ){
    evento.replicado(true)
}