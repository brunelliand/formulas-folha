package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.Afastamentos
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.EncargosSociais
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import com.betha.pessoal.scripts.padrao.Servidor.servidor
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 126, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

double base30 = Bases.valor(Bases.SALAFAM)
double base = Funcoes.remuneracao(matricula.tipo).valor

int dependentes = servidor.getDependenteSalarioFamilia(TipoSalarioFamilia.CELETISTA)
if (dependentes < 1) {
    suspender 'Sem dependentes de salário família!'
}

if (TipoProcessamento.MENSAL.equals(calculo.tipoProcessamento) && SubTipoProcessamento.INTEGRAL.equals(calculo.subTipoProcessamento)) {
    base = base + Bases.valorCalculado(Bases.SALAFAM, TipoProcessamento.FERIAS, SubTipoProcessamento.INTEGRAL)
}

if (base <= 0) {
    suspender 'Sem base de cálculo para salário família'
}

def classificacoes = [
        ClassificacaoTipoAfastamento.LICENCA_COM_VENCIMENTOS,
        ClassificacaoTipoAfastamento.LICENCA_MATERNIDADE,
        ClassificacaoTipoAfastamento.ABORTO_NAO_CRIMINOSO,
        ClassificacaoTipoAfastamento.ADOCAO_GUARDA_JUDICIAL_DE_CRIANCA,
        ClassificacaoTipoAfastamento.PRORROGACAO_DA_LICENCA_MATERNIDADE,
        ClassificacaoTipoAfastamento.PRORROGACAO_DA_LICENCA_MATERNIDADE_11_770
]

int diastrab = Funcoes.diastrab();
int dias = Funcoes.diasafastcalc30(calculo.competencia, classificacoes)
dias = diastrab + dias;

int diasgozo = Funcoes.diasferias()

def inicioCompetencia = Datas.data(Datas.ano(calculo.competencia), Datas.mes(calculo.competencia), 1)

if (diasgozo == 0) {
    classificacoes = [
            3 : ClassificacaoTipoAfastamento.ACIDENTE_DE_TRABALHO_PREVIDENCIA,
            4 : ClassificacaoTipoAfastamento.SERVICO_MILITAR,
            6 : ClassificacaoTipoAfastamento.AUXILIO_DOENCA_PREVIDENCIA,
            7 : ClassificacaoTipoAfastamento.LICENCA_SEM_VENCIMENTOS,
            8 : ClassificacaoTipoAfastamento.DEMITIDO,
            9 : ClassificacaoTipoAfastamento.APOSENTADO,
            11: ClassificacaoTipoAfastamento.ACIDENTE_DE_TRAJETO_PREVIDENCIA,
            12: ClassificacaoTipoAfastamento.DOENCA_DO_TRABALHO_PREVIDENCIA
    ];

    for (classificacao in classificacoes) {
        def i = classificacao.key

        int diasAfastado = Afastamentos.buscaPorPeriodo(classificacoes[i]).count{it}
        if (diasAfastado > 0) {
            suspender 'Possui afastamento na competência que inicia na competencia anterior ou no início desta competência'
        }
        if (i == 7 && [UnidadePagamento.DIARISTA, UnidadePagamento.HORISTA].contains(Funcoes.remuneracao(matricula.tipo).unidade)) {
            if (Funcoes.afaslicsvenc() == calculo.quantidadeDiasCompetencia) {
                suspender 'Afastado o mês todo'
            }
        }
        if (i == 7 && UnidadePagamento.MENSALISTA == Funcoes.remuneracao(matricula.tipo).unidade) {
            if (Funcoes.afaslicsvenc().equals(30)) {
                suspender 'Afastado o mês todo'
            }
        }

    }
} else {
    if (dias > 0) {
        suspender "Cálculo do evento ${evento.codigo} cancelado"
    }
}

valorReferencia = dependentes
def vvar = Lancamentos.valor(evento)
if (vvar > 0) {
    valorCalculado = vvar
} else {
    double vaux2 = base.trunc(2)
    double vtot = EncargosSociais.SalarioFamilia.Celetista.buscaContribuicao(vaux2, 2) * valorReferencia

    def dataAdmissao = funcionario.dataAdmissao
    def anoadm = Datas.ano(dataAdmissao)
    def mesadm = Datas.mes(dataAdmissao)
    def diaadm = Datas.dia(dataAdmissao)
    int diaTrab = 0

    def dataRescisao = calculo.dataRescisao

    boolean mesAdmissaoDemissao //Mês da admissão que não seja no primeiro dia do mês ou rescisão que não seja o último


    mesAdmissaoDemissao = (inicioCompetencia < dataAdmissao)
    if (!mesAdmissaoDemissao) {
        if (dataRescisao != null) {
            if (Datas.ano(dataRescisao) == Datas.ano(calculo.competencia) && Datas.mes(dataRescisao) == Datas.mes(calculo.competencia) && calculo.quantidadeDiasCompetencia > Datas.dia(dataRescisao)) {
                mesAdmissaoDemissao = true
            }
        }
    }

    if (mesAdmissaoDemissao) {
        diaTrab = diastrab;
        vtot *= diaTrab;
    }


    if (diaTrab != 0) {
        if (UnidadePagamento.HORISTA.equals(funcionario.unidadePagamento) || UnidadePagamento.DIARISTA.equals(funcionario.unidadePagamento)) {
            vtot /= calculo.quantidadeDiasCompetencia
        } else {
            vtot /= 30;
        }
    }
    valorCalculado = vtot
}

imprimir(valorCalculado)