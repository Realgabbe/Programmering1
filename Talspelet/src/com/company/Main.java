package com.company;

import java.util.Scanner;

public class Main {

    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {

        boolean exit = false;

        do {
            System.out.println("Välkommen till mitt talspel! Skriv 1 för att fortsätta! **Skriv något annat tal för att stänga av programmet.**");

            int respons = IntInput();

            switch(respons) {
                case 1:
                    System.out.println("För att välja svårighetsgrad, för att välja in en svårighetsgrad, skriv in den tillhörande siffran. **Skriv något annat tal för att stänga av programmet.**");
                    System.out.println(" ");
                    System.out.println("(1) Lätt, 0-25 med 20 gissningar.");
                    System.out.println("(2) Medelsvårighet, 0-50 med 10 gissningar.");
                    System.out.println("(3) Svårt, 0-100 med 5 gissningar.");
                    System.out.println("(4) Egen svårighet, skriv tre tal där de första två är intervallet mellan vad talen ska randomizas, ex: 0 20 10 blir 0-20 och det tredje talet är antalet gissningar vilket blir 10 i detta fallet.");

                    int respons2 = IntInput();

                    switch(respons2) {
                        case 1:
                            System.out.println("Du valde svårighetsgrad (1): Lätt. du får 20 gissningar på dig att gissa på ett tal mellan 0-25.");
                            TalSpel(0, 25, 20);
                            break;
                        case 2:
                            System.out.println("Du valde svårighetsgrad (2): Medelsvårt. Du frå 10 gissningar på dig att gissa på ett tal mellan 0-50.");
                            TalSpel(0,50,10);
                            break;
                        case 3:
                            System.out.println("Du valde svårighetsgrad (3): Svårt. Du får 5 gissningar på dig att gissa på ett tal mellan 0-100.");
                            TalSpel(0,100,5);
                            break;

                        case 4:
                            System.out.println("Du valde svårighetsgrad (4): Din egna. Vänligen skriv tre tal enligt den föregående instruktionen.");

                            int Value1 = IntInput();
                            int Value2 = IntInput();
                            int Value3 = IntInput();

                            TalSpel(Value1, Value2, Value3);

                            boolean exit1 = false;

                            do{
                                System.out.println("Vill du spela igen på samma svårighetsgrad?" + "\n");
                                System.out.println("(1) Ja!");
                                System.out.println("(2) Nej.");

                                int respons3 = IntInput();

                                    switch(respons3) {
                                        case 1:
                                            TalSpel(Value1, Value2, Value3);
                                        case 2:
                                            System.out.println("Vill du spela igen på en annan svårighetsgrad?" + "\n");
                                            System.out.println("(1) Ja!");
                                            System.out.println("(2) Nej.");

                                            int respons4 = IntInput();

                                            switch(respons4) {
                                                case 1:
                                                    exit1 = true;
                                                    break;
                                                case 2:
                                                    exit = true;
                                                    break;
                                            }
                                    }



                        } while(exit1 == false);
                    }

            }


        }

        while (exit == false);
    }

    public static int IntInput(){

        do {
            try{
                int inmatning = input.nextInt();
                return inmatning;

            }
            catch(Exception e){
                System.out.println("Din input är i inkorrekt form");
                input.nextLine();
            }
        }

        while(true);

    }

    public static void TalSpel(int Min, int Max, int Guesses){

        int TheNumber = (int) (Math.random() * ((Max+1) - Min) + Min);

        for (int i = 0; i < Guesses; i++) {
            System.out.println("Skriv ett tal så säger jag om det inskrivna talet är lägre eller högre än mitt tal.");

            int Svar = IntInput();

            System.out.println("Du har " + (Guesses-i) + " gissningar kvar!" + "\n");

            if (Svar > Max){
                System.out.println("Amen hallå eller, tror du att du kan skriva in vad som helst? Skriv inte in tal som är större än " + Max + "!" + "\n");
            }

            else if (Svar < Min){
                System.out.println("Amen hallå eller, tror du att du kan skriva in vad som helst? Skriv inte in tal som är mindre än " + Min + "!" + "\n");
            }

            else if (Svar > TheNumber) {
                System.out.println("Det angivna talet är högre än mitt tal." + "\n");
            }

            else if (Svar < TheNumber) {
                System.out.println("Det angivna talet är lägre än mitt tal." + "\n");
            }

            else {
                System.out.println("Grattis du skrev in rätt tal! Det tog dig " + i + " gissningar vilket betyder att du hade " + (Guesses-i) + " gissningar kvar!" + "\n");
                break;
            }

        }

    }


}
