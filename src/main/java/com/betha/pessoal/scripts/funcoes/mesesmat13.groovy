package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Afastamentos
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import static com.betha.pessoal.scripts.padrao.Calculo.evento
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir;
import static com.betha.pessoal.scripts.padrao.enums.*;

/**
 * Busca o número de meses afastados no ano por auxílio maternidade, para desconto de 13º salário. A contagem destes meses será de acordo com a regra dos avos para o 13º salário (mais que 14 dias trabalhados no mês conta como avo de 13º).
 */
class Mesesmat13 {
    public static long getMesesmat13() {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        def ano = Datas.ano(calculo.competencia)

        def classificacoes = [ClassificacaoTipoAfastamento.LICENCA_MATERNIDADE]
        int meses = 0
        for (int mes = 1; mes <= 12; mes++) {
            def iniCpt = Datas.data(ano, mes, 1)
            def finCpt = Datas.data(ano, mes, calculo.quantidadeDias(mes, ano))

            Afastamentos.buscaPorPeriodo(iniCpt, finCpt, *classificacoes).each { a ->

                def inicio = a.inicio
                def fim
                if (a.fim == null) {
                    fim = finCpt
                } else {
                    if (a.fim > finCpt) {
                        fim = finCpt
                    } else {
                        fim = a.fim
                    }
                }
                if (inicio < iniCpt) {
                    inicio = iniCpt
                }

                def dias = Datas.diferencaDias(inicio, fim) + 1

                imprimir 'Dias de licença na competência : ' + dias

                if (dias > 14) meses++

            }
        }

        return meses

        /** FINAL **/
    }
}
