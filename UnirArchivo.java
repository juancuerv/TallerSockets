import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class UnirArchivo {

	public static void main(String[] args) throws IOException {
		String FileOut= "C:/Users/Public/Documents/PruebaFinal.txt";
		BufferedWriter bw=new BufferedWriter(new FileWriter(FileOut));
		for (int i = 1; i <= 4; i++) {
			String num=Integer.toString(i);
			
			String FileName="C:/Users/Public/Documents/Prueba"+num+".txt";
			
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

}


