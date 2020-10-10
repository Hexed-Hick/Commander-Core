package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;

public class MenuButton extends Actor {
	
	Sprite skin;
	int xChord;
	int yChord;
	String buttonType;
	Boolean startGame;
	Boolean isHosting;
	Boolean isConnecting;
	Boolean settings;
	Boolean exitGame;
	Boolean sound;
	Boolean resolution;
	Boolean characters;
	Boolean load;
	Boolean delete;
	Boolean connect;
	
	

	public MenuButton(Texture texture, int x, int y, String type)
	{
		skin = new Sprite(texture);
		xChord = x;
		yChord = y;
		buttonType = type;
		startGame = false;
		isHosting = false;
		isConnecting = false;
		settings = false;
		exitGame = false;
		sound = false;
		resolution = false;
		characters = false;
		load = false;
		delete = false;
		connect = false;
		

		setBounds(x, y, skin.getWidth(), skin.getHeight());
		setTouchable(Touchable.enabled);
		
		
		addListener(new InputListener()
				{

					@Override
					public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
						if(buttonType == "start")
						{
							startGame = true;
						}
						if(buttonType == "host")
						{
							isHosting = true;
						}
						if(buttonType == "connect")
						{
							if(isConnecting)
							{
								connect = true;
							}
							isConnecting = true;
							
						}
						if(buttonType == "settings")
						{
							settings = true;
						}
						if(buttonType == "exit")
						{
							exitGame = true;
						}
						if(buttonType == "sound")
						{
							sound = true;
						}
						if(buttonType == "resolution")
						{
							resolution = true;
						}
						if(buttonType == "characters")
						{
							characters = true;
						}
						if(buttonType == "load")
						{
							load = true;
						}
						if(buttonType == "delete")
						{
							delete = true;
						}

						
						return super.touchDown(event, x, y, pointer, button);
						
					}
				});
	}
	public int getXC()
	{
		return xChord;
	}
	
	public int getYC()
	{
		return yChord;
	}
	
	public Sprite getSkin()
	{
		return skin;
	}
	public boolean startGame()
	{
		return startGame;
	}
	public boolean isHosting()
	{
		return isHosting;
	}
	public boolean isConnecting()
	{
		return isConnecting;
	}
	public boolean settings()
	{
		return settings;
	}
	public boolean exitGame()
	{
		return exitGame;
	}
	public boolean sound()
	{
		return sound;
	}
	public boolean resolution()
	{
		return resolution;
	}
	public boolean characters()
	{
		return characters;
	}
	public boolean load()
	{
		return load;
	}
	public boolean delete()
	{
		return delete;
	}

}
