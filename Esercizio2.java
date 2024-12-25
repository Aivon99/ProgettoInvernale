import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Esercizio2 { //DP 
    public static void main(String[] args) {
        String nomeFile =  args[1];
        
    }
    public static String[] leggiFile(String path){
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String riga;
            while ((riga = br.readLine()) != null) { 
                

            }
            br.close();
            } 
        catch (FileNotFoundException e) { // eccezione File non Trovato
            System.out.println("File non Trovato, Stack segue: /n");
            e.printStackTrace();
            String[] vuoto = new String[2];  
            return  vuoto;
        }
              
        catch (IOException IOe){ // eccezione generica 
        
        }

    }
}
