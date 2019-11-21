package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import com.betha.pessoal.scripts.padrao.Folhas.folhasPeriodo
import com.betha.pessoal.scripts.utils
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.enums.*;

class InssFerias {
    static LinkedHashMap getInssFerias() {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        def zero = ['valor': 0, 'referencia': 0];

        if (! calculo.replicaEventosCalculoFeriasParaCalculoMensal) return zero

        if (!calculo.tipoProcessamento.equals(TipoProcessamento.MENSAL)) {
            return zero
        }

        double valor;
        double referencia;
        double feriasTotal;

        folhasPeriodo.buscaFolhas().each { f ->
            if (f.tipoProcessamento.equals(TipoProcessamento.FERIAS) && f.folhaPagamento) {

                if (feriasTotal == 0) {
                    feriasTotal = Bases.valorCalculado(Bases.PAGAPROP, TipoProcessamento.FERIAS, SubTipoProcessamento.INTEGRAL, f.competencia);
                };

                f.eventos.each { e ->
                    if (ClassificacaoEvento.INSS.equals(e.classificacao)) {
                        referencia += e.referencia
                        valor += e.valor
                    }
                }
            }
        }

        feriasTotal = feriasTotal.round(2);

        if (valor <= 0) {
            return zero;
        }

        double feriasCompetencia = Funcoes.cnvdpbase(Funcoes.diasferias());

        feriasCompetencia = feriasCompetencia.round(2)

        imprimir "feriasTotal ${feriasTotal} "
        imprimir "feriasCompetencia ${feriasCompetencia} "
        imprimir "valor ${valor} "
        imprimir "referencia ${referencia} "

        def inss = [:]
        try {

            double valorTotal = (valor / feriasTotal) * feriasCompetencia;
            inss['referencia'] = referencia ? referencia : 0;
            inss['valor'] = valorTotal ? valorTotal : 0;
            return inss;
        } catch (ignore) {
            imprimir 'Houve algum problema na função getInssFerias';
            return zero;
        }


        /** FINAL **/
    }
}
