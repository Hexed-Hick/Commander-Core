package com.mygdx.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MenuButton;
import com.mygdx.game.MyGdxGame;

import characterPack.Archer;
import characterPack.Knight;
import characterPack.Musketeer;
import characterPack.Priest;
import characterPack.character;

public class CharacterSelectScreen extends ScreenAdapter{

	SpriteBatch batch;
	Sprite sprite;
	
	MenuButton A1;
	MenuButton K1;
	MenuButton P1;
	MenuButton M1;
	MenuButton acceptButton;
	MenuButton currentlySelected;
	ArrayList<MenuButton> Displays;
	ArrayList<character> team1;
	ArrayList<character> team2;

	Texture archButtonSkin;
	Texture knightButtonSkin;
	Texture priestButtonSkin;
	Texture muskButtonSkin;
	Texture acceptButtonSkin;
	
	Priest p;
	Musketeer m;
	Knight k;
	Archer a;

	Boolean Chosen;
	
	MyGdxGame game;
	Stage charSelectStage;
	
	public CharacterSelectScreen(MyGdxGame game)
	{
			this.game = game;
	}
	
	@Override
	public void show() {
		
		batch = new SpriteBatch();
		charSelectStage = new Stage(new ScreenViewport(), batch);
		archButtonSkin = new Texture("ARCHER_PORTRAIT.PNG");
		knightButtonSkin = new Texture("TEMPLAR_PORTRAIT.PNG");
		priestButtonSkin = new Texture("PRIEST_PORTRAIT.PNG");
		muskButtonSkin = new Texture("MUSKETEER_PORTRAIT.PNG");
		acceptButtonSkin = new Texture("ACCEPT.png");
		Displays = new ArrayList<>();

		acceptButton = new MenuButton(acceptButtonSkin,800,500,"accept");
		A1 = new MenuButton(archButtonSkin,750,800,"archer");
		K1 = new MenuButton(knightButtonSkin,900,800,"knight");
		P1 = new MenuButton(priestButtonSkin,1050,800,"priest");
		M1 = new MenuButton(muskButtonSkin,1200,800,"musketeer");
		
		Displays.add(A1);
		Displays.add(K1);
		Displays.add(P1);
		Displays.add(M1);

		game.team1 = new ArrayList<character>();
		game.team2 = new ArrayList<character>();
		a = new Archer(game);
		p = new Priest(game);
		m = new Musketeer(game);
		k = new Knight(game);
		
		Chosen = false;

		charSelectStage.addActor(acceptButton);
		charSelectStage.addActor(A1);
		charSelectStage.addActor(K1);
		charSelectStage.addActor(P1);
		charSelectStage.addActor(M1);		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);				//needed for background

		game.batch.begin();
		game.batch.draw(A1.getSkin(), A1.getXC(), A1.getYC());
		game.batch.draw(K1.getSkin(), K1.getXC(), K1.getYC());
		game.batch.draw(P1.getSkin(), P1.getXC(), P1.getYC());
		game.batch.draw(M1.getSkin(), M1.getXC(), M1.getYC());

		game.batch.end();
		charSelectStage.act();
		charSelectStage.draw();
		
