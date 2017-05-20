import java.util.ArrayList;
import java.util.Scanner;

/**
 * Human
 */
public class Human extends Player {
    private Scanner _sc = new Scanner(System.in);

    public Human(String name) {
        this._hand = new ArrayList<Card>();
        this._points = new ArrayList<Card>();
        this.name = name;
    }

    @Override
    public Card play() {
        int cardToPlayIndex = 0;
        Card cardToPlay;
        while (cardToPlayIndex < 1 || cardToPlayIndex > this._hand.size()) {
            System.out.println("Choose a card to play:");
            this._printCards();
            cardToPlayIndex = this._sc.nextInt();
            if(cardToPlayIndex==this._hand.size()+1) {
                System.out.println("Trump card is: " + Game.getTrump());
            }
        }
        cardToPlay = this._hand.get(cardToPlayIndex - 1);
        this._hand.remove(cardToPlayIndex-1);
        return cardToPlay;
    }

    @Override
    public Card play(Card played) {
        int cardToPlayIndex = 0;
        Card cardToPlay;
        while (cardToPlayIndex < 1 || cardToPlayIndex > this._hand.size()) {
            System.out.println("Your oponent played: " + played);
            System.out.println("Choose a card to play:");
            this._printCards();
            cardToPlayIndex = this._sc.nextInt();
            if(cardToPlayIndex==this._hand.size()+1) {
                System.out.println("Trump card is: " + Game.getTrump());
            }
        }
        cardToPlay = this._hand.get(cardToPlayIndex - 1);
        this._hand.remove(cardToPlayIndex-1);
        return cardToPlay;
    }    

    @Override
    public void draw(Card card) {
        this._hand.add(card);
    }

    private void _printCards() {
        int i;
        for (i = 0; i < this._hand.size(); i++) {
            System.out.println(i+1 + ": " + this._hand.get(i));
        }
        System.out.println(i+1 + ": " + "Check trump");
    }
}