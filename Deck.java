import java.util.ArrayList;

public class Deck {
    private  ArrayList<Card> deck;

    public boolean deckCreated = false;
    public boolean deckShuffled = false;
    public boolean cardsDealt = false;

    public Deck() {
        this.deck = new ArrayList<>();
    }

    public void setDeck(Card c) {
        this.deck.add(c);
    }

    public Card getCard(int num) {
        return this.deck.get(num);
    }

    public void clearDeck() {
        deck.clear();
    }

    public void addCard(Card card) {
        this.deck.add(card);
    }

    public void displayDeck() {
        for (Card card : deck) {
            System.out.println(card.getCard());
        }
    }

    public Card draw() {
        if (this.deck.isEmpty()) {
            System.out.println("Nothing to draw.");
            return null;
        }  else {
            Card card = this.deck.get(0);
            this.deck.remove(0);
            return card;
        }
    }

    public boolean isDeckEmpty() {
        return this.deck.isEmpty();
    }

    public int deckSize() {
        return deck.size();
    }
}
