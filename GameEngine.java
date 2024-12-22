import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GameEngine {

    public static void createDeck(Deck deck) {

        for (int i = 0; i < 13; i++) {
            String suit = "Clubs";
            for (int j = 0; j < 4; j++) {
                if (j == 1) {
                    suit = "Hearts";
                } else if (j == 2) {
                    suit = "Spades";
                } else if (j == 3) {
                    suit = "Diamonds";
                }
                Card c = new Card(i+1, suit);
                deck.setDeck(c);
            }
        }
        deck.deckCreated = true;
    }

    public static void shuffleDeck(Deck deck) {
        ArrayList<Card> tempDeck = new ArrayList<>();
        Set<Card> hash = new HashSet<>();

        while (hash.size() < deck.deckSize()) {
            int min = 0;
            int max = deck.deckSize() - 1;
            Random random = new Random();
            int randomNumber = random.nextInt(max - min + 1) + min;

            if (!hash.contains(deck.getCard(randomNumber))) {
                tempDeck.add(deck.getCard(randomNumber));
            }

            hash.add(deck.getCard(randomNumber));
        }

        deck.clearDeck();

        for (int i = 0; i < tempDeck.size(); i++) {
            deck.addCard(tempDeck.get(i));
        }
        tempDeck.clear();
        deck.deckShuffled = true;
    }

    public static void dealCards(Deck deck, PlayerDeck pd, PlayerDeck cd) {
        for (int i = 0; i < 52; i++) {
            if (i % 2 == 0) {
                cd.setDeck(deck.getCard(i));
            } else {
                pd.setDeck(deck.getCard(i));
            }
        }
        deck.cardsDealt = true;
    }

    public static void returnDiscardsToDeck(PlayerDeck pd) {
        pd.clearDeck();
        for (int i = 0; i < pd.getSizeOfDeck(); i++) {
            pd.setDeck(pd.getDiscardCard(i));
        }
        shuffleDeck(pd);
        pd.clearDiscardDeck();
    }

    public static void addToWarDeck(PlayerDeck wd, Card pCard, Card cCard, PlayerDeck pd, PlayerDeck cd) {

        wd.addCard(pCard, cCard);

        for (int i = 0; i < 3; i++) {
            wd.addCard(pd.draw(), cd.draw());
        }
    }

    public static void sendToWinnerDiscard(PlayerDeck wd, PlayerDeck pDeck) {
        while (wd.getSizeOfDeck() > 0) {
            pDeck.discardMany(wd.discardDraw());
        }
    }
}
