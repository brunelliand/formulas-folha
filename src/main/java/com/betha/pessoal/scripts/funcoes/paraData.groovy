package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.enums.*;
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender;


class ParaData {
    static Date getParaData(valor) {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/

        def dataString = valor.toString();
        def data;

           if ( dataString.length().equals(6) ) dataString += '01'
            try {
                dataString.replaceAll(/(\d{4}).*(\d{2}).*(\d{2})/) { tudo, ano, mes, dia -> data = Datas.data(ano.toInteger(), mes.toInteger(), dia.toInteger()  ) }
                return data
            }catch(ignore){
                suspender "O valor ${valor} não pode ser convertido para uma data válida"
            }

        /** FINAL **/
    }
}
