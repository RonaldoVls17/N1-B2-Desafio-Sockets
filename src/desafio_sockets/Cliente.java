/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio_sockets;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arthur
 */
public class Cliente implements Runnable {

    private Socket clienteSocket;

    @Override
    public void run() {

        try {
            InputStream stream = clienteSocket.getInputStream();

            try {
                boolean ativo = true;
                do {

                    if (stream.available() != 0) {
                        byte[] dados = new byte[stream.available()];
                        stream.read(dados);
                        String dadosLidos = new String(dados);
                        Pega_Requisicao(dadosLidos);
//                        ativo = false;
                    }

                } while (ativo);

            } finally {
                if (stream != null) {
                    stream.close();
                }
                if (clienteSocket.isConnected()) {
                    clienteSocket.close();
                }
            }
        } catch (IOException ex) {
            if (ex.getMessage().equals("Socket closed")) {
                System.out.println(clienteSocket.hashCode() + ": Conexão Cliente Encerrada");
            }
            if (ex.getMessage().equals("Stream closed.")) {
                System.out.println("Transmissão Encerrada");
            } else {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void setClientSocket(Socket clientSocket) {
        this.clienteSocket = clientSocket;
    }

    public void encerra() throws IOException {
        if (clienteSocket.isConnected()) {
            clienteSocket.close();
        }
    }

    void identifiqueSe() {
        if ((clienteSocket.isConnected()) && (!clienteSocket.isClosed())) {
            System.out.println(clienteSocket.hashCode() + ": Conexão Cliente Estabelecida.");
        } else {
            System.out.println(clienteSocket.hashCode() + ": Conexão Cliente Encerrada.");
        }
    }

    public Socket getClientSocket() {
        return clienteSocket;
    }

    private void Pega_Requisicao(String dadosLidos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
