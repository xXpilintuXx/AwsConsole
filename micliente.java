import java.io.*;  
import java.net.*;  
import java.util.Scanner; 
    
public class micliente {  
    
	public static void main(String[] args) {  
		try{      	
			//Socket s=new Socket("34.229.211.1",6666);  
			Socket s=new Socket("localhost",6666);
			
			DataOutputStream dout=new DataOutputStream(s.getOutputStream()); 

			System.out.print("Enter a string : ");
        
			Scanner scanner = new Scanner(System. in);
        
			String inputString = scanner. nextLine(); 
			
			dout.writeUTF(inputString); 
			
			System.out.println("Mande el mensaje\n");	
			
			dout.flush();  
			
			dout.close();  
			
			s.close();  
			
		}catch(Exception e){System.out.println(e);}  
	}  
}  