import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
public class Cliente {
	private static final String IP = "127.0.0.1"; // Puedes cambiar a localhost
	private static final int PUERTO = 1100; //Si cambias aquí el puerto, recuerda cambiarlo en el servidor
	
    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(IP, PUERTO);
        System.out.println("Buscando el registro");
        Interfaz interfaz = (Interfaz) registry.lookup("Calculadora"); //Buscar en el registro...
        System.out.println("Ya lo encontre");
        String respuesta;
        float num1 = 0, num2 = 0;
		Scanner sc = new Scanner(System.in);
		int eleccion=1;
		do {
            System.out.println("Elige la operacion:\n1) Sumar\n2) Restar \n3) Multiplicar\n4) Dividir\n0) Salir\nElige : ");
            respuesta = sc.nextLine();
            if (respuesta.equals("0")){
                System.out.println("Hasta la proximaaaaaa");
                eleccion = -1; 
            } else {
                System.out.println("Inserte el primer numero");
                num1 = Integer.parseInt(sc.nextLine());
                System.out.println("Inserte el segundo numero numero");
                num2 = Integer.parseInt(sc.nextLine());
            }
                switch(respuesta){
                    case "1":
                        System.out.println(interfaz.Suma(num1, num2));
                    break;
                    case "2":
                        System.out.println(interfaz.Resta(num1, num2));
                    break;
                    case "3":
                        System.out.println(interfaz.Multiplicacion(num1, num2));
                    break;
                    case "4":
                        System.out.println(interfaz.Division(num1, num2));
                    break;
                    default:
                        System.out.println("Eliga una opcion bien cabron");
                    break;
                }
            System.out.println("Presiona enter para continuar");
            sc.nextLine();
        } while (eleccion != -1);
        sc.close();
    }
}
