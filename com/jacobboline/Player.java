package com.jacobboline;

import java.util.*;
//import java.util.concurrent.atomic.AtomicReference;

public class Player {

    private String Name;
    private ArrayList<Card> Hand;
    private int Score;

    public Player() {}

    public void setHand(ArrayList<Card> newHand)
    {
        this.Hand = newHand;
    }

    public void setName(String newName)
    {
        this.Name = newName;
    }

    public String getName()
    {
        return this.Name;
    }

    public String getHandInfo()
    {
        String cardString = "***************\n"
                            + this.getName()
                            + "'s hand contains: \n";

        for (Card card : this.Hand) cardString += (card.getFullCard(card) + " \n");

        cardString += "***************\n";

        return cardString;
    }

    public void addCard(Card card)
    {
        this.Hand.add(card);
    }

    /*public void playCard(Card card)
    {
        this.Hand.remove(card);
    }*/

    public int getScore()
    {
        return this.Score;
    }

    public void setScore(int newScore)
    {
        this.Score += newScore;
    }

   // public void getCard(int x)
    /*TODO there is a note on get card while loop in block comment below this
    can i use this to end the do/while loop...ie this.getCard == null/exception
    * if this is placed at the end of the do loop, it should break?
    * the alternative in mind is this :
    * if : for player in player list :
    * if player.hand == empty : return/break
    * this would then move to the next function within c8man, which would calculate the scores.
    */
    /*{
        this.Hand.get(x);
    }*/

    public int getHandSize()
    {
        return this.Hand.size();
    }


