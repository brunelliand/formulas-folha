package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.DadosAposentado.aposentado;
import com.betha.pessoal.scripts.padrao.DadosEstagiario.estagiario;
import com.betha.pessoal.scripts.padrao.DadosPensionista.pensionista
import com.betha.pessoal.scripts.padrao.DadosAutonomo.autonomo;
import static com.betha.pessoal.scripts.padrao.enums.*;

class Remuneracao {
    static LinkedHashMap getRemuneracao(Enum tipo) {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        try {
            if (tipo == TipoMatricula.APOSENTADO) {
                return [
                        valor             : aposentado.valorBeneficio,
                        unidade           : UnidadePagamento.MENSALISTA,
                        quantidadeHorasMes: 0]
            }
            if (tipo == TipoMatricula.FUNCIONARIO) {
                return [
                        valor             : funcionario.salario,
                        unidade           : funcionario.unidadePagamento,
                        quantidadeHorasMes: funcionario.quantidadeHorasMes]
            }
            if (tipo == TipoMatricula.ESTAGIARIO) {
                return [
                        valor             : estagiario.valorBeneficio,
                        unidade           : UnidadePagamento.MENSALISTA,
                        quantidadeHorasMes: estagiario.quantidadeHorasMes]
            }
            if (tipo == TipoMatricula.AUTONOMO) {
                return [
                        valor             : autonomo.totalServicosAutonomo,
                        unidade           : UnidadePagamento.MENSALISTA,
                        quantidadeHorasMes: 0]
            }
            if (tipo == TipoMatricula.PENSIONISTA) {
                return [
                        valor             : pensionista.valorBeneficio,
                        unidade           : UnidadePagamento.MENSALISTA,
                        quantidadeHorasMes: 0]
            }
        } catch (ignore) {
            return [
                    valor             : 0,
                    unidade           : UnidadePagamento.MENSALISTA,
                    quantidadeHorasMes: 0
            ]
        }


        /** FINAL **/
    }
}
