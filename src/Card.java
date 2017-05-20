
/**
 * Card
 */
public class Card {
    private String _identity;
    private Character _suit;
    private int _value;

    public Card(String _identity, Character _suit) {
        this._identity = _identity;
        this._suit = _suit;
        this._value = this._calcValue();
    }

    @Override
    public String toString() {
        return this._identity + this._suit;
    }

    public String getIdentity() {
        return this._identity;
    }

    public Character getSuit() {
        return this._suit;
    }

    public int getValue() {
        return this._value;
    }

    private int _calcValue() {
        int result;
        switch (this._identity) {
        case "A":
            result = 11;
            break;
        case "K":
            result = 4;
            break;
        case "Q":
            result = 2;
            break;
        case "J":
            result = 3;
            break;
        case "7":
            result = 10;
            break;
        default:
            result = 0;
            break;
        }

        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Card ) {
            return this.toString().equals(((Card)obj).toString());
        }

        return false;
    }
}