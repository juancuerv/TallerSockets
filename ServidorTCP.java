import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Scanner;

  

public class ServidorTCP {

  public final static int SOCKET_PORT = 5000;  
  public final static String FILE_TO_SEND = "C:/Users/Public/Documents/Prueba.txt";
  

  public static void main (String [] args ) throws IOException {
    final ServidorTCP server = new ServidorTCP();
    server.envioTxt();
    String[] archivos = server.Division(FILE_TO_SEND);
    server.envioTxtPartes(archivos);
  }

  
  public void envioTxt() throws IOException{
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream os = null;
    ServerSocket servsock = null;
    Socket sock = null;
    try {
      servsock = new ServerSocket(SOCKET_PORT);
      while (true) {
        System.out.println("Esperando...");
        try {
          sock = servsock.accept();
          System.out.println("Conexion aceptada : " + sock);
          File myFile = new File (FILE_TO_SEND);
                   
          byte [] mybytearray  = new byte [(int)myFile.length()];
          fis = new FileInputStream(myFile);
          bis = new BufferedInputStream(fis);
          bis.read(mybytearray,0,mybytearray.length);
          os = sock.getOutputStream();
          System.out.println("Enviando " + FILE_TO_SEND + "(" + mybytearray.length + " bytes)");
          os.write(mybytearray,0,mybytearray.length);
          os.flush();
          System.out.println("Recibido.");
        }
        finally {
          if (bis != null) bis.close();
          if (os != null) os.close();
          if (sock!=null) sock.close();
        }
      }
    }
    finally {
      if (servsock != null) servsock.close();
    }
    }

  public void envioTxtPartes(String[] archivos) throws IOException{
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream os = null;
    ServerSocket servsock = null;
    Socket sock = null;
    int i=0;
    
    try {
      servsock = new ServerSocket(SOCKET_PORT);
      while (true && (i<=archivos.length)) {
        System.out.println("Esperando...");
        try {
          sock = servsock.accept();
          System.out.println("Conexion aceptada : " + sock);
          File myFile = new File (archivos[i]);
                   
          byte [] mybytearray  = new byte [(int)myFile.length()];
          fis = new FileInputStream(myFile);
          bis = new BufferedInputStream(fis);
          bis.read(mybytearray,0,mybytearray.length);
          os = sock.getOutputStream();
          System.out.println("Enviando " + archivos[i] + "(" + mybytearray.length + " bytes)");
          os.write(mybytearray,0,mybytearray.length);
          os.flush();
          System.out.println("Recibido.");
          i++;
        }
        finally {
          if (bis != null) bis.close();
          if (os != null) os.close();
          if (sock!=null) sock.close();
        }
      }
      
    }
    finally {
      if (servsock != null) servsock.close();
    }
  }
  
  public void NumeroDiv(String Archivo){
    try {
        FileReader read = new FileReader(Archivo);     
        BufferedReader br = new BufferedReader(read);    
        
        int rownum = 1;            
        while ((br.readLine()) != null) {
            rownum ++;
        }            
        System.out.println("Número de filas del archivo txt: "+ rownum);      
        br.close();      
        } 
        catch (FileNotFoundException e) {
            e.printStackTrace();    
        } 
        catch (IOException e) {
            e.printStackTrace();        
        }
    
}

  public String[] Division(String Archivo){
      NumeroDiv(FILE_TO_SEND);
      String [] archivos =  new String[1000];
      char Punto = '.';
      int P=0;
      
      for (int i=0; i<Archivo.length();i++){     
                
          if (Character.compare(Punto, Archivo.charAt(i))==0){             
              P=i;                
          }
      }

      try {
      FileReader read = new FileReader(Archivo);
      BufferedReader br = new BufferedReader(read);
      String row;
      
      archivos[0] = "";
      int div = 0;
      int rownum = 1;
      int fileNo = 1;
      System.out.println("Ingrese el número de filas que tendrá las partes de los archivos: ");
      Scanner sc = new Scanner (System.in); //Creación de un objeto Scanner
      div = sc.nextInt();
      sc.close();
      FileWriter fw = new FileWriter(Archivo.substring(0, P)+fileNo +".txt");

      while ((row = br.readLine()) != null) 
      {
          rownum ++;
          fw.append(row + "\r\n");
          if((rownum / div) > (fileNo - 1))
          {
              fw.close();
              fileNo ++ ;
              fw = new FileWriter(Archivo.substring(0, P)+fileNo +".txt");
              archivos[fileNo] = Archivo.substring(0, P)+fileNo +".txt";
              
          }
      }

      fw.close();
      br.close();
      System.out.println("Número de filas del archivo: "+rownum+"; Número de archivos creados="+fileNo);
      
      
      } catch (FileNotFoundException e) {
          e.printStackTrace();
          } catch (IOException e) {
              e.printStackTrace();
          }
      return archivos;
  }

  public void envioImagen() throws IOException{
    FileInputStream fis = null;
    BufferedInputStream bis = null;
    OutputStream os = null;
    ServerSocket servsock = null;
    Socket sock = null;
    try {
      servsock = new ServerSocket(SOCKET_PORT);
      while (true) {
        System.out.println("Esperando...");
        try {
          sock = servsock.accept();
          System.out.println("Conexion aceptada : " + sock);
          File myFile = new File (FILE_TO_SEND);
          //CODIFICANDO EN BASE64 LA IMAGEN
          byte src[] = Files.readAllBytes(Paths.get("C:/Users/Public/Documents/paisaje.jpg"));
		      byte mybytearray[] = Base64.getEncoder().encode(src);    

          fis = new FileInputStream(myFile);
          bis = new BufferedInputStream(fis);
          bis.read(mybytearray,0,mybytearray.length);
          os = sock.getOutputStream();
          System.out.println("Enviando " + FILE_TO_SEND + "(" + mybytearray.length + " bytes)");
          os.write(mybytearray,0,mybytearray.length);
          os.flush();
          System.out.println("Recibido.");
        }
        finally {
          if (bis != null) bis.close();
          if (os != null) os.close();
          if (sock!=null) sock.close();
        }
      }
    }
    finally {
      if (servsock != null) servsock.close();
    }
    }
  
}
