import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

public class ClienteTCP {

  public final static int SOCKET_PORT = 5000;      
  public final static String SERVER = "192.168.1.2";  
  public final static String
       FILE_TO_RECEIVED = "/home/juan0103/Enfasis3";  

  public final static int FILE_SIZE = 6022386; 

  public static void main (String [] args ) throws IOException {
    ClienteTCP cliente = new ClienteTCP();
    //cliente.descargaTxt();
    //cliente.UnirArchivo();
    cliente.descargaTxtPartes();
    
  }
   
  public void descargaTxt() throws IOException{
    int bytesRead;
    int current = 0;
    FileOutputStream fos = null;
    BufferedOutputStream bos = null;
    Socket sock = null;
    
    try {
      sock = new Socket(SERVER, SOCKET_PORT);
      System.out.println("Conectando...");

      // receive file
      byte [] mybytearray  = new byte [FILE_SIZE];
      InputStream is = sock.getInputStream();
      fos = new FileOutputStream(FILE_TO_RECEIVED);
      bos = new BufferedOutputStream(fos);
      bytesRead = is.read(mybytearray,0,mybytearray.length);
      current = bytesRead;

      do {
         bytesRead =
            is.read(mybytearray, current, (mybytearray.length-current));
         if(bytesRead >= 0) current += bytesRead;
      } while(bytesRead > -1);

      bos.write(mybytearray, 0 , current);
      bos.flush();
      System.out.println("Archivo " + FILE_TO_RECEIVED
          + " descargado (" + current + " bytes leidos)");
    }
    finally {
      if (fos != null) fos.close();
      if (bos != null) bos.close();
      if (sock != null) sock.close();
    }
  }

  public void UnirArchivo() throws IOException{
    String FileOut= FILE_TO_RECEIVED;
		BufferedWriter bw=new BufferedWriter(new FileWriter(FileOut));
		for (int i = 1; i <= 1000; i++) {
			String num=Integer.toString(i);
			
			String FileName="/home/juan0103/Enfasis3/Prueba"+num+".txt";
			
			File file=new File(FileName);
			if(file.exists()) {
				System.out.println(FileName);
				BufferedReader br = new BufferedReader(new FileReader(file));
				String line;
				while((line=br.readLine())!=null) {
					bw.write(line);
					bw.newLine();
				
				}
			br.close();
			}
		}
		bw.close();
	}

  public void descargaImagen() throws IOException{
    int bytesRead;
    int current = 0;
    FileOutputStream fos = null;
    BufferedOutputStream bos = null;
    Socket sock = null;
    
    try {
      sock = new Socket(SERVER, SOCKET_PORT);
      System.out.println("Conectando...");

      // receive file
      byte [] mybytearray  = new byte [FILE_SIZE];
      InputStream is = sock.getInputStream();
      fos = new FileOutputStream(FILE_TO_RECEIVED);
      bos = new BufferedOutputStream(fos);
      bytesRead = is.read(mybytearray,0,mybytearray.length);
      current = bytesRead;

      do {
         bytesRead =
            is.read(mybytearray, current, (mybytearray.length-current));
         if(bytesRead >= 0) current += bytesRead;
      } while(bytesRead > -1);

      bos.write(mybytearray, 0 , current);
      bos.flush();
      System.out.println("Archivo " + FILE_TO_RECEIVED
          + " descargado (" + current + " bytes leidos)");
      //DECODIFICANDO LA IMAGEN
      byte trans[] = Base64.getDecoder().decode(mybytearray);
      Files.write(Paths.get("C:/Users/Public/Documents/PaisajeR.png"), trans);          
    }
    finally {
      
      if (fos != null) fos.close();
      if (bos != null) bos.close();
      if (sock != null) sock.close();
    }
  }

  public void descargaTxtPartes() throws IOException{
    int bytesRead;
    int current = 0;
    FileOutputStream fos = null;
    BufferedOutputStream bos = null;
    Socket sock = null;
    
    try {
    int i=1;
    String FileName="/home/juan0103/Enfasis3/Prueba"+i+".txt";
    
      while(i<=4){
      
      sock = new Socket(SERVER, SOCKET_PORT);
      System.out.println("Conectando...");

      // receive file
      byte [] mybytearray  = new byte [FILE_SIZE];
      InputStream is = sock.getInputStream();
      fos = new FileOutputStream(FileName);
      bos = new BufferedOutputStream(fos);
      bytesRead = is.read(mybytearray,0,mybytearray.length);
      current = bytesRead;

      do {
         bytesRead =
            is.read(mybytearray, current, (mybytearray.length-current));
         if(bytesRead >= 0) current += bytesRead;
      } while(bytesRead > -1);

      bos.write(mybytearray, 0 , current);
      bos.flush();
      System.out.println("Archivo " + FileName
          + " descargado (" + current + " bytes leidos)");
      i++;
      FileName="/home/juan0103/Enfasis3/Prueba"+i+".txt";
      
    }
    }
    finally {
      if (fos != null) fos.close();
      if (bos != null) bos.close();
      if (sock != null) sock.close();
    }
  }

}