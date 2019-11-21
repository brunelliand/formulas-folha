package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Bases
import com.betha.pessoal.scripts.padrao.Numeros
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import com.sun.java.util.jar.pack.Package

import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 124, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

Funcoes.somenteFuncionarios()

if (!Funcoes.possuiPrevidenciaFederal(matricula.tipo)) {
    suspender "Não possui previdência federal"
}


def diasafastados = Funcoes.afasadocao();

if (diasafastados <= 0) {
    suspender 'Sem dias afastado'
}

def dias
def vaux = Funcoes.cnvdpbase(diasafastados);
def base = Bases.valor(Bases.INSS) + Eventos.valor(40)

if (calculo.dataRescisao != null) {
    def dataRescisao = calculo.dataRescisao
    def dataAdmissao = funcionario.dataAdmissao
    dias = Datas.dia(dataRescisao)
    if (Datas.ano(dataAdmissao) == Datas.ano(calculo.competencia) && Datas.mes(dataAdmissao) == Datas.mes(calculo.competencia)) {
        dias -= (Datas.dia(dataAdmissao) + 1)
    }
    base = base * funcionario.quantidadeHorasMes / Funcoes.cnvdpbase(dias)
    base = Numeros.arredonda(base, 2)
    valorCalculado = Funcoes.calcprop(base, vaux)
} else {
    valorReferencia = vaux
    if (diasafastados == 30) {
        valorCalculado = base
    } else {
        def pagp = Bases.valor(Bases.PAGAPROP)
        dias = Funcoes.diastrab() + diasafastados
        if (dias < 30) {
            if (UnidadePagamento.HORISTA.equals(funcionario.unidadePagamento)) {
                base *= funcionario.quantidadeHorasMes
            } else {
                base *= 30
            }
            base /= pagp
            base = Numeros.arredonda(base, 2)
        }
        valorCalculado = Funcoes.calcprop(base, vaux)
    }
}
if (valorCalculado > 0) {
    Bases.compor(valorCalculado, Bases.IRRF, Bases.EXCEINSS)
}

imprimir(valorCalculado)