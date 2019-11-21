package com.betha.pessoal.scripts.padrao

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.temporal.ChronoUnit

class utilitarioDatas {
    class Datas extends Date {

        static Date data(int ano, int mes, int dia) {
            LocalDate date = LocalDate.of(ano, mes, dia);
            return date.toDate();
        }

        static Date hoje() {
            return new Date()
        }

        static int ano(Date data) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(data);
            return calendar.get(Calendar.YEAR);
        }

        static int mes(Date data) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(data);
            return calendar.get(Calendar.MONTH).plus(1);
        }

        static int dia(Date data) {
            data.getDate()
        }

        static long diferencaDias(Date dataInicial, Date dataFinal) {
            return ChronoUnit.DAYS.between(dataInicial.toLocalDate(), dataFinal.toLocalDate())
        }

        static long diferencaMeses(Date dataInicial, Date dataFinal) {
            return ChronoUnit.MONTHS.between(dataInicial.toLocalDate(), dataFinal.toLocalDate())
        }

        static long diferencaAnos(Date dataInicial, Date dataFinal) {
            return ChronoUnit.YEARS.between(dataInicial.toLocalDate(), dataFinal.toLocalDate())
        }

        static Date removeMeses(Date data, int meses) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(data);
            calendar.add(Calendar.MONTH, -meses);
            return calendar.getTime();

        }

        static Date removeDias(Date data, int dias) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(data);
            calendar.add(Calendar.DATE, -dias);
            return calendar.getTime();

        }

        static Date adicionaMeses(Date data, int meses) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(data);
            calendar.add(Calendar.MONTH, meses);
            return calendar.getTime();

        }

        static Date adicionaDias(Date data, int dias) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(data);
            calendar.add(Calendar.DATE, dias);
            return calendar.getTime();

        }

        static String formatar(Date data, String formato) {
            SimpleDateFormat format = new SimpleDateFormat(formato);
            return format.format(data);
        }

    }
}
