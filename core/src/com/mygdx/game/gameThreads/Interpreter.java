package com.mygdx.game.gameThreads;

import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.mygdx.game.MyGdxGame;

public class Interpreter implements Runnable {

	MyGdxGame game;
	String message;
	MoveToAction move;
	boolean complete;
	
	
	public Interpreter(MyGdxGame game)
	{
		this.game = game;
		move = new MoveToAction();
	}
	@Override
	public void run() {
		
		
		if(game.currentDirection.substring(0, 1).equals("2"))
		{
			game.currentCharacterID1 = game.currentDirection.substring(1, 3);
			game.currentX = Integer.valueOf(game.currentDirection.substring(3, 5));
			game.currentY = Integer.valueOf(game.currentDirection.substring(5));
			
			for(int i = 0; i < game.playerList.size(); i++)
			{
				if(game.playerList.get(i).getID().equals(game.currentCharacterID1))
				{
					for(int x = 0; x < game.tiles.size(); x++)
					{
						for(int z = 0; z < game.tiles.get(x).size(); z++)
						{
							System.out.println("Current Tile X: " + game.tiles.get(x).get(z).getX());
							System.out.println("Current Tile Y: " + game.tiles.get(x).get(z).getY());
							System.out.println("Acting X: " + game.currentX);
							System.out.println("Acting Y: " + game.currentY);
							if((game.tiles.get(x).get(z).getX() == game.currentX) && (game.tiles.get(x).get(z).getY() == game.currentY))
							{
								System.out.println("Moving character " + game.currentCharacterID1 + " to X: " + game.currentX + " Y: " + game.currentY);
								move.setPosition(game.tiles.get(x).get(z).fxC - game.tiles.get(x).get(z).camX, game.tiles.get(x).get(z).fyC - game.tiles.get(x).get(z).camY);
								move.setDuration(1f);
								game.playerList.get(i).setfxC(game.tiles.get(x).get(z).getfX());
								game.playerList.get(i).setfyC(game.tiles.get(x).get(z).getfY());
								game.playerList.get(i).setxC(game.currentX);
								game.playerList.get(i).setyC(game.currentY);
								System.out.println("New character coords: " + game.playerList.get(i).getXc() + " , " +  game.playerList.get(i).getYc());
								System.out.println("New Character Screen Location: X " + game.playerList.get(i).getX() + " , Y " + game.playerList.get(i).getY());
								System.out.println("Supposed to be: X " + game.tiles.get(x).get(z).getfX() + " , Y " +  game.tiles.get(x).get(z).getfY());
								game.playerList.get(i).addAction(move);
								game.playerList.get(i).setTurn(true);
								game.playerList.get(i).setNext(false);
								game.playerList.get(i).setSelected(false);
								game.currentCharacterID1 = "";
								game.newDirection = false;
								game.currentDirection = "";
								System.out.println("Directions complete.");
								complete = true;
								break;
							}
						}
						if(complete)
						{
							break;
						}
					}
				if(complete)
				{
					break;
				}
				}
				if(complete)
				{
					break;
				}
			}
			complete = false;
		}
	}

}
