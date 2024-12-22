import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    Scanner keyboard = new Scanner(System.in);
    Deck deck = new Deck();
    PlayerDeck pd = new PlayerDeck();
    PlayerDeck cd = new PlayerDeck();
    PlayerDeck wd = new PlayerDeck();
    int choice = 0;
    boolean isWarring = false;

        do {
            System.out.println("\n***GAME OF WAR***");
            System.out.println("1 - Create Deck " + (deck.deckCreated ? "(Check!)" : ""));
            System.out.println("2 - Shuffle Deck " + (deck.deckShuffled ? "(Check!)" : ""));
            System.out.println("3 - Deal Cards " + (deck.cardsDealt ? "(Check!)" : ""));
            System.out.println("4 - Play");
            System.out.println("5 - New Game");
            System.out.println("6 - Exit");
            System.out.print("Enter choice: ");
            choice = keyboard.nextInt();

            switch (choice) {
                case 1:
                    GameEngine.createDeck(deck);
                    break;
                case 2:
                    GameEngine.shuffleDeck(deck);
                    break;
                case 3:
                    GameEngine.dealCards(deck, pd, cd);
                    break;
                case 4:
                    int playerChoice = 0;
                    do {
                        System.out.println("\n***Play Menu***");
                        System.out.println("1 - Play");
                        System.out.println("2 - Exit");
                        System.out.println("3 - Display Deck (" + pd.deckSize() + ")");
                        System.out.println("4 - Display Discard Deck (" + pd.getSizeOfDeck() + ")");
                        System.out.print("Enter choice: ");
                        playerChoice = keyboard.nextInt();

                        switch(playerChoice) {
                            case 1:
                                do {
                                    Card playerDrawnCard = pd.draw();
                                    Card computerDrawnCard = cd.draw();
                                    int playerNumber = Integer.parseInt(playerDrawnCard.getCard().substring(0, 2).strip());
                                    int computerNumber = Integer.parseInt(computerDrawnCard.getCard().substring(0, 2).strip());

                                    System.out.println("Player card: " + playerDrawnCard.getCard());
                                    System.out.println("***");
                                    System.out.println("Computer card: " + computerDrawnCard.getCard());
                                    System.out.println("***");

                                    if (playerNumber > computerNumber) {
                                        System.out.println("Player wins this battle.");
                                        pd.addCard(playerDrawnCard, computerDrawnCard);
                                        if (isWarring) {
                                            GameEngine.sendToWinnerDiscard(wd, pd);
                                            isWarring = false;
                                            System.out.println("You won the war!**********************************************");
                                        }
                                    } else if (computerNumber > playerNumber) {
                                        System.out.println("Computer wins this battle.");
                                        cd.addCard(playerDrawnCard, computerDrawnCard);
                                        if (isWarring) {
                                            GameEngine.sendToWinnerDiscard(wd, cd);
                                            isWarring = false;
                                            System.out.println("You lost the war!**********************************************");
                                        }
                                    } else {
                                        System.out.println("It was a tie. TIME FOR WAR!**********************************************");
                                        if (pd.deckSize() < 4 && pd.getSizeOfDeck() < (4 - pd.deckSize())) {
                                            pd.clearDeck();
                                            pd.clearDiscardDeck();
                                            System.out.println("Not enough cards...");
                                            isWarring = false;
                                        } else if (cd.deckSize() < 4 && cd.getSizeOfDeck() < (4 - cd.deckSize())) {
                                            cd.clearDeck();
                                            cd.clearDiscardDeck();
                                            System.out.println("Not enough cards...");
                                            isWarring = false;
                                        } else if (pd.deckSize() < 4 && pd.getSizeOfDeck() >= (4 - pd.deckSize())) {
                                            GameEngine.returnDiscardsToDeck(pd);
                                            GameEngine.returnDiscardsToDeck(cd);
                                            isWarring = true;
                                        } else if (cd.deckSize() < 4 && cd.getSizeOfDeck() >= (4 - cd.deckSize())) {
                                            GameEngine.returnDiscardsToDeck(pd);
                                            GameEngine.returnDiscardsToDeck(cd);
                                            isWarring = true;
                                        } else {
                                            GameEngine.addToWarDeck(wd, playerDrawnCard, computerDrawnCard, pd, cd);
                                            isWarring = true;
                                        }
                                    }
                                } while (isWarring);

                                if (pd.isDeckEmpty() && pd.isDiscardDeckEmpty()) {
                                    System.out.println("You lost the game...");
                                    System.out.println("Computer is the winner!");
                                } else if (cd.isDeckEmpty() && cd.isDiscardDeckEmpty()) {
                                    System.out.println("You won the game!");
                                } else if (pd.isDeckEmpty() && !pd.isDiscardDeckEmpty()) {
                                    GameEngine.returnDiscardsToDeck(pd);
                                    GameEngine.returnDiscardsToDeck(cd);
                                } else if (cd.isDeckEmpty() && !pd.isDiscardDeckEmpty()) {
                                    GameEngine.returnDiscardsToDeck(cd);
                                    GameEngine.returnDiscardsToDeck(pd);
                                }
                                break;
                            case 2:
                                System.out.println("Exiting to main menu...");
                                break;
                            case 3:
                                pd.displayDeck();
                                cd.displayDeck();
                                break;
                            case 4:
                                pd.displayDiscardDeck();
                                cd.displayDiscardDeck();
                                break;
                            default:
                                System.out.println("Ehh, let's try something else...");
                        }
                    } while (playerChoice != 2);

                    break;
                case 5:
                    deck.deckCreated = false;
                    deck.deckShuffled = false;
                    deck.cardsDealt = false;
                    pd.clearDeck();
                    pd.clearDiscardDeck();
                    cd.clearDeck();
                    cd.clearDiscardDeck();
                case 6:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Select a different choice...");
            }
        } while (choice != 6);
    }
}

