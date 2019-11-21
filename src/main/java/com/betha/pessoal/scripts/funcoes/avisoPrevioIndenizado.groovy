package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.AvisoPrevio
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.enums.*;

class AvisoPrevioIndenizado {
    static Boolean getAvisoPrevioIndenizado() {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        boolean avisoIndenizado;

        if ( ! TipoProcessamento.RESCISAO.equals(calculo.tipoProcessamento) ) return avisoIndenizado

        try{
            return TipoAvisoPrevio.INDENIZADO.equals(AvisoPrevio.tipo)
        }catch(ignored){
            return false;
        }

        return avisoIndenizado;
        /** FINAL **/
    }
}
