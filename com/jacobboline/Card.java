package com.jacobboline;

public class Card {

    public enum Suits
    {
        Clubs,
        Diamonds,
        Hearts,
        Spades,
    }

    public enum Ranks
    {
        Two,
        Three,
        Four,
        Five,
        Six,
        Seven,
        Eight,
        Nine,
        Ten,
        Jack,
        Queen,
        King,
        Ace,
    }

    private Suits Suit;
    private Ranks Rank;

    public Card(Suits suit, Ranks rank)
    {
        this.Suit = suit;
        this.Rank = rank;
    }

        public Suits getSuit()
        {
            return Suit;
        }

        public Ranks getRank()
        {
            return Rank;
        }

        public void setRank(Ranks rank)
        {
            this.Rank = rank;
        }

        public String getFullCard(Card card)
        {
            return String.format("%s of %s", card.getRank(), card.getSuit());
        }

        public void setSuit(Suits suit)
        {
            this.Suit = suit;
        }

        public void AIplayInfo ()
        {
            System.out.println("BEHOLD, I HAVE CHOSEN TO PLAY THE MIGHTY " + this.getFullCard(this) + "\n\n");
        }

        public int getScore(Ranks Rank)
        {
            int Score = 0;

            switch (Rank)
            {

                case Two:
                    Score = 2;
                    return Score;
                case Three:
                    Score = 3;
                    return Score;
                case Four:
                    Score = 4;
                    return Score;
                case Five:
                    Score = 5;
                    return Score;
                case Six:
                    Score = 6;
                    return Score;
                case Seven:
                    Score = 7;
                    return Score;
                case Eight:
                    Score = 50;
                    return Score;
                case Nine:
                    Score = 9;
                    return Score;
                case Ten:
                    Score = 10;
                    return Score;
                case Jack:
                    Score = 10;
                    return Score;
                case Queen:
                    Score = 10;
                    return Score;
                case King:
                    Score = 10;
                    return Score;
                case Ace:
                    Score = 10;
                    return Score;

            }

            return Score;
        }
    }




