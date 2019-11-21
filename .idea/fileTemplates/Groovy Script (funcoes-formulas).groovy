#if (${PACKAGE_NAME} && $PACKAGE_NAME != "" )package ${PACKAGE_NAME}
#end

import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.enums.*;

#parse("File Header.java")
#set($capitalizedFilename = $NAME.substring(0,1).toUpperCase() + $NAME.substring(1))
class $capitalizedFilename {
    static void get$capitalizedFilename(){
    /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
    
    /** FINAL **/
    }
}
