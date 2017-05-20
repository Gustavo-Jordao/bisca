import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Pc
 */
public class Pc extends Player {
    private static final Character[] _suits = { 'D', 'C', 'H', 'S' };
    private ArrayList<Card> _unknownCards;
    private HashMap<Card, Integer> _worth;

    public Pc(String name) {
        this.name = name;
        this._hand = new ArrayList<Card>();
        this._points = new ArrayList<Card>();
        this._populateUnknownCards();
    }

    public void draw(Card card) {
        this._hand.add(card);
        if (Game.getDeck().size() == 0 || Game.getDeck().size() == 1) {
            this._unknownCards.add(Game.getTrump());
        }
        this._unknownCards.remove(card);
    }

    public Card play() {
        System.out.println(this._hand); //For testing purposes
        this._evaluateFirst();
        Card card = this._pickLowest();
        this._hand.remove(card);
        return card;
    }

    public Card play(Card played) {
        System.out.println(this._hand); //For testing purposes        
        this._unknownCards.remove(played);
        this._evaluateLast(played);
        Card card = this._pickLowest();
        this._hand.remove(card);
        return card;
    }

    private void _evaluateLast(Card played) {
        this._worth = new HashMap<Card, Integer>();
        Integer cardWorth;
        for (Card card : this._hand) {
            cardWorth = 0;
            if (card.getSuit().compareTo(played.getSuit()) == 0) { // Se a tua carta e a carta jogada são do mesmo naipe
                cardWorth--; 
                cardWorth -= this._isCardDirectlyBelow(card, played);
                cardWorth += this._evaluateBelow(card);
                cardWorth += this._evaluateChanceToPass(card);
            } else {
                if(card.getSuit().compareTo(Game.getTrump().getSuit())==0) { // Se a tua carta é um trunfo
                    if(played.getValue()>=2) { 
                        cardWorth -= this._evaluateTrumpValue(card);
                    } else {
                        cardWorth += 2;
                    }
                } else if (played.getSuit().compareTo(Game.getTrump().getSuit())==0) { // Se a carta do oponente é um trunfo
                    cardWorth = this._giveWorth(card);
                } else { // Se são naipes diferentes
                    cardWorth = this._giveWorth(card);
                    if (cardWorth < 1) {
                        cardWorth -= 2;
                    }
                }
            }
            this._worth.put(card, cardWorth);
        }
    }

    private int _evaluateTrumpValue(Card card) {
        int trumpWorth;
        switch(card.getValue()) {
            case 11:
                trumpWorth = 1;
                break;
            case 10:
                trumpWorth = 2;
                break;
            case 4:
                trumpWorth = 3;
                break;
            case 3:
                trumpWorth = 4;
                break;
            case 2:
                trumpWorth = 5;
                break;
            default:
                trumpWorth = 6;
                break;
        }
        return trumpWorth;
    }

    private int _isCardDirectlyBelow(Card card, Card played) {
        switch (card.getIdentity()) {
        case "A":
            if (played.getIdentity().compareTo("7") == 0) {
                return 50;
            } else if (played.getIdentity().compareTo("K") == 0) {
                return 1;
            }
            break;
        case "7":        
            if (played.getIdentity().compareTo("K") == 0) {
                return 50;
            } else if (played.getIdentity().compareTo("J") == 0) {
                return 1;
            }
            break;
        case "K":
            if (played.getIdentity().compareTo("J") == 0) {
                return 50;
            } else if (played.getIdentity().compareTo("Q") == 0) {
                return 1;
            }
            break;
        case "J":
            if(played.getValue() < 3)
                return 50;
            break;
        case "Q":
            if(played.getValue() < 2)
                return 50;
            break;
        default:
            break;
        }

        return 0;
    }

    private int _evaluateChanceToPass(Card card) {
        double beingEatenChance;
        int eatingCards = 0, edibleCards = 0;
        for (Card cardIn : this._unknownCards) {
            if (cardIn.getSuit().compareTo(card.getSuit()) == 0) {
                if (cardIn.getValue() > card.getValue()) {
                    eatingCards++;
                } else {
                    edibleCards++;
                }
            } else if (cardIn.getSuit().compareTo(Game.getTrump().getSuit()) == 0) {
                eatingCards++;
            }
        }

        beingEatenChance = ((eatingCards - edibleCards) / (eatingCards + edibleCards)) * 100;
        if (beingEatenChance >= 55) {
            return -1;
        }

        return 1;
    }

