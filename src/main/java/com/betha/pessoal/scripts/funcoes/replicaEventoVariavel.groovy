package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.evento
import com.betha.pessoal.scripts.padrao.Calculo.calculo

import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.enums.*;

class ReplicaEventoVariavel {
    static LinkedHashMap getReplicaEventoVariavel(int codigoEvento) {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/

        def zero = [valor: 0, referencia: 0]

        if (!calculo.replicaEventosCalculoFeriasParaCalculoMensal) return zero

        if (!TipoProcessamento.MENSAL.equals(calculo.tipoProcessamento)) return zero

        def ferias = Funcoes.getValorFerias(codigoEvento, true)

        if (ferias.valor > 0) {
            evento.replicado(true)
            return ferias
        }

        return zero


        /** FINAL **/
    }
}
