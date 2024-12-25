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
            List<Map.Entry< Integer, String>> packages = leggiFile(inputFile);
            int truckCapacity = 50; // Example capacity
            for (Map.Entry<Integer, String> entry : packages) {
                System.out.println(entry.getKey() + " - " + entry.getValue());
            }
            //allocatePackages(packages, truckCapacity);
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

    // Allocate packages to trucks using a greedy heuristic and DP for truck optimization 
    private static void allocatePackages(HashMap<String, Integer> packages, int capacity) {
        int truckCount = 0;

        while (!packages.isEmpty()) {
            truckCount++;
            System.out.println("Camion: " + truckCount);

            HashMap <String, Integer> truckLoad = caricaCamion(packages, capacity);
            int usedCapacity = 0;

            for (String pkg : truckLoad.keySet()) {
                System.out.println(pkg + " " + truckLoad.get(pkg));
                usedCapacity += truckLoad.get(pkg);
                packages.remove(pkg); // rimuovi pacchetti utilizzati
            }

            System.out.println("Capacità residua: " + (capacity - usedCapacity));
            System.out.println();
        }
    }

    // DP per il singolo furgone
    private static HashMap<String, Integer> caricaCamion(HashMap<String, Integer> packages, int capacity) {
        
        HashMap<String, Integer> result = new HashMap<>();
        String[] packageNames = packages.keySet().toArray(new String[0]);

        int[] weights = new int[packages.size()];
        int i = 0;

        for (String pkg : packageNames) {
            weights[i++] = packages.get(pkg);
        }

        // DP table initialization
        int[][] dp = new int[weights.length + 1][capacity + 1];

        for (i = 1; i <= weights.length; i++) {
            for (int w = 1; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - weights[i - 1]] + weights[i - 1]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Backtracking to find the chosen packages
        int w = capacity;
        for (i = weights.length; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                String pkgName = packageNames[i - 1];
                result.put(pkgName, packages.get(pkgName));
                w -= packages.get(pkgName);
            }
        }

        return result;
    }
}
