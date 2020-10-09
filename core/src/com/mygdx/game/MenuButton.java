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
	public MenuButton(Texture texture, int x, int y, String type)
	{
		skin = new Sprite(texture);
		xChord = x;
		yChord = y;
		buttonType = type;
		startGame = false;
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

}
