//Idea geniale genialissima: Dato un grafo si costruisce un metodo o serie di metodi, che tramite algoritmo 
// di Dijksra computa il percorso ottimo  
// fatto ciò si eliminano gli edge utilizzati dal grafo (uno alla volta) si calcola quindi il percorso minimo escludendoli, e si reitera la operazione n volte (dove n sono i percorsi minimi da trovare)
// Quale sia la fondamenta teorica di questa cosa non mi è chiaro ma l'ho sognato e mi pare avere senso <-- ricordo però 

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Arco{
    String destinazione; // Destinazione
    double peso;   // Costo

    Arco(String destinazione, double peso) {
        this.destinazione = destinazione;
        this.peso = peso;
    }
}

public class Esercizio3 {
    public static void main(String[] args) {
        Map<Integer, List<Arco>> grafo = new HashMap<>();


    }
    private Map<Integer, List<Arco>> letturaFile(String path){
        Map<Integer, List<Arco>> grafo = new HashMap<>();
        return grafo;
    }
}
