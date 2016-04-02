package com.jacobboline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

public class C8Manager {

    public static void main(String[] args) {
        /*if(args.length == 0)
        {
            System.out.println("Proper Usage is: java program filename");
        }*/

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

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }

        //System.out.println( playerList.playerNames(playerList) );

        startGame(playerList, Deck, lastCardPlayedAI);
        //didn't print the first time, but i also added it when the code was running
        System.out.println("Let's take a look here....");
        getScores(playerList);
    }


    private static boolean passCounter(int playResult, PlayerList playerList) //don't need to account for an empty draw pile since players cannot pass if there are cards left in it
    {
        boolean allPlayersPass; //= false;
        int passCounter = 0;

        if (playResult == 1) {
            passCounter++;
        } else {
            passCounter = 0;
        }

        allPlayersPass = passCounter == playerList.size();
        //above was suggested by intellij
        //previous is :
        // if (passCounter == playerList.size()) allPlayersPass = true;
        //else allPlayersPass = false;

        return allPlayersPass;
    }


    private static void startGame(PlayerList playerList, CardDeck deck, ArrayList<Card> lastCardPlayedList) {


        CardDeck drawPileDeck = new CardDeck();
        drawPileDeck.clear();
        CardDeck discardPileDeck = new CardDeck();
        discardPileDeck.clear();
        drawPileDeck.makeDrawPileFrom(deck);
        drawPileDeck.moveFromDrawToDiscard(discardPileDeck);
        boolean passCounterBoolean = true;
        //Card lastCardPlayedByAI = new Card(null, null);

        do {
            int passCounterInt = 0;
            //if (passCounter == playerList.size()) passCounter(passCounter, playerList);

            //TODO This format allows for the loop to finish even if one player has extinguished their cards
            //I should combine the takeTurn/AIlogic along with the first if/else below of the passOrNot method
            for (int p = 0 % playerList.size(); p < playerList.size(); p++) {
                Player playerUp = playerList.playerAt(p);
                int passOrNot = takeTurn(playerUp, discardPileDeck, drawPileDeck, lastCardPlayedList);
                if (passOrNot == 1) passCounterInt++;
                else passCounterInt = 0;

                /*if (p == playerList.size() - 1) {
                    Player AI = playerList.playerAt(playerList.size() - 1);
                    int AIpassOrNot = AI.computerLogic(AI, drawPileDeck, discardPileDeck, lastCardPlayedList);
                    if (AIpassOrNot == 1) passCounterInt++;
                    else passCounterInt = 0;
                } else {
                    Player playerUp = playerList.playerAt(p);
                    int passOrNot = playerUp.takeTurn(playerUp, discardPileDeck, drawPileDeck);

                    //passCounter(passOrNot, playerList);
                    if (passOrNot == 1) passCounterInt++;
                    else passCounterInt = 0;
                }*/

                if (!passCounter(passCounterInt, playerList)) {
                    continue;
                } else {
                    passCounterBoolean = false;
                }
            }
        }
        while ((handCheck(playerList)) && (passCounterBoolean));
    }


    private static boolean handCheck(PlayerList pList) {
        boolean keepPlaying = true;
        //AtomicBoolean keepPlaying = new AtomicBoolean();
        for (int p2 = 0; p2 < pList.size() - 1; p2++) {
            Player playerToCheck = pList.playerAt(p2);
            //System.out.println("Handcheck result for " + playerToCheck.getName() + " is " + (playerToCheck.getHandSize() != 0));
            if (playerToCheck.getHandSize() == 0) keepPlaying = false;
            //if (playerToCheck.getHandSize() == 0 ? false : true) keepPlaying = true;
            //else keepPlaying = false;
        }

        //if ()
        return keepPlaying;
    }


    private static void dealCards(PlayerList playerList, CardDeck deck) {

           /* System.out.println
            (
                "The dealCards function has been called\n"
                + "Deck before 'dealing : "
            );*/
        //deck.deckInfo();

        System.out.println("Shuffling cards here...without fingers. One sec!");
        int numberOfCardsToDeal;
            /*if (playerList.size() == 2) numberOfCardsToDeal = 7;
            else numberOfCardsToDeal = 5;*/
        if (playerList.size() == 2) numberOfCardsToDeal = 2;
        else numberOfCardsToDeal = 2;
        ArrayList<Card> cardsToRemove = new ArrayList<>();
        int indexOfCard = 0;
        int x = 0;

        while (x < playerList.size()) {

            Player player = playerList.playerAt(x);

            for (int y = 0; y < numberOfCardsToDeal; y++) {
                Card cardToDeal = deck.getCard(indexOfCard);
                player.addCard(cardToDeal);
                cardsToRemove.add(cardToDeal);
                indexOfCard += 1;
            }
            x++;
        }

        deck.removeDealtCards(cardsToRemove);
            /*System.out.println
            (
                "The removeDealtCards function has been called from within dealCards\n"
                + "The effects are : \n"
            );*/
        //deck.deckInfo();
    }


    private static void getPlayers(PlayerList pList) {

        Scanner scanner = new Scanner(System.in);

        System.out.println
                (
                        "Welcome to Crazy8s!\n\n"
                                + "You'll be playing against me, HAL! The computer!\n\n"
                                + "How many HUMANS will be STRUGGLING today?\n\n"
                                + "Please Enter 1, 2, or 3.\n\n"
                );


        int numPlayers = scanner.nextInt();
        while (numPlayers < 1 || numPlayers > 3) {
            scanner.hasNextInt();
        }
        if (numPlayers < 1 || numPlayers > 3)

        {
            System.out.println("Please Enter a Valid Selection");
            scanner.nextInt();
        }

        for (int p = 0; p < numPlayers; p++) {
            System.out.println("Please enter the name of the player to add.");
            String playerName = scanner.next();

            if (playerName == null) {
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

    private static void getScores(PlayerList pList) {
        //Integer currentBest = 0;
        ArrayList<Integer> pScores = new ArrayList<>();
        ArrayList<String> pNames = new ArrayList<>();


        for (int p = 0; p < pList.size(); p++) {

            Player player = pList.playerAt(p);
            //System.out.println("Player info : " + (player.getHandInfo()));

            //System.out.println("For getScores 'for' loop, pList size is : " + pList.size());

            //System.out.println("Player : " + player.getName());


            int thisScore = player.setScore();
            //System.out.println(player.getName() + "'s score is : " + player.getScore());
            int scorePlacement = 0;
            //System.out.println("Player.getScore() : " + player.getScore()); putting player.getscore in string caused it to run, doubling the score
            //System.out.println("Integer.getInteger(String.valueOf(thisScore) : " + Integer.getInteger(String.valueOf(thisScore)));
            Integer scoreAsInt = thisScore;

            //Integer asInt = Integer.getInteger(String.valueOf(thisScore));
           /* System.out.println("Integer of score = " + scoreAsInt.toString());
            System.out.println("Integer of score w/out string cast : " + scoreAsInt);*/

            //System.out.println("Is !pScores.isEmpty : " + !pScores.isEmpty());
            if (!pScores.isEmpty()) {
                //System.out.println("Iterating through scores to find placement");
                //Integer asInt = Integer.getInteger(String.valueOf(thisScore));
                for (Integer score : pScores) {
                    if (scoreAsInt > score) {
                        scorePlacement++;
                    }
                }
                //System.out.println("pScores.add(scorePlacement, scoreAsInt)");
                pScores.add(scorePlacement, scoreAsInt);
                //System.out.println("Testing Result : pScore - " + pScores.get(scorePlacement));
                //System.out.println("Next : pNames.add(scorePlacement, player.getName());");
                pNames.add(scorePlacement, player.getName());
                //System.out.println("Testing Result : " + pNames.get(scorePlacement));

            } else {
                //System.out.println("Adding first score to lists");
                pScores.add(scorePlacement, scoreAsInt);
                pNames.add(scorePlacement, player.getName());
            }

        }

        System.out.println
                (
                        "The winner of all things good in the world is "
                                + pNames.get(0).toUpperCase() + "!!!\n"
                                + "With an astounding, yet fitting, score of : " + pScores.get(0).toString() + "!!!"
                );

        for (int x = 1; x < pNames.size(); x++) {
            if (pScores.get(x).equals(pScores.get(0))) {
                System.out.println(
                        "BUT WAIT! BEHOLD, AN EQUAL!"
                                + pNames.get(x) + " SHATTERS THE CURRENT REALITY WITH A SCORE OF "
                                + pScores.get(x) + "!!!");
            } else {
                System.out.println(
                        "Somehow still not having left in shame : "
                                + pNames.get(x) + "!!!\n" + "With a score of : "
                                + pScores.get(x).toString() + "!!!");
            }
        }

    }





    private static int takeTurn(Player player, CardDeck discardPile, CardDeck drawPile, ArrayList<Card> lastCardList)

    // TODO --- Have takeTurn return an int, add this int to a count, if the count reaches the playerList size
    // i.e. every player has passed their turn, AND the drawPile is empty, call a getScore() method

    {
        System.out.println("Does player.getName().equals('HAL') : " + player.getName().equals("HAL"));
        System.out.println("Player up : " + player.getName());
        if (player.getName().equals("HAL"))
        {
            Card topOfDiscardPile = discardPile.topOfDrawPile();
            ArrayList<Card> eightsInHand = new ArrayList<>();
            ArrayList<Card> sameSuitInHand = new ArrayList<>();
            ArrayList<Card> sameRankInHand = new ArrayList<>();
            //Card lastCardPlayed = new Card (null, null);
            int returnInt = 0;

            //AtomicReference<Card> cardToPlay = new AtomicReference<>(new Card(null, null));


            System.out.println("It's my turn now humans!!! Bathe in my glory!");

            for (Card card : player.getHand()) {
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
                System.out.println("Last card AI player : " + lastCardList.get(0).getFullCard(lastCardList.get(0)));
                //lastCardPlayed = cardToPlay1;
                cardToPlay1.AIplayInfo();
                discardPile.addCard(cardToPlay1);
                lastCardList.remove(0);
                lastCardList.add(cardToPlay1);
                System.out.println("After AI play, Last card is : " + lastCardList.get(0).getFullCard(lastCardList.get(0)));
                //System.out.println("A before playing a card : " + AI.getHandInfo());
                player.getHand().remove(cardToPlay1);
                return returnInt;
                //System.out.println("A after playing a card : " + AI.getHandInfo());

            }


            if (sameSuitInHand.size() != 0 && sameRankInHand.size() == 0)
            //AI will next try to play a card of the same suit, excluding an 8
            {
                Card suitsCard = new Card(null, Card.Ranks.Two);
                int indexOfHighestRankedCard = 0;
                for (Card card : sameSuitInHand) {
                    if (card.getScore(card.getRank()) >= suitsCard.getScore(suitsCard.getRank())) {
                        indexOfHighestRankedCard = sameSuitInHand.indexOf(card);
                    }
                }
                Card cardFromSuitHand = sameSuitInHand.get(indexOfHighestRankedCard);
                System.out.println("Last card AI player : " + lastCardList.get(0).getFullCard(lastCardList.get(0)));
                //lastCardPlayed = cardFromSuitHand;
                discardPile.addCard(cardFromSuitHand);
                lastCardList.remove(0);
                lastCardList.add(cardFromSuitHand);
                System.out.println("After AI play, Last card is : " + lastCardList.get(0).getFullCard(lastCardList.get(0)));
                cardFromSuitHand.AIplayInfo();
                //System.out.println("A before playwing a card : " + AI.getHandInfo());
                player.getHand().remove(cardFromSuitHand);
                return returnInt;

                //System.out.println("A after playing a card : " + AI.getHandInfo());

            }

            if ((eightsInHand.size() != 0) && (sameSuitInHand.size() == 0) && (sameRankInHand.size() == 0))
            //Card.Suits[] suitsForAn8 = new Card.Suits[]{Card.Suits.Clubs, Card.Suits.Diamonds, Card.Suits.Hearts, Card.Suits.Spades};
            {
                Card cardToPlay = new Card(null, null);
                //Random random = new Random();
                Card.Suits[] suitsForAn8 = new Card.Suits[]{Card.Suits.Clubs, Card.Suits.Diamonds, Card.Suits.Hearts, Card.Suits.Spades};
                ArrayList<Card.Suits> suitsForAn8List = new ArrayList<>();


                Collections.addAll(suitsForAn8List, suitsForAn8);

                System.out.println("SuitsForAn 8 contains : " + suitsForAn8List.size());
                for (Card.Suits suit : suitsForAn8List) {
                    System.out.println(suit.toString());
                }

                System.out.println("Result of topOfDiscardPile.getSuit().toString() : "
                        + topOfDiscardPile.getSuit().toString());

                suitsForAn8List.remove(topOfDiscardPile.getSuit());

                System.out.println("SuitsForAn 8 contains : " + suitsForAn8List.size());
                for (Card.Suits suit : suitsForAn8List) {
                    System.out.println(suit.toString());
                }

                cardToPlay.setRank(Card.Ranks.Eight);
                //Card.Suits suitFor8 = suitsForAn8List.get(random.nextInt(0 - suitsForAn8List.size()));
                Collections.shuffle(suitsForAn8List);

                System.out.println("After shuffle : ");

                System.out.println("SuitsForAn 8 contains : " + suitsForAn8List.size());
                for (Card.Suits suit : suitsForAn8List) {
                    System.out.println(suit.toString());
                }

                cardToPlay.setSuit(suitsForAn8List.get(0));
                suitsForAn8List.add(topOfDiscardPile.getSuit()); //added back to avoid "illegal-argument-exception bound must be positive"
                cardToPlay.AIplayInfo();
                System.out.println("Last card AI player : " + lastCardList.get(0).getFullCard(lastCardList.get(0)));
                //lastCardPlayed = cardToPlay;
                //System.out.println("After AI play, Last card is : " + lastCardList.get(0).getFullCard(lastCardList.get(0)));
                discardPile.addCard(cardToPlay);
                lastCardList.remove(0);
                lastCardList.add(cardToPlay);
                System.out.println("After AI play, Last card is : " + lastCardList.get(0).getFullCard(lastCardList.get(0)));

                //System.out.println("A before playing a card : " + AI.getHandInfo());
                player.getHand().remove(eightsInHand.get(0));
                return returnInt;
                //System.out.println("A after  playing a card : " + AI.getHandInfo());




            /*cardToPlay = eightsInHand.get(random.nextInt(0 - eightsInHand.size()));
            System.out.println("Random card from AI if it needs to play an eight is : " + cardToPlay.getFullCard(cardToPlay));
            System.out.println("This is out of the selection of : ");*/


            }

            else
            {

            /*if ((discardPile.getCard(0).getRank() != Card.Ranks.Eight) && (discardPile.getCard(0) != lastCardPlayed))
            {
                Card cardFromDiscard = discardPile.getCard(0);
                AI.Hand.add(cardFromDiscard);
                cardFromDiscard.AIplayInfo();
                discardPile.removeCard(cardFromDiscard);
                if (discardPile.size() == 0) drawPile.moveFromDrawToDiscard(discardPile);

                return returnInt;

            }
            else
            {*/
                if (drawPile.size() != 0)
                {
                    Card cardFromDrawPile = drawPile.topOfDrawPile();
                    player.getHand().add(cardFromDrawPile);
                    //cardFromDrawPile.AIplayInfo();
                    System.out.println("Well...I suppose I could draw a card...");
                    drawPile.removeCard(cardFromDrawPile);
                    return returnInt;
                }
                else
                {
                    return returnInt + 1;
                }
            }
        }




        //HUMAN PLAYERS
        else {


            int returnInt = 0;
            int cardIndex = 0;
            //System.out.println(player.getHandInfo());
            ArrayList<Card> playerChoice = new ArrayList<>();

            for (Card card : player.getHand()) {
                player.getHand().get(cardIndex);
                cardIndex += 1;
                playerChoice.add(card);
            }

            System.out.println("\n\nIt is " + player.getName() + "'s turn!");
        /*System.out.println(
                "Player and Game Info : \n"
                + "Hand Info : " +player.getHandInfo()
                + "\n DrawPile.toString() and size : " + drawPile.toString() + drawPile.size()
                + "\n Discard Pile Size : " + discardPile.size());*/


            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }


            //Card topDiscard = discardPile.getCard(0);
            //String topDiscardInfo = topDiscard.getFullCard(topDiscard);

       /* System.out.printf(String.format
            (
            "\n\nEnter the number of the play you wish to make." +
            "\n\nEnter 0 (Zero) to select : %%s" +
            " from the discard pile%%n"), topDiscardInfo
            );*/
            System.out.println
                    (
                            "The top of the Discard Pile is : "
                                    + discardPile.getCard(0).getFullCard(discardPile.getCard(0)) + "\n\n"
                    );


            int choice = 1;
            for (Card cardChosen : playerChoice) {
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


            try {

                if ((playersChoiceInt < 1) || (playersChoiceInt > player.getHand().size() + 2)) {
                    System.out.println("Your selection needs to be an integer from the menu!");
                    takeTurn(player, discardPile, drawPile, lastCardList);
                }

            /*if (playersChoiceInt == 0)
            {
                String cardPlayed = discardPile.getCard(0).getFullCard(discardPile.getCard(0));
                this.Hand.add(discardPile.topOfDrawPile());
                System.out.printf
                        (
                                player.Name + " added " + cardPlayed + " to their hand from the discard pile!"
                        );
                System.out.println("Card before removal : " + cardPlayed);
                System.out.println
                        (
                        "Discard Pile Info : \n"
                        + "Size : " + discardPile.size() + "\n Below is From the deckInfo method : \n"
                        );
                discardPile.deckInfo();
                discardPile.removeCardAtIndex(0);
                drawPile.moveFromDrawToDiscard(discardPile);
                System.out.println("Card removed, top of discard pile is now a : " + discardPile.getCard(0).getFullCard(discardPile.getCard(0)));

                 return returnInt;
            }*/

                if (playersChoiceInt == player.getHand().size() + 1) {
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


                //TODO Need to enforce that a player can only pass their turn if non of their cards are legal, and the draw pile is empty
                //TODO Might as well make a function to check for validity of this.suit/rank == foo.suit/rank

                if (playersChoiceInt == player.getHand().size() + 2) {
                    if (drawPile.size() != 0) {
                        System.out.println("You cannot pass your turn while there are still cards left in the draw pile.");
                        takeTurn(player, discardPile, drawPile, lastCardList);
                    } else {
                        System.out.println(player.getName() + " has passed their turn!");
                        return returnInt + 1;
                    }


                } else {
                    Card cardChosen = player.getHand().get(playersChoiceInt - 1);

                    if (cardChosen.getRank() == Card.Ranks.Eight) {
                        try {
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

                            switch (switchSelection) {
                                case 1:

                                    System.out.println(
                                            "Card for suit selection : "
                                                    + cardChosen.getFullCard(cardChosen));

                                    cardChosen.setSuit(Card.Suits.Clubs);

                                    System.out.println(
                                            "Card after 'club' suit selection : "
                                                    + cardChosen.getFullCard(cardChosen));

                                    Card cardFromDiscardPile = discardPile.topOfDrawPile();

                                    System.out.println(
                                            "Discard pile top before card added : "
                                                    + cardFromDiscardPile.getFullCard(cardFromDiscardPile));

                                    discardPile.addCard(cardChosen);

                                    Card cardToDiscardPile = discardPile.topOfDrawPile();

                                    System.out.println(
                                            "After card added : "
                                                    + cardFromDiscardPile.getFullCard(cardToDiscardPile));

                                    player.getHand().remove(cardChosen);

                                    System.out.println(
                                            player.getName() + " played " +
                                                    cardChosen.getFullCard(cardChosen) + "!\n");
                                    return returnInt;


                                case 2:
                                    System.out.println(
                                            "Card for suit selection : "
                                                    + cardChosen.getFullCard(cardChosen));

                                    cardChosen.setSuit(Card.Suits.Diamonds);

                                    System.out.println(
                                            "Card after 'diamond' suit selection : "
                                                    + cardChosen.getFullCard(cardChosen));

                                    Card cardFromDiscardPile1 = discardPile.topOfDrawPile();

                                    System.out.println(
                                            "Discard pile top before card added : "
                                                    + cardFromDiscardPile1.getFullCard(cardFromDiscardPile1));

                                    discardPile.addCard(cardChosen);

                                    Card cardToDiscardPile1 = discardPile.topOfDrawPile();

                                    System.out.println(
                                            "After card added : "
                                                    + cardToDiscardPile1.getFullCard(cardToDiscardPile1));

                                    player.getHand().remove(cardChosen);

                                    System.out.println(
                                            player.getName() + " played " +
                                                    cardChosen.getFullCard(cardChosen) + "!\n");
                                    return returnInt;


                                case 3:
                                    System.out.println(
                                            "Card for suit selection : "
                                                    + cardChosen.getFullCard(cardChosen));

                                    cardChosen.setSuit(Card.Suits.Hearts);

                                    System.out.println(
                                            "Card after 'heart' suit selection : "
                                                    + cardChosen.getFullCard(cardChosen));

                                    Card cardFromDiscardPile2 = discardPile.topOfDrawPile();

                                    System.out.println(
                                            "Discard pile top before card added : " +
                                                    cardFromDiscardPile2.getFullCard(cardFromDiscardPile2));

                                    discardPile.addCard(cardChosen);

                                    Card cardToDiscardPile2 = discardPile.topOfDrawPile();

                                    System.out.println(
                                            "After card added : "
                                                    + cardToDiscardPile2.getFullCard(cardToDiscardPile2));

                                    player.getHand().remove(cardChosen);

                                    System.out.println(
                                            player.getName() + " played " +
                                                    cardChosen.getFullCard(cardChosen) + "!\n");
                                    return returnInt;


                                case 4:
                                    System.out.println(
                                            "Card for suit selection : "
                                                    + cardChosen.getFullCard(cardChosen));

                                    cardChosen.setSuit(Card.Suits.Spades);

                                    System.out.println(
                                            "Card after 'spade' suit selection : "
                                                    + cardChosen.getFullCard(cardChosen));

                                    Card cardFromDiscardPile3 = discardPile.topOfDrawPile();

                                    System.out.println(
                                            "Discard pile top before card added : " +
                                                    cardFromDiscardPile3.getFullCard(cardFromDiscardPile3));

                                    discardPile.addCard(cardChosen);

                                    Card cardToDiscardPile3 = discardPile.topOfDrawPile();

                                    System.out.println(
                                            "After card added : "
                                                    + cardToDiscardPile3.getFullCard(cardToDiscardPile3));

                                    player.getHand().remove(cardChosen);

                                    System.out.println(
                                            player.getName() + " played " +
                                                    cardChosen.getFullCard(cardChosen) + "!\n");
                                    return returnInt;

                            }

                        } catch (InputMismatchException ime) {
                            System.out.println("Please choose a valid play!");
                            takeTurn(player, discardPile, drawPile, lastCardList);
                        }
                    } else {

                        if (validityCheck(cardChosen, discardPile, player, drawPile)) {
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
                        } else {
                            System.out.println("That is not a valid play, try again.");
                            takeTurn(player, discardPile, drawPile, lastCardList);
                        }
                    }
                }
            } catch (InputMismatchException ime) {
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
            //takeTurn(player, discardPile, drawPileDeck);
            return false;
        }
    }
}
