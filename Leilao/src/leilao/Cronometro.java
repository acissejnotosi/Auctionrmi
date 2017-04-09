/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leilao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.security.PublicKey;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author allan
 */
public class Cronometro extends Thread {
    
    DatagramSocket socket = null;
     String pid;
     String myport;
     String hostport;
     PublicKey pubKey;
     String nomeProduto;

    public Cronometro(String pid, String myport, String hostport, String nomeProduto) {
        this.pid = pid;
        this.myport = myport;
        this.hostport = hostport;
        this.nomeProduto = nomeProduto;
             
    }
    @Override
    public void run() {
        
        byte[] buffer;
        char type; // type of message
        DatagramPacket messageIn;
        ByteArrayInputStream bis;
        ObjectInputStream ois;
           System.out.println("passei");
            try { 
                int i=0;
                while (i<5) {          
                    i++;
                    Thread.sleep(10000); 
                }
                 System.out.println("saii");
                 ByteArrayOutputStream bos1 = new ByteArrayOutputStream(10);
                 ObjectOutputStream oos1 = new ObjectOutputStream(bos1);
       
                 oos1.writeUTF(pid);
                 oos1.writeUTF(myport);
                 oos1.writeUTF(nomeProduto);
                 oos1.flush();
                
                byte[] output = bos1.toByteArray();
                DatagramPacket messageOut1 = new DatagramPacket(output, output.length, InetAddress.getLocalHost(), Integer.parseInt(hostport));
                System.out.println("");    
                System.out.print("[UNICAST - SEND]");
                System.out.print(" Voce venceu o Leilao " + pid);
                System.out.print(" Proudut0  " + nomeProduto);
                        
                socket.send(messageOut1);
                System.out.println("");
            }
              
            catch (InterruptedException e) {  
            } catch (IOException ex) {  
            Logger.getLogger(Cronometro.class.getName()).log(Level.SEVERE, null, ex);
        }  
        }  
  
    
}
