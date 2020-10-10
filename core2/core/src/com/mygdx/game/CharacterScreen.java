package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
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

public class CharacterScreen extends ScreenAdapter {

	SpriteBatch batch;
	Sprite sprite;

	MenuButton loadButton;
	MenuButton deleteButton;
	MenuButton exitButton;

	Texture loadButtonSkin;
	Texture deleteButtonSkin;
	Texture exitButtonSkin;

	MyGdxGame game;
	Stage characterStage;
	
	public CharacterScreen(MyGdxGame game)
	{
			this.game = game;
	}
	
	@Override
	public void show() {
		
		batch = new SpriteBatch();
		characterStage = new Stage(new ScreenViewport(), batch);
		exitButtonSkin = new Texture("EXIT.png");
		loadButtonSkin = new Texture("EXIT.png");
		deleteButtonSkin = new Texture("SOUND.png");
	
		loadButton = new MenuButton(loadButtonSkin,860,800,"sound");
		deleteButton = new MenuButton(deleteButtonSkin,860,700,"resolution");
		exitButton = new MenuButton(exitButtonSkin,860,800,"exit");

		characterStage.addActor(loadButton);
		characterStage.addActor(deleteButton);
		characterStage.addActor(exitButton);
		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);				//needed for background

		game.batch.begin();
		game.batch.draw(exitButton.getSkin(), exitButton.getXC(), exitButton.getYC());
		game.batch.draw(loadButton.getSkin(), loadButton.getXC(), loadButton.getYC());
		game.batch.draw(deleteButton.getSkin(), deleteButton.getXC(), deleteButton.getYC());
		game.batch.end();
		characterStage.act();
		characterStage.draw();
		
		Gdx.input.setInputProcessor(characterStage);
		
		if(exitButton.exitGame())
		{
			System.exit(0);
		}
		if(loadButton.load())
		{
			System.out.println("No character to load ya foo ya foo\n");
		}
		if(deleteButton.delete())
		{
			System.out.println("No character to delete ya foo ya foo\n");

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
