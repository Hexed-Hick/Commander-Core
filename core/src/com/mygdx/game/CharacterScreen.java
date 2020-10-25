package com.mygdx.game;

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

public class CharacterScreen extends ScreenAdapter{

	SpriteBatch batch;
	Sprite sprite;
	
	MenuButton startButton;
	MenuButton charButton;
	MenuButton exitButton;

	Texture startButtonSkin;
	Texture charButtonSkin;
	Texture exitButtonSkin;

	MyGdxGame game;
	Stage charStage;
	
	public CharacterScreen(MyGdxGame game)
	{
			this.game = game;
	}
	
	@Override
	public void show() {
		
		batch = new SpriteBatch();
		charStage = new Stage(new ScreenViewport(), batch);
		charButtonSkin = new Texture("CHARACTERS.png");
		exitButtonSkin = new Texture("EXIT.png");
		startButtonSkin = new Texture("START.png");


		startButton = new MenuButton(startButtonSkin, 860, 700, "start");
		charButton = new MenuButton(charButtonSkin,860,800,"characters");
		exitButton = new MenuButton(exitButtonSkin,860,600,"exit");

		charStage.addActor(startButton);
		charStage.addActor(charButton);
		charStage.addActor(exitButton);
		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);				//needed for background

		game.batch.begin();
		game.batch.draw(startButton.getSkin(), startButton.getXC(), startButton.getYC());
		game.batch.draw(exitButton.getSkin(), exitButton.getXC(), exitButton.getYC());
		game.batch.draw(charButton.getSkin(), charButton.getXC(), charButton.getYC());

		game.batch.end();
		charStage.act();
		charStage.draw();
		

		Gdx.input.setInputProcessor(charStage);
		if(startButton.startGame())
		{
			game.setScreen(new GameScreen(game));

		}
		if(exitButton.exitGame())
		{
			System.exit(0);
		}		
		if(charButton.characters())						
		{
			game.setScreen(new CharacterSelectScreen(game));

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
