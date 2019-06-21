/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SD_SistemaDistribuido;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Jean Amorim
 */
public class Cliente extends Thread {

    private Socket conexao;

    public Cliente(Socket socket) {
        this.conexao = socket;
    }

    public static void main(String args[]) {
        Scanner ler = new Scanner(System.in);
        try {

            int porta = 0;
            String ip = null;

            System.out.println("**********************************************");
            System.out.println("                  Bem Vindo!                   ");
            System.out.println("**********************************************");
            System.out.println("Digite o ip do servidor que deseja se conectar:");
            ip = ler.nextLine();
            System.out.println("**********************************************");
            System.out.println("Digite a porta que deseja se conectar:"  );
            porta = ler.nextInt();
            System.out.println("**********************************************");

            Socket conexao = new Socket(ip, porta);

            DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
            DataInputStream entrada = new DataInputStream(conexao.getInputStream());
            BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        
            saida.writeUTF(ip);
 
         
            Thread t = new Cliente(conexao);
            t.start();
        
           
            String msg;
            System.out.println("*********************************************************");
            String titulo = entrada.readUTF();
            System.out.println(titulo);
            String cand1 = entrada.readUTF();
            System.out.println(cand1);
            String cand2 = entrada.readUTF();
            System.out.println(cand2);
            String cand3 = entrada.readUTF();
            System.out.println(cand3);
            String cand4 = entrada.readUTF();
            System.out.println(cand4);
            String cand5 = entrada.readUTF();
            System.out.println(cand5);
            System.out.println("*********************************************************");

            msg = bfr.readLine();
            saida.writeUTF(msg);

            String obrigado = entrada.readUTF();
            System.out.println(obrigado);
            System.out.println("*********************************************************");

            String resultado = entrada.readUTF();
            System.out.println(resultado);
            System.out.println("");
            String resul1 = entrada.readUTF();
            System.out.println(resul1);
            String resul2 = entrada.readUTF();
            System.out.println(resul2);
            String resul3 = entrada.readUTF();
            System.out.println(resul3);
            String resul4 = entrada.readUTF();
            System.out.println(resul4);
            String resul5 = entrada.readUTF();
            System.out.println(resul5);

            System.out.println("*********************************************************");
            String bye = entrada.readUTF();
            System.out.println(bye);
            

        } catch (IOException e) {
            System.out.println("IOException" + e);
        }
    }

}
