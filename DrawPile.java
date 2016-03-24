package com.jacobboline;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public class DrawPile {

    private LinkedList<Card> drawPileObject;

        public Card getCard(int IndexValue)
        {
            return drawPileObject.get(IndexValue);
        }

        public void removeCard(Card cardToRemove)
        {
            this.drawPileObject.remove(cardToRemove);
        }

        public void shuffleDrawPile()
        {
            Collections.shuffle(this.drawPileObject);
        }

        public void addAll(CardDeck deck)
    {
        int deckSize = deck.size();
        for ( int x = 0; x < deckSize; x++ ) {
            Card card = deck.getCard(x);
            this.drawPileObject.add(card);
        }

    }

        public void showTopCard()
        {
            Card topCard = this.drawPileObject.peek();
            System.out.println("The ");
        }

        public void printDrawPileSize()
        {
            int deckSizeInt = this.drawPileObject.size();
            String Size = "The number of remaining cards in the draw pile is : " + deckSizeInt;
            System.out.println(Size);
        }
}
