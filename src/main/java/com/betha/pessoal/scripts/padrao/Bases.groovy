package com.betha.pessoal.scripts.padrao

import com.betha.pessoal.scripts.utils
import com.sun.xml.internal.bind.v2.TODO;

public class Bases extends enums {
    Object data = utils.getFile("bases");
    /**
     * Valor do evento lançado em variáveis
     * @param evento
     * @return o valor do lançamento, caso não tenha valor lançado retorna -1
     */
    static void compor(BigDecimal valor, Enum[] bases) {
        //Bases object = new Bases();
        //return object.data.lancamentos.sum(0, { it.evento.codigo.equals(evento) ? it.valor : 0 } )

    }

    static Enum getTipoBase(String base) {
        AbreviacaoBases value = base as AbreviacaoBases;
        return value
    }

    static BigDecimal getValor(Enum TipoBase) {
        Bases bases = new Bases();

        BigDecimal total = bases.data.sum(0, { getTipoBase(it.abreviacao).equals(TipoBase) ? it.valor : 0 })

        return BigDecimal.valueOf(total)

    }

    static BigDecimal valor(Enum tipoBase) {
        return getValor(tipoBase)
    }

    static BigDecimal valorComposicaoMes() {
        return BigDecimal.valueOf(0)
    }

    static BigDecimal valorCalculado(Enum tipoBase ,Enum tipoProcessamento , Enum subTipoProcessamento ){
        return getValor(tipoBase)
    }

    static BigDecimal valorCalculado(Enum tipoBase ,Enum tipoProcessamento , Enum subTipoProcessamento, Date competencia ){
        return getValor(tipoBase)
    }



    static Enum PAGAPROP = AbreviacaoBases.PAGAPROP;

    static Enum SALBASE = AbreviacaoBases.SALBASE;

    static Enum HORAEXTRA = AbreviacaoBases.HORAEXTRA;

    static Enum PERIC = AbreviacaoBases.PERIC;

    static Enum SIND = AbreviacaoBases.SIND;

    static Enum FGTS = AbreviacaoBases.FGTS;

    static Enum FGTS13 = AbreviacaoBases.FGTS13;

    static Enum IRRF = AbreviacaoBases.IRRF;

    static Enum IRRF13 = AbreviacaoBases.IRRF13;

    static Enum IRRFFERRESC = AbreviacaoBases.IRRFFERRESC;

    static Enum INSS = AbreviacaoBases.INSS;

    static Enum INSS13 = AbreviacaoBases.INSS13;

    static Enum INSSFER = AbreviacaoBases.INSSFER;

    static Enum PREVEST = AbreviacaoBases.PREVEST;

    static Enum PREVEST13 = AbreviacaoBases.PREVEST13;

    static Enum FUNDASS = AbreviacaoBases.FUNDASS;

    static Enum FUNDASS13 = AbreviacaoBases.FUNDASS13;

    static Enum FUNDOPREV = AbreviacaoBases.FUNDOPREV;

    static Enum FUNDPREV13 = AbreviacaoBases.FUNDPREV13;

    static Enum OUTRASBASES = AbreviacaoBases.OUTRASBASES;

    static Enum FGTSAVISO = AbreviacaoBases.FGTSAVISO;

    static Enum ABATIRRF = AbreviacaoBases.ABATIRRF;

    static Enum ABATIRRF13 = AbreviacaoBases.ABATIRRF13;

    static Enum DESCIRRF = AbreviacaoBases.DESCIRRF;

    static Enum DESCIRRF13 = AbreviacaoBases.DESCIRRF13;

    static Enum DESCIRRFERES = AbreviacaoBases.DESCIRRFERES;

    static Enum EXCEINSS = AbreviacaoBases.EXCEINSS;

    static Enum EXCEINSS13 = AbreviacaoBases.EXCEINSS13;

    static Enum ABATINSS = AbreviacaoBases.ABATINSS;

    static Enum DESCTERFER = AbreviacaoBases.DESCTERFER;

    static Enum SALAFAM = AbreviacaoBases.SALAFAM;

    static Enum INSSOUTRA = AbreviacaoBases.INSSOUTRA;

    static Enum INSSOUTRA13 = AbreviacaoBases.INSSOUTRA13;

    static Enum IRRFOUTRA = AbreviacaoBases.IRRFOUTRA;

    static Enum IRRFOUTRA13 = AbreviacaoBases.IRRFOUTRA13;

    static Enum IRRFFER = AbreviacaoBases.IRRFFER;

