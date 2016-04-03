package com.jacobboline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class C8Manager {

    public static void main(String[] args)
    {

        CardDeck Deck = new CardDeck();
        Deck.shuffle();

        ArrayList<Player> ListOfPlayers = new ArrayList<>();
        PlayerList playerList = new PlayerList(ListOfPlayers);
        ArrayList<Card> lastCardPlayedAI = new ArrayList<>();
        Card blankCard = new Card(null, null);
        lastCardPlayedAI.add(blankCard);

        getPlayers(playerList);
        dealCards(playerList, Deck);

        System.out.println("Alright..., looks like, uh, well... ");

        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }

        startGame(playerList, Deck, lastCardPlayedAI);
        System.out.println("Let's take a look here....\n\n");
        getScores(playerList);
    }


    private static boolean passCounter(int playResult, PlayerList playerList)
    {
        boolean allPlayersPass;
        int passCounter = 0;

        if (playResult == 1) passCounter++;
        else passCounter = 0;

        allPlayersPass = passCounter == playerList.size();
        return allPlayersPass;
    }


    private static void startGame(PlayerList playerList, CardDeck deck, ArrayList<Card> lastCardPlayedList)
    {
        CardDeck drawPileDeck = new CardDeck();
        drawPileDeck.clear();
        CardDeck discardPileDeck = new CardDeck();
        discardPileDeck.clear();
        drawPileDeck.makeDrawPileFrom(deck);
        drawPileDeck.moveFromDrawToDiscard(discardPileDeck);
        boolean passCounterBoolean = true;

        do
        {
            int passCounterInt = 0;

            for (int p = 0 % playerList.size(); p < playerList.size(); p++)
            {
                Player playerUp = playerList.playerAt(p);
                int passOrNot = takeTurn(playerUp, discardPileDeck, drawPileDeck, lastCardPlayedList);

                if (passOrNot == 1) passCounterInt++;
                else passCounterInt = 0;

                if (passCounter(passCounterInt, playerList)) passCounterBoolean = false;
                if (!handCheck(playerList)) break;
            }
        }
        while ((handCheck(playerList)) && (passCounterBoolean));
    }


    private static boolean handCheck(PlayerList pList)
    {
        boolean keepPlaying = true;

        for (int p2 = 0; p2 < pList.size() - 1; p2++)
        {
            Player playerToCheck = pList.playerAt(p2);
            if (playerToCheck.getHandSize() == 0) keepPlaying = false;
        }
        return keepPlaying;
    }


    private static void dealCards(PlayerList playerList, CardDeck deck)
    {

        System.out.println("Shuffling cards here...without fingers. One sec!");
        int numberOfCardsToDeal;
        if (playerList.size() == 2) numberOfCardsToDeal = 7
        else numberOfCardsToDeal = 5
        ArrayList<Card> cardsToRemove = new ArrayList<>();
        int indexOfCard = 0;
        int x = 0;

        while (x < playerList.size())
        {
            Player player = playerList.playerAt(x);

            for (int y = 0; y < numberOfCardsToDeal; y++)
            {
                Card cardToDeal = deck.getCard(indexOfCard);
                player.addCard(cardToDeal);
                cardsToRemove.add(cardToDeal);
                indexOfCard += 1;
            }
            x++;
        }
        deck.removeDealtCards(cardsToRemove);
    }


    private static void getPlayers(PlayerList pList)
    {
        Scanner scanner = new Scanner(System.in);

        System.out.println
        (
            "Welcome to Crazy8s!\n\n"
            + "You'll be playing against me, HAL! The computer!\n\n"
            + "How many HUMANS will be STRUGGLING today?\n\n"
            + "Please Enter 1, 2, or 3.\n\n"
        );

        int numPlayers = scanner.nextInt();

        while (numPlayers < 1 || numPlayers > 3) scanner.hasNextInt();

        if (numPlayers < 1 || numPlayers > 3)
        {
            System.out.println("Please Enter a Valid Selection");
            scanner.nextInt();
        }

        for (int p = 0; p < numPlayers; p++)
        {
            System.out.println("Please enter the name of the player to add.");
            String playerName = scanner.next();

            if (playerName == null)
            {
                System.out.println
                (
                    "You know the phrase, 'What's in a name?'"
                    + "\n I tell ya, it ain't nothin'!"
                    + "\n Enter a valid name HUMAN."
                );

                playerName = scanner.next();
            }

            Player newPlayer = new Player();
            newPlayer.setName(playerName);
            ArrayList<Card> hand = new ArrayList<>();
            newPlayer.setHand(hand);
            newPlayer.setBaseScore();
            pList.addPlayer(newPlayer);
        }

        Player AI = new Player();
        AI.setName("HAL");
        ArrayList<Card> hand = new ArrayList<>();
        AI.setHand(hand);
        AI.setBaseScore();
        pList.addPlayer(AI);

    }

    private static void getScores(PlayerList pList)
    {
        ArrayList<Integer> pScores = new ArrayList<>();
        ArrayList<String> pNames = new ArrayList<>();

        for (int p = 0; p < pList.size(); p++)
        {

            Player player = pList.playerAt(p);
            int thisScore = player.setScore();
            int scorePlacement = 0;
            Integer scoreAsInt = thisScore;

            if (!pScores.isEmpty())
            {
                for (Integer score : pScores) if (scoreAsInt > score) scorePlacement++;
                pScores.add(scorePlacement, scoreAsInt);
                pNames.add(scorePlacement, player.getName());
            }
            else
            {
                pScores.add(scorePlacement, scoreAsInt);
                pNames.add(scorePlacement, player.getName());
            }
        }
        System.out.println
        (
            "The winner of all things good in the world is "
            + pNames.get(0).toUpperCase() + "!!!\n"
            + "With an astounding, yet fitting, score of : "
            + pScores.get(0).toString() + "!!!"
        );

        for (int x = 1; x < pNames.size(); x++)
        {
            if (pScores.get(x).equals(pScores.get(0)))
            {
                System.out.println
                (
                    "BUT WAIT! BEHOLD, AN EQUAL!"
                    + pNames.get(x) + " SHATTERS THE CURRENT REALITY WITH A SCORE OF "
                    + pScores.get(x) + "!!!"
                );
            }
            else
            {
                System.out.println
                (
                    "Somehow still not having left in shame : "
                    + pNames.get(x) + "!!!\n" + "With a score of : "
                    + pScores.get(x).toString() + "!!!"
                );
            }
        }

    }





    private static int takeTurn(Player player, CardDeck discardPile, CardDeck drawPile, ArrayList<Card> lastCardList)

    // TODO --- Have takeTurn return an int, add this int to a count, if the count reaches the playerList size

    {
        System.out.println("Player up : " + player.getName());

        if (player.getName().equals("HAL"))
        {
            Card topOfDiscardPile = discardPile.topOfDrawPile();
            ArrayList<Card> eightsInHand = new ArrayList<>();
            ArrayList<Card> sameSuitInHand = new ArrayList<>();
            ArrayList<Card> sameRankInHand = new ArrayList<>();
            int returnInt = 0;

            System.out.println("It's my turn now humans!!! BATHE IN MY GLORY!");

            for (Card card : player.getHand())
            {
                if (card.getRank() == topOfDiscardPile.getRank() && card.getRank() != Card.Ranks.Eight)
                    sameRankInHand.add(card);

                if (card.getSuit() == topOfDiscardPile.getSuit() && card.getRank() != Card.Ranks.Eight)
                    sameSuitInHand.add(card);

                if (card.getRank() == Card.Ranks.Eight)
                    eightsInHand.add(card);
            }


            if (sameRankInHand.size() != 0)
            //AI will try to first play a card of this same rank as the top card of the discard pile if it can, excluding an 8
            {
                Card cardToPlay1;
                Collections.shuffle(sameRankInHand);
                cardToPlay1 = sameRankInHand.get(0);

                cardToPlay1.AIplayInfo();
                discardPile.addCard(cardToPlay1);
                lastCardList.remove(0);
                lastCardList.add(cardToPlay1);

                player.getHand().remove(cardToPlay1);
                return returnInt;
            }

            if (sameSuitInHand.size() != 0 && sameRankInHand.size() == 0)
            //AI will next try to play a card of the same suit, excluding an 8
            {
                Card suitsCard = new Card(null, Card.Ranks.Two);
                int indexOfHighestRankedCard = 0;
                for (Card card : sameSuitInHand)
                {
                    if (card.getScore(card.getRank()) >= suitsCard.getScore(suitsCard.getRank()))
                    {
                        indexOfHighestRankedCard = sameSuitInHand.indexOf(card);
                    }
                }
                Card cardFromSuitHand = sameSuitInHand.get(indexOfHighestRankedCard);
                discardPile.addCard(cardFromSuitHand);
                lastCardList.remove(0);
                lastCardList.add(cardFromSuitHand);
                cardFromSuitHand.AIplayInfo();
                player.getHand().remove(cardFromSuitHand);

                return returnInt;
            }

            if ((eightsInHand.size() != 0) && (sameSuitInHand.size() == 0) && (sameRankInHand.size() == 0))
            {
                Card cardToPlay = new Card(null, null);
                Card.Suits[] suitsForAn8 = new Card.Suits[]{Card.Suits.Clubs, Card.Suits.Diamonds, Card.Suits.Hearts, Card.Suits.Spades};
                ArrayList<Card.Suits> suitsForAn8List = new ArrayList<>();
                Collections.addAll(suitsForAn8List, suitsForAn8);

                suitsForAn8List.remove(topOfDiscardPile.getSuit());

                cardToPlay.setRank(Card.Ranks.Eight);
                Collections.shuffle(suitsForAn8List);

                cardToPlay.setSuit(suitsForAn8List.get(0));
                suitsForAn8List.add(topOfDiscardPile.getSuit());
                cardToPlay.AIplayInfo();
                discardPile.addCard(cardToPlay);
                lastCardList.remove(0);
                lastCardList.add(cardToPlay);
                player.getHand().remove(eightsInHand.get(0));

                return returnInt;
            }

            else
            {
                if (drawPile.size() != 0)
                {
                    Card cardFromDrawPile = drawPile.topOfDrawPile();
                    player.getHand().add(cardFromDrawPile);
                    System.out.println("Well...I suppose I could draw a card...");
                    drawPile.removeCard(cardFromDrawPile);
                    return returnInt;
                }
                else return returnInt + 1;

            }
        }
        //HUMAN PLAYERS
        else
        {

            int returnInt = 0;
            int cardIndex = 0;
            ArrayList<Card> playerChoice = new ArrayList<>();

            for (Card card : player.getHand())
            {
                player.getHand().get(cardIndex);
                cardIndex += 1;
                playerChoice.add(card);
            }

            System.out.println("\n\nIt is " + player.getName() + "'s turn!");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

            System.out.println
            (
                "The top of the Discard Pile is : "
                + discardPile.getCard(0).getFullCard(discardPile.getCard(0)) + "\n\n"
            );

            int choice = 1;
            for (Card cardChosen : playerChoice)
            {
                System.out.println("Enter " + choice + " to select : " + cardChosen.getFullCard(cardChosen));
                choice += 1;
            }

            System.out.printf
            (
                "Enter %d to draw a card.\nEnter %d to pass your turn%n",
                player.getHand().size() + 1, player.getHand().size() + 2
            );

            Scanner scanner = new Scanner(System.in);
            int playersChoiceInt = scanner.nextInt();

            try
            {
                if ((playersChoiceInt < 1) || (playersChoiceInt > player.getHand().size() + 2))
                {
                    System.out.println("Your selection needs to be an integer from the menu!");
                    takeTurn(player, discardPile, drawPile, lastCardList);
                }

                if (playersChoiceInt == player.getHand().size() + 1)
                {
                    Card cardFromDrawPile = drawPile.topOfDrawPile();
                    player.getHand().add(0, cardFromDrawPile);
                    drawPile.removeCard(cardFromDrawPile);
                    System.out.printf
                    (
                        "%s drew a %s from the draw pile!\n%n",
                        player.getName(), cardFromDrawPile.getFullCard(cardFromDrawPile)
                    );

                    return returnInt;
                }

                if (playersChoiceInt == player.getHand().size() + 2)
                {
                    if (drawPile.size() != 0)
                    {
                        System.out.println("You cannot pass your turn while there are still cards left in the draw pile.");
                        takeTurn(player, discardPile, drawPile, lastCardList);
                    }
                    else
                    {
                        System.out.println(player.getName() + " has passed their turn!");
                        return returnInt + 1;
                    }


                }
                else
                {
                    Card cardChosen = player.getHand().get(playersChoiceInt - 1);

                    if (cardChosen.getRank() == Card.Ranks.Eight)
                    {
                        try
                        {
                            System.out.printf
                            (
                                "You've played an eight!\n " +
                                "Please select the suit for play : \n" +
                                "1 : Clubs    \n" +
                                "2 : Diamonds \n" +
                                "3 : Hearts   \n" +
                                "4 : Spades   \n" +
                                "%n"
                            );

                            Scanner switchScanner = new Scanner(System.in);

                            int switchSelection = switchScanner.nextInt();

                            while (switchSelection > 4 || 1 > switchSelection) switchScanner.hasNextInt();

                            switch (switchSelection)
                            {
                                case 1:

                                    System.out.println("Card for suit selection : " + cardChosen.getFullCard(cardChosen));
                                    cardChosen.setSuit(Card.Suits.Clubs);
                                    discardPile.addCard(cardChosen);
                                    player.getHand().remove(cardChosen);

                                    System.out.println
                                    (player.getName() + " played " + cardChosen.getFullCard(cardChosen) + "!\n");

                                    return returnInt;


                                case 2:
                                    System.out.println("Card for suit selection : " + cardChosen.getFullCard(cardChosen));
                                    cardChosen.setSuit(Card.Suits.Diamonds);
                                    discardPile.addCard(cardChosen);
                                    player.getHand().remove(cardChosen);

                                    System.out.println
                                    (player.getName() + " played " + cardChosen.getFullCard(cardChosen) + "!\n");

                                    return returnInt;


                                case 3:
                                    System.out.println("Card for suit selection : " + cardChosen.getFullCard(cardChosen));
                                    cardChosen.setSuit(Card.Suits.Hearts);
                                    discardPile.addCard(cardChosen);
                                    player.getHand().remove(cardChosen);

                                    System.out.println
                                    (player.getName() + " played " + cardChosen.getFullCard(cardChosen) + "!\n");

                                    return returnInt;


                                case 4:
                                    System.out.println("Card for suit selection : " + cardChosen.getFullCard(cardChosen));
                                    cardChosen.setSuit(Card.Suits.Spades);
                                    discardPile.addCard(cardChosen);
                                    player.getHand().remove(cardChosen);

                                    System.out.println
                                    (player.getName() + " played " + cardChosen.getFullCard(cardChosen) + "!\n");

                                    return returnInt;

                            }

                        }
                        catch (InputMismatchException ime)
                        {
                            System.out.println("Please choose a valid play!");
                            takeTurn(player, discardPile, drawPile, lastCardList);
                        }
                    }

                    else
                    {
                        if (validityCheck(cardChosen, discardPile, player, drawPile))
                        {
                            System.out.println
                            (
                                player.getName()
                                + " played : "
                                + cardChosen.getFullCard(cardChosen)
                                + " from their hand! \n\n"
                            );

                            discardPile.addCard(cardChosen);
                            player.getHand().remove(cardChosen);
                            return returnInt;
                        }

                        else
                        {
                            System.out.println("That is not a valid play, try again.");
                            takeTurn(player, discardPile, drawPile, lastCardList);
                        }
                    }
                }
            }
            catch (InputMismatchException ime)
            {
                System.out.println("Menu selection must be an integer from the menu!");
                takeTurn(player, discardPile, drawPile, lastCardList);
            }
            return returnInt;
        }
    }


    private static boolean validityCheck(Card cardChosen, CardDeck discardPile, Player player, CardDeck drawPileDeck)
    {
        if
        (
            cardChosen.getSuit() == discardPile.topOfDrawPile().getSuit()
            ||
            cardChosen.getRank() == discardPile.topOfDrawPile().getRank()
        )
        return true;

        else
        {
            System.out.println("That play is not valid, try again.\n\n");
            return false;
        }
    }
}
