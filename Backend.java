import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.net.SocketAddress;

import javafx.scene.chart.PieChart.Data;

public class Backend extends Thread {

    Archivo escritor;
    String nickname = "";
    int limit = 3;
    SocketAddress ip;
    DataInputStream dis;
    DataOutputStream dos;
    Socket s;

    //palabras
    String[] palabrasEscritas = new String[limit];
    String[] palabras = {"Palabra", "Moneda", "Dinero", "Pastel", "Bienvenido" , "huuuu"};
    String[] palabrasElegidas = new String[limit];
    int[] indexElegidos = new int[limit];

    public Backend(Socket s, DataInputStream dis, DataOutputStream dos){
        this.s = s;
        this.dis = dis;
        this.dos = dos;
        this.ip = s.getRemoteSocketAddress();
    }

    String[] generarPalabras(){
        NumeroAleatorio random = new NumeroAleatorio(palabras.length, true);

        for(int i = 0; i < this.palabrasElegidas.length; i++){
            indexElegidos[i] = random.get(); 
            this.palabrasElegidas[i] = palabras[indexElegidos[i]];
        }
        String[] rand = this.palabrasElegidas;
        String[] lista = randomizarPalabras(rand);
        return lista;
    }

    String[] randomizarPalabras(String[] lista){
        String[] templista = lista;

        for(int i = 0; i < lista.length; i++){

            char[] tempString = new char[templista[i].length()];

            char[] palabra = templista[i].toCharArray();

            NumeroAleatorio random = new NumeroAleatorio(lista[i].length(), true);

            for(int j = 0; j < templista[i].length(); j++){

                tempString[j] = palabra[random.get()];
            }

            templista[i] = String.copyValueOf(tempString);
        }

        return templista;
    }


    @Override
    public void run(){
        
        boolean nice = true;
        String received;
        String toreturn;
        String[] palabrastosend;

        while (true) {
            try {
                int palabrasmal = 0;
                if (this.nickname.equals("")){
                    dos.writeUTF("Bienvenido, Escribe tu nickname");
                    received = dis.readUTF();
                    this.nickname = received;
                    received = "";
                    System.out.println("Cliente: "+this.nickname+" se ha conectado");
                }

                palabrastosend = generarPalabras();
                nice = true;
                dos.writeUTF("Bienvenido "+this.nickname+" Escribe Exit para salir, A continuancion recibiras palabras \n Intenta adivinar cuales son \n Inserta un Si para continuar");
                received = dis.readUTF();

                if (received.equals("EXIT") || received.equals("Exit")) {
                    System.out.println("Cliente: "+this.nickname+" ha cerrado la sesion");
                    this.s.close();
                    break;
                }

                for(int i = 0; i < limit; i++){
                    dos.writeUTF("Palabra numero "+i+": "+palabrastosend[i]+"\n Escribe la palabra correctamente: ");
                    received = dis.readUTF();
                    palabrasEscritas[i] = received;
                    if (!received.equals(this.palabras[indexElegidos[i]])){
                        nice = false;
                        palabrasmal++;
                    }
                }

                if (nice) {
                    dos.writeUTF("Tienes todas las palabras correctas! Quieres volver a jugar?");
                } else {
                    dos.writeUTF("Has fallado en: "+palabrasmal+" palabras");
                }
                received = dis.readUTF();

                escritor = new Archivo();
                escritor.CrearArchivo(nickname, palabrasElegidas, palabrasEscritas, ip, limit);
                

                if (received.equals("EXIT") || received.equals("Exit")) {
                    System.out.println("Cliente: "+this.nickname+" ha cerrado la sesion");
                    this.s.close();
                    break;
                }
            }
            catch(Exception e) {
                System.out.println(e);
            }
        }
    }
}