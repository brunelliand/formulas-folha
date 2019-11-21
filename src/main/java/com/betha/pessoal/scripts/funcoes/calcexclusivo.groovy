package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender;
import static com.betha.pessoal.scripts.padrao.enums.*;

/**
 * Calcula para o tipo de processamento que possui maior numero de dias, suspende cálculo do evento que tem menor número de dias
 */
class Calcexclusivo {
    public static BigDecimal getCalcexclusivo(BigDecimal valorReferencia, Enum tipoProcessamento1, long diasProcessamento1, Enum tipoProcessamento2, long diasProcessamento2) {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        //Criado por André Brunelli em 29/01/2019

        if ( valorReferencia <= 0 ){
            return 0
        }

        if ( diasProcessamento1 >= diasProcessamento2 ){
            if ( calculo.tipoProcessamento == tipoProcessamento2 ){
                suspender "Cálculo exclusivo para tipo processamento ${tipoProcessamento1}"
            }
        }else{
            if ( calculo.tipoProcessamento == tipoProcessamento1 ) {
                suspender "Cálculo exclusivo para tipo processamento ${tipoProcessamento2}"
            }
        }
        return valorReferencia
        /** FINAL **/
    }
}
