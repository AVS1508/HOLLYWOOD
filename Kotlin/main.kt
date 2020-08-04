/*
    Project Title    : Hollywood - A Movie Guessing Game
    Version          : 1.0
    Developed By     : Aditya Vikram Singh
*/

class Hollywood {
    private var moviesList: Array<String> = arrayOf<String>("INTERSTELLAR", "ARMAGEDDON", "INCEPTION",
    "PLANET OF THE APES", "VAN HELSING", "IRON MAN", "PHANTOM THREAD", "LES MISERABLES", "THE REVENANT", "TITANIC", "THE DARK KNIGHT",
    "ROCKY", "JUSTICE LEAGUE", "STAR WARS", "THE GODFATHER", "WONDER WOMAN", "COCO", "THE AVENGERS", "AVATAR",
    "BRAVEHEART", "JURASSIC PARK", "MAN OF STEEL", "THE MATRIX", "LOGAN", "FORREST GUMP")

    fun SelectRandomMovie(): String {
        return moviesList[Math.floor(25 * Math.random()).toInt()]
    }

    fun CreateDummyMovieAndEncode(movie: String): String {
        var movieCopy: CharArray = movie.toCharArray()
        for (i in 0..movieCopy.size - 1) {
            if (!(movieCopy[i] == 'A' || movieCopy[i] == 'E' || movieCopy[i] == 'I' || movieCopy[i] == 'O' ||
            movieCopy[i] == 'U' || movieCopy[i] == ' ')) {
                movieCopy[i] = '*'
            }
        }
        return String(movieCopy)
    }

    fun FillOrSlash(movie: String, current: String) {
        var chances: Int = 9
        var input: Char
        var currentCopy: CharArray = current.toCharArray()
        do {
            do {
                print("Guess a non-vowel alphabet: ")
                input = readLine()!!.toCharArray().get(0)
            } while (input == 'A' || input == 'E' || input == 'I' || input == 'O' || input == 'U' || input == ' ')
            var found: Boolean = false
            for (i in 0..movie.length - 1) {
                if (input == movie.get(i)) {
                    currentCopy[i] = input
                    found = true
                }
            }
            if (movie == String(currentCopy)) {
                chances = 0
                println(movie)
                println("Congratulations! You guessed the movie.")
            } else if (!found) {
                --chances
                println("Character not present!")
                println(String(currentCopy))
                println("Chances remaining: " + chances)
            } else {
                println("Good guess!")
                println(String(currentCopy))
            }
        } while (chances > 0)
        if (movie != String(currentCopy)) {
            println("Chances are over, sorry! Better luck next time!")
            println("The movie was: " + movie)
        }
    }
}

fun main(args: Array<String>) {
    println("Hello! Let's play a game of HOLLYWOOD - a movie guessing game! ")
    var toggle: Char
    do {
        var HollywoodGame = Hollywood()
        var movie: String = HollywoodGame.SelectRandomMovie()
        var dummy: String = HollywoodGame.CreateDummyMovieAndEncode(movie)
        println(dummy)
        HollywoodGame.FillOrSlash(movie, dummy)

        println("Do you want to play another game (Y/N)?")
        toggle = readLine()!!.toCharArray().get(0)
    } while (toggle != 'N')
}
