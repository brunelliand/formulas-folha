package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.PeriodosAquisitivosDecimoTerceiro
import com.betha.pessoal.scripts.padrao.PeriodosAquisitivosDecimoTerceiro.periodoAquisitivoDecimoTerceiro
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.enums.*;

class ExistePagamento13Salario {
    static Boolean getExistePagamento13Salario() {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/

        if ( ! TipoProcessamento.DECIMO_TERCEIRO_SALARIO.equals(calculo.tipoProcessamento) ) return false;

        if ( periodoAquisitivoDecimoTerceiro.totalMovimentacoes > 0 ) return true;

        return false;

        /** FINAL **/
    }
}
