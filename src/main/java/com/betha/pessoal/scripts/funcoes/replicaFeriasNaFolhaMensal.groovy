package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.evento
import static com.betha.pessoal.scripts.padrao.Calculo.evento
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender;
import static com.betha.pessoal.scripts.padrao.enums.*;

class ReplicaFeriasNaFolhaMensal {
    public static LinkedHashMap getReplicaFeriasNaFolhaMensal(int codigoEvento, ArrayList listaComposicaoBasesValorCalculado, ArrayList listaComposicaoBasesSobreValorReferencia, ArrayList listaComposicaoBasesSobreSalario, ArrayList listaComposicaoBasesSobrePagaProporcional) {
        Binding binding = new Binding()

        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/

        def zero = ['valor': 0, 'referencia': 0];

        if ( ! calculo.replicaEventosCalculoFeriasParaCalculoMensal ) return zero

        if (!TipoProcessamento.MENSAL.equals(calculo.tipoProcessamento)) return zero

        def _codigoEvento
        if (binding.variables["codigoEvento"]) {
            _codigoEvento = codigoEvento
        } else {
            _codigoEvento = evento.codigo
        }

        def _listaComposicaoBasesValorCalculado = []
        if (binding.variables["listaComposicaoBasesValorCalculado"]) {
            _listaComposicaoBasesValorCalculado = listaComposicaoBasesValorCalculado
        }

        def _listaComposicaoBasesSobreValorReferencia = []
        if (binding.variables["listaComposicaoBasesSobreValorReferencia"]) {
            _listaComposicaoBasesSobreValorReferencia = listaComposicaoBasesSobreValorReferencia
        }

        def _listaComposicaoBasesSobreSalario = []
        if (binding.variables["listaComposicaoBasesSobreSalario"]) {
            _listaComposicaoBasesSobreSalario = listaComposicaoBasesSobreSalario
        }

        def _listaComposicaoBasesSobrePagaProporcional = []
        if (binding.variables["listaComposicaoBasesSobrePagaProporcional"]) {
            _listaComposicaoBasesSobrePagaProporcional = listaComposicaoBasesSobrePagaProporcional
        }


        if (!TipoProcessamento.MENSAL.equals(calculo.tipoProcessamento)) return zero
        if (!SubTipoProcessamento.INTEGRAL.equals(calculo.subTipoProcessamento)) return zero

        def valorFerias = Funcoes.getValorFerias(codigoEvento, false);

        if (valorFerias.valor <= 0 ){
            valorFerias = Funcoes.getValorFerias(codigoEvento,true);
        }

        BigDecimal valor = valorFerias.valor
        BigDecimal referencia = valorFerias.referencia

        //Composicao de bases de cálculo
        if (valor > 0) {

            //Marca o evento como replicado
            evento.replicado(true)

            def valorRetorno = [:]
            try {
                if (_listaComposicaoBasesValorCalculado.size > 0) {
                    Bases.compor(valor, *_listaComposicaoBasesValorCalculado)
                }

                if (_listaComposicaoBasesSobreValorReferencia.size > 0) {
                    Bases.compor(referencia, *_listaComposicaoBasesSobreValorReferencia)
                }

                if (_listaComposicaoBasesSobreSalario.size > 0) {
                    Bases.compor(Funcoes.remuneracao(matricula.tipo).valor, *_listaComposicaoBasesSobreSalario)
                }

                if (_listaComposicaoBasesSobrePagaProporcional.size > 0) {
                    if (_listaComposicaoBasesSobrePagaProporcional.contains(Bases.PAGAPROP)) {
                        suspender 'Não é possível uma base incidir para ela mesmo (Bases.PAGAPROP) '
                    }
                    Bases.compor(Bases.valor(Bases.PAGAPROP), *_listaComposicaoBasesSobrePagaProporcional)
                }

                imprimir 'IMPORTANTE : Este evento foi replicado do cálculo de férias'
                return ['valor': valor, 'referencia': referencia]

            } catch (ignore) {
                suspender 'Não foi possível retornar o valor das férias no cálculo mensal, verifique os argumentos passados na função "replicaFeriasNaFolhaMensal()"'
            }
        } else {
            return zero
        }


        /** FINAL **/
    }
}
