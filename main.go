package main

import (
	"bufio"
	"fmt"
	"math/rand"
	"os"
)

var moviesList = []string{"INTERSTELLAR", "ARMAGEDDON", "INCEPTION", "PLANET OF THE APES",
	"VAN HELSING", "IRON MAN", "PHANTOM THREAD", "LES MISERABLES", "THE REVENANT", "TITANIC", "THE DARK KNIGHT",
	"ROCKY", "JUSTICE LEAGUE", "STAR WARS", "THE GODFATHER", "WONDER WOMAN", "COCO", "THE AVENGERS", "AVATAR",
	"BRAVEHEART", "JURASSIC PARK", "MAN OF STEEL", "THE MATRIX", "LOGAN", "FORREST GUMP"}

func SelectRandomMovie() string {
	return moviesList[rand.Int()%25]
}

func CreateDummyMovieAndEncode(movie string) string {
	var movieCopy = []rune(movie)
	for i := 0; i < len(movieCopy); i++ {
		if !(movieCopy[i] == 'A' || movieCopy[i] == 'E' || movieCopy[i] == 'I' || movieCopy[i] == 'O' || movieCopy[i] == 'U' || movieCopy[i] == ' ') {
			movieCopy[i] = '*'
		}
	}
	return string(movieCopy)
}

func FillOrSlash(movie, current string) {
	chances := 9
	var input rune
	var currentCopy = []rune(current)
	for {
		for {
			fmt.Print("Guess a non-vowel alphabet: ")
			input = read()
			if !(input == 'A' || input == 'E' || input == 'I' || input == 'O' || input == 'U' || input == ' ') {
				break
			}
		}
		found := false
		for i := 0; i < len(movie); i++ {
			if input == rune(movie[i]) {
				currentCopy[i] = input
				found = true
			}
		}
		if movie == string(currentCopy) {
			chances = 0
			fmt.Println(movie)
			fmt.Println("Congratulations! You guessed the movie.")
		} else if !found {
			chances--
			fmt.Println("Character not present!")
			fmt.Println(string(currentCopy))
			fmt.Println("Chances remaining: ", chances)
		} else {
			fmt.Println("Good guess!")
			fmt.Println(string(currentCopy))
		}
		if chances <= 0 {
			break
		}
	}
}

func main() {
	fmt.Println("Hello! Let's play a game of HOLLYWOOD - a movie guessing game!")
	toggle := 'Y'
	for {
		var movie string = SelectRandomMovie()
		var dummy string = CreateDummyMovieAndEncode(movie)
		fmt.Println(dummy)
		FillOrSlash(movie, dummy)
		fmt.Println("Do you want to play another game (Y/N)?")
		toggle = read()
		if toggle == 'N' {
			break
		}
	}
}

func read() rune {
	reader := bufio.NewReader(os.Stdin)
	char, _, err := reader.ReadRune()
	if err != nil {
		fmt.Println(err)
	}
	return char
}
