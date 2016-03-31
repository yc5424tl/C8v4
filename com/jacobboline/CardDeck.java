package com.jacobboline;

import java.util.ArrayList;
import java.util.Collections;

public  class CardDeck {

    private ArrayList<Card> DeckOfCards;

        public CardDeck()
        {
            this.DeckOfCards = new ArrayList<>();

            for (Card.Suits suit : Card.Suits.values())
            {
                for (Card.Ranks rank : Card.Ranks.values())
                {
                    Card cardToAdd = new Card(suit, rank);
                    DeckOfCards.add(cardToAdd);
                }
            }
        }

        public Card getCard(int IndexValue)
        {
            return DeckOfCards.get(IndexValue);
        }

        public void removeDealtCards(ArrayList<Card> cardsToRemove)
        {
            this.DeckOfCards.removeAll(cardsToRemove);
        }

        public void clear()
        {
            this.DeckOfCards.clear();
        }

        public void removeCardAtIndex(int IndexValue)
        {
            this.DeckOfCards.remove(IndexValue);
        }
        public void deckInfo()
        {
            System.out.printf
            (
                "This decks' size is : %d\n"
                + " And contains the following cards"
                + " :%n", this.DeckOfCards.size()
            );


            for (Card card : this.DeckOfCards)
            {
                System.out.println(card.getFullCard(card));
            }
        }

        public void shuffle()
        {
            System.out.printf
            (
                "This decks' size is : %d\n "
                + "And contains the following cards"
                + " :%n", this.DeckOfCards.size()
            );


            /*for (Card card : this.DeckOfCards)
            {
                System.out.println(card.getFullCard(card));
            }*/

            System.out.println
            (
                "\n\n\n"
                + "Now we will shuffle the deck, "
                + "which has the size of "
                + this.DeckOfCards.size()
            );

            Collections.shuffle(this.DeckOfCards);

            /*System.out.println
            (
                "The shuffled decks' size is : "
                + this.DeckOfCards.size()
                + "\n And contains the following cards :"
            );*/


            /*for (Card card : this.DeckOfCards)
            {
                System.out.println(card.getFullCard(card));
            }*/

        }

        public int size()
        {
            return this.DeckOfCards.size();
        }

        public void cardsRemainingCount()
        {
            int deckSizeInt = this.DeckOfCards.size();
            String deckSize = "The number of remaining cards is : " + deckSizeInt;
            System.out.println(deckSize);
        }

        public void addCard(Card card)
        {
            this.DeckOfCards.add(0, card);
        }

        public void makeDrawPileFrom(CardDeck cardDeck)
        {

            for (int x =0 ; x < cardDeck.DeckOfCards.size() ; x++)
            {
                Card card = cardDeck.getCard(x);
                this.DeckOfCards.add(card);
            }

        }

        public void moveFromDrawToDiscard(CardDeck discardPile)
        {
            Card card = this.DeckOfCards.get(0);
            discardPile.addCard(card);
            this.DeckOfCards.remove(card);
            System.out.println
            (
                "The top card of the Discard Pile is a : \n"
                 + card.getFullCard(card)
            );
        }

        public Card topOfDrawPile()
        {
            return this.DeckOfCards.get(0);
        }

        public void removeCard(Card card)
        {
            this.DeckOfCards.remove(card);
        }


        /*public void toDrawPile(DrawPile drawPile)
        {
            drawPile.addAll(this.DeckOfCards);
            //this.DeckOfCards.clear();
        }*/

    }
