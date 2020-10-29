package com.mygdx.game.gameThreads;

import com.mygdx.game.MyGdxGame;

public class Interpreter implements Runnable {

	MyGdxGame game;
	String message;
	
	
	public Interpreter(String message, MyGdxGame game)
	{
		this.game = game;
		this.message = message;
	}
	@Override
	public void run() {
		
		
		if(game.currentDirection.substring(0, 1).equals("2"))
		{
			currentCharacterID = game.currentDirection.substring(1, 3);
			currentX = Integer.valueOf(game.currentDirection.substring(3, 5));
			currentY = Integer.valueOf(game.currentDirection.substring(5));
			
			for(int i = 0; i < game.playerList.size(); i++)
			{
				if(game.playerList.get(i).getID().equals(currentCharacterID))
				{
					for(int x = 0; x < tiles.size(); x++)
					{
						for(int z = 0; z < tiles.get(x).size(); z++)
						{
							System.out.println("Current Tile X: " + tiles.get(x).get(z).getX());
							System.out.println("Current Tile Y: " + tiles.get(x).get(z).getY());
							System.out.println("Acting X: " + currentX);
							System.out.println("Acting Y: " + currentY);
							if((tiles.get(x).get(z).getX() == currentX) && (tiles.get(x).get(z).getY() == currentY))
							{
								System.out.println("Moving character " + currentCharacterID + " to X: " + currentX + " Y: " + currentY);
								move.setPosition(tiles.get(x).get(z).fxC - tiles.get(x).get(z).camX, tiles.get(x).get(z).fyC - tiles.get(x).get(z).camY);
								move.setDuration(1f);
								game.playerList.get(i).setfxC(tiles.get(x).get(z).getfX());
								game.playerList.get(i).setfyC(tiles.get(x).get(z).getfY());
								game.playerList.get(i).setxC(currentX);
								game.playerList.get(i).setyC(currentY);
								System.out.println("New character coords: " + game.playerList.get(i).getXc() + " , " +  game.playerList.get(i).getYc());
								System.out.println("New Character Screen Location: X " + game.playerList.get(i).getX() + " , Y " + game.playerList.get(i).getY());
								System.out.println("Supposed to be: X " + tiles.get(x).get(z).getfX() + " , Y " +  tiles.get(x).get(z).getfY());
								game.playerList.get(i).addAction(move);
								game.playerList.get(i).setTurn(true);
								game.playerList.get(i).setNext(false);
								game.playerList.get(i).setSelected(false);
								currentCharacterID = "";
								game.newDirection = false;
								game.currentDirection = "";
								System.out.println("Directions complete.");
								break;
							}
						}
					}
				/*	System.out.println("Moving character " + currentCharacterID + " to X: " + currentX + " Y: " + currentY);
					move.setPosition(tiles.get(currentX).get(currentY).fxC - tiles.get(currentX).get(currentY).camX, tiles.get(currentX).get(currentY).fyC - tiles.get(currentX).get(currentY).camY);
					move.setDuration(1f);
					game.playerList.get(i).setfxC(tiles.get(currentX).get(currentY).getfX());
					game.playerList.get(i).setfyC(tiles.get(currentX).get(currentY).getfY());
					game.playerList.get(i).setxC(currentX);
					game.playerList.get(i).setyC(currentY);
					System.out.println("New character coords: " + game.playerList.get(i).getXc() + " , " +  game.playerList.get(i).getYc());
					System.out.println("New Character Screen Location: X " + game.playerList.get(i).getX() + " , Y " + game.playerList.get(i).getY());
					System.out.println("Supposed to be: X " + tiles.get(currentX).get(currentY).getfX() + " , Y " +  tiles.get(currentX).get(currentY).getfY());
					game.playerList.get(i).addAction(move);
					game.playerList.get(i).setTurn(true);
					game.playerList.get(i).setNext(false);
					game.playerList.get(i).setSelected(false);
					currentX = 0;
					currentY = 0;
					currentCharacterID = "";
					game.newDirection = false;
					game.currentDirection = "";*/
				}
			}
			
		}
		
		
	}

}
