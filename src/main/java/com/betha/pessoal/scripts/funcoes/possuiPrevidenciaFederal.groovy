package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.enums.*;

class PossuiPrevidenciaFederal {
    public static Boolean getPossuiPrevidenciaFederal(Enum tipo) {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        if (TipoMatricula.FUNCIONARIO.equals(tipo)) {
            return funcionario.possuiPrevidenciaFederal
        }

        return false
        /** FINAL **/
    }
}
