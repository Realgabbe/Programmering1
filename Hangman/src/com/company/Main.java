package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);
    private static ArrayList<String> ordlistan = new ArrayList<>();
    private static ArrayList<String> felGissningar = new ArrayList<>();
    private static String fyllnadsOrd = "";

    public static void main(String[] args) {

        skrivVälkomstMeddelande();

        boolean exit1 = false;

        do {
            System.out.println("Skriv (1) för att välja lätt svårighet.");
            System.out.println("Skriv (2) för att välja mellan svårighet.");
            System.out.println("Skriv (3) för att välja svår svårighet.");

            int svårighetsgrad = intInput();
            if (svårighetsgrad >= 1 && svårighetsgrad <= 3){
                startaSpel(svårighetsgrad);

                System.out.println("Vill du spela igen? Skriv in 'y' ifall du vill spela igen, allat annat räknas som ett nej.");
                String spelaIgen = input.nextLine();
                if(!(spelaIgen.toLowerCase().equals("y"))){
                    exit1 = true;
                }
            }

        }

        while(exit1==false);

        skrivHejdåMeddelande();
        
    }

    private static void startaSpel(int svårighetsgrad) {
        switch (svårighetsgrad){

            case 1:
                lättSpel();
                break;
            case 2:
                mellanSpel();
                break;
            case 3:
                svårSpel();
                break;
        }

    }

    private static void lättSpel() {
        populeraLättOrdlista();
        spelKärna();
    }

    private static void populeraLättOrdlista() {
        ordlistan.clear();
        ordlistan.add("SNOPP");
        ordlistan.add("kisS");
        ordlistan.add("baJs");
        ordlistan.add("rumpa");
    }

    private static void mellanSpel() {
        populeraMellanOrdlista();
        spelKärna();
    }

    private static void populeraMellanOrdlista() {
        ordlistan.clear();
        ordlistan.add("Snopparnas");
        ordlistan.add("Dvärgarnas");
        ordlistan.add("Härskare");
        ordlistan.add("Svordomar");
    }

    private static void svårSpel() {
        populeraSvårOrdlista();
        spelKärna();
    }

    private static void populeraSvårOrdlista() {

    }

    private static void spelKärna() {
        felGissningar.clear();
        String svar = hämtaSlumpvaltOrd();
        initfyllnadsOrd(svar);
        for (int försök = 0; försök <7; försök++) {
            System.out.println("Gissa nu för fan!");
            String gissning = input.nextLine();

            if(felGissningar.contains(gissning)){
                System.out.println("Du har redan gissat på den bokstaven");
                försök--;
                continue;
            }

            if (fyllnadsOrd.toLowerCase().equals(svar)){
                System.out.println("BRA FUCKING JOBBAT MANNEN, du gissade rätt.");
                break;
            }

            if(gissning.length() > 1){
                System.out.println("Fel inmatning eller inmatningslängd.");
                continue;

            } else if (gissning.length() == 0){
                System.out.println("Hjärnan din, använda du ska.");
                försök--;
                continue;
            }
            char gissningChar = gissning.charAt(0);

            if(svar.toLowerCase().contains(gissning)){
                uppdateraStatus(true, svar, gissningChar, försök);
                if(svar.toLowerCase().equals(fyllnadsOrd.toLowerCase())){
                    System.out.println("BRA FUCKING JOBBAT MANNEN, du gissade rätt.");
                    break;
                }
                försök--;
                continue;
            }
            felGissningar.add(gissning);
            uppdateraStatus(false, svar, gissningChar, försök);
        }
    }

    private static void initfyllnadsOrd(String svar) {
        fyllnadsOrd = "";
        for (int i = 0; i < svar.length(); i++) {
            fyllnadsOrd += "*";
        }
    }

    private static void uppdateraStatus(boolean korrekt, String svar, char gissningChar, int försök) {
        if(korrekt){
            char[] statusLista = fyllnadsOrd.toCharArray();
            for (int i = 0; i < svar.length(); i++) {
                if(svar.toLowerCase().charAt(i) == gissningChar){
                    statusLista[i] = gissningChar;
                }
            }
            fyllnadsOrd = String.copyValueOf(statusLista);
        }
        else {
        }
        System.out.println(fyllnadsOrd);
        System.out.println(felGissningar.toString());
        System.out.println("Du har " + (6 - försök) + " försök kvar.");

    }

    private static String hämtaSlumpvaltOrd() {
        int randomIndex = (int)(Math.random()*ordlistan.size());
        String slumpvaltOrd = ordlistan.get(randomIndex);
        slumpvaltOrd.toLowerCase();
        return slumpvaltOrd;
    }

    /**
     *Denna metod kollar så att din input(inmatning) är en int, om den inte är det så skriver den ut ett error meddelande och loopar tills du skriver in en int.
     * @return skickar tillbaka din input(inmatning).
     */
    public static int intInput(){

        do {
            try{
                int inmatning = input.nextInt();
                input.nextLine();
                return inmatning;

            }
            catch(Exception e){
                System.out.println("Din input är i inkorrekt form, det bör vara en siffra.");
                input.nextLine();
            }
        }

        while(true);

    }

    /**
     * Skriver ut ett välkomstmedddelande.
     */
    private static void skrivVälkomstMeddelande() {
        //Skriver välkomstmeddelande
        System.out.println("Välkommen!");
    }

    /**
     * Skriver ut ett avskedsmeddelande.
     */
    private static void skrivHejdåMeddelande() {
        //Skriver meddelande för då användaren slutar spelar.
        System.out.println("Hejdå!");
    }

}

