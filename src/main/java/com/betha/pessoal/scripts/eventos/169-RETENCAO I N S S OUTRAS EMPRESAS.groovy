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
def evento = [codigo: 169, taxa: 0, tipo: TipoEvento.DESCONTO, unidade: UnidadeEvento.AUTOMATICO]; //Configuracao do evento

Funcoes.somenteFuncionarios();

boolean mensalIntegral = (TipoProcessamento.MENSAL.equals(calculo.tipoProcessamento) && SubTipoProcessamento.INTEGRAL.equals(calculo.subTipoProcessamento))
boolean mensalComplementar = (TipoProcessamento.MENSAL.equals(calculo.tipoProcessamento) && SubTipoProcessamento.COMPLEMENTAR.equals(calculo.subTipoProcessamento))

if(((Eventos.valorCalculado(evento.codigo, TipoValor.CALCULADO, TipoProcessamento.MENSAL, SubTipoProcessamento.INTEGRAL ) > 0) && (TipoProcessamento.MENSAL.equals(calculo.tipoProcessamento) && SubTipoProcessamento.COMPLEMENTAR.equals(calculo.subTipoProcessamento)))){
    suspender "Este evento já foi calculado no processamento mensal"
}

if (!funcionario.possuiPrevidenciaFederal){
    suspender "Calcular apenas para previdência federal"
}

def vaux = Lancamentos.valor(evento);
if(vaux <= 0){
    if((TipoProcessamento.FERIAS.equals(calculo.tipoProcessamento) && folha.diasGozo) || (mensalIntegral)){
        vaux = BasesOutrasEmpresas.buscaPor(TipoProcessamento.MENSAL).sum(0, {it.valorRetidoInss})
    }else{
        vaux = BasesOutrasEmpresas.buscaPor(calculo.tipoProcessamento).sum(0, {it.valorRetidoInss})
    }
}

if(TipoProcessamento.FERIAS.equals(calculo.tipoProcessamento) && folha.diasGozo > 14){
    valorCalculado = Funcoes.calcprop(vaux, Funcoes.cnvdpbase(30));
}else{
    if(TipoProcessamento.MENSAL.equals(calculo.tipoProcessamento)){
        if(((funcionario.unidadePagamento.equals(UnidadePagamento.HORISTA)) || ( funcionario.unidadePagamento.equals(UnidadePagamento.DIARISTA))) && (calculo.quantidadeDiasCompetencia == 31)){
            valorCalculado = Funcoes.calcprop(vaux, Funcoes.cnvdpbase(31))
        }else {
            valorCalculado = Funcoes.calcprop(vaux, Funcoes.cnvdpbase(30));
        }
    }
}

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/



