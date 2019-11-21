package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.enums.*;

class Diasuteis {
    static Integer getDiasuteis(Date dataInicial , Date dataFinal, def sabadoUtil ) {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/

        int ultimoDiaSemana = 7;
        if ( sabadoUtil == "S" || sabadoUtil == true ){
            ultimoDiaSemana = 8;
        }

        def dia
        def diasTotal
        int diasUteis = 0
        def diaSemana


        def intervalo = Datas.diferencaDias(dataInicial, dataFinal)

        diasTotal = intervalo //Datas.dias(intervalo) //  .days.toInteger()

        for (int i = 0; i <= diasTotal; i++) {
            dia = Datas.adicionaDias(dataInicial, i)
            Calendar diaCalendar = dia.toCalendar();
            diaSemana = diaCalendar.get(Calendar.DAY_OF_WEEK).toInteger()

            if (diaSemana < ultimoDiaSemana && diaSemana > 1) {
                diasUteis++;
            }
        }

        return diasUteis

        /** FINAL **/
    }
}
