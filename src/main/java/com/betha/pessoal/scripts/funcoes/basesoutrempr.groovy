package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.BasesOutrasEmpresas
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.enums.*;

class Basesoutrempr {
    Binding binding = new Binding()
    static BigDecimal getBasesoutrempr(int tipo, int tipoRetorno, Enum tipoProcessamento  ) {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/

        // tipo :  Tipo de valor a ser calculado, 1 - INSS ou 2 - IRRF.
        // retorno : Tipo de valor a ser retornado, 1 - Base ou 2 - Valor.

        String tpRetorno
        if ( tipo.equals(1) && tipoRetorno.equals(1) ) tpRetorno = 'baseInss';
        if ( tipo.equals(1) && tipoRetorno.equals(2) ) tpRetorno = 'valorRetidoInss';
        if ( tipo.equals(2) && tipoRetorno.equals(1) ) tpRetorno = 'baseIrrf';
        if ( tipo.equals(2) && tipoRetorno.equals(2) ) tpRetorno = 'valorRetidoIrrf';

        return BasesOutrasEmpresas.buscaPor(tipoProcessamento).sum(0, { it."${tpRetorno}" })

        /** FINAL **/
    }
}