		Gdx.input.setInputProcessor(charSelectStage);
		if(Chosen == true)
		{
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			game.batch.begin();
			for(MenuButton i : Displays)
			{
				game.batch.draw(i.getSkin(), i.getXC(), i.getYC());
			}
			game.batch.end();
			charSelectStage.act();
			charSelectStage.draw();
			
			if(game.team1.size() >= 2 && game.team2.size() >= 2 || game.team1.size() + game.team2.size() == 4)
			{
				game.setScreen(new GameScreen(game));

			}

		}
		if(A1.characterSelected)
		{
			Displays.get(0).setX(750);
			Displays.get(0).setY(700);
			
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			game.batch.begin();
			game.batch.draw(Displays.get(0).getSkin(), Displays.get(0).getXC(), Displays.get(0).getYC());
			game.batch.draw(acceptButton.getSkin(), acceptButton.getXC(), acceptButton.getYC());
			game.batch.end();
			charSelectStage.act();
			charSelectStage.draw();

			if(acceptButton.Accept())
			{
				if(game.player == 1)
				{
					Displays.get(0).setX(150);
					Displays.get(0).setY(800 - (150 * game.team1.size()));
					game.team1.add(a);
					System.out.println("Team 1: " + game.team1.toString());
					acceptButton.Accept = false;
					A1.characterSelected = false;
					if(game.socketClient != null)
					{
					game.socketClient.sendData("6100");
					}
					Chosen = true;
				}
				else
				{
					Displays.get(0).setX(1770);
					Displays.get(0).setY(800 - (150 * game.team2.size()));
						game.team2.add(a);
						System.out.println(game.team2.toString());
						acceptButton.Accept = false;
						A1.characterSelected = false;
						Chosen = true;
						if(game.socketClient != null)
						{
						game.socketClient.sendData("6200");
						}
				}
			}
		}
		if(K1.characterSelected)
		{
			Displays.get(1).setX(900);
			Displays.get(1).setY(700);

			game.batch.begin();
			game.batch.draw(Displays.get(1).getSkin(), Displays.get(1).getXC(), Displays.get(1).getYC());
			game.batch.draw(acceptButton.getSkin(), acceptButton.getXC(), acceptButton.getYC());
			game.batch.end();
			charSelectStage.act();
			charSelectStage.draw();

			if(acceptButton.Accept)
			{
				if(game.player == 1)
				{
					Displays.get(1).setX(150);
					Displays.get(1).setY(800 - (150 * game.team1.size()));
					k.setTeam(1);
				game.team1.add(k);
				System.out.println(game.team1.toString());
				acceptButton.Accept = false;
				K1.characterSelected = false;
				Chosen = true;
				if(game.socketClient != null)
				{
				game.socketClient.sendData("6101");
				}
				}
				else
				{
					Displays.get(1).setX(1770);
					Displays.get(1).setY(800 - (150 * game.team2.size()));
					k.setTeam(2);
						game.team2.add(k);
						System.out.println(game.team2.toString());
						acceptButton.Accept = false;
						K1.characterSelected = false;
						Chosen = true;
						if(game.socketClient != null)
						{
						game.socketClient.sendData("6201");
						}
				}
			}
		}
		if(P1.characterSelected)
		{
			Displays.get(2).setX(1050);
			Displays.get(2).setY(700);

			game.batch.begin();
			game.batch.draw(Displays.get(2).getSkin(), Displays.get(2).getXC(), Displays.get(2).getYC());
			game.batch.draw(acceptButton.getSkin(), acceptButton.getXC(), acceptButton.getYC());
			game.batch.end();
			charSelectStage.act();
			charSelectStage.draw();

			if(acceptButton.Accept)
			{
				if(game.player == 1)
				{	
				Displays.get(2).setX(150);
				Displays.get(2).setY(800 - (150 * game.team1.size()));
				p.setTeam(1);
				game.team1.add(p);
					System.out.println(game.team1.toString());
					acceptButton.Accept = false;
					P1.characterSelected = false;
					Chosen = true;
					if(game.socketClient != null)
					{
					game.socketClient.sendData("6102");
					}
				}
				else
				{
					Displays.get(2).setX(1770);
					Displays.get(2).setY(800 - (150 * game.team2.size()));
					p.setTeam(2);
						game.team2.add(p);
						System.out.println(game.team2.toString());
						acceptButton.Accept = false;
						P1.characterSelected = false;
						Chosen = true;
						if(game.socketClient != null)
						{
						game.socketClient.sendData("6202");
						}
					
				}
			}
		}
		if(M1.characterSelected)
		{
			Displays.get(3).setX(1200);
			Displays.get(3).setY(700);
			game.batch.begin();
			game.batch.draw(Displays.get(3).getSkin(), Displays.get(3).getXC(), Displays.get(3).getYC());
			game.batch.draw(acceptButton.getSkin(), acceptButton.getXC(), acceptButton.getYC());
			game.batch.end();
			charSelectStage.act();
			charSelectStage.draw();

			if(acceptButton.Accept)
			{
				if(game.player == 1)
				{
					Displays.get(3).setX(150);
					Displays.get(3).setY(800 - (150 * game.team1.size()));
					m.setTeam(1);
					game.team1.add(m);
					System.out.println(game.team1.toString());
					acceptButton.Accept = false;
					M1.characterSelected = false;
					Chosen = true;
					if(game.socketClient != null)
					{
					game.socketClient.sendData("6103");
					}
				}
				else
				{
					Displays.get(3).setX(1770);
					Displays.get(3).setY(800 - (150 * game.team2.size()));
					m.setTeam(2);
						game.team2.add(m);
						System.out.println(game.team2.toString());
						acceptButton.Accept = false;
						M1.characterSelected = false;
						Chosen = true;
						if(game.socketClient != null)
						{
						game.socketClient.sendData("6203");
						}
				}
			}
		}
		
