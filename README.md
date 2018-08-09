# The game of "Bisca" CI_TEST

This project was made for an assignment for the University. The premise was to create a simple AI with the concept of Game theory.


## The game

This is a traditional Portuguese game of cards, many versions of this game roam the planet. The original version is Italian.

![https://en.wikipedia.org/wiki/Minimax](https://upload.wikimedia.org/wikipedia/commons/e/ea/Italian_Playing_Cards.jpg)

The game is played using the Portuguese deck of 52 cards, removing all the 8s, 9s and 10s.

![Portuguese Deck](http://www.sobesapo.com/wp-content/uploads/2016/01/product.A4CARD462.h1.jpg)

### The suits
There are 4 suits with 10 cars each. The suits are as follow:
 * Diamonds
 * Spades
 * Hearts
 * Clubs

### Card values
Cards are worth points. The values are:
 * Ace - 11 points
 * 7 - 10 points
 * King - 4 points
 * Queen - 2 points
 * Jack - 3 points
 * All other cards - 0 points

Making a total of 120 points.

### Rules
1. The game is always played until the end of the deck.

2. The player with highest count of points win.

3. It is not mandatory to play the same suit the other player did.

4. The game is best played with 2 players but can be played with 3 or 4.

5. The higher value card wins the hand.

6. In case the cards played are worth 0 points, its numerical value is used. (For example 5 diamonds is smaller than 6 of diamonds).

7. If the first player plays a card from one suit and the other player plays a card from a different suit that is not a trump card, the first player wins the hand. (For example: first card played 2 of Diamonds second card played Ace of Hearths, the winner is the 2 of Diamonds).

7. Trump cards always win against regular cards.

8. In case multiple trump cards are played normal rules apply to said trump cards.


### How to play

1. Shuffle the cards.

2. Remove one card either from top or bottom of deck. This card is now the trump card and will be place on the table faced up with the remaining deck on top faced down.

3. Each player draws 3 cards from the deck.

4. A coin is thrown to pick the first player. (A similar random game can be used for example: Rock, Scissors, Paper).

5. The first player plays a card followed by the second player.

6. The player that wins the hand collects it and adds it to their point stack, then draws a card followed by the second player.

7. The game follows from there.

8. At the end the points are counted and a winner is picked.


## The project

To run this project you need to have the Java SDK installed.

Git clone the project or download it. With a terminal on the src folder run:

```bash
javac AppStart.java

java AppStart
```

## To Do

- [ ] Refactor code
- [ ] Comment the code
- [ ] Enhance or remake the AI
- [ ] Create a GUI

Since this was a project for the University I won't be playing around with it very much. I planned on putting it online for anyone to play with it. That said feel free to enhanced it, remake it or simply use it for fun.


This project was made in partnership with [Penélope Gonçalves](https://github.com/penelopeg)
