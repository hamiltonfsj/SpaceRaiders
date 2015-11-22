package com.SpaceRaiders.Game;

public class Bullet extends GameActor {
	
	public Bullet(int x, int y, float speedY){
		box.set(x, y, 2, 6);
		this.speedY = speedY;
	}
	
	@Override
	public void update(){
		box.y += speedY;
		
	}

}
