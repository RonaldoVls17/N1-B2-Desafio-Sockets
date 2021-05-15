/*
https://www.w3schools.com/java/java_threads.asp
https://github.com/gabrielbaptista/linguagem-programacao-ii-ftt/tree/master/SimulacaoDesafioThreads

 */
package desafio_sockets;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arthur
 */
public class ThreadRequisicao extends Thread {

    private boolean ServidorAtivo;
    private static ServerSocket server;
    private static int port = 9879;
    private List<Cliente> clientesConectados = Collections.synchronizedList(new ArrayList<Cliente>());

    @Override
    public void run() {

        try {
            ServidorAtivo = true;
            server = new ServerSocket(port);
            while (ServidorAtivo) {
                Socket socket = server.accept();
                adicionaClient(socket);
                Thread.sleep(10);
            }
        } catch (IOException | InterruptedException ex) {
            if (ex.getMessage().equals("socket closed")) {
                System.out.println("Conexão Server Encerrada...");
            } else {
                Logger.getLogger(ThreadRequisicao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void adicionaClient(Socket socket) {
        Cliente manager = new Cliente();
        manager.setClientSocket(socket);
        clientesConectados.add(manager);
        Thread threadSocket = new Thread(manager);
        threadSocket.start();
    }

    public void listarClientes() {
        for (Cliente cliente : clientesConectados) {
            cliente.identifiqueSe();
        }
    }

    public void encerra() throws IOException {
        clientesConectados.forEach((cliente) -> {
            try {
                cliente.encerra();
            } catch (IOException ex) {
                Logger.getLogger(ThreadRequisicao.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        ServidorAtivo = false;
        server.close();
    }

}
