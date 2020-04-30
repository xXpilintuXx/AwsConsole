import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
public class Cliente {
	private static final String IP = "ec2-3-89-121-99.compute-1.amazonaws.com"; // Puedes cambiar a localhost
	private static final int PUERTO = 1100; //Si cambias aquí el puerto, recuerda cambiarlo en el servidor
	
    public static void main(String[] args) throws RemoteException, NotBoundException {
        System.out.print("Intentando Conexion\n");
        Registry registry = LocateRegistry.getRegistry(IP, PUERTO);
        System.out.print("Conexion completa, buscando en registro\n");
        Interfaz interfaz = (Interfaz) registry.lookup("Ejemplo"); //Buscar en el registro...
        System.out.print("encontrado el registro\n");
        String respuesta;
		Scanner sc = new Scanner(System.in);
		int eleccion=1;
		do {
            
            System.out.print("Creando Respuesta\n");
				respuesta = interfaz.hola("Victor");
	                  

                System.out.println("Respuesta => " + respuesta);
                System.out.println("Presiona ENTER para continuar");
                sc.nextLine();
            
        } while (eleccion != -1);
    }
}
