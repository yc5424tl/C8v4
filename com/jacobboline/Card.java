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
            System.out.println("BEHOLD : I HAVE CHOSEN TO PLAY MY MIGHTY : " + this.getFullCard(this));
        }




        public int getScore(Ranks Rank)
        {

            int Score = 0;

            switch (Rank)
            {

                case Two:
                    Score = 2;

                    break;
                case Three:
                    Score = 3;
                    break;
                case Four:
                    Score = 4;
                    break;
                case Five:
                    Score = 5;
                    break;
                case Six:
                    Score = 6;
                    break;
                case Seven:
                    Score = 7;
                    break;
                case Eight:
                    Score = 50;
                    break;
                case Nine:
                    Score = 9;
                    break;
                case Ten:
                    Score = 10;
                    break;
                case Jack:
                    Score = 10;
                    break;
                case Queen:
                    Score = 10;
                    break;
                case King:
                    Score = 10;
                    break;
                case Ace:
                    Score = 10;
                    break;
            }

            return Score;
    }

    public int getAIplayScore(Ranks Rank)
    {

        int AIplayScore = 0;

        switch (Rank)

        {

            case Two:
                AIplayScore = 2;
                break;
            case Three:
                AIplayScore = 3;
                break;
            case Four:
                AIplayScore = 4;
                break;
            case Five:
                AIplayScore = 5;
                break;
            case Six:
                AIplayScore = 6;
                break;
            case Seven:
                AIplayScore = 7;
                break;
            case Nine:
                AIplayScore = 8;
                break;
            case Ten:
                AIplayScore = 9;
                break;
            case Jack:
                AIplayScore = 10;
                break;
            case Queen:
                AIplayScore = 11;
                break;
            case King:
                AIplayScore = 12;
                break;
            case Ace:
                AIplayScore = 13;
                break;
        }

        return AIplayScore;
    }

}

