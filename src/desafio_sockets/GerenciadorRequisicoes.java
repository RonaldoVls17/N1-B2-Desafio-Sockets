/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio_sockets;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 *
 * @author arthur
 */
public class GerenciadorRequisicoes {

    private static Object objeto = new Object();
    public static GerenciadorRequisicoes _instance;

    public static GerenciadorRequisicoes getInstancia() {
        if (_instance == null) {
            _instance = new GerenciadorRequisicoes();
        }
        return _instance;
    }
    //fila de mensagens
    public static ConcurrentLinkedQueue<String> FilaRequisicoes1 = new ConcurrentLinkedQueue<String>();

    public void Adiciona_Requisicao_1(String mensagem) {
        FilaRequisicoes1.add(mensagem);
    }

    public void Retira_Requisicao_1() {
        if (!FilaRequisicoes1.isEmpty()) {
            FilaRequisicoes1.remove();
        }
    }
}
