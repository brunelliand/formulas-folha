package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.DadosEstagiario.estagiario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo


import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.enums.*;

class RecebeDecimoTerceiro {
    public static Boolean getRecebeDecimoTerceiro(tipoDaMatricula ) {
        groovy.lang.Binding binding = new groovy.lang.Binding()
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        def tipo;
        if (binding.variables["tipoDaMatricula"]){
            tipo = tipoDaMatricula
        }else{
            tipo = matricula.tipo
        }

        if ( TipoMatricula.PENSIONISTA.equals(tipo) ) return true;


        if ( TipoMatricula.FUNCIONARIO.equals(tipo) ){
            return funcionario.recebeDecimoTerceiro
        }else{
            if ( TipoMatricula.ESTAGIARIO.equals(tipo) ){
                return estagiario.recebeDecimoTerceiro
            }
        }
        return false





        /** FINAL **/
    }
}
