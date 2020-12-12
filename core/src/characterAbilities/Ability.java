package characterAbilities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.mygdx.game.MyGdxGame;

public class Ability extends Actor{
	
	
	public Boolean mouseOver;
	public Sprite sprite;
	public Boolean selected;
	public int x;
	public int y;
	public Boolean radial;
	public int range;
	
	public Ability(MyGdxGame game) {
		
		sprite = new Sprite(new Texture("ARCHER_BASIC.png"));
		setBounds(750, 50, 140, 140);
		setTouchable(Touchable.disabled);
		mouseOver = false;
		selected = false;
		x = 300;
		y = 50;
		range = 5;
	
		addListener(new InputListener()
		{

			@Override
			public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
				mouseOver = true;
				
				super.enter(event, x, y, pointer, fromActor);
			}

			@Override
			public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
				mouseOver = false;
				super.exit(event, x, y, pointer, toActor);
			}

			@Override
			public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
				if(selected == false) {
				selected = true;
				}
				else {
					selected = false;
				}
				
				return super.touchDown(event, x, y, pointer, button);
			}

			@Override
			public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
				
				
				
				super.touchUp(event, x, y, pointer, button);
			}
			
			
			
			
			
			
			
			
			
		});
		
	}
	public Sprite getSprite() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
