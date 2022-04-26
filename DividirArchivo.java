import java.io.*;
import java.util.Scanner;


public class DividirArchivo {
 
public static void main(String args[]) {
    DividirArchivo div=  new DividirArchivo();
    String Archivo= "C:/Users/Public/Documents/Prueba.txt";
    div.NumeroDiv(Archivo);
    div.Division(Archivo);
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

    public void Division(String Archivo){
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
    }
}
