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
def evento = [codigo: 185, taxa: 0, tipo: TipoEvento.VENCIMENTO, unidade: UnidadeEvento.AUTOMATICO]; //Configuracao do evento

/** INICIO DA FÓRMULA DO EVENTO DE CÁLCULO DA FOLHA **/

//Convertido do folharh desktop em 05/08/2019 16:01:18.  (Evento de ferias alterado para a formula modelo)

valorCalculado = 0;
valorReferencia = 0;