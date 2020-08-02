
/**
 * Project Title    : Hollywood - A Movie Guessing Game
 * Version          : 1.0
 * Developed By     : Aditya Vikram Singh
*/

import java.util.*;

public class Hollywood {
    public static String[] moviesList = { "INTERSTELLAR", "ARMAGEDDON", "INCEPTION", "PLANET OF THE APES",
            "VAN HELSING", "IRON MAN", "PHANTOM THREAD", "LES MISERABLES", "THE REVENANT", "TITANIC", "THE DARK KNIGHT",
            "ROCKY", "JUSTICE LEAGUE", "STAR WARS", "THE GODFATHER", "WONDER WOMAN", "COCO", "THE AVENGERS", "AVATAR",
            "BRAVEHEART", "JURASSIC PARK", "MAN OF STEEL", "THE MATRIX", "LOGAN", "FORREST GUMP" };

    static Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello! Let's play a game of HOLLYWOOD - a movie guessing game! ");
        char toggle = 'Y';
        do {
            String movie = SelectRandomMovie();
            String dummy = CreateDummyMovieAndEncode(movie);
            System.out.println(dummy);
            FillOrSlash(movie, dummy);

            System.out.println("Do you want to play another game (Y/N)?");
            toggle = scan.nextLine().charAt(0);
        } while (toggle != 'N');
        scan.close();
    }

    public static String SelectRandomMovie() {
        return moviesList[(int) (25.0 * Math.random())];
    }

    public static String CreateDummyMovieAndEncode(String movie) {
        char[] movieCopy = movie.toCharArray();
        for (int i = 0; i < movieCopy.length; ++i) {
            if (!(movieCopy[i] == 'A' || movieCopy[i] == 'E' || movieCopy[i] == 'I' || movieCopy[i] == 'O'
                    || movieCopy[i] == 'U' || movieCopy[i] == ' ')) {
                movieCopy[i] = '*';
            }
        }
        return new String(movieCopy);
    }

    public static void FillOrSlash(String movie, String current) {
        int chances = 9;
        char input;
        char[] currentCopy = current.toCharArray();
        do {
            do {
                System.out.print("Guess a non-vowel alphabet: ");
                input = Character.toUpperCase(scan.nextLine().charAt(0));
            } while (input == 'A' || input == 'E' || input == 'I' || input == 'O' || input == 'U' || input == ' ');
            boolean found = false;
            for (int i = 0; i < movie.length(); ++i) {
                if (input == movie.charAt(i)) {
                    currentCopy[i] = input;
                    found = true;
                }
            }
            if (movie.equals(new String(currentCopy))) {
                chances = 0;
                System.out.println(movie);
                System.out.println("Congratulations! You guessed the movie.");
            } else if (!found) {
                --chances;
                System.out.println("Character not present!");
                System.out.println(new String(currentCopy));
                System.out.println("Chances remaining: " + chances);
            } else {
                System.out.println("Good guess!");
                System.out.println(new String(currentCopy));
            }
        } while (chances > 0);
        if (!movie.equals(new String(currentCopy))) {
            System.out.println("Chances are over, sorry! Better luck next time!");
            System.out.println("The movie was: " + movie);
        }
    }
}