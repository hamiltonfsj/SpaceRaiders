package com.SpaceRaiders.Game;

public class Bullet extends GameActor {
	
	private boolean hostile;
	public float dmg;
	
	public Bullet(int x, int y, float speedY, float dmg, boolean hostile){
		box.set(x, y, 7, 6);
		this.dmg = dmg;
		this.visible = true;
		this.hostile = hostile;
		this.speedY = speedY;
	}
	
	@Override
	public void update(){
		box.y += speedY;
		
	}

	public boolean isHostile(){
		return hostile;
		
	}
}
