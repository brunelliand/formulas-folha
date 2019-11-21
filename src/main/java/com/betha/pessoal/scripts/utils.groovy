package com.betha.pessoal.scripts

import groovy.json.JsonGenerator
import groovy.json.JsonParserType
import groovy.json.JsonSlurper
import static com.betha.pessoal.scripts.padrao.enums.*

import java.time.LocalDate
import java.time.temporal.TemporalAdjusters


class utils {
    static Object getFile(String fileName) throws IOException {
        def jsonSlurper = new JsonSlurper(type: JsonParserType.INDEX_OVERLAY)

        Object file = jsonSlurper.parse(new File("C:\\dev\\folha\\arquivos\\${fileName}.json"));


        return file


    }

    static Date endOfMonth(Date data) {
        LocalDate date = data.toLocalDate()
        LocalDate endOfMonth = date.with(TemporalAdjusters.lastDayOfMonth());
        return endOfMonth.toDate()

    }

    static Date startOfMonth(Date data) {
        LocalDate date = data.toLocalDate()
        LocalDate startOfMonth = date.with(TemporalAdjusters.firstDayOfMonth());
        return startOfMonth.toDate()

    }

    @SuppressWarnings("unchecked")
    public
    static <E extends Enum<E>> E getEnumValue(Class<?> c, String value) {
        return Enum.valueOf((Class<E>) c, value);
    }


}

