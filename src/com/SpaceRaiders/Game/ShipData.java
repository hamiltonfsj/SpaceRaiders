package com.SpaceRaiders.Game;

public class ShipData extends GameActor {
	
	public int data;
	private boolean isDestroyed;
	public final static int radius = 100, distance = 52;
	public final static float speed = 0.02f;
	public float rotationCont, centerX, centerY;
	public EnemyShip owner;

	
	public ShipData(EnemyShip owner){
		this.data = Math.round((float)Math.random());
		this.owner = owner;
		box.set(0, 0, 20, 20);
		
		centerX = owner.box.x + owner.box.width/2 - box.width/2;
		centerY = owner.box.y + owner.box.height/2 - box.height/2;
		
		move(owner.data.size*distance);
	}
	
	@Override
	public void update(){

		centerX = owner.box.x + owner.box.width/2 - box.width/2;
		centerY = owner.box.y + owner.box.height/2 - box.height/2;
		
		move(1);
		
	}
	
	public void move(int times){
		rotationCont += times*speed;
		box.x = (float) (centerX + radius*Math.sin(rotationCont));
		box.y = (float) (centerY + radius*Math.cos(rotationCont));
		
		
	}
	
	

}
