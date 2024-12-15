/*
Formato linea txt file> <linea> ::= <nomefile><spazio><dimensione> 

Il programma riceve tre parametri da linea di comando:
• il nome del file da cui acquisire i dati;
• un percorso del file system (nello stesso formato di <nomefile>);
• un'estensione (nello stesso formato di <nome>).

E deve visualizzare in output la dimensione totale dei file che si trovano nel ramo del file system identificato
dal percorso specificato, il cui nome termina con l'estensione passata come parametro, preceduta dal
carattere ‘.’.

Stampare i risultati ottenuti utilizzando due implementazioni distinte basate su strutture dati diverse (che,
ovviamente, devono risultare uguali) prefissando i risultati dalle stringhe ‘Totale 1:’ e ‘Totale 2:’.

Devono essere gestiti i seguenti errori:
• file di input non valido;
• parametri di input mancanti o malformati;
• percorso o estensione non trovati.

 */

//Divisione: un metodo per la lettura del file, una classe per ciascun metodo di conteggio, un main 

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class Esercizio1{
    public static void main(String args[]){
        try{
        String nomeFile =  args[1];
        String percorsoFile = args[2];
        String estensione = args[3];

        int Contatore1 = Metodo1(nomeFile, percorsoFile, estensione);
        int Contatore2 = Metodo1(nomeFile, percorsoFile, estensione);
        
        System.out.println("Totale 1: "+Contatore1);
        System.out.println("Totale 2: "+Contatore2);
        }
        finally{
            System.out.println("GoodBye");
        }
    }

    public static int Metodo1(String nomeFile, String percorsoFile ,String estensione){ //legge una riga alla volta e se la rica rispetta le condizioni aggiunge il valore numerico al contatore
        int contatore = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(nomeFile))) {
            String riga;
            while ((riga = br.readLine()) != null) { 
                String[] rigaSeparata = separaRiga(riga);
                if(rigaSeparata[0] == percorsoFile & rigaSeparata[1] == estensione){
                    contatore += Integer.parseInt(rigaSeparata[2]);
                }
            } 
            br.close();
        } 
        catch (FileNotFoundException e) { // eccezione File non Trovato
            System.out.println("File non Trovato, Stack segue: /n");
            e.printStackTrace(); 
            return -1;
        }

        catch (IOException IOe){ // eccezione generica 
        
        }
        return contatore;
    }
 
 
    public static String[] separaRiga(String riga){  
        //Partendo dal fondo so che devono esserci numeri, proseguo dal fondo e al primo char non numerico segno la posizione,
        // identica cosa per il . e / : dopodiche uso queste posizioni per estrarre ciò che mi serve   
        char[] caratteri = riga.toCharArray();
        int cont = caratteri.length ;
        int[] posizioni;
        boolean cond = true;
        
        while(cond & cont > 0){
            if(!caratteri[cont].isDigit()  ) System.out.println("ya did something wrong mate");


        }


        return new String[] {"pappa" ,"pappa" };
    
    }


}