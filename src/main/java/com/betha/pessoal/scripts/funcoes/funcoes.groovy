package com.betha.pessoal.scripts.funcoes

import java.time.LocalDate

import static com.betha.pessoal.scripts.padrao.enums.*


class Funcoes {

    static possuiPrevidencia(Enum<TipoPrevidencia> TipoPrevidencia){
        return PossuiPrevidencia.getPossuiPrevidencia(TipoPrevidencia)
    }

    static Date inicioCompetencia() {
        return InicioCompetencia.getInicioCompetencia()
    }

    static Date primeiroDia(Date data) {
        return PrimeiroDia.getPrimeiroDia(data)
    }

    static Boolean avisoPrevioIndenizado() {
        return AvisoPrevioIndenizado.getAvisoPrevioIndenizado()
    }

    static Boolean avisoPrevioDescontado() {
        return AvisoPrevioDescontado.getAvisoPrevioDescontado()
    }

    static Boolean possuiPrevidenciaFederal(Enum tipo) {
        return PossuiPrevidenciaFederal.getPossuiPrevidenciaFederal(tipo)
    }

    static LinkedHashMap replicaFeriasNaFolhaMensal(int codigoEvento) {
        return ReplicaFeriasNaFolhaMensal.getReplicaFeriasNaFolhaMensal(codigoEvento, new ArrayList(), new ArrayList(), new ArrayList(), new ArrayList())
    }

    static LinkedHashMap replicaFeriasNaFolhaMensal(int codigoEvento, ArrayList listaComposicaoBasesValorCalculado) {
        return ReplicaFeriasNaFolhaMensal.getReplicaFeriasNaFolhaMensal(codigoEvento, listaComposicaoBasesValorCalculado, new ArrayList(), new ArrayList(), new ArrayList())
    }

    static LinkedHashMap replicaFeriasNaFolhaMensal(int codigoEvento, ArrayList listaComposicaoBasesValorCalculado, ArrayList listaComposicaoBasesSobreValorReferencia) {
        return ReplicaFeriasNaFolhaMensal.getReplicaFeriasNaFolhaMensal(codigoEvento, listaComposicaoBasesValorCalculado, listaComposicaoBasesSobreValorReferencia, new ArrayList(), new ArrayList())
    }

    static LinkedHashMap replicaFeriasNaFolhaMensal(int codigoEvento, ArrayList listaComposicaoBasesValorCalculado, ArrayList listaComposicaoBasesSobreValorReferencia, ArrayList listaComposicaoBasesSobreSalario) {
        return ReplicaFeriasNaFolhaMensal.getReplicaFeriasNaFolhaMensal(codigoEvento, listaComposicaoBasesValorCalculado, listaComposicaoBasesSobreValorReferencia, listaComposicaoBasesSobreSalario, new ArrayList())
    }

    static LinkedHashMap replicaFeriasNaFolhaMensal(int codigoEvento, ArrayList listaComposicaoBasesValorCalculado, ArrayList listaComposicaoBasesSobreValorReferencia, ArrayList listaComposicaoBasesSobreSalario, ArrayList listaComposicaoBasesSobrePagaProporcional) {
        return ReplicaFeriasNaFolhaMensal.getReplicaFeriasNaFolhaMensal(codigoEvento, listaComposicaoBasesValorCalculado, listaComposicaoBasesSobreValorReferencia, listaComposicaoBasesSobreSalario, listaComposicaoBasesSobrePagaProporcional)
    }

    static LinkedHashMap getValorFerias(int codigoEvento) {
        return ValorFerias.getValorFerias(codigoEvento, false)
    }

    static LinkedHashMap getValorFerias(int codigoEvento, Boolean buscaFolhaPagamento) {
        return ValorFerias.getValorFerias(codigoEvento, buscaFolhaPagamento)
    }

    static BigDecimal acumula(int codigoEvento, Enum tipo, Date competenciaInicial, Date competenciaFinal, ArrayList tiposProcessamento) {
        return Acumula.getAcumula(codigoEvento, tipo, competenciaInicial, competenciaFinal, tiposProcessamento)
    }

    static long diasferias() {
        return Diasferias.getDiasferias()
    }

    static BigDecimal calcexclusivo(BigDecimal valorReferencia, Enum tipoProcessamento1, long diasProcessamento1, Enum tipoProcessamento2, long diasProcessamento2) {
        return Calcexclusivo.getCalcexclusivo(valorReferencia, tipoProcessamento1, diasProcessamento1, tipoProcessamento2, diasProcessamento2)
    }

    static LinkedHashMap getInssFerias() {
        return InssFerias.getInssFerias()
    }

