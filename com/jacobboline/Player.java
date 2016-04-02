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

    public ArrayList<Card> getHand()
    {
        return this.Hand;
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

    public int setScore()
    {
        System.out.println("Is this players hand empty : " + this.Hand.isEmpty());
        if(this.Hand.isEmpty()) this.Score = 0;
        else for (Card card : this.Hand) {
            System.out.println("Adding to score : " + card.getScore(card.getRank()));
            this.Score += card.getScore(card.getRank());
        }
        return this.Score;
    }

    public void setBaseScore()
    {
        this.Score = 0;
    }


    public int getScore()
    {
        return this.Score;
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

























   /* public void playCardFromHand(Card card, Player player, CardDeck discardPile)
    {
        player.Hand.remove(card);
        discardPile.addCard(card);
    }*/









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





