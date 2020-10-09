package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

public class MenuScreen extends ScreenAdapter {

	SpriteBatch batch;
	Sprite sprite;
	
	MenuButton startButton;
	MenuButton hostButton;
	Texture startButtonSkin;
	Texture hostButtonSkin;
	MyGdxGame game;
	Stage menuStage;
	boolean startGame;
	
	public MenuScreen(MyGdxGame game)
	{
			this.game = game;
	}
	
	@Override
	public void show() {
		batch = new SpriteBatch();
		menuStage = new Stage(new ScreenViewport(), batch);
		startButtonSkin = new Texture("START.png");
		hostButtonSkin = new Texture("HOST.png");
		startButton = new MenuButton(startButtonSkin, 860, 750, "start");
		hostButton = new MenuButton(hostButtonSkin, 860, 600, "host");
		menuStage.addActor(startButton);
		menuStage.addActor(hostButton);
		startGame = false;
		Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keyCode) {
                if (keyCode == Input.Keys.ENTER) {
                  game.setScreen(new GameScreen(game));
                }
                return true;
            }
        });
		
	}

	@Override
	public void render(float delta) {
		Gdx.input.setInputProcessor(menuStage);
		game.batch.begin();
		game.batch.draw(startButton.getSkin(), startButton.getXC(), startButton.getYC());
		game.batch.end();
		menuStage.act();
		menuStage.draw();
		if(startButton.startGame)
		{
			game.setScreen(new GameScreen(game));
			
		}
		//Gdx.input.setInputProcessor(menuStage);
		
		
	}

	@Override
	public void hide() {
		Gdx.input.setInputProcessor(null);
		
	}

	@Override
	public void dispose() {
		
	}

}