    private int _evaluateBelow(Card card) {
        int nrCardsBelow = 0;
        for (Card cardIn : this._unknownCards) {
            if (cardIn.getSuit().compareTo(card.getSuit()) == 0) {
                switch (card.getIdentity()) {
                case "A":
                    if (cardIn.getIdentity().compareTo("7") == 0 || cardIn.getIdentity().compareTo("K") == 0) {
                        nrCardsBelow++;
                    }
                    break;
                case "K":
                    if (cardIn.getIdentity().compareTo("J") == 0 || cardIn.getIdentity().compareTo("Q") == 0) {
                        nrCardsBelow++;
                    }
                    break;
                case "7":
                    if (cardIn.getIdentity().compareTo("K") == 0 || cardIn.getIdentity().compareTo("J") == 0) {
                        nrCardsBelow++;
                    }
                    break;
                default:
                    break;
                }
                if (nrCardsBelow == 2) {
                    return 2;
                }
            }
        }
        return -1;
    }

    public void seePlayedCard(Card card) {
        this._unknownCards.remove(card);
    }

    private void _populateUnknownCards() {
        this._unknownCards = new ArrayList<Card>();
        for (Character suit : _suits) {
            for (Integer i = 1; i < 11; i++) {
                if (i != 1 && i <= 7) {
                    this._unknownCards.add(new Card(i.toString(), suit));
                } else {
                    switch (i) {
                    case 1:
                        this._unknownCards.add(new Card("A", suit));
                        break;
                    case 8:
                        this._unknownCards.add(new Card("J", suit));
                        break;
                    case 9:
                        this._unknownCards.add(new Card("Q", suit));
                        break;
                    case 10:
                        this._unknownCards.add(new Card("K", suit));
                        break;
                    default:
                        break;
                    }
                }
            }
        }
        this._unknownCards.remove(Game.getTrump());
    }

    private void _evaluateFirst() {
        this._worth = new HashMap<Card, Integer>();
        Integer cardWorth;
        for (Card card : this._hand) {
            cardWorth = this._giveWorth(card);
            cardWorth += this._evaluateBySuit(card);            
            if (!this.trumpsExist()) {
                cardWorth += this._checkEaters(card);                
             }

        
            this._worth.put(card, cardWorth);
        }
    }

    private int _giveWorth(Card card) {
        int result = 0;
        switch (card.getIdentity()) {
        case "A":
            result = 9;
            break;
        case "7":
            result = 8;
            break;
        case "K":
            result = 4;
            break;
        case "J":
            result = 2;
            break;
        case "Q":
            result = 1;
            break;
        default:
            result = 0;
            break;
        }

        return result;
    }

    private int _evaluateBySuit(Card card) {
        if (card.getSuit().compareTo(Game.getTrump().getSuit()) == 0) {
            return 3;
        }

        return 0;
    }

    private int _checkEaters(Card card) {
        boolean noEatNoEaten = false, eatNoEaten = false, noEatEaten = false, eatEaten = false;
        for (Card inCard : this._unknownCards) {
            if (inCard.getSuit().compareTo(card.getSuit()) == 0) {
                noEatNoEaten = true;
                if (inCard.getValue() < card.getValue()) {
                    noEatNoEaten = false;
                    eatNoEaten = true;
                }
                if (inCard.getValue() > card.getValue()) {
                    noEatNoEaten = false;
                    noEatEaten = true;
                }
                if (eatNoEaten && noEatEaten) {
                    eatEaten = true;
                }
            }
        }
        if (noEatNoEaten) {
            return -10;
        } else if (eatEaten) {
            return 7;
        } else if (noEatEaten) {
            return 6;
        } else if (eatNoEaten) {
            return 1;
        }

        return 0;
    }

    private Card _pickLowest() {
        Card result = null;
        Integer worth = 100;
        for (Map.Entry<Card, Integer> pair : this._worth.entrySet()) {
            if (pair.getValue() < worth) {
                result = pair.getKey();
                worth = pair.getValue();
            } else if (pair.getValue() == worth) {
                if (result.getValue() > pair.getKey().getValue()) {
                    result = pair.getKey();
                    worth = pair.getValue();
                }
            }
        }
        
        return result;
    }

    private boolean trumpsExist() {
        for (Card card : this._unknownCards) {
            if (card.getSuit().compareTo(Game.getTrump().getSuit()) == 0) {
                return true;
            }
        }

        return false;
    }
}