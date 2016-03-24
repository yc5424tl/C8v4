package com.jacobboline;

import java.util.ArrayList;

import java.util.Scanner;

public class C8Manager
{

    public static void main(String[] args) {

        CardDeck Deck = new CardDeck();

        ArrayList<Player> ListOfPlayers = new ArrayList<>();
        PlayerList playerList = new PlayerList(ListOfPlayers);


        getPlayers(playerList);
        dealCards(playerList, Deck);

                System.out.println("Alright..., looks like, uh, well... ");
                try {
                    Thread.sleep(1000);
                } catch(InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
                System.out.println(playerList.playerNames(playerList));

        startGame(playerList, Deck);
    }







    private static void startGame(PlayerList playerList, CardDeck deck) {

        CardDeck drawPileDeck = new CardDeck();
        CardDeck discardPileDeck = new CardDeck();
        drawPileDeck.makeDrawPileFrom(deck);
        drawPileDeck.moveFromDrawToDiscard(discardPileDeck);


        for (int p = 0; p < playerList.size() ; p++) {
            if (p == playerList.size() - 1) {
                Player AI = playerList.playerAt(playerList.size() - 1);
                AI.computerLogic(AI, drawPileDeck, discardPileDeck);
            } else {
                Player playerUp = playerList.playerAt(p);
                playerUp.takeTurn(playerUp, discardPileDeck, drawPileDeck);

            }
        }
    }







    private static void dealCards(PlayerList playerList, CardDeck deck) {

            deck.shuffle();
            System.out.println("Shuffling cards here...without fingers. One sec!");
            int numberOfCardsToDeal;

            if (playerList.size() == 2) numberOfCardsToDeal = 7;
            else numberOfCardsToDeal = 5;

            ArrayList<Card> cardsToRemove = new ArrayList<>();
            int indexOfCard = 0;
            int x = 0;
            while (x < playerList.size()) {

                Player player = playerList.playerAt(x);

                    for (int y = 0; y < numberOfCardsToDeal; y++)
                    {
                        Card cardToDeal = deck.getCard(indexOfCard);
                        player.addCard(cardToDeal);
                        cardsToRemove.add(cardToDeal);
                        indexOfCard += 1;
                    }
                x += 1;
            }
            deck.removeDealtCards(cardsToRemove);
        }










    public static void getPlayers(PlayerList pList) {

            System.out.println(
                      "Welcome to Crazy8s!\n"
                    + "You'll be playing against the computer.\n"
                    + "How many HUMANS will be STRUGGLING today?\n"
                    + "Please Enter 1, 2, or 3."
            );

            Scanner scanner = new Scanner(System.in);

            int numPlayers = scanner.nextInt();
            if (numPlayers < 1 || numPlayers > 3) {
                System.out.println("Please Enter a Valid Selection");
                scanner.nextInt();
            }

            for (int p = 0; p < numPlayers; p++) {
                System.out.println("Please enter the name of the player to add.");
                String playerName = scanner.next();
                if (playerName == null) {
                    System.out.println(
                            "You know the phrase, 'What's in a name?'"
                            +"\n I tell ya, it ain't nothin'!"
                            +"\n Enter a valid name HUMAN."
                    );
                    playerName = scanner.next();
                }

                Player newPlayer = new Player();
                newPlayer.setName(playerName);

                ArrayList<Card> hand = new ArrayList<>();
                newPlayer.setHand(hand);
                newPlayer.setScore(0);
                pList.addPlayer(newPlayer);

            }
            Player AI = new Player();
            AI.setName("HAL");
            ArrayList<Card> hand = new ArrayList<>();
            AI.setHand(hand);
            AI.setScore(0);
            pList.addPlayer(AI);

        }
}
