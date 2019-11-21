package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.*
import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.Servidor.servidor
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 247, taxa: 0, tipo: TipoEvento.DESCONTO, unidade: UnidadeEvento.AUTOMATICO]; //Configuracao do evento

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()

if (!funcionario.possuiPrevidencia(TipoPrevidencia.FUNDO_FINANCEIRO)) {
    suspender "Calcular somente para previdência própria"
}

def classificacoes = [ClassificacaoTipoAfastamento.LICENCA_MATERNIDADE, ClassificacaoTipoAfastamento.ABORTO_NAO_CRIMINOSO, ClassificacaoTipoAfastamento.PRORROGACAO_DA_LICENCA_MATERNIDADE]
int dias = Funcoes.diasafast(Funcoes.inicioCompetencia(), calculo.competencia, classificacoes)


if (!Sexo.FEMININO.equals(servidor.sexo)) {
    suspender "calcular somente para sexo feminino"
}

def vaux = Lancamentos.valor(evento)
if (vaux >= 0) {
    valorCalculado = vaux
} else {
    def ultimoDia = calculo.competencia
    def afastadoUltimoDia = Funcoes.diasafast(ultimoDia, ultimoDia, classificacoes)
    if (afastadoUltimoDia == 0 && SubTipoProcessamento.COMPLEMENTAR.equals(calculo.subTipoProcessamento)) {
        suspender "Calculo do evento cancelado"
    }

    double base = (Bases.valor(Bases.FUNDFIN) + Eventos.valor(40)) - (Eventos.valor(22) + Eventos.valor(23) + Eventos.valor(24) + Eventos.valor(178) + Eventos.valor(179) + Eventos.valor(180))
    def vDiasLicencaMaternidade = Funcoes.cnvdpbase(Funcoes.afaslicmat()) + Funcoes.cnvdpbase(Funcoes.afasprorroglicmat()) + Funcoes.cnvdpbase(Funcoes.afasaborto())
    def vsmataux
    def baseaux
    def hrsmesaux
    def pagaProporcional = Bases.valor(Bases.PAGAPROP)
    if (base > funcionario.salario) {
        vsmataux = funcionario.salario
    } else {
        if (UnidadePagamento.DIARISTA.equals(funcionario.unidadePagamento)) {
            baseaux = funcionario.quantidadeHorasMes / 30 * pagaProporcional
            baseaux = Numeros.arredonda(baseaux, 12)
            hrsmesaux = Numeros.arredonda(funcionario.quantidadeHorasMes, 12)
        } else {
            baseaux = pagaProporcional
            hrsmesaux = funcionario.quantidadeHorasMes
        }
        if (baseaux != hrsmesaux) {
            vsmataux = Eventos.valor(3) + Eventos.valor(161) + Eventos.valor(162)
        }
    }
    if (vsmataux != funcionario.salario && Eventos.valor(195) == 0) {
        vsmataux = Eventos.valor(3) + Eventos.valor(161) + Eventos.valor(162)
        if (dias < calculo.quantidadeDiasCompetencia) {
            def diafin = pagaProporcional
            if (UnidadePagamento.HORISTA.equals(funcionario.unidadePagamento) || UnidadePagamento.DIARISTA.equals(funcionario.unidadePagamento)) {
                diafin = diafin + (funcionario.quantidadeHorasMes / 30)
            }
            if (TipoProcessamento.RESCISAO.equals(calculo.tipoProcessamento)) {
                if (Funcoes.cnvdpbase(calculo.dataRescisao) < diafin) {
                    diafin = Funcoes.cnvdpbase(calculo.dataRescisao)
                }
            }
            base = base - Bases.valor(Bases.COMPHORAMES)
            base = ((base * vDiasLicencaMaternidade / diafin) + vsmataux)
        }
        valorCalculado = base
    } else {
        valorCalculado = Eventos.valor(3) + Eventos.valor(161) + Eventos.valor(162) + Eventos.valor(195)
    }
}