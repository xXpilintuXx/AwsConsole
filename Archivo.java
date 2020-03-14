import java.io.*;
import java.net.SocketAddress;

public class Archivo {
    
public Archivo(){

}


public void CrearArchivo(String nickname, String[] PalabrasElegidas, String[] PalabrasEscritas, SocketAddress ip , int limit){
    try
    {
    //Crear un objeto File se encarga de crear o abrir acceso a un archivo que se especifica en su constructor
    File archivo=new File(nickname+"Respuestas.txt");

    //Crear objeto FileWriter que sera el que nos ayude a escribir sobre archivo
    FileWriter escribir=new FileWriter(archivo,true);

    String cadena = "Reporte: "+nickname+" Ip :"+ip+"\n";
    cadena+="Palabras Seleccionadas : \n";
    for (int i = 0; i < limit; i++){
        cadena+="Palabra "+i+": "+PalabrasElegidas[i]+"\n";
    }
    cadena+="Palabras Escritas : \n";
    for (int i = 0; i < limit; i++){
        cadena+="Palabra"+i+": "+PalabrasEscritas[i]+"\n";
    }
    cadena += "----------------------------------------";




    //Escribimos en el archivo con el metodo write
    escribir.write(cadena);


    escribir.close();
}

//Si existe un problema al escribir cae aqui
catch(Exception e)
{
System.out.println("Error al escribir");
}

}
}