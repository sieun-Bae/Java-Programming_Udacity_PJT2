/**
 * all the codes programmed by sieun-Bae in 19/04/19
 * PJT in Udacity OOP course
 * only the project summary provided by Udacity.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class GuessMovie {

    private static int randomNum(int num) {
        int randomNum = (int) (Math.random() * num + 1);
        return randomNum;
    }

    public static void main(String[] args) throws FileNotFoundException {
        String[] movies = new String[50];
        int listIndex = 0;
        // read file
        try {
            File file = new File("movies.txt");
            Scanner scanner = new Scanner(file);

            int i = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                movies[i] = line;
                listIndex++;
                i++;
            }
        } catch (FileNotFoundException e) {
            System.out.println("There is no such file. Try again.");
        }

        // store random movie title in the string answer, then convert it to string array
        // variables to set answer
        int randomNumAnswer = randomNum(listIndex);
        String answer = movies[randomNumAnswer];
        String[] answerLetter = new String[answer.length()];

        // variables to trace the answer
        int wrongNum = 0;
        String[] wrongLetter = new String[11];
        String[] guessLetter = new String[answer.length()];


        for (int j = 0; j < answer.length(); j++) {
            String letter = answer.substring(j, j+1);
            answerLetter[j] = letter;
            guessLetter[j] = "_";
        }

        // get user input, and trace the answer, comparing answerLetter with guessLetter
        while (wrongNum <= 10) {
            // print current state
            System.out.print("You are guessing: ");
            for (int j = 0; j < answer.length(); j++) {
                System.out.print(guessLetter[j]);
            }
            System.out.println("\nYou have guessed (" + wrongNum + ") wrong letters: " + Arrays.toString(wrongLetter));
            Scanner userInput = new Scanner(System.in);
            String strInput = userInput.nextLine();

            // if the user's guessing is false, it goes 0
            int flag=0;

            for (int j = 0; j < answer.length(); j++) {
                if (strInput.equals(answerLetter[j])) {
                    guessLetter[j] = strInput;
                    flag++;
                }
            }
            if(flag==0) {
                wrongLetter[wrongNum] = strInput;
                wrongNum++;
            }

            // if the guess corrects in the appropriate condition, break
            if (Arrays.equals(guessLetter, answerLetter)){
                System.out.println("You win!");
                System.out.println("You have guessed \'"+answer+"\' correctly.");
                break;
            }

        }
        if (wrongNum == 11) {
            System.out.println("You lose... GG....");
        }


    }
}
