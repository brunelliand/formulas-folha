package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Servidor.servidor;
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.enums.*;

class Dtnascdep {
    static Date getDtnascdep(Integer sequencialDependente) {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/

        def dependentes;
        dependentes = servidor.buscaDependentes

        if ( dependentes.size() == 0 ) return null;

        Date dataNascimento;
        dependentes.eachWithIndex{ item, indice ->
            if ( (indice + 1 ) == sequencialDependente ){
                dataNascimento = item.dataNascimento
            }
        }

        return dataNascimento




        /** FINAL **/
    }
}