    static LinkedHashMap replicaEventoVariavel(int codigoEvento) {
        return ReplicaEventoVariavel.getReplicaEventoVariavel(codigoEvento)
    }

    static Boolean pagapensao() {
        return Pagapensao.getPagapensao()
    }

    static long diasafast(Date datainicial, Date datafinal, def afastamentos) {
        return Diasafast.getDiasafast(datainicial, datafinal, afastamentos)
    }

    static BigDecimal faltas(def justificada, Date dataCompetenciaInicial, Date dataCompetenciaFinal, def motivo, def abonada) {
        return Faltas.getFaltas(justificada, dataCompetenciaInicial, dataCompetenciaFinal, motivo, abonada)
    }

    static Boolean optanteFgts(Enum tipoMatricula) {
        return OptanteFgts.getOptanteFgts(tipoMatricula)
    }

    static int buscaMes(Enum mes) {
        return BuscaMes.getBuscaMes(mes);
    }

    static void somenteFuncionarios() {
        SomenteFuncionarios.getSomenteFuncionarios();
    }


    static int diastrab() {
        return DiasTrab.getDiasTrab();
    }

    static BigDecimal cnvdpbase(dias) {
        return ConverteBase.getConverteBase(dias);
    }

    static LinkedHashMap dadosMatricula() {
        return DadosMatricula.getDadosMatricula()
    }

    static LinkedHashMap remuneracao(Enum tipo) {
        return Remuneracao.getRemuneracao(tipo)
    }

    static BigDecimal calcprop(BigDecimal vlrCalc, BigDecimal proporcional) {
        return CalculoProporcional.getCalcProp(vlrCalc, proporcional)
    }

    static Boolean recebeDecimoTerceiro(Enum tipoDaMatricula) {
        return RecebeDecimoTerceiro.getRecebeDecimoTerceiro()
    }

    static String ocorrenciaSefip() {
        return OcorrenciaSefip.getOcorrenciaSefip();
    }

    static int avos13(int mes) {
        boolean calculoFGTS = false;
        return Avos13.getAvos13(mes, calculoFGTS)
    }

    static int avos13(int mes, Boolean calculoFGTS) {
        return Avos13.getAvos13(mes, calculoFGTS)
    }

    static ArrayList listaafastamentos(ClassifOuTipo) {
        return Listaafastamentos.getListaafastamentos(ClassifOuTipo)
    }

    static int mesesafast(Date dataInicial, Date dataFinal, classificacoes) {
        return Mesesafast.getMesesafast(dataInicial, dataFinal, classificacoes)
    }

    static int diasafastcalc30(Date competencia, afastamentos) {
        return Diasafastcalc30.getDiasafastcalc30(competencia, afastamentos)
    }

    static Boolean permitecalc13integral() {
        return Permitecalc13integral.getPermitecalc13integral()
    }

    static dtrescisao() {
        return Dtrescisao.getDtrescisao()
    }

    static long afaslicmat() {
        return Afaslicmat.getAfaslicmat();
    }

    static long cedidocomonus() {
        return Cedidocomonus.getCedidocomonus()
    }

    static long cedidosemonus() {
        return Cedidosemonus.getCedidosemonus()
    }

    static long afasaborto() {
        return Afasaborto.getAfasaborto();
    }

    static long afasacidtrab() {
        return Afasacidtrab.getAfasacidtrab()
    }

    static long afaslicsvenc() {
        return Afaslicsvenc.getAfaslicsvenc();
    }

    static long afasservmil() {
        return Afasservmil.getAfasservmil()
    }

    static long afasdirinteg() {
        return Afasdirinteg.getAfasdirinteg()
    }

    static long afasprorroglicmat() {
        return Afasprorroglicmat.getAfasprorroglicmat()
    }

    static long afasadocao() {
        return Afasadocao.getAfasadocao()
    }

    static long afasacidtrabemp() {
        return Afasacidtrabemp.getAfasacidtrabemp()
    }

    static long afasauxdoencemp() {
        return Afasauxdoencemp.getAfasauxdoencemp()
    }

    static long afasauxdoenc() {
        return Afasauxdoenc.getAfasauxdoenc()
    }

    static long afasprorroglicmatlei11770() {
        return Afasprorroglicmatlei11770.getAfasprorroglicmatlei11770()
    }

    static long diasaposent() {
        return Diasaposent.getDiasaposent()
    }

    static long mesesmat13() {
        return Mesesmat13.getMesesmat13()
    }

    static int idade(Date menorData, Date maiorData) {
        return Idade.getIdade(menorData, maiorData)
    }

    static BigDecimal deducauxmat13(BigDecimal base, int avos) {
        return Deducauxmat13.getDeducauxmat13(base, avos)
    }

