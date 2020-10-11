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

public class CharacterSelectScreen extends ScreenAdapter{

	SpriteBatch batch;
	Sprite sprite;
	
	MenuButton startButton;
	MenuButton A1;
	MenuButton K1;
	MenuButton P1;
	MenuButton M1;
	MenuButton loadButton;
	MenuButton deleteButton;
	MenuButton exitButton;
	MenuButton acceptButton;
	ArrayList<MenuButton> Displays;
	
	Texture startButtonSkin;
	Texture charButtonSkin;
	Texture archButtonSkin;
	Texture knightButtonSkin;
	Texture priestButtonSkin;
	Texture muskButtonSkin;
	Texture loadButtonSkin;
	Texture deleteButtonSkin;
	Texture exitButtonSkin;
	Texture acceptButtonSkin;
	
	Priest p;
	Musketeer m;
	Knight k;
	Archer a;

	Boolean Chosen;
	//Boolean Kchosen;
	//Boolean Pchosen;
	//Boolean Mchosen;
	
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
		charButtonSkin = new Texture("CHARACTERS.png");
		archButtonSkin = new Texture("ARCHER_PORTRAIT.PNG");
		knightButtonSkin = new Texture("TEMPLAR_PORTRAIT.PNG");
		priestButtonSkin = new Texture("PRIEST_PORTRAIT.PNG");
		muskButtonSkin = new Texture("MUSKETEER_PORTRAIT.PNG");
		exitButtonSkin = new Texture("EXIT.png");
		startButtonSkin = new Texture("START.png");
		acceptButtonSkin = new Texture("ACCEPT.png");

		Displays = new ArrayList<>();

		startButton = new MenuButton(startButtonSkin, 1650, 100, "start");
		exitButton = new MenuButton(exitButtonSkin,100,100,"exit");
		acceptButton = new MenuButton(acceptButtonSkin,800,500,"accept");
		A1 = new MenuButton(archButtonSkin,750,800,"archer");
		K1 = new MenuButton(knightButtonSkin,900,800,"knight");
		P1 = new MenuButton(priestButtonSkin,1050,800,"priest");
		M1 = new MenuButton(muskButtonSkin,1200,800,"musketeer");
		
		Displays.add(startButton);
		Displays.add(exitButton);
		Displays.add(acceptButton);
		Displays.add(A1);
		Displays.add(K1);
		Displays.add(P1);
		Displays.add(M1);

		a = new Archer(game);
		p = new Priest(game);
		m = new Musketeer(game);
		k = new Knight(game);
		
		Chosen = false;

		charSelectStage.addActor(startButton);
		charSelectStage.addActor(acceptButton);
		charSelectStage.addActor(A1);
		charSelectStage.addActor(K1);
		charSelectStage.addActor(P1);
		charSelectStage.addActor(M1);
		charSelectStage.addActor(exitButton);
		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);				//needed for background

		game.batch.begin();
		game.batch.draw(startButton.getSkin(), startButton.getXC(), startButton.getYC());
		game.batch.draw(exitButton.getSkin(), exitButton.getXC(), exitButton.getYC());
		game.batch.draw(A1.getSkin(), A1.getXC(), A1.getYC());
		game.batch.draw(K1.getSkin(), K1.getXC(), K1.getYC());
		game.batch.draw(P1.getSkin(), P1.getXC(), P1.getYC());
		game.batch.draw(M1.getSkin(), M1.getXC(), M1.getYC());

		game.batch.end();
		charSelectStage.act();
		charSelectStage.draw();
		
		Gdx.input.setInputProcessor(charSelectStage);
		if(startButton.startGame())
		{
			game.setScreen(new GameScreen(game));

		}
		if(exitButton.exitGame())
		{
			System.exit(0);
		}
		if(Chosen == true)
		{
			Displays.get(3).setX(750);
			Displays.get(3).setY(700);
			
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			game.batch.begin();
			game.batch.draw(A1.getSkin(), A1.getXC(), A1.getYC());
			game.batch.end();
			charSelectStage.act();
			charSelectStage.draw();

		}
		if(A1.A1choice())
		{
			Displays.get(3).setX(750);
			Displays.get(3).setY(700);
			
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

			game.batch.begin();
			game.batch.draw(Displays.get(3).getSkin(), Displays.get(3).getXC(), Displays.get(3).getYC());
			game.batch.draw(acceptButton.getSkin(), acceptButton.getXC(), acceptButton.getYC());
			game.batch.end();
			
			charSelectStage.act();
			charSelectStage.draw();

			if(acceptButton.Accept())
			{
				game.team1.add(a);
				acceptButton.Accept = false;
				A1.A1choice = false;
				Chosen = true;
			}
		}
		if(K1.K1choice())
		{
			MenuButton Display = new MenuButton(knightButtonSkin,800,600,"knight");
			game.batch.begin();

			game.batch.draw(Display.getSkin(), Display.getXC(), Display.getYC());
			game.batch.draw(acceptButton.getSkin(), acceptButton.getXC(), acceptButton.getYC());
			game.batch.end();
			charSelectStage.act();
			charSelectStage.draw();

			if(acceptButton.Accept)
			{
				game.team1.add(k);
				acceptButton.Accept = false;
				K1.K1choice = false;
			}
		}
		if(P1.P1choice())
		{
			MenuButton Display = new MenuButton(priestButtonSkin,800,600,"priest");
			game.batch.begin();

			game.batch.draw(Display.getSkin(), Display.getXC(), Display.getYC());
			game.batch.draw(acceptButton.getSkin(), acceptButton.getXC(), acceptButton.getYC());
			game.batch.end();
			charSelectStage.act();
			charSelectStage.draw();

			if(acceptButton.Accept)
			{
				game.team1.add(p);
				acceptButton.Accept = false;
				P1.P1choice = false;

			}
		}
		if(M1.M1choice())
		{
			MenuButton Display = new MenuButton(muskButtonSkin,800,600,"musketeer");
			game.batch.begin();

			game.batch.draw(Display.getSkin(), Display.getXC(), Display.getYC());
			game.batch.draw(acceptButton.getSkin(), acceptButton.getXC(), acceptButton.getYC());
			game.batch.end();
			charSelectStage.act();
			charSelectStage.draw();

			if(acceptButton.Accept)
			{
					game.team1.add(m);
					acceptButton.Accept = false;
					M1.M1choice = false;

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
