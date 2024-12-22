import java.util.ArrayList;

public class PlayerDeck extends Deck {

    private ArrayList<Card> discardDeck;

    public PlayerDeck() {
        this.discardDeck = new ArrayList<>();
    }

    public ArrayList<Card> getDiscardDeck() {
        return this.discardDeck;
    }

    public void addCard(Card pc, Card cd) {
        this.discardDeck.add(pc);
        this.discardDeck.add(cd);
    }

    public void discardMany(Card card) {
        this.discardDeck.add(card);
    }

    public void displayDiscardDeck() {

        for (Card card : discardDeck) {
            System.out.println(card.getCard());
        }
    }

    public boolean isDiscardDeckEmpty() {
        return this.discardDeck.isEmpty();
    }

    public int getSizeOfDeck() {
        return discardDeck.size();
    }

    public void clearDiscardDeck() {
        discardDeck.clear();
    }

    public Card getDiscardCard(int num) {
        return this.discardDeck.get(num);
    }

    public Card discardDraw() {
        Card card = this.discardDeck.get(0);
        this.discardDeck.remove(0);
        return card;
    }
}
