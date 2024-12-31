/*
 * Razionale (poco):
 * Legge il file e lo salva in una mappatura (lista) che poi ordina
 * I dati passano per una funzione che non si interrompe fino a che la lista pacchetti non è vuota 
 * e chiama un'altra funzione che si occupa di allocare in maniera greedy i pacchi
 * 
 * 
 */
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Esercizio2 {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Error: Provide the input file.");
            return;
        }

        String inputFile = args[0];

        try {
            // Parse input and initialize hashmap
            List<Map.Entry< Integer, String>> pacchetti = leggiFile(inputFile);
            int c = 50; // capacita max
            
            for (Map.Entry<Integer, String> entry : pacchetti) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
            allocaPacchi(pacchetti, c);
        } catch (IOException e) {
            System.out.println("Error reading input file: " + e.getMessage());
        }
    }

    // Legge da file e salva in una lista di mappe
    private static List<Map.Entry<Integer, String>> leggiFile(String inputFile) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        
        List<Map.Entry<Integer, String>> pacchetti = new ArrayList<>();

        String riga = new String();
        
        while ( (riga = br.readLine()) != null) {      //legge riga per riga il file 

                String[] parts = riga.split("\\s+"); //separa le due parti
                pacchetti.add(new AbstractMap.SimpleEntry<>( Integer.parseInt(parts[1]), parts[0]) );  //salva nella lista di mappe
                
        }

        br.close(); 
        Collections.sort(pacchetti, Comparator.comparing(Map.Entry::getKey)); //ordino i pacchi per dimensione 
        return pacchetti;
    }

    private static void allocaPacchi(List<Map.Entry< Integer, String>> pacchetti, int C) {
        int contatore = 0;

        while (!pacchetti.isEmpty()) {
            contatore++;
            System.out.println("Camion: " + contatore);

            HashMap <String, Integer> carico = caricaCamion( pacchetti, C);
            int CUtilizzata = 0;

            for (String pkg : carico.keySet()) {
                System.out.println(pkg + " " + carico.get(pkg));
                CUtilizzata += carico.get(pkg);
                pacchetti.remove(pkg); // rimuovi pacchetti utilizzati
            }

            System.out.println("Capacità residua: " + (C - CUtilizzata));
            System.out.println();
        }
    }

    // DP per il singolo furgone
    private static HashMap<String, Integer> caricaCamion(List<Map.Entry<Integer, String>> pacchetti, int C) {
        int[] dp = new int[C + 1];
        boolean[][] selected = new boolean[pacchetti.size()][C + 1];
        
        // Build the DP table
        for (int i = 0; i < pacchetti.size(); i++) {
            int weight = pacchetti.get(i).getKey();
            for (int w = C; w >= weight; w--) {
                if (dp[w - weight] + weight > dp[w]) {
                    dp[w] = dp[w - weight] + weight;
                    selected[i][w] = true;
                }
            }
        }
        
    
        HashMap<String, Integer> carico = new HashMap<>();
        int w = C;
        for (int i = pacchetti.size() - 1; i >= 0 && w > 0; i--) {
            if (selected[i][w]) {
                Map.Entry<Integer, String> pkg = pacchetti.get(i);
                carico.put(pkg.getValue(), pkg.getKey());
                w -= pkg.getKey();
                pacchetti.remove(i);
            }
        }
        
        return carico;
    }
        
}
