package com.betha.pessoal.scripts.padrao

import static com.betha.pessoal.scripts.padrao.enums.*;
import java.time.LocalDate;
import java.time.Month;
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import java.time.temporal.ChronoUnit;
import com.betha.pessoal.scripts.utils;

class Calculo {
    Object data = utils.getFile("dadosCalculo");
    class calculo {

        static Boolean getReplicaEventosCalculoFeriasParaCalculoMensal(){
            Calculo object = new Calculo();
            return object.data.replicaEventosCalculoFeriasParaCalculoMensal
        }

        static Boolean replicaEventosCalculoFeriasParaCalculoMensal = getReplicaEventosCalculoFeriasParaCalculoMensal()

        static Boolean getPagarUmTercoIntegral() {
            Calculo object = new Calculo();
            return object.data.pagarUmTercoIntegral
        }
        static Boolean pagarUmTercoIntegral = getPagarUmTercoIntegral()

        static Boolean getPagarDecimoTerceiroFerias(){
            Calculo object = new Calculo();
            return object.data.pagarDecimoTerceiroFerias
        }

        static Boolean pagarDecimoTerceiroFerias = getPagarDecimoTerceiroFerias()

        static Boolean getConsideraAvosPerdidos() {
            Calculo object = new Calculo();
            return object.data.consideraAvosPerdidos
        }

        static consideraAvosPerdidos = getConsideraAvosPerdidos();

        static long getAnoDecimoTerceiro() {
            Calculo object = new Calculo();
            return object.data.anoDecimoTerceiro
        }
        static long anoDecimoTerceiro = getAnoDecimoTerceiro();

        public static int getQuantidadeDiasCompetencia() {
            Calculo object = new Calculo();
            return object.data.quantidadeDiasCompetencia
        }

        static int quantidadeDiasCompetencia = getQuantidadeDiasCompetencia();

        public static Date getDataRescisao() {
            Calculo object = new Calculo();

            return object.data.dataRescisao
        }

        static Date dataRescisao = getDataRescisao();

        public static Enum getUnidade() {
            Calculo object = new Calculo();
            String value = object.data.unidade

            UnidadeCalculo convertedEnumValue = value as UnidadeCalculo
            return convertedEnumValue
        }
        static unidadePagamento = getUnidade();

        public static Date getCompetencia() {
            Calculo object = new Calculo();

            return object.data.competencia;

        }

        static Date competencia = getCompetencia();

        static Enum getTipoProcessamento() {
            Calculo object = new Calculo();
            String value = object.data.tipoProcessamento;

            TipoProcessamento convertedEnumValue = value as TipoProcessamento
            return convertedEnumValue

        }
        static tipoProcessamento = getTipoProcessamento();

        static Enum getSubTipoProcessamento() {
            Calculo object = new Calculo();
            String value = object.data.subTipoProcessamento;

            SubTipoProcessamento convertedEnumValue = value as SubTipoProcessamento
            return convertedEnumValue

        }
        static subTipoProcessamento = getSubTipoProcessamento();

        static int quantidadeDias(int mes, int ano ){
            Date data = Datas.data(ano, mes, 1 )
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(data);
            return  calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        }


    }

    public static Object getEvento() {
        return [codigo: 1, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];
    }

    static evento = getEvento();
}