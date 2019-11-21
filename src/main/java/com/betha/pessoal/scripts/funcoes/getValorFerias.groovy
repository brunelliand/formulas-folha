package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import com.betha.pessoal.scripts.padrao.Folhas.folhasPeriodo
import static com.betha.pessoal.scripts.padrao.Calculo.evento
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir;
import static com.betha.pessoal.scripts.padrao.enums.*;

/**
 * Esta função busca o valor das férias das folhas internas referente a competência que esta sendo calculada
 */
class ValorFerias {
    public static LinkedHashMap getValorFerias(int codigoEvento, Boolean buscarFolhaPagamento) {
        Binding binding = new Binding()
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/

        def zero = ['valor': 0, 'referencia': 0];

        if (! calculo.replicaEventosCalculoFeriasParaCalculoMensal) return zero

        boolean buscaFolhaExterna
        if (binding.variables["buscarFolhaPagamento"]) {
            buscaFolhaExterna = buscarFolhaPagamento
        } else {
            buscaFolhaExterna = false
        }

        double referencia = 0;
        double valor = 0;

        def fls
        if (buscaFolhaExterna) {
            imprimir "Buscando informações da folha de pagamento de férias..."
            fls = folhasPeriodo.buscaFolhasProcessamento(TipoProcessamento.FERIAS)
        } else {
            imprimir "Buscando informações da folha interna de férias..."
            fls = folhasPeriodo.buscaFolhasInternas(TipoProcessamento.FERIAS);
        }

        if (fls.size() == 0) {
            return zero
        };

        fls.each { f ->
            def cptCalcFolha = Datas.data(Datas.ano(calculo.competencia), Datas.mes(calculo.competencia), 1);
            def cptCalcInterna = Datas.data(Datas.ano(f.competencia), Datas.mes(f.competencia), 1);
            if (cptCalcFolha == cptCalcInterna) {
                f.eventos.each { e ->
                    if (e.codigo == codigoEvento) {
                        referencia += e.referencia
                        valor += e.valor
                    }
                }
            }
        }

        imprimir "Valor referencia férias : ${referencia}"
        imprimir "Valor calculado férias : ${valor}"

        def ferias = [:]
        ferias['valor'] = valor
        ferias['referencia'] = referencia

        return ferias;
        /** FINAL **/
    }
}
