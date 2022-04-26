import java.util.Base64;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

 
public class Imagen64 {
    
	public static void main(String args[]) throws IOException {
		byte src[] = Files.readAllBytes(Paths.get("C:/Users/Public/Documents/paisaje.jpg"));
		byte dst[] = Base64.getEncoder().encode(src);
		// Test System.out.println(new String(dst));
		byte trans[] = Base64.getDecoder().decode(dst);
		Files.write(Paths.get("C:/Users/Public/Documents/PaisajeR.png"), trans);
	}
}
