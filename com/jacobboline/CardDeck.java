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

        public void shuffle()
        {
            Collections.shuffle(this.DeckOfCards);
        }

        public int size()
        {
            return this.DeckOfCards.size();
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
        }

        public Card topOfDrawPile()
        {
            return this.DeckOfCards.get(0);
        }

        public void removeCard(Card card)
        {
            this.DeckOfCards.remove(card);
        }

    }
