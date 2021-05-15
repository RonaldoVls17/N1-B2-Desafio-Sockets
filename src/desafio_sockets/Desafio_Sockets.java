/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio_sockets;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author arthur
 */
public class Desafio_Sockets {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        // TODO code application logic here
        GerenciadorRequisicoes.getInstancia();
        ThreadImpressora thread = new ThreadImpressora();
        thread.start();
        System.out.println("Esperando A Requisição do Cliente");
        ThreadRequisicao thread_requisicao = new ThreadRequisicao();
        thread_requisicao.start();

        Scanner scan = new Scanner(System.in);
        try {
            boolean sair = false;
            do {
                System.out.println("Bem-Vindo");
                System.out.println("Escolha um Opção:");
                System.out.println("1 - Clientes");
                System.out.println("2 - Sair");
                int opcao = scan.nextInt();
                switch (opcao) {
                    case 1:
                        thread_requisicao.listarClientes();
                        break;
                    case 2:
                        sair = true;
                        break;
                }
            } while (!sair);
        } finally {
            scan.close();
            thread_requisicao.encerra();
        }

    }

}
