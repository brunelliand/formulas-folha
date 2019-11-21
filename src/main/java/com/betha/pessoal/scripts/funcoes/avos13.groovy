package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.PeriodosAquisitivosDecimoTerceiro

import com.betha.pessoal.scripts.padrao.PeriodosAquisitivosDecimoTerceiro.periodoAquisitivoDecimoTerceiro;
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir;
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.enums.*;

class Avos13 {
    public static int getAvos13(int mes, Boolean calculoFGTS) {
        Binding binding = new Binding();

        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        imprimir 'Cálculo da função avos 13 linha 1 '

        def  _mes = mes;
        def _calculoFGTS;
        if (binding.variables["mes"]) {
            //Para manter a compatibilidade com a fórmula antiga
            if (mes instanceof java.lang.Integer) {
                _mes = mes
            } else {
                _mes = mes[0];
            }
        } else {
            return 0;
        }

        if (binding.variables["calculoFGTS"]) {
            //Para manter a compatibilidade com a fórmula antiga
            if (mes instanceof java.lang.Integer) {
                _calculoFGTS = calculoFGTS;
            } else {
                _calculoFGTS = mes[1];
            }
        } else {
            _calculoFGTS = false;
        }


        boolean recebeDecimoTerceiro = Funcoes.recebeDecimoTerceiro(matricula.tipo)

        if (!recebeDecimoTerceiro) return 0

        double remuneracao;
        boolean ehRescisao;
        boolean ehDecimo;
        def avos = 0;

        if (TipoProcessamento.RESCISAO.equals(calculo.tipoProcessamento)) {
            ehRescisao = true
            ehDecimo = false
        } else {
            ehRescisao = false
            if (calculo.tipoProcessamento == TipoProcessamento.DECIMO_TERCEIRO_SALARIO) {
                ehDecimo = true
            } else {
                ehDecimo = false
            }
        }
        remuneracao = Funcoes.remuneracao(matricula.tipo).valor;

        if (!ehDecimo && !ehRescisao) {
            return 0;
        } else {
            if (ehDecimo) {
                imprimir 'É processamento decimo terceiro '
                if (SubTipoProcessamento.ADIANTAMENTO.equals(calculo.subTipoProcessamento)) {
                    if (calculo.consideraAvosPerdidos) {
                        avos = periodoAquisitivoDecimoTerceiro.avosDireito
                    } else {
                        avos = periodoAquisitivoDecimoTerceiro.avosDireito
                    }
                } else {
                    if (_calculoFGTS) {
                        avos = periodoAquisitivoDecimoTerceiro.avosAdquiridosFgts
                    } else {
                        avos = periodoAquisitivoDecimoTerceiro.avosAdquiridos
                    }

                }
            } else {
                imprimir 'É processamento rescisao '
                PeriodosAquisitivosDecimoTerceiro.buscaPeriodosAquisitivos().each { p ->
                    if (p.anoExercicio == calculo.competencia.ano) {
                        avos = p.avosAdquiridos

                    }
                }
            }
        }

        imprimir 'Avos a pagar :  ' + avos

        return avos


        /** FINAL **/
    }
}