    static Enum CONTSIND = AbreviacaoBases.CONTSIND;

    static Enum MEDIAUXMAT = AbreviacaoBases.MEDIAUXMAT;

    static Enum DESC13REINT = AbreviacaoBases.DESC13REINT;

    static Enum COMPHORAMES = AbreviacaoBases.COMPHORAMES;

    static Enum TERFERVENRES = AbreviacaoBases.TERFERVENRES;

    static Enum FUNDFIN = AbreviacaoBases.FUNDFIN;

    static Enum FUNDFIN13 = AbreviacaoBases.FUNDFIN13;

    static Enum MEDAUXMATPR = AbreviacaoBases.MEDAUXMATPR;

    static Enum IN13SADI = AbreviacaoBases.IN13SADI;

    static Enum FUPR13SAPR = AbreviacaoBases.FUPR13SAPR;

    static Enum FUPR13SAAJPR = AbreviacaoBases.FUPR13SAAJPR;

    static Enum FUPR13SAESPR = AbreviacaoBases.FUPR13SAESPR;

    static Enum FUPR13SADI = AbreviacaoBases.FUPR13SADI;

    static Enum PRES13SAPR = AbreviacaoBases.PRES13SAPR;

    static Enum PRES13SAAJPR = AbreviacaoBases.PRES13SAAJPR;

    static Enum PRES13SAESPR = AbreviacaoBases.PRES13SAESPR;

    static Enum PRES13SADI = AbreviacaoBases.PRES13SADI;

    static Enum FUAS13SAPR = AbreviacaoBases.FUAS13SAPR;

    static Enum FUAS13SAAJPR = AbreviacaoBases.FUAS13SAAJPR;

    static Enum FUAS13SAESPR = AbreviacaoBases.FUAS13SAESPR;

    static Enum FUAS13SADI = AbreviacaoBases.FUAS13SADI;

    static Enum FG13SAPR = AbreviacaoBases.FG13SAPR;

    static Enum FG13SAAJPR = AbreviacaoBases.FG13SAAJPR;

    static Enum FG13SAESPR = AbreviacaoBases.FG13SAESPR;

    static Enum INFEPR = AbreviacaoBases.INFEPR;

    static Enum INFEAJPR = AbreviacaoBases.INFEAJPR;

    static Enum INFEESPR = AbreviacaoBases.INFEESPR;

    static Enum INFEDI = AbreviacaoBases.INFEDI;

    static Enum FUPRFEPR = AbreviacaoBases.FUPRFEPR;

    static Enum FUPRFEAJPR = AbreviacaoBases.FUPRFEAJPR;

    static Enum FUPRFEESPR = AbreviacaoBases.FUPRFEESPR;

    static Enum FUPRFEDI = AbreviacaoBases.FUPRFEDI;

    static Enum PRESFEPR = AbreviacaoBases.PRESFEPR;

    static Enum PRESFEAJPR = AbreviacaoBases.PRESFEAJPR;

    static Enum PRESFEESPR = AbreviacaoBases.PRESFEESPR;

    static Enum PRESFEDI = AbreviacaoBases.PRESFEDI;

    static Enum FUASFEPR = AbreviacaoBases.FUASFEPR;

    static Enum FUASFEAJPR = AbreviacaoBases.FUASFEAJPR;

    static Enum FUASFEESPR = AbreviacaoBases.FUASFEESPR;

    static Enum FUASFEDI = AbreviacaoBases.FUASFEDI;

    static Enum FGFEPR = AbreviacaoBases.FGFEPR;

    static Enum FGFEAJPR = AbreviacaoBases.FGFEAJPR;

    static Enum FGFEESPR = AbreviacaoBases.FGFEESPR;

    static Enum PRBAAUMEHOFE = AbreviacaoBases.PRBAAUMEHOFE;

    static Enum PRBAAUMEHO13 = AbreviacaoBases.PRBAAUMEHO13;

    static Enum PRBAAUDI13SA = AbreviacaoBases.PRBAAUDI13SA;

    static Enum PRBAAUDIFE = AbreviacaoBases.PRBAAUDIFE;

    static Enum DEVIRRF = AbreviacaoBases.DEVIRRF;

    static Enum DEVINSS = AbreviacaoBases.DEVINSS;

    static Enum PARCINSENIRRF = AbreviacaoBases.PARCINSENIRRF;

    static Enum PARCINSENIRRF13 = AbreviacaoBases.PARCINSENIRRF13;


}





