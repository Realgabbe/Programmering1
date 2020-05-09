package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static Scanner input = new Scanner(System.in);
    private static ArrayList<String> ordlistan = new ArrayList<>();
    private static ArrayList<String> felGissningar = new ArrayList<>();
    private static String fyllnadsOrd = "";
    private static ArrayList<String> storShrek = new ArrayList<>();

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

    /**
     * Denna metod ankallar andra metoder för att spela spelet utifrån vad användaren skriver in för siffra; den tillåter
     * användaren att välja svårighetsgrad.
     *
     * @param svårighetsgrad är det värdet som användaren skickade in ifrån main metoden för att välja svårighetsgrad.
     */
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

    /**
     * Denna metod påbörjar spelet i spelläget lätt svårighet, alltså med lätta ord.
     */
    private static void lättSpel() {
        populeraLättOrdlista();
        spelKärna();
    }

    /**
     * Denna metod rensar orden från förra spelomgången och fyller på med lätta ord för det lätta spelläget.
     */
    private static void populeraLättOrdlista() {
        ordlistan.clear();
        ordlistan.add("SNOPP");
        ordlistan.add("kisS");
        ordlistan.add("baJs");
        ordlistan.add("rumpa");
    }

    /**
     * Denna metod påbörjar spelet i spelläget medelsvårighet, alltså med medelsvåra ord.
     */
    private static void mellanSpel() {
        populeraMellanOrdlista();
        spelKärna();
    }

    /**
     * Denna metod rensar orden från förra spelomgången och fyller på med svåra ord för det svåra spelläget.
     */
    private static void populeraMellanOrdlista() {
        ordlistan.clear();
        ordlistan.add("Snopparnas");
        ordlistan.add("Dvärgarnas");
        ordlistan.add("Härskare");
        ordlistan.add("Svordomar");
    }

    /**
     *  Denna metod påbörjar spelet i spelläget svår svårighet, alltså med svåra ord.
     */
    private static void svårSpel() {
        populeraSvårOrdlista();
        spelKärna();
    }

    /**
     * Denna metod rensar orden från förra spelomgången och fyller på med svåra ord för det svåra spelläget.
     */
    private static void populeraSvårOrdlista() {
        ordlistan.clear();
        ordlistan.add("Motorcykeldäcksekernippelnyckel");
        ordlistan.add("Hyponeurikostiskadiafragmakontravibrationer ");
        ordlistan.add("Nagellackborttagningsmedelsradioreklam");
        ordlistan.add("Förstamajdemonstrationstalstolsuppsättarlärling");
        ordlistan.add("Denitrifikationsbakteriekulturaliseringsutvecklingsförmåga");
        ordlistan.add("Flaggstångsknoppsputspoleringsmedelsflaskskorksetikettspåklistrare");
    }

    /**
     * Denna metod är den s.k. kärnan av hänga gubbe spelet, det är här som majoriteten av koden körs. Den består av
     * många mindre metoder som är deklarerade på andra ställen men kortfattat vad den gör är i princip detta: den
     * rensar ordet från föregående runda, den väljer ut ett slumpvalt ord efter vilken svårighetsgrad som spelaren väljer,
     * den låter dig gissa och jämför gissningen med ordet och kollar om det finns karaktärer/bokstäver som stämmer överens
     * med varandra, den ger dig feedback beroende på om din gissning var korrekt eller ej.
     */

    private static void spelKärna() {
        felGissningar.clear();
        String svar = hämtaSlumpvaltOrd();
        initfyllnadsOrd(svar);
        for (int försök = 0; försök <7; försök++) {
            System.out.println("\n" + "Gissa nu för fan!" + "\n");
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

    /**
     * Denna metod ör den som gör så att det står "*":or istället för att inte stå något alls innan använderen gissat.
     *
     * @param svar är det rätta ordet.
     */

    private static void initfyllnadsOrd(String svar) {
        fyllnadsOrd = "";
        for (int i = 0; i < svar.length(); i++) {
            fyllnadsOrd += "*";
        }
    }

    /**
     *Denna metod skickar ut statusmeddelande så att användaren ser hur det går för den.
     *
     * @param korrekt är en boolean som är sann när du har gissat rätt, det defineras i metodens spelKärna.
     * @param svar är det rätta ordet.
     * @param gissningChar är användarens gissning i form av en char.
     * @param försök är antalet gissningar kvar.
     */

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
        skapaShrek(försök);
        System.out.println(fyllnadsOrd);
        System.out.println(felGissningar.toString());
        System.out.println("Du har " + (6 - försök) + " försök kvar.");

    }

    private static void skapaShrek(int försök) {
        storShrek.clear();
        storShrek.add("⠟⠑⡄⠀⠀⠀⠀⠀⠀⠀ ⣀⣀⣤⣤⣤⣀⡀");
        storShrek.add("⠸⡇⠀⠿⡀⠀⠀⠀⣀⡴⢿⣿⣿⣿⣿⣿⣿⣿⣷⣦⡀");
        storShrek.add("⠀⠀⠀⠀⠑⢄⣠⠾⠁⣀⣄⡈⠙⣿⣿⣿⣿⣿⣿⣿⣿⣆");
        storShrek.add("⠀⠀⠀⠀⢀⡀⠁⠀⠀⠈⠙⠛⠂⠈⣿⣿⣿⣿⣿⠿⡿⢿⣆");
        storShrek.add("⠀⠀⠀⢀⡾⣁⣀⠀⠴⠂⠙⣗⡀⠀⢻⣿⣿⠭⢤⣴⣦⣤⣹⠀⠀⠀⢀⢴⣶⣆");
        storShrek.add("⠀⠀⢀⣾⣿⣿⣿⣷⣮⣽⣾⣿⣥⣴⣿⣿⡿⢂⠔⢚⡿⢿⣿⣦⣴⣾⠸⣼⡿");
        storShrek.add("⠀⢀⡞⠁⠙⠻⠿⠟⠉⠀⠛⢹⣿⣿⣿⣿⣿⣌⢤⣼⣿⣾⣿⡟⠉");
        storShrek.add("⠀⣾⣷⣶⠇⠀⠀⣤⣄⣀⡀⠈⠻⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⡇");
        storShrek.add("⠀⠉⠈⠉⠀⠀⢦⡈⢻⣿⣿⣿⣶⣶⣶⣶⣤⣽⡹⣿⣿⣿⣿⡇");
        storShrek.add("⠀⠉⠈⠉⠀⠀⢦⡈⢻⣿⣿⣿⣶⣶⣶⣶⣤⣽⡹⣿⣿⣿⣿⡇");
        storShrek.add("⠀⠀⠀⠀⠀⠀⠀⠉⠲⣽⡻⢿⣿⣿⣿⣿⣿⣿⣷⣜⣿⣿⣿⡇");
        storShrek.add("⠀⠀ ⠀⠀⠀⠀⠀⢸⣿⣿⣷⣶⣮⣭⣽⣿⣿⣿⣿⣿⣿⣿⠇");
        storShrek.add("⠀⠀⠀⠀⠀⠀⣀⣀⣈⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠇");
        storShrek.add("⠀⠀⠀⠀⠀⠀⢿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⣿⠃");

        for (int i = 0; i < (försök*2); i+=2) {
            System.out.println(storShrek.get(i));
            System.out.println(storShrek.get(i+1));
        }

    }

    /**
     *Denna metod väljer ut ett slumpvalt ord ifrån ArrayListen "ordlistan".
     * @return skickar tillbaka det slumpvalda ordet.
     */

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

