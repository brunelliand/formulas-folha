package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.padrao.Afastamentos;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.enums.*;

class DiasTrab{
    public static int getDiasTrab(){

        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/

        //Criado por André Brunelli
        /*Competências com 31 dias
        - Quando o dia 31 caiu em período de férias,  o dia 31 é considerado na contagem e desconta-se o primeiro dia do mês.
        - Quando existe afastamento o dia 31 é desconsiderado */

        def dados = Funcoes.dadosMatricula();
        def diastrab;

        boolean mensalista;
        if(TipoMatricula.FUNCIONARIO.equals(matricula.tipo)){
            if(UnidadePagamento.MENSALISTA.equals(funcionario.unidadePagamento) ){
                mensalista = true;
            }
        }


        if(TipoMatricula.FUNCIONARIO.equals(matricula.tipo)){
            if(UnidadePagamento.MENSALISTA.equals(funcionario.unidadePagamento)){
                diastrab = 30
            }else{
                diastrab = calculo.quantidadeDiasCompetencia;
            }
        }else{
            diastrab = 30; //calculo.quantidadeDiasCompetencia
        }
        int dias = 0
        String unidadeDias = UnidadeCalculo.DIAS.toString()

        def afastamentos = [];
        Afastamentos.buscaPorPeriodo(
                ClassificacaoTipoAfastamento.ABORTO_NAO_CRIMINOSO,
                ClassificacaoTipoAfastamento.ACIDENTE_DE_TRABALHO_EMPREGADOR,
                ClassificacaoTipoAfastamento.ACIDENTE_DE_TRABALHO_PREVIDENCIA,
                ClassificacaoTipoAfastamento.ACIDENTE_DE_TRAJETO_EMPREGADOR,
                ClassificacaoTipoAfastamento.ACIDENTE_DE_TRAJETO_PREVIDENCIA,
                ClassificacaoTipoAfastamento.DOENCA_DO_TRABALHO_EMPREGADOR,
                ClassificacaoTipoAfastamento.DOENCA_DO_TRABALHO_PREVIDENCIA,
                ClassificacaoTipoAfastamento.AUXILIO_DOENCA_EMPREGADOR,
                ClassificacaoTipoAfastamento.AUXILIO_DOENCA_PREVIDENCIA,
                ClassificacaoTipoAfastamento.ACOMPANHAR_MEMBRO_DA_FAMILIA_ENFERMO,
                ClassificacaoTipoAfastamento.ADOCAO_GUARDA_JUDICIAL_DE_CRIANCA,
                ClassificacaoTipoAfastamento.APOSENTADORIA_POR_INVALIDEZ,
                ClassificacaoTipoAfastamento.CANDIDATO_A_CARGO_ELETIVO,
                ClassificacaoTipoAfastamento.CARCERE,
                ClassificacaoTipoAfastamento.CEDENCIA,
                ClassificacaoTipoAfastamento.FERIAS,
                ClassificacaoTipoAfastamento.LICENCA_COM_VENCIMENTOS,
                ClassificacaoTipoAfastamento.LICENCA_SEM_VENCIMENTOS,
                ClassificacaoTipoAfastamento.LICENCA_MATERNIDADE,
                ClassificacaoTipoAfastamento.MANDATO_ELEITORAL_COM_REMUNERACAO,
                ClassificacaoTipoAfastamento.MANDATO_ELEITORAL_SEM_REMUNERACAO,
                ClassificacaoTipoAfastamento.MANDATO_SINDICAL,
                ClassificacaoTipoAfastamento.MULHER_VITIMA_DE_VIOLENCIA_LEI_MARIA_DA_PENHA,
                ClassificacaoTipoAfastamento.PRORROGACAO_DA_LICENCA_MATERNIDADE,
                ClassificacaoTipoAfastamento.SERVICO_MILITAR,
                ClassificacaoTipoAfastamento.SERVIDOR_PUBLICO_EM_DISPONIBILIDADE,
                ClassificacaoTipoAfastamento.SUSPENSAO_DISCIPLINAR_ART474_CLT,
                ClassificacaoTipoAfastamento.SUSPENSAO_DO_CONTRATO_ART476_CLT).each{it-> afastamentos.push(it) }


        def finalCompetencia = calculo.competencia
        if ( calculo.dataRescisao ){
            finalCompetencia = finalCompetencia.after(calculo.dataRescisao) ?  calculo.dataRescisao : finalCompetencia
        }

        def inicioCompetencia = Datas.data( Datas.ano(calculo.competencia) , Datas.mes(calculo.competencia) , 1 )

        if ( dados.dataInicio > inicioCompetencia ){
            if (inicioCompetencia > finalCompetencia ) return 0
            inicioCompetencia = dados.dataInicio
        }

        if ( finalCompetencia.before(inicioCompetencia) ) return 0

        if ( Datas.dia(finalCompetencia).equals(31) && mensalista ){
            if ( afastamentos.find { ClassificacaoTipoAfastamento.FERIAS.equals(it.tipo.classificacao) && it.fim >= finalCompetencia  } ){
                inicioCompetencia = inicioCompetencia.adicionaDias(1);
            }else{
                finalCompetencia = Datas.removeDias(finalCompetencia,1)
            }
        }

        if ( calculo.dataRescisao ){
            diastrab = Datas.diferencaDias(inicioCompetencia,finalCompetencia)
        }


        afastamentos.each{ afast->

            def unidadeAfastamento = afast.unidade.toString()

            def fim;
            if ( afast.fim == null ){
                fim = finalCompetencia
            }else{
                fim = afast.fim > finalCompetencia ? finalCompetencia : afast.fim;
            }
            def inicio = afast.inicio < inicioCompetencia ? inicioCompetencia : afast.inicio ;



            dias = dias + Datas.diferencaDias(inicio , fim ) + 1

        }

        diastrab = diastrab - dias;
        if ( mensalista ){
            if(diastrab > 30 ){return 30 }
        }

        return diastrab
        /** FINAL **/

    }
}