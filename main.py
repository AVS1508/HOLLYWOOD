# Project Title    : Hollywood - A Movie Guessing Game
# Version          : 1.0
# Developed By     : Aditya Vikram Singh

import math
import random


class Hollywood:

    moviesList = [
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
        "FORREST GUMP",
    ]

    def SelectRandomMovie():
        return Hollywood.moviesList[math.floor(random.randint(0, 24))]

    def CreateDummyMovieAndEncode(movie):
        movieCopy = list(movie)
        for i, item in enumerate(movieCopy):
            if item not in ["A", "E", "I", "O", "U", " "]:
                item = "*"
        return "".join(movieCopy)

    def FillOrSlash(movie, current):
        chances = 9
        currentCopy = list(current)
        while chances > 0:
            while True:
                print("Guess a non-vowel alphabet: ")
                inp = input()
                if inp not in ["A", "E", "I", "O", "U", " "]:
                    break
            found = False
            for i, item in enumerate(movie):
                if inp == item:
                    currentCopy[i] = inp
                    found = True
            if movie == "".join(currentCopy):
                chances = 0
                print(movie)
                print("Congratulations! You guessed the movie.")
            elif found:
                print("Good guess!")
                print("".join(currentCopy))
            else:
                chances = chances - 1
                print("Character not present!")
                print("".join(currentCopy))
                print("Chances remaining: ", chances)
        if movie != "".join(currentCopy):
            print("Chances are over, sorry! Better luck next time!")
            print("The movie was: ", movie)


def main():
    print("Hello! Let's play a game of HOLLYWOOD - a movie guessing game! ")
    toggle = "Y"
    while toggle != "N":
        movie = Hollywood.SelectRandomMovie()
        dummy = Hollywood.CreateDummyMovieAndEncode(movie)
        print(dummy)
        Hollywood.FillOrSlash(movie, dummy)
        print("Do you want to play another game (Y/N)?")
        toggle = input()


if __name__ == "__main__":
    main()