		//
		if(game.newDirection)
		{
			if(game.currentDirection.substring(0, 1).equals("6"))
			{
				System.out.println("Character selection.");
				if(game.currentDirection.substring(1, 2).equals("1"))
				{
					System.out.println("Player 1 is making a selection");
					
					if(game.currentDirection.substring(2).equals("00"))
					{
						System.out.println("Player 1 has chosen Archer.");
						game.team1.add(a);
						game.newDirection = false;
						Displays.get(0).setX(150);
						Displays.get(0).setY(800 - (150 * game.team1.size()));
					}
					if(game.currentDirection.substring(2).equals("01"))
					{
						System.out.println("Player 1 has chosen Knight.");
						game.team1.add(k);
						game.newDirection = false;
						Displays.get(1).setX(150);
						Displays.get(1).setY(800 - (150 * game.team1.size()));
					}
					if(game.currentDirection.substring(2).equals("02"))
					{
						System.out.println("Player 1 has chosen Priest.");
						game.team1.add(p);
						game.newDirection = false;
						Displays.get(2).setX(150);
						Displays.get(2).setY(800 - (150 * game.team1.size()));
					}
					if(game.currentDirection.substring(2).equals("03"))
					{
						System.out.println("Player 1 has chosen Musketeer.");
						game.team1.add(m);
						game.newDirection = false;
						Displays.get(3).setX(150);
						Displays.get(3).setY(800 - (150 * game.team1.size()));
					}
					
				}
				if(game.currentDirection.substring(2).equals(2)) {
					
						System.out.println("Player 2 is making a selection");
					
					if(game.currentDirection.substring(2).equals("00"))
					{
						System.out.println("Player 2 has chosen Archer.");
						game.team2.add(new Archer(game));
						game.newDirection = false;
						Displays.get(0).setX(1770);
						Displays.get(0).setY(800 - (150 * game.team2.size()));
					}
					if(game.currentDirection.substring(2).equals("01"))
					{
						System.out.println("Player 2 has chosen Knight.");
						game.team2.add(new Knight(game));
						game.newDirection = false;
						Displays.get(1).setX(1770);
						Displays.get(1).setY(800 - (150 * game.team2.size()));
					}
					if(game.currentDirection.substring(2).equals("02"))
					{
						System.out.println("Player 2 has chosen Priest.");
						game.team2.add(new Priest(game));
						game.newDirection = false;
						Displays.get(2).setX(1770);
						Displays.get(2).setY(800 - (150 * game.team2.size()));
					}
					if(game.currentDirection.substring(2).equals("03"))
					{
						System.out.println("Player 2 has chosen Musketeer.");
						game.team2.add(new Musketeer(game));
						game.newDirection = false;
						Displays.get(3).setX(1770);
						Displays.get(3).setY(800 - (150 * game.team2.size()));
					}
					
					
					
					
				}
			}
			game.currentDirection = "";
		}
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
		
	}

	@Override
	public void dispose() {
		
	}

}
