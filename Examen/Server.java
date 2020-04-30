import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * Server
 */
public class Server {
    Scanner teclado = new Scanner(System.in);
    final int port = 1100;
    ServerSocket ss;
    Socket s;
    
    public Server(){
        System.out.println("Iniciando motores");
        try {
            ss = new ServerSocket(port);
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void run(){
        while(true){
            s = null;
            try {
                s = ss.accept();

                System.out.println("Cliente Conectado: "+s);

                DataInputStream dis = new DataInputStream(s.getInputStream());
                DataOutputStream dos = new DataOutputStream(s.getOutputStream());

                Thread t = new Backend(s, dis, dos);
                t.start();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
    
    public static void main(String[] args) {
        Server ser = new Server();
        ser.run();
    }

}