import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.SocketAddress;

public class Backend extends Thread {

    SocketAddress ip;
    Socket s;
    DataInputStream dis;
    DataOutputStream dos;

    public Backend(Socket s, DataInputStream dis, DataOutputStream dos){
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        this.ip = s.getRemoteSocketAddress();
    }




    @Override
    public void run() {
        String received;
        int response = 0;


        while(true){
            try {


                
                this.dos.writeUTF("Bievenido escribe Exit \n Escribe cualquier otra cosa para saber su valor : ");
                received = this.dis.readUTF();
                System.out.println(this.ip+"ha ejecutado codigo");

                if (received.equals("EXIT") || received.equals("Exit")) {
                    System.out.println("Cliente: "+this.ip+" ha cerrado la sesion");
                    this.s.close();
                    break;
                }

                char[] ch = received.toCharArray();

                for ( int i = 0; i < ch.length; i++){
                    response += ch[i];
                    System.out.println(ch[i]+" = "+(int)ch[i]+"\n"+(response-(int)ch[i])+" + "+(int)ch[i]+" = "+response);
                }

                dos.writeUTF("El valor total de "+received+" es: "+response+"\nPulsa cualquier tecla para continuar");
                this.dis.readUTF();
                response = 0;
                received = "";
            } catch (Exception e) 
            {
                System.out.println(e);
            }
        }
    }

}