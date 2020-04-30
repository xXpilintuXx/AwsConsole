import java.rmi.Remote;
import java.rmi.RemoteException;

/*
	Declarar firma de métodos que serán sobrescritos
*/
public interface Interfaz extends Remote {
    String hola(String nombre) throws RemoteException;
    float Suma(float num1, float num2) throws RemoteException;
    float Resta(float num1, float num2) throws RemoteException;
    float Division(float num1, float num2) throws RemoteException;
    float Multiplicacion(float num1, float num2) throws RemoteException;
}