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

    }







