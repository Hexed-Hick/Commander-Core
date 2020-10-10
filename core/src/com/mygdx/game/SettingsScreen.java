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

public class SettingsScreen extends ScreenAdapter {

	SpriteBatch batch;
	Sprite sprite;

	MenuButton soundButton;
	MenuButton resolutionButton;
	MenuButton exitButton;

	Texture soundButtonSkin;
	Texture resolutionButtonSkin;
	Texture exitButtonSkin;

	MyGdxGame game;
	Stage settingsStage;
	
	public SettingsScreen(MyGdxGame game)
	{
			this.game = game;
	}
	
	@Override
	public void show() {
		
		batch = new SpriteBatch();
		settingsStage = new Stage(new ScreenViewport(), batch);
		exitButtonSkin = new Texture("EXIT.png");
		soundButtonSkin = new Texture("SOUND.png");
		resolutionButtonSkin = new Texture("RESOLUTION.png");
	
		soundButton = new MenuButton(soundButtonSkin,860,800,"sound");
		resolutionButton = new MenuButton(resolutionButtonSkin,860,700,"resolution");
		exitButton = new MenuButton(exitButtonSkin,860,600,"exit");

		settingsStage.addActor(soundButton);
		settingsStage.addActor(resolutionButton);
		settingsStage.addActor(exitButton);
		
	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);				//needed for background

		game.batch.begin();
		game.batch.draw(exitButton.getSkin(), exitButton.getXC(), exitButton.getYC());
		game.batch.draw(soundButton.getSkin(), soundButton.getXC(), soundButton.getYC());
		game.batch.draw(resolutionButton.getSkin(), resolutionButton.getXC(), resolutionButton.getYC());
		game.batch.end();
		settingsStage.act();
		settingsStage.draw();
		
		Gdx.input.setInputProcessor(settingsStage);
		
		if(exitButton.exitGame())
		{
			System.exit(0);
		}
		if(soundButton.sound())
		{
			System.out.println("No sound ya foo ya foo\n");
		}
		if(resolutionButton.resolution())
		{
			System.out.println("No resolution ya foo ya foo\n");

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
