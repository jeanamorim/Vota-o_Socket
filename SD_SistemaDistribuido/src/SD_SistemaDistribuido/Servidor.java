/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SD_SistemaDistribuido;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;


/**
 *
 * @author Jean Amorim
 */
public class Servidor extends Thread {

    private String eleitores[] = {"Jean", "Marcos", "Gilberto", "Almir", "Andrei"};

    private String ip;
    private static Vector ELEITORES;
    public static int votos[];


    public static void main(String args[]) {

        Scanner ler = new Scanner(System.in);
        ELEITORES = new Vector();
        votos = new int[5];
        votos[0] = 0;
        votos[1] = 0;
        votos[2] = 0;
        votos[3] = 0;
        votos[4] = 0;

        ServerSocket s = null;
        try {
            int porta = 0;
            System.out.println("*********************************************************");
            System.out.println("                Olá, Bem Vindo!                           ");
            System.out.println("*********************************************************");
            System.out.println(" Digite uma porta para que seu servidor fique escutando  ");
            System.out.println("*********************************************************");
            porta = ler.nextInt();
            System.out.println("*********************************************************");
            ServerSocket server = new ServerSocket(porta);

            System.out.println("                  Servidor Iniciado                      ");
            System.out.println("               Servidor rodando na porta " + porta);
            System.out.println("*********************************************************");

            while (true) {

                Socket conexao = server.accept();
                Thread t = new Servidor(conexao);
                t.start();

            }
        } catch (IOException e) {
            System.out.println("IOException " + e);
        }
    }
    private Socket conexao;

    public Servidor(Socket s) {
        conexao = s;
    }

    public void run() {
        VerificarIP verifica = new VerificarIP(); 

        try {

            DataInputStream entrada = new DataInputStream(conexao.getInputStream());
            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());

            this.ip = entrada.readUTF();

            if (verifica.verificaIP(this.ip)) {
                ELEITORES.add(saida);
                saida.writeUTF("OPS, desculpa, mais vc so tem permissão de votar 5 vezes...");
                this.conexao.close();
                return;
            } else {
                

                System.out.println(" Conectado ao Servidor com o ip: " + this.ip);
             }
            //adiciona os dados de saida do cliente no objeto ELEITORES
            ELEITORES.add(saida);

            String titulo = " Digite o numero do candidato que queira votar!";
            String cand1 = "(1) Jean";
            String cand2 = "(2) Marcos";
            String cand3 = "(3) Gilberto";
            String cand4 = "(4) Almir";
            String cand5 = "(5) Andrei";

            saida.writeUTF(titulo);
            saida.writeUTF(cand1);
            saida.writeUTF(cand2);
            saida.writeUTF(cand3);
            saida.writeUTF(cand4);
            saida.writeUTF(cand5);

            String msg = entrada.readUTF();

            if (msg.equals("1")) {
                votos[0]++;
            } else if (msg.equals("2")) {
                votos[1]++;
            } else if (msg.equals("3")) {
                votos[2]++;
            } else if (msg.equals("4")) {
                votos[3]++;
            } else if (msg.equals("5")) {
                votos[4]++;
            }
            String obrigado = "  Obrigado pelo seu voto";
            saida.writeUTF(obrigado);

            String resul1 = "Jean tem     -> ";
            String resul2 = "Marcos tem   -> ";
            String resul3 = "Gilberto tem -> ";
            String resul4 = "Almir tem    -> ";
            String resul5 = "Andrei tem   -> ";
            String mensagem = "Resultado da Votação";

            saida.writeUTF(mensagem);
            saida.writeUTF(resul1 + votos[0]);
            saida.writeUTF(resul2 + votos[1]);
            saida.writeUTF(resul3 + votos[2]);
            saida.writeUTF(resul4 + votos[3]);
            saida.writeUTF(resul5 + votos[4]);

            System.out.println("Eleitor saiu da Urna!");

            String bye = "bye";
            saida.writeUTF(bye);

            this.conexao.close();

        } catch (IOException e) {
            // Caso ocorra alguma excessão mostre qual foi.
            System.out.println("Falha na Conexao... .. ." + " IOException: " + e);
        }
    }

}
