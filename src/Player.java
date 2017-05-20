import java.util.ArrayList;

/**
 * Player
 */
public abstract class Player {
    protected ArrayList<Card> _hand;
    protected ArrayList<Card> _points;
    protected String name;
    public abstract Card play();

    public abstract Card play(Card played);

    public abstract void draw(Card card);

    public ArrayList<Card> getHand() {
        return this._hand;
    }

    public ArrayList<Card> getPoints() {
        return this._points;
    }

    public void addToPoints(Card c1, Card c2) {
        this._points.add(c1);
        this._points.add(c2);
    }

    public int countPoints() {
        int result = 0;
        for (int i = 0; i < this._points.size(); i++) {
            result += this._points.get(i).getValue();
        }

        return result;
    }

    @Override
    public String toString() {
        return this.name;
    }
}