package com.SpaceRaiders.Game;

public class ShipData extends GameActor {
	
	public int data;
	public final static int radius = 50;
	public int distance = 52;
	public final static float speed = 0.00001f;
	public float rotationCont, hp, centerX, centerY;
	public boolean restore;
	public EnemyShip owner;

	
	public ShipData(EnemyShip owner){
		restore = true;
		hp = 1.0f;
		
		distance = (int) (1.047/speed);
		this.data = Math.round((float)Math.random());
		this.owner = owner;
		box.set(0, 0, 20, 20);
		
		centerX = owner.box.x + owner.box.width/2 - box.width/2;
		centerY = owner.box.y + owner.box.height/2 - box.height/2;
		
		move((int) (owner.data.size*distance));
	}
	
	@Override
	public void update(){

		centerX = owner.box.x + owner.box.width/2 - box.width/2;
		centerY = owner.box.y + owner.box.height/2 - box.height/2;
		
		box.width = 20*hp;
		box.height = 20*hp;
		
		move(1);


		if(restore){
		
			hp += owner.restoreRate;
		
		}
		else{
			if(hp<0.5)
				hp = 0;
			
		}
		
		
		
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
