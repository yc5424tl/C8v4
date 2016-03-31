package com.jacobboline;

import java.util.ArrayList;

public class PlayerList {

    private ArrayList<Player> ListOfPlayers;

            public PlayerList(ArrayList<Player> listOfPlayers)
            {
                this.ListOfPlayers = listOfPlayers;
            }

            public void addPlayer(Player player)
            {
                this.ListOfPlayers.add(this.ListOfPlayers.size(), player);
            }

            public int size()
            {
                return this.ListOfPlayers.size();
            }

            public Player playerAt(int playerIndex)
            {
                return this.ListOfPlayers.get(playerIndex);
            }

            public String playerNames(PlayerList listOfPlayers)
            {
                String playerNames = "it's...us today : \n";

                for ( int x = 0 ; x < listOfPlayers.size() ; x++ )
                    playerNames += "\t\t" + (listOfPlayers.playerAt(x).getName()) + "\n";
                return playerNames;
            }

            public void playerInfo(PlayerList listOfPlayers)
            {
                for ( int x = 0 ; x < listOfPlayers.size() ; x++ )
                {
                    Player player = playerAt(x);

                    String playerStats = player.getName()
                                        + " has a score of : "
                                        + player.getScore() +"\n"
                                        + player.getHandInfo();

                    System.out.println(playerStats);
                }
            }

    }







