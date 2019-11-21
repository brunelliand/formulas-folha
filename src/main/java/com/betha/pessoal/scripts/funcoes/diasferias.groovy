package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Afastamentos
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.enums.*;

/**
 * Retorna a quantidade de dias de férias na competência
 */
class Diasferias {
    public static long getDiasferias() {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/

        def inicio = Datas.data(Datas.ano(calculo.competencia), Datas.mes(calculo.competencia), 1);
        def fim = Datas.data(Datas.ano(calculo.competencia), Datas.mes(calculo.competencia), calculo.quantidadeDiasCompetencia);

        int dias = 0;

        Afastamentos.buscaPorPeriodo(ClassificacaoTipoAfastamento.FERIAS).each { a ->
            if (a.inicio > inicio) {
                inicio = a.inicio;
            }
            if (a.fim < fim) {
                fim = a.fim;
            }

            dias += Datas.diferencaDias(inicio, fim) + 1
        }

        return dias

        /** FINAL **/
    }
}
