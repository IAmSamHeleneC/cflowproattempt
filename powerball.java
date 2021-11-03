import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;

public class PowerBall {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = input.nextLine();

        // Empty powerball array
        int[] powerball = new int[5];
        int magicNum;

        System.out.print("\nWould you like to continue to the interactive portion, " + name + "? (y / n) ");
        String ans = input.nextLine();

        // Condition for the loop
        boolean willToContinue = true;
        if (ans.equals("n")) {
            willToContinue = false;
            System.out.println("Please return later to complete the survey.");
        }

        while (willToContinue) {
            System.out.println("We will gather some information from you to generate your winning Powerball numbers.");

            // ask questions
            System.out.print("\nWhat is the name of your favorite pet? ");
            String favPet = input.nextLine();

            System.out.print("\nWhat is the age of your favorite pet? ");
            int petAge = input.nextInt();
            input.nextLine();

            System.out.print("\nPlease enter your favorite number: ");
            int favNum = input.nextInt();
            input.nextLine();

            int quartNum = 0;
            System.out.print("\nDo you have a favorite quarterback? (yes / no): ");
            String favQuart = input.nextLine();
            if (favQuart.equals("yes")) {
                System.out.print("\nWhat is their jersey number? ");
                quartNum = input.nextInt();
                input.nextLine();
            } else {
                System.out.println("That's fine, we'll just use your favorite number.");
            }

            // Ask Questions
            System.out.print("\nWhat is the 2-digit model year of your car? ");
            int carYear = input.nextInt();
            input.nextLine();

            System.out.print("\nWhat is the first name of your favorite actress/actor? ");
            String favAct = input.nextLine();

            System.out.print("\nEnter a random number between 1 and 50: ");
            int randoNum = input.nextInt();
            input.nextLine();

            System.out.println("That is the end of the questionnaire!");
            System.out.println("Would you like to do the questions again or find out your answers? (redo / end) ");
            String endOfQuest = input.nextLine();

            // Build powerball numbers
            // get PB0 && Magic Number
            if (favQuart.equals("yes")) {
                powerball[0] = getPowerBallZero(quartNum, petAge, favNum);
                magicNum = getMagicNumber(quartNum);
            } else {
                powerball[0] = getPowerBallZeroNoQuart(petAge, favNum);
                magicNum = getMagicNumber(randoNum);
            }

            // getPB1
            powerball[1] = getPowerBallOne(favPet);

            // getPB2
            powerball[2] = getPowerBallTwo(randoNum, carYear);

            // getPB3
            powerball[3] = getPowerBallThree(randoNum);

            // getPB4
            powerball[4] = getPowerBallFour(favAct);

            if (endOfQuest.equals("redo")) {
                System.out.println("...preparing for next quiz...");
            } else {
                willToContinue = false;
                System.out.println("...building your results...");

                // Remove duplicates
                removeDups(powerball);
                // Sort array
                Arrays.sort(powerball);
                System.out.println("Lottery numbers: " + Arrays.toString(powerball));
                System.out.println("Magic ball: " + magicNum);
            }
        }
        input.close();
    }

    public static int getPowerBallZero(int quart, int pet, int lucky) {
        int total = quart + pet + lucky;
        while (total > 75) {
            total -= 75;
        }
        return total;
    }

    public static int getPowerBallZeroNoQuart(int pet, int lucky) {
        int total = pet + lucky;
        while (total > 75) {
            total -= 75;
        }
        return total;
    }

    public static int getPowerBallOne(String pet) {
        int charAtThree = (int) pet.charAt(2);
        while (charAtThree > 75) {
            charAtThree -= 75;
        }
        return charAtThree;
    }

    public static int getPowerBallTwo(int car, int num) {
        int total = car + num;
        while (total > 75) {
            total -= 75;
        }
        return total;
    }

    public static int getPowerBallThree(int favNum) {
        return favNum;
    }

    public static int getPowerBallFour(String favAct) {
        int first = (int) favAct.charAt(0);
        while (first > 75) {
            first -= 75;
        }
        return first;
    }

    public static int getMagicNumber(int num) {
        Random rand = new Random();
        int n = rand.nextInt(100) + 1;
        num *= n;
        while (num > 75) {
            num -= 75;
        }
        return num;
    }

    // Remove duplicates
    public static int[] removeDups(int[] array) {
        int[] newArray = new int[5];

        // Sort before removing duplicates
        Arrays.sort(array);
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == array[i + 1]) {
                array[i]++;
                newArray[i] = array[i];
            } else {
                newArray[i] = array[i];
            }
        }

        return newArray;
    }
}
