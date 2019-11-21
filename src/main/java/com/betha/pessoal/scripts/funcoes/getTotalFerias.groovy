package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import com.betha.pessoal.scripts.padrao.Folhas.folhasPeriodo
import static com.betha.pessoal.scripts.padrao.Calculo.evento
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir;
import static com.betha.pessoal.scripts.padrao.enums.*;

class TotalFerias {
    public static LinkedHashMap getTotalFerias() {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        def zero = ['provento': 0, 'desconto': 0, 'liquido': 0];

        if (! calculo.replicaEventosCalculoFeriasParaCalculoMensal) return zero

        if (!calculo.tipoProcessamento.equals(TipoProcessamento.MENSAL)) {
            return zero
        };

        double provento = 0;
        double desconto = 0;
        double liquido = 0;

        def folhasInternas = folhasPeriodo.buscaFolhasInternas(TipoProcessamento.FERIAS);

        if (folhasInternas.size() == 0) {
            return zero
        };

        def cptCalc = Funcoes.inicioCompetencia()

        def eventos = [];
        folhasInternas.each { f ->
            def cptFerias = Funcoes.primeiroDia(f.competencia)

            if (cptCalc.equals(cptFerias)) {
                f.eventos.each { e ->
                    eventos.push(e.codigo);
                    imprimir 'Evento ' + e.codigo + ' valor ' + e.valor;
                    if (TipoEvento.VENCIMENTO.equals(e.tipo)) {
                        provento += e.valor.abs()
                    };
                    if (TipoEvento.DESCONTO.equals(e.tipo)) {
                        desconto += e.valor.abs()
                    };
                }
            }
        }

        folhasPeriodo.buscaFolhasProcessamento(TipoProcessamento.FERIAS).each { f ->
            f.eventos.each { e ->
                if (!eventos.contains(e.codigo) && !ClassificacaoEvento.INSS.equals(e.classificacao) && !ClassificacaoEvento.IRRF.equals(e.classificacao)) {
                    if (TipoEvento.VENCIMENTO.equals(e.tipo)) {
                        provento += e.valor.abs()
                    };
                    if (TipoEvento.DESCONTO.equals(e.tipo)) {
                        desconto += e.valor.abs()
                    };
                }
            }
        }

        double irrf = Funcoes.getIrrfFerias().valor.abs();
        double inss = Funcoes.getInssFerias().valor.abs();

        desconto += irrf + inss;
        liquido = provento - desconto;


        def valores = [:];
        valores['provento'] = provento;
        valores['desconto'] = desconto;
        valores['liquido'] = liquido;

        return valores;
        /** FINAL **/
    }
}
