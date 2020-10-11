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

		team1 = new ArrayList<character>();
		team2 = new ArrayList<character>();

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
			
			if(team1.size() >= 2 && team2.size() >= 2)
			{
				game.setScreen(new GameScreen(game));

			}

		}
		if(A1.A1choice())
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
				if(team1.size() < 2)
				{
					team1.add(a);
					System.out.println(team1.toString());
					acceptButton.Accept = false;
					A1.A1choice = false;
					Chosen = true;
				}
				else
				{
					if(team2.size() < 2)
					{
						team2.add(a);
						System.out.println(team2.toString());
						acceptButton.Accept = false;
						A1.A1choice = false;
						Chosen = true;
					}
				}
			}
		}
		if(K1.K1choice())
		{
			Displays.get(1).setX(900);
			Displays.get(1).setY(750);

			game.batch.begin();
			game.batch.draw(Displays.get(1).getSkin(), Displays.get(1).getXC(), Displays.get(1).getYC());
			game.batch.draw(acceptButton.getSkin(), acceptButton.getXC(), acceptButton.getYC());
			game.batch.end();
			charSelectStage.act();
			charSelectStage.draw();

			if(acceptButton.Accept)
			{
				if(team1.size() < 2)
				{
				team1.add(k);
				System.out.println(team1.toString());
				acceptButton.Accept = false;
				K1.K1choice = false;
				Chosen = true;
				}
				else
				{
					if(team2.size() < 2)
					{
						team2.add(k);
						System.out.println(team2.toString());
						acceptButton.Accept = false;
						K1.K1choice = false;
						Chosen = true;
					}
				}
			}
		}
		if(P1.P1choice())
		{
			Displays.get(2).setX(1050);
			Displays.get(2).setY(750);

			game.batch.begin();
			game.batch.draw(Displays.get(2).getSkin(), Displays.get(2).getXC(), Displays.get(2).getYC());
			game.batch.draw(acceptButton.getSkin(), acceptButton.getXC(), acceptButton.getYC());
			game.batch.end();
			charSelectStage.act();
			charSelectStage.draw();

			if(acceptButton.Accept)
			{
				if(team1.size() < 2)
				{
					team1.add(p);
					System.out.println(team1.toString());
					acceptButton.Accept = false;
					P1.P1choice = false;
					Chosen = true;
				}
				else
				{
					if(team2.size() < 2)
					{
						team2.add(p);
						System.out.println(team2.toString());
						acceptButton.Accept = false;
						P1.P1choice = false;
						Chosen = true;
					}
				}
			}
		}
		if(M1.M1choice())
		{
			Displays.get(3).setX(1200);
			Displays.get(3).setY(750);
			game.batch.begin();
			game.batch.draw(Displays.get(3).getSkin(), Displays.get(3).getXC(), Displays.get(3).getYC());
			game.batch.draw(acceptButton.getSkin(), acceptButton.getXC(), acceptButton.getYC());
			game.batch.end();
			charSelectStage.act();
			charSelectStage.draw();

			if(acceptButton.Accept)
			{
				if(team1.size() < 2)
				{
					team1.add(m);
					System.out.println(team1.toString());
					acceptButton.Accept = false;
					M1.M1choice = false;
					Chosen = true;
				}
				else
				{
					if(team2.size() < 2)
					{
						team2.add(m);
						System.out.println(team2.toString());
						acceptButton.Accept = false;
						M1.M1choice = false;
						Chosen = true;
					}
				}
			}
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
