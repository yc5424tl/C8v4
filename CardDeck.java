package com.jacobboline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;


public class CardDeck {

    private ArrayList<Card> DeckOfCards;

        public CardDeck() {
            this.DeckOfCards = new ArrayList<>();
            for (Card.Suits suit : Card.Suits.values()) {
                for (Card.Ranks rank : Card.Ranks.values()) {
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

        public void shuffle()
        {
           Collections.shuffle(this.DeckOfCards);
        }

        public int size()
        {
            int size = this.DeckOfCards.size();
            return size;
        }

        public void printDeckSize()
        {
            int deckSizeInt = this.DeckOfCards.size();
            String deckSize = "The number of remaining cards is : " + deckSizeInt;
            System.out.println(deckSize);
        }

        public void addCard(Card card)
        {
            this.DeckOfCards.add(card);
        }

        public void makeDrawPileFrom(CardDeck cardDeck)
        {
            //CardDeck drawPileDeck = new CardDeck();
            for (int x =0 ; x < cardDeck.DeckOfCards.size() ; x++) {
                Card card = cardDeck.getCard(x);
                this.DeckOfCards.add(card);
            }

        }

        public void moveFromDrawToDiscard(CardDeck discardPile)
        {
            Card card = this.DeckOfCards.get(0);
            discardPile.addCard(card);
            this.DeckOfCards.remove(card);
            System.out.println(
                    "The top card of the Discard Pile is a : \n"
                     + card.getFullCard(card));
        }

        public Card topOfDrawPile()
        {
            Card topCard = this.DeckOfCards.get(0);
            return topCard;
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
