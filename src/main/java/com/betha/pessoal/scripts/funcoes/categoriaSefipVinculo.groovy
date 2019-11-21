package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.enums.*;

class Categvinc {
    static Integer getCategoriaSefipVinculo() {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/

        if ( TipoMatricula.AUTONOMO.equals(matricula.tipo) ) return 13;

        if ( ! TipoMatricula.FUNCIONARIO.equals(matricula.tipo) ) return 0;

        return funcionario.categoriaSefipVinculo.toString();

        /** FINAL **/
    }
}
