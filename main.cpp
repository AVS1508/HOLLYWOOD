/*
    Project Title  : HOLLYWOOD - A MOVIE GUESSING GAME
    Version        : 1.0
    Developed By   : Aditya Vikram Singh
*/

//Pre-processor directives to include necessary libraries.
#include <conio.h>
#include <ctype.h>
#include <iomanip.h>
#include <iostream.h>
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

//FUNCTION PROTOTYPES
void SelectRandomMovie(char M[20]);                         //TO RANDOMLY SELECT A MOVIE FROM THE LIST OF MOVIES
void CreateDummyMovieAndEncode(char A[20], char Dummy[20]); //TO CREATE A COPY OF THE MOVIE AND ENCODE IT TO ONLY SHOW VOWELS
void FillOrSlash(char Input, char A[20], char Dummy[20]);   //TO ACCEPT AND CHECK USER'S GUESSES AND "FILL" THE COPY OF THE MOVIE FOR THE CORRECT GUESS OR DECREMENT("SLASH") THE CHANCES REMAINING

//GLOBAL ARRAY: LIST OF 25 MOVIES WITH A CHARACTER LIMIT OF 20
char MList[25][20] =
    {
        "INTERSTELLAR",
        "ARMAGEDDON",
        "INCEPTION",
        "PLANET OF THE APES",
        "VAN HELSING",
        "IRON MAN",
        "PHANTOM THREAD",
        "LES MISERABLES",
        "THE REVENANT",
        "TITANIC",
        "THE DARK KNIGHT",
        "ROCKY",
        "JUSTICE LEAGUE",
        "STAR WARS",
        "THE GODFATHER",
        "WONDER WOMAN",
        "COCO",
        "THE AVENGERS",
        "AVATAR",
        "BRAVEHEART",
        "JURASSIC PARK",
        "MAN OF STEEL",
        "THE MATRIX",
        "LOGAN",
        "FORREST GUMP"};

void main()
{
    clrscr();

    cout << "Hello! Let's play a game of HOLLYWOOD - a movie guessing game! " << endl;
    char toggle = 'Y', input;

    do
    {
        char Movie[20];
        char Dummy[20] = "###################";

        SelectRandomMovie(Movie);
        CreateDummyMovieAndEncode(Movie, Dummy);
        cout << Dummy << endl;
        FillOrSlash(input, Movie, Dummy);

        cout << "Do you want to play another game (Y/N)?";
        cin >> toggle;
        toggle = toupper(toggle);
    } while (toggle != 'N');

    cout << "Good Game! Well Played!" << endl;
    getch();
}

void SelectRandomMovie(char M[20])
{
    randomize();
    int Choice = random(25);

    for (int I = 0; I < 20 && MList[Choice][I] != '\0'; I++)
    {
        M[I] = MList[Choice][I]; //TO COPY A RANDOMLY SELECTED MOVIE
        M[I + 1] = '\0';         //TO MAKE THE MOVIE A STRING INSTEAD OF A CHARACTER ARRAY
    }
}

void CreateDummyMovieAndEncode(char A[20], char Dummy[20])
{
    int I = 0;
    for (; I < 20 && A[I] != '\0'; I++)
    {
        if (A[I] == 'A' || A[I] == 'E' || A[I] == 'I' || A[I] == 'O' || A[I] == 'U')
            Dummy[I] = A[I]; //ONLY KEEP VOWEL CHARACTERS VISIBLE
        else if (A[I] == ' ')
            Dummy[I] = ' '; //KEEP SPACE AS A SPACE CHARACTER ONLY
        else
            Dummy[I] = '*'; //REPLACE NON-VOWEL CHARACTERS WITH AN ASTERIX
    }
    Dummy[I] = '\0'; //TO KEEP THE MOVIE A STRING INSTEAD OF A CHARACTER ARRAY
}

void FillOrSlash(char Input, char A[20], char Dummy[20])
{
    int Chances = 9;
    do
    {
        //TO ENSURE ENTRY OF ONLY NON-VOWEL CHARACTERS
        do
        {
            cout << "Guess a non-vowel character:";
            cin >> Input;
            Input = toupper(Input);
        } while (Input == 'A' || Input == 'E' || Input == 'I' || Input == 'O' || Input == 'U');
        int Found = 0;
        for (int K = 0; K < 20 && A[K] != '\0'; K++) //LOOP FOR LINEARLY SEARCHING THE CHARACTER GUESS
            if (Input == A[K])                       //IF 1 OR MORE ITERATIONS FOUND, THE GUESS IS CORRECT ELSE INCORRECT
            {
                Dummy[K] = A[K]; //FILL THE CORRECTLY GUESSED CHARACTER IN THE COPY OF MOVIE
                Found = 1;
            }

        if (strcmp(A, Dummy) == 0) //IMPLIES THE USER HAS CORRECTLY GUESSED THE WHOLE MOVIE
        {
            Chances = 0; //TO ESCAPE THE GUESSING LOOP
            cout << Dummy << endl
                 << "CONGRATULATIONS, YOU GUESSED THE MOVIE RIGHT!!" << endl;
        }
        else if (!Found) //IMPLIES USER'S GUESSED CHARACTER WAS NOT PRESENT
        {
            Chances--; //DECREMENT CHANCES FOR INCORRECT GUESSES
            cout << "Character not present!" << endl
                 << Dummy << endl;
            cout << "Chances Remaining: " << Chances << endl;
        }
        else
            cout << "Good guess!" << endl
                 << Dummy << endl; //IMPLIES USER GUESSED CORRECTLY
    } while (Chances);             //WILL RUN TILL EITHER USER GUESSES WHOLE MOVIE OR RUNS OUT OF CHANCES
    if (strcmp(A, Dummy) != 0)     //WILL RUN ONLY IF USER DID NOT GUESS THE WHOLE MOVIE
    {
        cout << "Chances are over, sorry! Better luck next time!" << endl;
        cout << "The movie was: " << A << endl;
    }
}