package com.jacobboline;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class Player {

    private String Name;
    private ArrayList<Card> Hand;
    private int Score;


    public Player() {
    }

    public void setHand(ArrayList<Card> newHand) {
        this.Hand = newHand;
    }

    public void setName(String newName) {
        this.Name = newName;
    }

    public String getName() {
        return this.Name;
    }

    public String getHandInfo() {
        String cardString =
                "***************\n" +
                        this.getName() +
                        "'s hand contains: \n";
        for (Card card : this.Hand) {
            cardString += (card.getFullCard(card) + " \n");
        }
        cardString += "***************\n";

        return cardString;
    }

    public void addCard(Card card) {
        this.Hand.add(card);
    }

    public void playCard(Card card) {
        this.Hand.remove(card);
    }

    public int getScore() {
        return this.Score;
    }

    public void setScore(int newScore) {
        this.Score = newScore;
    }

    public void getCard(int x) {
        this.Hand.get(x);
    }


    private boolean validityCheck(Card cardChosen, CardDeck discardPile, Player player, CardDeck drawPileDeck) {


        if
                (
                cardChosen.getSuit() == discardPile.topOfDrawPile().getSuit()
                        ||
                        cardChosen.getRank() == discardPile.topOfDrawPile().getRank()
                ) return true;

        else {
            System.out.println("That play is not valid, try again.\n\n");
            takeTurn(player, discardPile, drawPileDeck);
            return false;
        }


    }


    public void takeTurn(Player player, CardDeck discardPile, CardDeck drawPile) {
        int cardIndex = 0;
        ArrayList<Card> playerChoice = new ArrayList<>();

        for (Card card : this.Hand) {
            this.Hand.get(cardIndex);
            cardIndex += 1;
            playerChoice.add(card);
        }


        System.out.println("\n\nIt is " + player.getName() + "'s turn!");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n\nEnter the number of the play you wish to make.\n\n"
                + "Enter 0 (Zero) to select : " + discardPile.topOfDrawPile().getFullCard(discardPile.topOfDrawPile()) +
                "\n from the discard pile");
        int choice = 1;
        for (Card cardChosen : playerChoice) {
            System.out.println("Enter " + choice + " to select : " + cardChosen.getFullCard(cardChosen));
            choice += 1;
        }

        System.out.println(
                "Enter " + (this.Hand.size() + 1) + " to draw a card.\n" +
                        "Enter " + (this.Hand.size() + 2) + " to pass your turn");

        Scanner scanner = new Scanner(System.in);


        int playersChoiceInt = scanner.nextInt();


        try {


            if ((playersChoiceInt < 0) || (playersChoiceInt > this.Hand.size() + 2)) {
                System.out.println("Your selection needs to be an integer from the menu!");
                player.takeTurn(player, discardPile, drawPile);
            }

            if (playersChoiceInt == 0) {

                Card cardFromDiscardPile = discardPile.topOfDrawPile();
                this.Hand.add(cardFromDiscardPile);
                discardPile.removeCard(cardFromDiscardPile);
                System.out.println(player.getName() + " drew the "
                        + cardFromDiscardPile.getFullCard(cardFromDiscardPile)
                        + " from the discard pile!\n");
            }

            if (playersChoiceInt == this.Hand.size() + 1) {

                Card cardFromDrawPile = drawPile.topOfDrawPile();
                this.Hand.add(cardFromDrawPile);
                drawPile.removeCard(cardFromDrawPile);
                System.out.println(player.getName() +
                        " drew a " + cardFromDrawPile.getFullCard(cardFromDrawPile)
                        + " from the draw pile!\n");

            }

            if (playersChoiceInt == this.Hand.size() + 2) {

                System.out.println(player.getName() + " has passed their turn!");

            } else {

                Card cardChosen = this.Hand.get(playersChoiceInt - 1);

                if (cardChosen.getRank() == Card.Ranks.Eight) {
                    try {
                        System.out.println
                                (
                                        "You've played an eight!" +
                                                "\n Please select the suit for play : \n"
                                                + "1 : Clubs\n"
                                                + "2 : Diamonds\n"
                                                + "3 : Hearts\n"
                                                + "4 : Spades\n"
                                );
                        Scanner switchScanner = new Scanner(System.in);
                        int switchSelection = switchScanner.nextInt();
                        while (switchSelection > 4 || 1 > switchSelection) {
                            switchScanner.hasNextInt();
                        }


                        switch (switchSelection) {

                            case 1:
                                cardChosen.setSuit(Card.Suits.Clubs);
                                discardPile.addCard(cardChosen);
                                player.Hand.remove(cardChosen);
                                System.out.println(player.getName() + " played " +
                                        cardChosen.getFullCard(cardChosen) + "!\n");

                                break;

                            case 2:
                                cardChosen.setSuit(Card.Suits.Diamonds);
                                discardPile.addCard(cardChosen);
                                player.Hand.remove(cardChosen);
                                System.out.println(player.getName() + " played " +
                                        cardChosen.getFullCard(cardChosen) + "!\n");
                                break;

                            case 3:
                                cardChosen.setSuit(Card.Suits.Hearts);
                                discardPile.addCard(cardChosen);
                                player.Hand.remove(cardChosen);
                                System.out.println(player.getName() + " played " +
                                        cardChosen.getFullCard(cardChosen) + "!\n");
                                break;

                            case 4:
                                cardChosen.setSuit(Card.Suits.Spades);
                                discardPile.addCard(cardChosen);
                                player.Hand.remove(cardChosen);
                                System.out.println(player.getName() + " played " +
                                        cardChosen.getFullCard(cardChosen) + "!\n");
                                break;
                        }

                    } catch (InputMismatchException ime) {
                        System.out.println("Please choose a valid play!");
                        takeTurn(player, discardPile, drawPile);
                    }
                } else {
                    if (validityCheck(cardChosen, discardPile, player, drawPile) == true) {
                        System.out.println(player.getName() + " played : "
                                + cardChosen.getFullCard(cardChosen) + " from their hand!");
                        discardPile.addCard(cardChosen);
                        player.Hand.remove(cardChosen);
                    }
                }

            }
        } catch (InputMismatchException ime) {
            System.out.println("Menu selection must be an integer from the menu!");
            player.takeTurn(player, discardPile, drawPile);
        }

    }

    public void computerLogic(Player AI, CardDeck drawPile, CardDeck discardPile) {
        //NEED TO FINISH computerLogic for computer turn =(
            Card topOfDiscardPile = discardPile.topOfDrawPile();
            ArrayList<Card> eightsInHand = new ArrayList<>();

            for (int x = 0; x < this.Hand.size(); x++) {

                Card cardToPlay = this.Hand.get(x);

                if (
                        cardToPlay.getSuit() == topOfDiscardPile.getSuit()
                                ||
                                cardToPlay.getRank() == topOfDiscardPile.getRank()
                        ) {
                    if (cardToPlay.getRank() == Card.Ranks.Eight) {
                        eightsInHand.add(cardToPlay);
                    }
                    else {
                        discardPile.addCard(cardToPlay);
                        this.Hand.remove(cardToPlay);
                        break;
                    }
                }


                /*if (card.getRank() == Card.Ranks.Eight && card.getSuit() == topOfDiscardPile.getSuit()) {*/

            }

        }

    }



