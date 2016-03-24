package com.jacobboline;

public class Card {



    public enum Suits {
        Clubs,
        Diamonds,
        Hearts,
        Spades,
    }

    public enum Ranks {
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

    public Card(Suits suit, Ranks rank){
        this.Suit = suit;
        this.Rank = rank;

    }

        public Suits getSuit(){
            return Suit;
        }

        public Ranks getRank(){
            return Rank;
        }

        public String getFullCard(Card card) {
            return card.getRank() + " of " + card.getSuit();
        }

        public void setSuit(Suits suit)
        {
            this.Suit = suit;
        }

        public int getScore(Ranks Rank){

            int Score = 0;

            switch (Rank) {

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

    }
