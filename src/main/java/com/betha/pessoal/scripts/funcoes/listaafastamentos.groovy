package com.betha.pessoal.scripts.funcoes

import com.betha.pessoal.scripts.padrao.Caracteres
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas;
import com.betha.pessoal.scripts.padrao.Matricula.matricula;
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario;
import com.betha.pessoal.scripts.padrao.Calculo.calculo;
import static com.betha.pessoal.scripts.padrao.Calculo.evento;
import static com.betha.pessoal.scripts.padrao.enums.*;

class Listaafastamentos {
    public static ArrayList getListaafastamentos(ClassifOuTipo) {
        /** Dentro deste bloco deverá esta o conteúdo do script, o mesmo do cadastro de funções para fórmulas **/
        def afastamentos = []

        if (ClassifOuTipo != 'T') {
            String aux = ''
            //percorre uma string que é uma lista de valores separadas por virgula e retorna
            def tamanho = Caracteres.tamanho(ClassifOuTipo)
            for (int indice = 0; indice <= tamanho; indice++) {
                def i = Caracteres.subTexto(ClassifOuTipo, indice - 1, 1)
                if (i == ',' || indice == tamanho) {
                    if (indice == tamanho) {
                        aux += i
                    }
                    if (aux == 'ABORTO_NAO_CRIMINOSO') {
                        afastamentos << ClassificacaoTipoAfastamento.ABORTO_NAO_CRIMINOSO
                    }
                    if (aux == 'ACIDENTE_DE_TRABALHO_EMPREGADOR') {
                        afastamentos << ClassificacaoTipoAfastamento.ACIDENTE_DE_TRABALHO_EMPREGADOR
                    }
                    if (aux == 'ACIDENTE_DE_TRABALHO_PREVIDENCIA') {
                        afastamentos << ClassificacaoTipoAfastamento.ACIDENTE_DE_TRABALHO_PREVIDENCIA
                    }
                    if (aux == 'ACIDENTE_DE_TRAJETO_EMPREGADOR') {
                        afastamentos << ClassificacaoTipoAfastamento.ACIDENTE_DE_TRAJETO_EMPREGADOR
                    }
                    if (aux == 'ACIDENTE_DE_TRAJETO_PREVIDENCIA') {
                        afastamentos << ClassificacaoTipoAfastamento.ACIDENTE_DE_TRAJETO_PREVIDENCIA
                    }
                    if (aux == 'DOENCA_DO_TRABALHO_EMPREGADOR') {
                        afastamentos << ClassificacaoTipoAfastamento.DOENCA_DO_TRABALHO_EMPREGADOR
                    }
                    if (aux == 'DOENCA_DO_TRABALHO_PREVIDENCIA') {
                        afastamentos << ClassificacaoTipoAfastamento.DOENCA_DO_TRABALHO_PREVIDENCIA
                    }
                    if (aux == 'AUXILIO_DOENCA_EMPREGADOR') {
                        afastamentos << ClassificacaoTipoAfastamento.AUXILIO_DOENCA_EMPREGADOR
                    }
                    if (aux == 'AUXILIO_DOENCA_PREVIDENCIA') {
                        afastamentos << ClassificacaoTipoAfastamento.AUXILIO_DOENCA_PREVIDENCIA
                    }
                    if (aux == 'ACOMPANHAR_MEMBRO_DA_FAMILIA_ENFERMO') {
                        afastamentos << ClassificacaoTipoAfastamento.ACOMPANHAR_MEMBRO_DA_FAMILIA_ENFERMO
                    }
                    if (aux == 'ADOCAO_GUARDA_JUDICIAL_DE_CRIANCA') {
                        afastamentos << ClassificacaoTipoAfastamento.ADOCAO_GUARDA_JUDICIAL_DE_CRIANCA
                    }
                    if (aux == 'APOSENTADORIA_POR_INVALIDEZ') {
                        afastamentos << ClassificacaoTipoAfastamento.APOSENTADORIA_POR_INVALIDEZ
                    }
                    if (aux == 'CANDIDATO_A_CARGO_ELETIVO') {
                        afastamentos << ClassificacaoTipoAfastamento.CANDIDATO_A_CARGO_ELETIVO
                    }
                    if (aux == 'CARCERE') {
                        afastamentos << ClassificacaoTipoAfastamento.CARCERE
                    }
                    if (aux == 'CEDENCIA') {
                        afastamentos << ClassificacaoTipoAfastamento.CEDENCIA
                    }
                    if (aux == 'FERIAS') {
                        afastamentos << ClassificacaoTipoAfastamento.FERIAS
                    }
                    if (aux == 'LICENCA_COM_VENCIMENTOS') {
                        afastamentos << ClassificacaoTipoAfastamento.LICENCA_COM_VENCIMENTOS
                    }
                    if (aux == 'LICENCA_SEM_VENCIMENTOS') {
                        afastamentos << ClassificacaoTipoAfastamento.LICENCA_SEM_VENCIMENTOS
                    }
                    if (aux == 'LICENCA_MATERNIDADE') {
                        afastamentos << ClassificacaoTipoAfastamento.LICENCA_MATERNIDADE
                    }
                    if (aux == 'MANDATO_ELEITORAL_COM_REMUNERACAO') {
                        afastamentos << ClassificacaoTipoAfastamento.MANDATO_ELEITORAL_COM_REMUNERACAO
                    }
                    if (aux == 'MANDATO_ELEITORAL_SEM_REMUNERACAO') {
                        afastamentos << ClassificacaoTipoAfastamento.MANDATO_ELEITORAL_SEM_REMUNERACAO
                    }
                    if (aux == 'MANDATO_SINDICAL') {
                        afastamentos << ClassificacaoTipoAfastamento.MANDATO_SINDICAL
                    }
                    if (aux == 'MULHER_VITIMA_DE_VIOLENCIA_LEI_MARIA_DA_PENHA') {
                        afastamentos << ClassificacaoTipoAfastamento.MULHER_VITIMA_DE_VIOLENCIA_LEI_MARIA_DA_PENHA
                    }
                    if (aux == 'PRORROGACAO_DA_LICENCA_MATERNIDADE') {
                        afastamentos << ClassificacaoTipoAfastamento.PRORROGACAO_DA_LICENCA_MATERNIDADE
                    }
                    if (aux == 'SERVICO_MILITAR') {
                        afastamentos << ClassificacaoTipoAfastamento.SERVICO_MILITAR
                    }
                    if (aux == 'SERVIDOR_PUBLICO_EM_DISPONIBILIDADE') {
                        afastamentos << ClassificacaoTipoAfastamento.SERVIDOR_PUBLICO_EM_DISPONIBILIDADE
                    }
                    if (aux == 'SUSPENSAO_DISCIPLINAR_ART474_CLT') {
                        afastamentos << ClassificacaoTipoAfastamento.SUSPENSAO_DISCIPLINAR_ART474_CLT
                    }
                    if (aux == 'SUSPENSAO_DO_CONTRATO_ART476_CLT') {
                        afastamentos << ClassificacaoTipoAfastamento.SUSPENSAO_DO_CONTRATO_ART476_CLT
                    }

                    aux = ''
                } else {
                    aux += i
                }
            }


        } else {

            //retorna todos com exceção de falta,demitido e aposentado
            afastamentos << ClassificacaoTipoAfastamento.ABORTO_NAO_CRIMINOSO
            afastamentos << ClassificacaoTipoAfastamento.ACIDENTE_DE_TRABALHO_EMPREGADOR
            afastamentos << ClassificacaoTipoAfastamento.ACIDENTE_DE_TRABALHO_PREVIDENCIA
            afastamentos << ClassificacaoTipoAfastamento.ACIDENTE_DE_TRAJETO_EMPREGADOR
            afastamentos << ClassificacaoTipoAfastamento.ACIDENTE_DE_TRAJETO_PREVIDENCIA
            afastamentos << ClassificacaoTipoAfastamento.DOENCA_DO_TRABALHO_EMPREGADOR
            afastamentos << ClassificacaoTipoAfastamento.DOENCA_DO_TRABALHO_PREVIDENCIA
            afastamentos << ClassificacaoTipoAfastamento.AUXILIO_DOENCA_EMPREGADOR
            afastamentos << ClassificacaoTipoAfastamento.AUXILIO_DOENCA_PREVIDENCIA
            afastamentos << ClassificacaoTipoAfastamento.ACOMPANHAR_MEMBRO_DA_FAMILIA_ENFERMO
            afastamentos << ClassificacaoTipoAfastamento.ADOCAO_GUARDA_JUDICIAL_DE_CRIANCA
            afastamentos << ClassificacaoTipoAfastamento.APOSENTADORIA_POR_INVALIDEZ
            afastamentos << ClassificacaoTipoAfastamento.CANDIDATO_A_CARGO_ELETIVO
            afastamentos << ClassificacaoTipoAfastamento.CARCERE
            afastamentos << ClassificacaoTipoAfastamento.CEDENCIA
            afastamentos << ClassificacaoTipoAfastamento.FERIAS
            afastamentos << ClassificacaoTipoAfastamento.LICENCA_COM_VENCIMENTOS
            afastamentos << ClassificacaoTipoAfastamento.LICENCA_SEM_VENCIMENTOS
            afastamentos << ClassificacaoTipoAfastamento.LICENCA_MATERNIDADE
            afastamentos << ClassificacaoTipoAfastamento.MANDATO_ELEITORAL_COM_REMUNERACAO
            afastamentos << ClassificacaoTipoAfastamento.MANDATO_ELEITORAL_SEM_REMUNERACAO
            afastamentos << ClassificacaoTipoAfastamento.MANDATO_SINDICAL
            afastamentos << ClassificacaoTipoAfastamento.MULHER_VITIMA_DE_VIOLENCIA_LEI_MARIA_DA_PENHA
            afastamentos << ClassificacaoTipoAfastamento.PRORROGACAO_DA_LICENCA_MATERNIDADE
            afastamentos << ClassificacaoTipoAfastamento.SERVICO_MILITAR
            afastamentos << ClassificacaoTipoAfastamento.SERVIDOR_PUBLICO_EM_DISPONIBILIDADE
            afastamentos << ClassificacaoTipoAfastamento.SUSPENSAO_DISCIPLINAR_ART474_CLT
            afastamentos << ClassificacaoTipoAfastamento.SUSPENSAO_DO_CONTRATO_ART476_CLT

        }

        return afastamentos

        /** FINAL **/
    }
}
