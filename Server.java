import java.io.*;
import java.net.*;
import java.util.*;
import java.io.File;
import java.io.FileWriter;
public class Server {
    public static void main(String[] args){
        Scanner teclado = new Scanner(System.in);

     
      
      String [] result = new String [1200] ;

      char caracter;
		try{
			ServerSocket ss=new ServerSocket(6666);
			Socket s=ss.accept();//establishes connection
            DataInputStream dis=new DataInputStream(s.getInputStream());
            String  cadena=(String)dis.readUTF();
            
      for (int i = 0; i < cadena.length(); i++) {

        caracter = (char) cadena.charAt(i);

        if (caracter == ' ') {
          result [i] = Character.toString(caracter);

            //Imprimimos el espacio en blanco
            System.out.print(caracter);
            

        } else {

          

                 if (Character.isLowerCase(caracter)) {

                    // PASAR A MAYUSCULA
                    caracter = (char) (caracter - 'a' + 'A');
                    result [i] = Character.toString(caracter);
                    System.out.print(caracter);
                    
                }

            else {

                 if (Character.isUpperCase(caracter)) {

                    // PASAR A MINUSCULA
                    caracter = (char) (caracter + 'a' - 'A');
                    result [i] = Character.toString(caracter);
                    System.out.print(caracter);
                    
                }

            }

            
        }
        

    }

   
    try
    {
    //Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
    File archivo=new File("texto.txt");

    //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
    FileWriter escribir=new FileWriter(archivo,true);

    //Escribimos en el archivo con el metodo write
    for (int i = 0; i < cadena.length(); i++) {
       escribir.write(result[i]);
    }
   

    //Cerramos la conexion
    escribir.close();
    }

    //Si existe un problema al escribir cae aqui
    catch(Exception e)
    {
    System.out.println("Error al escribir");
    }

			
			ss.close();
		}catch(Exception e){System.out.println(e);}
	}
}