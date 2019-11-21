package com.betha.pessoal.scripts.eventos

import com.betha.pessoal.scripts.padrao.DadosFuncionario.funcionario
import com.betha.pessoal.scripts.padrao.Eventos
import com.betha.pessoal.scripts.padrao.LancamentoEvento.Lancamentos
import com.betha.pessoal.scripts.padrao.Matricula.matricula
import com.betha.pessoal.scripts.funcoes.Funcoes
import com.betha.pessoal.scripts.padrao.Folhas.folhasPeriodo
import com.betha.pessoal.scripts.padrao.Calculo.calculo
import com.betha.pessoal.scripts.padrao.utilitarioDatas.Datas
import static com.betha.pessoal.scripts.padrao.bfcScript.imprimir
import static com.betha.pessoal.scripts.padrao.bfcScript.suspender
import static com.betha.pessoal.scripts.padrao.enums.*

/**a declaração de dados do evento pode ser removida, utilize apenas quando necessário testar alguma informação diferente **/
def evento = [codigo: 900, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO];

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

if (!calculo.replicaEventosCalculoFeriasParaCalculoMensal) {
    suspender "Não configurado para replicar no cálculo mensal"
}

if (!calculo.tipoProcessamento.equals(TipoProcessamento.MENSAL)) {
    suspender "Calcular apenas no processamento mensal"
};
double provento = 0;
double desconto = 0;
double liquido = 0;
folhasInternas = folhasPeriodo.buscaFolhasInternas(TipoProcessamento.FERIAS);
cptCalc = Datas.data(Datas.ano(calculo.competencia), Datas.mes(calculo.competencia), 1);
folhasInternas.each { f ->
    cptFerias = Datas.data(Datas.ano(f.competencia), Datas.mes(f.competencia), 1);
    if (cptCalc.equals(cptFerias)) {
        f.eventos.each { e ->
            imprimir 'Evento ' + e.codigo + ' valor ' + e.valor;
            if (Eventos.valor(e.codigo) == 0) {
                if (TipoEvento.VENCIMENTO.equals(e.tipo)) {
                    provento += e.valor.abs()
                };
                if (TipoEvento.DESCONTO.equals(e.tipo)) {
                    desconto += e.valor.abs()
                };
            }
        }
    }
}
provento = provento.abs();
if (provento > 0) {
    valorCalculado = provento;
}
