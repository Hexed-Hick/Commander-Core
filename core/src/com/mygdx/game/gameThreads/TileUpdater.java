package com.mygdx.game.gameThreads;

import java.util.ArrayList;

import com.mygdx.game.Actor1;
import com.mygdx.game.MyGdxGame;

import characterAbilities.Ability;

public class TileUpdater implements Runnable {
	
	MyGdxGame game;
	int mode;
	Ability ability;
	
	
	public TileUpdater(MyGdxGame game){
		this.game = game;
		mode = 0;
	}

	
	
	public void updateTempTiles(Actor1 tile, int distance)
	{
		if(game.selectedPlayer != null)
		{
			if(game.selectedPlayer.getAbility1().selected == true || game.selectedPlayer.getAbility2().selected == true || game.selectedPlayer.getAbility3().selected == true) {
				
														if(game.selectedPlayer.getAbility1().selected == true) {
															ability = game.selectedPlayer.getAbility1();
														}
														if(game.selectedPlayer.getAbility2().selected == true) {
															ability = game.selectedPlayer.getAbility2();
														}
														if(game.selectedPlayer.getAbility2().selected == true) {
															ability = game.selectedPlayer.getAbility2();
														}
					if(distance <= ability.range) {
					
				
						if(distance == 0)
				{
				//	System.out.println("Update TempTiles step 1.");
					tile = game.tiles.get(game.selectedPlayer.getXc()).get(game.selectedPlayer.getYc());
					System.out.println("Running updateTempTiles.exe.     Tile X: " + tile.getXCoord() + " Tile Y: " + tile.getYCoord());
					distance++;
					ArrayList<Actor1> tiles = getAdjacentTiles(tile);
					for(int i = 0; i < tiles.size(); i++)
					{
						this.updateTempTiles(tiles.get(i), distance);
					}
				}
				else
				{
					//System.out.println("UpdateTempTiles step " + distance + ".");
					tile.setAlpha(1);
					tile.acceptable = true;
					game.tiles.get(tile.getXCoord()).get(tile.getYCoord()).setAlpha(1);
					ArrayList<Actor1> tiles = getAdjacentTiles(tile);
					distance++;
					//if(distance < game.selectedPlayer.getSpeed())
					//{
					for(int i = 0; i < tiles.size(); i++)
					{
						this.updateTempTiles(tiles.get(i), distance);
					}
				}
			}
			}
				
			
			else if(distance <= game.selectedPlayer.getSpeed())
			{
				if(distance == 0)
				{
				//	System.out.println("Update TempTiles step 1.");
					tile = game.tiles.get(game.selectedPlayer.getXc()).get(game.selectedPlayer.getYc());
					System.out.println("Running updateTempTiles.exe.     Tile X: " + tile.getXCoord() + " Tile Y: " + tile.getYCoord());
					distance++;
					ArrayList<Actor1> tiles = getAdjacentTiles(tile);
					for(int i = 0; i < tiles.size(); i++)
					{
						this.updateTempTiles(tiles.get(i), distance);
					}
				}
				else
				{
					//System.out.println("UpdateTempTiles step " + distance + ".");
					tile.setAlpha(1);
					tile.acceptable = true;
					game.tiles.get(tile.getXCoord()).get(tile.getYCoord()).setAlpha(1);
					ArrayList<Actor1> tiles = getAdjacentTiles(tile);
					distance++;
					//if(distance < game.selectedPlayer.getSpeed())
					//{
					for(int i = 0; i < tiles.size(); i++)
					{
						this.updateTempTiles(tiles.get(i), distance);
					}
					//}
				}
				
			}
		else
		{
			
		}
		}
			
	}
	
	public ArrayList<Actor1> getAdjacentTiles(Actor1 tile)
	{
		ArrayList<Actor1> tiles = new ArrayList<Actor1>();
		Boolean even = false;
		System.out.println("RUNNING getAdjacentTiles.exe.    Tile X: " + tile.getXCoord() + " Tile Y: " + tile.getYCoord());
		if(tile.getXCoord()%2 == 0) {
			even = true;
		}
		if(even) {
						if(tile.getXCoord() + 1 < game.tiles.size() ) {
						tiles.add(game.tiles.get(tile.getXCoord() + 1).get(tile.getYCoord()));
			}
						if( tile.getYCoord() + 1 < game.tiles.size()) {
						tiles.add(game.tiles.get(tile.getXCoord()).get(tile.getYCoord() + 1 ));
						}
						if( tile.getXCoord() > 0) {
						tiles.add(game.tiles.get(tile.getXCoord() - 1).get(tile.getYCoord()));
						}
						if(tile.getYCoord() > 0) {
						tiles.add(game.tiles.get(tile.getXCoord()).get(tile.getYCoord() - 1));
						}
						if( tile.getXCoord() + 1 < game.tiles.size() && tile.getYCoord() > 0) {
						tiles.add(game.tiles.get(tile.getXCoord() + 1).get(tile.getYCoord() - 1));
						}
						if( tile.getXCoord() > 0 && tile.getYCoord() > 0) {
						tiles.add(game.tiles.get(tile.getXCoord() -1).get(tile.getYCoord() - 1));
						}
					
					

		}
		if(!even) {
						if(tile.getXCoord() > 0 && tile.getYCoord() + 1 < game.tiles.size()) {
						tiles.add(game.tiles.get(tile.getXCoord() - 1).get(tile.getYCoord() + 1));
						}
						if( tile.getYCoord() + 1 < game.tiles.size()) {
						tiles.add(game.tiles.get(tile.getXCoord()).get(tile.getYCoord() + 1));
						}
						if( tile.getXCoord() + 1 < game.tiles.size() && tile.getYCoord() + 1 < game.tiles.size()) {
						tiles.add(game.tiles.get(tile.getXCoord() + 1).get(tile.getYCoord() +1));
						}
						if( tile.getXCoord() > 0) {
						tiles.add(game.tiles.get(tile.getXCoord() - 1).get(tile.getYCoord()));
						}
						if(tile.getYCoord() > 0) {
						tiles.add(game.tiles.get(tile.getXCoord()).get(tile.getYCoord() - 1));
						}
						if(tile.getXCoord() + 1 < game.tiles.size()) {
						tiles.add(game.tiles.get(tile.getXCoord() + 1).get(tile.getYCoord()));
						}
					
					
				
			
		}
		
		return tiles;
	}
	
	
	public void resetTiles() {
		for(int i = 0; i < game.tiles.size(); i++) {
			for(int j = 0; j < game.tiles.get(i).size(); j++) {
				if(!game.tiles.get(i).get(j).mouseOver)
				game.tiles.get(i).get(j).setAlpha(0);
			}
		}
	}



	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		if (mode == 1) {
			updateTempTiles(null, 0);
		}
		
		if(mode == 2) {
			resetTiles();
		}
		
		
	}
	
	public void setMode(int mode) {
		this.mode = mode;
	}
}
