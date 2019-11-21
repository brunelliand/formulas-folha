package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.PeriodosAquisitivosDecimoTerceiro
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import com.betha.pessoal.scripts.padrao.PeriodosAquisitivosDecimoTerceiro.periodoAquisitivoDecimoTerceiro
import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.enums.*;

class Permitecalc13integral {
    static Boolean getPermitecalc13integral() {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/

        if (!Funcoes.recebeDecimoTerceiro(matricula.tipo)) return false;

        if (TipoProcessamento.RESCISAO.equals(calculo.tipoProcessamento)) {
            def periodosAbertos = PeriodosAquisitivosDecimoTerceiro.buscaPeriodosAquisitivos().sum(0, { p ->
                ([SituacaoPeriodoAquisitivoDecimoTerceiro.EM_ANDAMENTO, SituacaoPeriodoAquisitivoDecimoTerceiro.ATRASADO, SituacaoPeriodoAquisitivoDecimoTerceiro.QUITADO_PARCIALMENTE].contains(p.situacao)) ? 1 : 0
            })
            if (periodosAbertos > 0) return true;
        } else {
            def periodo = periodoAquisitivoDecimoTerceiro;

            return [SituacaoPeriodoAquisitivoDecimoTerceiro.EM_ANDAMENTO, SituacaoPeriodoAquisitivoDecimoTerceiro.ATRASADO, SituacaoPeriodoAquisitivoDecimoTerceiro.QUITADO_PARCIALMENTE].contains(periodo.situacao)
        }
        return false

        /** FINAL **/
    }
}
