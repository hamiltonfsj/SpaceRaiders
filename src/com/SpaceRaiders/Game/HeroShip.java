package com.SpaceRaiders.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HeroShip extends Ship {
	
	public HeroShip(){
		super();
		box.set(0, 0, 80, 100);
	}
	
	public void displayHud(){
	}
	
	public void shot(){		
	}
	
	@Override
	public void update(){
		input();
	}
	
	public void update(SpriteBatch batch){
		
	}
	
	private void input(){
		if(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)){
			box.x += 10;
		}
		if(box.x + box.width>Gdx.graphics.getWidth()){
			box.x = Gdx.graphics.getWidth() - box.width;
		}
		
		if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)){
			box.x -= 10;
		}
		if(box.x < 0){
			box.x = 0;
		}
	}
	
}