    static long recebidosemonus() {
        return Recebidosemonus.getRecebidosemonus()
    }

    static somenteAposentadosBeneficiarios() {
        return SomenteAposentadosBeneficiarios.getSomenteAposentadosBeneficiarios()
    }

    static Date dtafast(Enum classificacao, Boolean licencaMaternidadeOrgininal) {
        return Dtafast.getDtafast(classificacao, licencaMaternidadeOrgininal)
    }

    static LinkedHashMap getIrrfFerias() {
        return IrrfFerias.getIrrfFerias()
    }

    static LinkedHashMap getTotalFerias() {
        return TotalFerias.getTotalFerias()
    }

    static String descricaoVinculo() {
        return DescricaoVinculo.getDescricaoVinculo()
    }

    static BigDecimal adicionais(int tipoRetorno, int tipoAdicional) {
        return Adicionais.getAdicionais(tipoRetorno, tipoAdicional)
    }

    static String calcmultvincesocial() {
        return Calcmultvincesocial.getCalcmultvincesocial()
    }

    static Integer funcprinc() {
        return Funcprinc.getFuncprinc()
    }

    static BigDecimal basesoutrempr(int tipo, int tipoRetorno, Enum<TipoProcessamento> tipoProcessamento) {
        return Basesoutrempr.getBasesoutrempr(tipo, tipoRetorno, tipoProcessamento)
    }

    static String temoutrosfun() {
        return Temoutrosfun.getTemoutrosfun()
    }

    static Date paraData(def valor) {
        return ParaData.getParaData(valor)
    }

    static abonocompens(int tipo) {
        return Abonocompens.getAbonocompens(tipo)
    }

    static Integer trazDia(def data) {
        return TrazDia.getTrazDia(data)
    }

    static Integer altsalfer() {
        return Altsalfer.getAltsalfer()
    }

    static BigDecimal carghrnivel() {
        return Carghrnivel.getCarghrnivel()
    }

    static BigDecimal computa() {
        return Computa.getComputa()
    }

    static dadosadic(def codigoOuDescricao) {
        return Dadosadic.getDadosadic(codigoOuDescricao)
    }

    static Integer diarias() {
        return Diarias.getDiarias()
    }

    static Integer diaslocaistrabfunc() {
        return Diaslocaistrabfunc.getDiaslocaistrabfunc()
    }

    static Long diastransf(String tipo, Integer quantidadeTransferencias, String onus) {
        return Diastransf.getDiastransf(tipo, quantidadeTransferencias, onus)
    }

    static Integer diasuteis(Date dataInicial, Date dataFinal, def sabadoUtil) {
        return Diasuteis.getDiasuteis(dataInicial, dataFinal, sabadoUtil)
    }

    static Integer diasuteisafast(Date dataInicial, Date dataFinal, def sabadoUtil, List<ClassificacaoTipoAfastamento> classificacaoAfastamentos) {
        return Diasuteisafast.getDiasuteisafast(dataInicial, dataFinal, sabadoUtil, *classificacaoAfastamentos)
    }

    static Integer diasuteisafastcalc30(Date competencia, def sabadoUtil, List<ClassificacaoTipoAfastamento> classificacaoAfastamentos) {
        return Diasuteisafastcalc30.getDiasuteisafastcalc30(competencia, sabadoUtil, *classificacaoAfastamentos)
    }

    static Integer diasuteiscalc30(Date competencia, def sabadoUtil) {
        return Diasuteiscalc30.getDiasuteiscalc30(competencia, sabadoUtil)
    }

    static Date dtnascdep(Integer sequencialDependente) {
        return Dtnascdep.getDtnascdep(sequencialDependente)
    }

    static BigDecimal emprestimos(Date dataCompetencia, def financiador) {
        return Emprestimos.getEmprestimos(dataCompetencia, financiador)
    }

    static Boolean existePagamento13Salario() {
        return ExistePagamento13Salario.getExistePagamento13Salario()
    }

    static String existerescisao(Date dataInicial, Date dataFinal) {
        return Existerescisao.getExisterescisao(dataInicial, dataFinal)
    }

    static String categoriaSefipVinculo() {
        return Categvinc.getCategoriaSefipVinculo()
    }

    static Integer funcoesfunc(def codigoDescricaoFuncao) {
        return Funcoesfunc.getFuncoesfunc(codigoDescricaoFuncao)
    }

    static BigDecimal gerapensjud(Integer evento, Integer configuracao) {
        return Gerapensjud.getGerapensjud(evento, configuracao)
    }

    static String irrfultmesfer(){
        return Irrfultmesfer.getIrrfultmesfer()
    }

    static Integer mesfer(){
        return Mesfer.getMesfer()
    }



}

