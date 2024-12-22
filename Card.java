public class Card {
    private int value;
    private String suit;

    public Card(int value, String suit) {
        this.value = value;
        this.suit = suit;

    }

    public String getCard() {
        String name = "";
        if (value == 1) {
            name = String.valueOf(value) + " Ace";
        } else if (value == 11) {
            name = String.valueOf(value) + " Jack";
        } else if (value == 12) {
            name = String.valueOf(value) + " Queen";
        } else if (value == 13) {
            name = String.valueOf(value) + " King";
        } else {
            name = String.valueOf(value);
        }

        return name + " of " + suit;
    }
}

//Order of deck
//Clubs, Hearts, Spades, Diamonds
//Ace,2,3,4,5,6,7,8,9,10,Jack,Queen,King
