import java.util.Collections;
import java.util.Random;
import java.util.Stack;

/**
 * Game
 */
public class Game {
    private static Stack<Card> _deck;
    private static Card _trump;
    private Player _p1, _p2;
    private static final Character[] _suits = { 'D', 'C', 'H', 'S' };
    private static final String[] _pointlessCards = { "2", "3", "4", "5", "6" };

    public Game(Player p1, Player p2) {
        this._startDeck();
        this._p1 = p1;
        this._p2 = p2;
    }

    public Player play() {
        int playNr = 0;
        Card playedFirst, playedSecond;
        int result;
        this._trump = _deck.firstElement();
        this._chooseStarter();
        this._giveCards();
        System.out.println("----------------------------");
        System.out.println("*   Trump card is: " + this._trump + "   *");
        System.out.println("----------------------------");
        while (this._p1.getHand().size() != 0 && this._p2.getHand().size() != 0) {
            playNr++;
            this._printPlay(this._p1);
            playedFirst = this._p1.play();
            this._printPlay(this._p2);
            playedSecond = this._p2.play(playedFirst);
            result = this._evaluatePlay(playedFirst, playedSecond);
            if (this._p1 instanceof Pc) {
                ((Pc) this._p1).seePlayedCard(playedSecond);
            }
            if (result == 1) {
                this._printRoundWinner(this._p1, playedFirst, playedSecond);
                this._p1.addToPoints(playedFirst, playedSecond);
                this._chooseNextStart(this._p1, this._p2);
            } else {
                this._printRoundWinner(this._p2, playedSecond, playedFirst);
                this._p2.addToPoints(playedFirst, playedSecond);
                this._chooseNextStart(this._p2, this._p1);
            }

            if (!_deck.isEmpty()) {
                this._p1.draw(_deck.pop());
                this._p2.draw(_deck.pop());
            }
        }
        if (this._p1.countPoints() > this._p2.countPoints()) {
            return this._p1;
        }

        return this._p2;
    }

    public static Card getTrump() {
        return _trump;
    }

    public static Stack<Card> getDeck() {
        return _deck;
    }

    private void _printPlay(Player p) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println("*   Now playing: " + p + "   *");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    private void _printRoundWinner(Player p, Card c1, Card c2) {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println("    ROUND WINNER: " + p);
        System.out.println("    " + c1 + " > " + c2);
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");

    }

    private int _evaluatePlay(Card c1, Card c2) {
        int result;
        if (c1.getSuit() != c2.getSuit()) {
            if (c1.getSuit().compareTo(this._trump.getSuit()) != 0
                    && c2.getSuit().compareTo(this._trump.getSuit()) != 0) {
                result = 1;
            } else if (c1.getSuit().compareTo(this._trump.getSuit()) == 0) {
                result = 1;
            } else {
                result = -1;
            }
        } else {
            if (c1.getValue() > c2.getValue()) {
                result = 1;
            } else if (c1.getValue() < c2.getValue()) {
                result = -1;
            } else {
                if (c1.getIdentity().compareTo(c2.getIdentity()) > 0) {
                    result = 1;
                }
                else {
                    result = -1;
                }
            }
        }

        //player1 = 1
        //player2 != 1
        return result;
    }

    private void _chooseStarter() {
        int coin = new Random().nextInt(2);
        Player temp;
        if (coin != 0) {
            temp = this._p1;
            this._p1 = this._p2;
            this._p2 = temp;
        }
    }

    private void _chooseNextStart(Player winner, Player loser) {
        this._p1 = winner;
        this._p2 = loser;
    }

    private void _giveCards() {
        for (int i = 0; i < 3; i++) {
            this._p1.draw(_deck.pop());
            this._p2.draw(_deck.pop());
        }
    }

    private void _startDeck() {
        _deck = new Stack<Card>();
        for (Character suit : _suits) {
            for (Integer i = 1; i < 11; i++) {
                if (i != 1 && i <= 7) {
                    _deck.add(new Card(i.toString(), suit));
                } else {
                    switch (i) {
                    case 1:
                        _deck.add(new Card("A", suit));
                        break;
                    case 8:
                        _deck.add(new Card("J", suit));
                        break;
                    case 9:
                        _deck.add(new Card("Q", suit));
                        break;
                    case 10:
                        _deck.add(new Card("K", suit));
                        break;
                    default:
                        break;
                    }
                }
            }
        }
        Collections.shuffle(_deck);
    }
}