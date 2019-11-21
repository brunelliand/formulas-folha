package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.enums.*;

class Existerescisao {
    static String getExisterescisao(Date dataInicial, Date dataFinal) {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/

        def dados = Funcoes.dadosMatricula()

        if ( !dados.existeSaida ){
            return "N"
        }

        Date dataSaida = dados.dataSaida;

        if ( dataInicial >= dataSaida && dataFinal <= dataSaida ) return "S";

        return "N"



        /** FINAL **/
    }
}
