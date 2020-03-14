import java.io.*;
import java.net.*;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
public class Servidor {
    Scanner teclado = new Scanner(System.in);
    String [] result = new String [1200];
    int port = 6666;
    char caracter;
    ServerSocket ss;
    Socket s;

    public Servidor(){
        System.out.println("inicie");
        try {
            ss = new ServerSocket(port);
        } catch(Exception e ){
            System.out.println(e);
        }
        
    }
    public void run(){
        while (true){
            s = null;
            try{
                s = ss.accept(); //Establece la conexion

                System.out.println("Cliente conectado: "+s);

                DataInputStream dis=new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                Thread t = new Backend(s, dis, dos);
                t.start();
            }catch(Exception e){
                System.out.println(e);
            }
        }
       
    }



    









    public static void main(String[] args){
      

     
      
      

      
		
            
      

   
    
			
			
	}
}