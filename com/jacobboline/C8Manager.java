package com.jacobboline;

import java.util.ArrayList;
import java.util.Scanner;

public class C8Manager
{

    public static void main(String[] args)
    {
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

            try
            {
                Thread.sleep(1000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }

        //System.out.println( playerList.playerNames(playerList) );

        startGame(playerList, Deck, lastCardPlayedAI);
        //didn't print the first time, but i also added it when the code was running
        System.out.println("Thanks for playing!");
    }














    private static boolean passCounter(int playResult, PlayerList playerList) //don't need to account for an empty draw pile since players cannot pass if there are cards left in it
    {
        boolean allPlayersPass; //= false;
        int passCounter = 0;

        if (playResult == 1)
        {
            passCounter++;
        }
        else
        {
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
                if (p == playerList.size() - 1) {
                    Player AI = playerList.playerAt(playerList.size() - 1);
                    int AIpassOrNot = AI.computerLogic(AI, drawPileDeck, discardPileDeck, lastCardPlayedList);
                    if (AIpassOrNot == 1) passCounterInt++;
                    else passCounterInt = 0;
                }
                else
                {
                    Player playerUp = playerList.playerAt(p);
                    int passOrNot = playerUp.takeTurn(playerUp, discardPileDeck, drawPileDeck);

                    //passCounter(passOrNot, playerList);
                    if (passOrNot == 1) passCounterInt++;
                    else passCounterInt = 0;
                }

                if (!passCounter(passCounterInt, playerList))
                {
                    continue;
                }
                else
                {
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
            if (playerList.size() == 2) numberOfCardsToDeal = 7;
            else numberOfCardsToDeal = 5;
            ArrayList<Card> cardsToRemove = new ArrayList<>();
            int indexOfCard = 0;
            int x = 0;

            while (x < playerList.size())
            {

                Player player = playerList.playerAt(x);

                    for (int y = 0; y < numberOfCardsToDeal; y++)
                    {
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










    public static void getPlayers(PlayerList pList) {

            Scanner scanner = new Scanner(System.in);

            System.out.println
            (
                  "Welcome to Crazy8s!\n\n"
                + "You'll be playing against me, HAL! The computer!\n\n"
                + "How many HUMANS will be STRUGGLING today?\n\n"
                + "Please Enter 1, 2, or 3.\n\n"
            );



            int numPlayers = scanner.nextInt();
            while (numPlayers < 1 || numPlayers > 3)
            {
                scanner.hasNextInt();
            }
            if (numPlayers < 1 || numPlayers > 3)

            {
                System.out.println("Please Enter a Valid Selection");
                scanner.nextInt();
            }

            for (int p = 0; p < numPlayers; p++)
            {
                System.out.println("Please enter the name of the player to add.");
                String playerName = scanner.next();

                if (playerName == null)
                {
                    System.out.println
                    (
                        "You know the phrase, 'What's in a name?'"
                        +"\n I tell ya, it ain't nothin'!"
                        +"\n Enter a valid name HUMAN."
                    );
                    playerName = scanner.next();
                }

                Player newPlayer = new Player();
                newPlayer.setName(playerName);
                ArrayList<Card> hand = new ArrayList<>();
                newPlayer.setHand(hand);
                newPlayer.setScore(0);
                pList.addPlayer(newPlayer);

            }

            Player AI = new Player();
            AI.setName("HAL");
            ArrayList<Card> hand = new ArrayList<>();
            AI.setHand(hand);
            AI.setScore(0);
            pList.addPlayer(AI);

    }
}
