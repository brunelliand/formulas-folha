package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import com.betha.pessoal.scripts.padrao.Folhas.folhasPeriodo
import static com.betha.pessoal.scripts.padrao.Calculo.evento
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir;
import static com.betha.pessoal.scripts.padrao.enums.*;

class IrrfFerias {
    public static LinkedHashMap getIrrfFerias() {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        def zero = ['valor': 0, 'referencia': 0];

        if (! calculo.replicaEventosCalculoFeriasParaCalculoMensal) return zero

        if (!calculo.tipoProcessamento.equals(TipoProcessamento.MENSAL)) {
            return zero
        }

        double valor = 0;
        double referencia = 0;
        def feriasTotal = 0;

        folhasPeriodo.buscaFolhas().each { f ->
            if (f.tipoProcessamento.equals(TipoProcessamento.FERIAS) && f.folhaPagamento) {

                if (feriasTotal == 0) {
                    feriasTotal = Bases.valorCalculado(Bases.PAGAPROP, TipoProcessamento.FERIAS, SubTipoProcessamento.INTEGRAL, f.competencia);
                };

                f.eventos.each { e ->
                    if (ClassificacaoEvento.IRRF.equals(e.classificacao)) {
                        referencia += e.referencia
                        valor += e.valor
                    }
                }
            }
        }

        if (valor <= 0) {
            return zero;
        }

        def feriasCompetencia = Funcoes.cnvdpbase(Funcoes.diasferias());

        def irrf = [:]
        try {
            irrf['referencia'] = referencia ? referencia : 0;
            if (feriasTotal == feriasCompetencia) {
                irrf['valor'] = valor
                return irrf
            }
            double valorTotal = (valor / feriasTotal) * feriasCompetencia;
            valorTotal = valorTotal.round(2)
            irrf['valor'] = valorTotal ? valorTotal : 0;
            return irrf;
        } catch (ignore) {
            imprimir 'Houve algum problema na função getIrrfFerias';
            return zero;
        }
        /** FINAL **/
    }
}