    private boolean validityCheck(Card cardChosen, CardDeck discardPile, Player player, CardDeck drawPileDeck)
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
        takeTurn(player, discardPile, drawPileDeck);
        return false;
        }
    }











    public int takeTurn(Player player, CardDeck discardPile, CardDeck drawPile)

    // TODO --- Have takeTurn return an int, add this int to a count, if the count reaches the playerList size
    // i.e. every player has passed their turn, AND the drawPile is empty, call a getScore() method

    {
        int returnInt = 0;
        int cardIndex = 0;
        //System.out.println(player.getHandInfo());
        ArrayList<Card> playerChoice = new ArrayList<>();

        for (Card card : this.Hand)
        {
            this.Hand.get(cardIndex);
            cardIndex += 1;
            playerChoice.add(card);
        }

        System.out.println("\n\nIt is " + player.getName() + "'s turn!");
        /*System.out.println(
                "Player and Game Info : \n"
                + "Hand Info : " +player.getHandInfo()
                + "\n DrawPile.toString() and size : " + drawPile.toString() + drawPile.size()
                + "\n Discard Pile Size : " + discardPile.size());*/


            try
            {
                Thread.sleep(1000);
            }

            catch (InterruptedException ex)
            {
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
        for (Card cardChosen : playerChoice)
        {
            System.out.println("Enter " + choice + " to select : " + cardChosen.getFullCard(cardChosen));
            choice += 1;
        }

        System.out.printf
            (
            "Enter %d to draw a card.\nEnter %d to pass your turn%n",
            this.Hand.size() + 1, this.Hand.size() + 2
            );

        Scanner scanner = new Scanner(System.in);

        int playersChoiceInt = scanner.nextInt();





        try
        {

            if ((playersChoiceInt < 1) || (playersChoiceInt > this.Hand.size() + 2))
            {
                System.out.println("Your selection needs to be an integer from the menu!");
                player.takeTurn(player, discardPile, drawPile);
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

            if (playersChoiceInt == this.Hand.size() + 1)
            {
                Card cardFromDrawPile = drawPile.topOfDrawPile();
                this.Hand.add(0,cardFromDrawPile);
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

            if (playersChoiceInt == this.Hand.size() + 2)
            {
                if (drawPile.size() != 0)
                {
                    System.out.println("You cannot pass your turn while there are still cards left in the draw pile.");
                    player.takeTurn(player, discardPile, drawPile);
                }
                else
                {
                    System.out.println(player.getName() + " has passed their turn!");
                    return returnInt + 1;
                }


            }

            else
            {
                Card cardChosen = this.Hand.get(playersChoiceInt - 1);

                if (cardChosen.getRank() == Card.Ranks.Eight)
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

                            player.Hand.remove(cardChosen);

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

                            player.Hand.remove(cardChosen);

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

                            player.Hand.remove(cardChosen);

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

                            player.Hand.remove(cardChosen);

                            System.out.println(
                                    player.getName() + " played " +
                                    cardChosen.getFullCard(cardChosen) + "!\n");
                            return returnInt;

                    }

                }

                catch (InputMismatchException ime)
                {
                    System.out.println("Please choose a valid play!");
                    takeTurn(player, discardPile, drawPile);
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
                        player.Hand.remove(cardChosen);
                        return returnInt;
                    }
                    else
                    {
                        System.out.println("That is not a valid play, try again.");
                        player.takeTurn(player, discardPile, drawPile);
                    }
                }
            }
        }
        catch (InputMismatchException ime)
        {
            System.out.println("Menu selection must be an integer from the menu!");
            player.takeTurn(player, discardPile, drawPile);
        }

        return returnInt;
    }










   /* public void playCardFromHand(Card card, Player player, CardDeck discardPile)
    {
        player.Hand.remove(card);
        discardPile.addCard(card);
    }*/








    public int computerLogic(Player AI, CardDeck drawPile, CardDeck discardPile, ArrayList<Card> lastCardList) {
    //TODO the for loop ATM would go through every card in the AI hand, and make a play, should use a do/while loop to break it after a card is played
            Card topOfDiscardPile = discardPile.topOfDrawPile();
            ArrayList<Card> eightsInHand = new ArrayList<>();
            ArrayList<Card> sameSuitInHand = new ArrayList<>();
            ArrayList<Card> sameRankInHand = new ArrayList<>();
            //Card lastCardPlayed = new Card (null, null);
            int returnInt = 0;
            //AtomicReference<Card> cardToPlay = new AtomicReference<>(new Card(null, null));




        System.out.println("It's my turn now humans!!! Bathe in my glory!");

        for (Card card : this.Hand)
        {
            if (card.getRank() == topOfDiscardPile.getRank() && card.getRank() != Card.Ranks.Eight)
                sameRankInHand.add(card);

            if (card.getSuit() == topOfDiscardPile.getSuit() && card.getRank() != Card.Ranks.Eight)
                sameSuitInHand.add(card);

            if (card.getRank() == Card.Ranks.Eight)
                eightsInHand.add(card);
        }


        if (sameRankInHand.size() != 0 )
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
            AI.Hand.remove(cardToPlay1);
            return returnInt;
            //System.out.println("A after playing a card : " + AI.getHandInfo());

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
            System.out.println("Last card AI player : " + lastCardList.get(0).getFullCard(lastCardList.get(0)));
            //lastCardPlayed = cardFromSuitHand;
            discardPile.addCard(cardFromSuitHand);
            lastCardList.remove(0);
            lastCardList.add(cardFromSuitHand);
            System.out.println("After AI play, Last card is : " + lastCardList.get(0).getFullCard(lastCardList.get(0)));
            cardFromSuitHand.AIplayInfo();
            //System.out.println("A before playing a card : " + AI.getHandInfo());
            AI.Hand.remove(cardFromSuitHand);
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
                for (Card.Suits suit : suitsForAn8List)
                {
                System.out.println(suit.toString());
                }

            cardToPlay.setRank(Card.Ranks.Eight);
            //Card.Suits suitFor8 = suitsForAn8List.get(random.nextInt(0 - suitsForAn8List.size()));
            Collections.shuffle(suitsForAn8List);

                System.out.println("After shuffle : ");

                System.out.println("SuitsForAn 8 contains : " + suitsForAn8List.size());
                for (Card.Suits suit : suitsForAn8List)
                {
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
            AI.Hand.remove(cardToPlay);
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
                    AI.Hand.add(cardFromDrawPile);
                    cardFromDrawPile.AIplayInfo();
                    drawPile.removeCard(cardFromDrawPile);
                    return returnInt;
                }
                else
                {
                    return returnInt + 1;
                }
            }

            //System.out.println("looks like HAL can't play shit");
        }
        //System.out.println("The last card played by the AI was : " + lastCardPlayed.getFullCard(lastCardPlayed));




        //System.out.println("I have made the bold selection of : " + cardToPlay.getFullCard(cardToPlay));

       // System.out.println("AI turn done");



       /* {
            //for ( Card cardWithSimilarSuit : sameSuitInHand ) {
            //sameSuitInHand.stream().filter(cardWithSuit -> cardWithSuit.getSuit() == topOfDiscardPile.getSuit()).forEach(cardToPlay::set);
            //System.out.println(cardToPlay.toString());
            //This was suggested by intelliJ, it seems to work the same as an advanced for loop!
            //sameSuitInHand.stream().filter(cardWithSuit -> cardWithSuit.getSuit() == topOfDiscardPile.getSuit()).forEach(cardToPlay::set);

            //if (cardWithSimilarRank.)
                //cardToPlay.getAndSet(cardWithSimilar);
                bestWithRank = cardWithSimilarRank;

            //if (cardWithSimilar .getSuit() == topOfDiscardPile.getSuit())
                //cardToPlay.getAndSet(cardWithSimilar);

            //if (cardWithSimilar .getRank() == topOfDiscardPile.getRank() && cardWithSimilar .getSuit() == topOfDiscardPile.getSuit())
            //{
                //cardToPlay.getAndSet(cardWithSimilar);
            //}

        }*/


    }





