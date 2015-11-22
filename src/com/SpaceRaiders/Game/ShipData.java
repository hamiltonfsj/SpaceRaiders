package com.SpaceRaiders.Game;

public class ShipData extends GameActor {
	
	public int data;
	private boolean isDestroyed;
	public final static int radius = 50, distance = 52;
	public final static float speed = 0.02f;
	public float rotationCont, hp, centerX, centerY;
	public EnemyShip owner;

	
	public ShipData(EnemyShip owner){
		hp = 1.0f;
		this.data = Math.round((float)Math.random());
		System.out.print(data);
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
		
		box.width = 20*hp;
		box.height = 20*hp;
		
		move(1);
		
		hp += 0.001;
		
		if(hp<0)
			hp = 0;
		
		if(hp>1)
			hp = 1;
		
	}
	
	public void move(int times){
		rotationCont += times*speed;
		box.x = (float) (centerX + radius*Math.sin(rotationCont));
		box.y = (float) (centerY + radius*Math.cos(rotationCont));
		
		
	}
	
	public void treatCollision(GameActor actor){
		if(actor.getClass() == Bullet.class){
			Bullet act = (Bullet)actor;
			if(!act.isHostile()){
			owner.bullets.get(owner.bullets.indexOf((Bullet) actor, true)).visible = false;
			hp -= 0.5f;
			}
			
		}
	}

}
