# HOLLYWOOD

## About the Program

This is a game of HOLLYWOOD, in which the player will need to guess the movie after being provided its name with any consonants replaced by blanks. The program makes use of a constant-length string array of 20 movies out of which one is randomly picked and then a function encrypts it by hiding the consonants. The encrypted form is displayed on screen, and the user gets 9 chances (the number of letters in the word 'HOLLYWOOD') to guess the consonants. For every wrong consonant entered (i.e. a consonant that is absent in the movie name), chances gets deducted. For every right consonant entered (i.e. a consonant that is present one or more times in the movie name), chances remain same and the encrypted movie name is partially decrypted by revealing the location(s) of consonant entered. A vowel-entry exception is also handled by not decreasing chances but displaying a message urging user to enter valid input.
