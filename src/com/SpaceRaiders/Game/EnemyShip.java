package com.SpaceRaiders.Game;

import com.badlogic.gdx.utils.Array;

public class EnemyShip extends Ship {

	public int behaviour;
	private float speed = 1f;
	private int limitR, limitL;
	public Array<ShipData> data;
	
	public EnemyShip(Array<Bullet> bullets) {
		super(bullets);
		data = new Array<ShipData>();
		
		box.set(250, 320, 48, 42);
		
		limitR = (int) (box.x + 80);
		limitL = (int) (box.x - 80);
		
		data.add(new ShipData(this));
		data.add(new ShipData(this));
		data.add(new ShipData(this));
		data.add(new ShipData(this));
		data.add(new ShipData(this));
		data.add(new ShipData(this));
	}

	@Override
	public void update() {
		box.x += speed;
		
		if(box.x > limitR || box.x <limitL)
			speed *= -1;
		
		if(box.x%30 == 0)
			shot();
		
		for(int i=0; i<bullets.size; i++){
			if(box.overlaps(bullets.get(i).box))
				treatCollision(bullets.get(i));
			for(int j=0; j<data.size; j++){
			if(data.get(j).box.overlaps(bullets.get(i).box)){
				data.get(j).treatCollision(bullets.get(i));
			}
			}
		}
		
		hp = 0;
		for(int i=0; i<data.size; i++){
			hp += data.get(i).hp;
		}
		
		if(hp<0)
			hp = 0;
		
	}
	
	public void shot(){	
		bullets.add(new Bullet(Math.round(box.x + box.width/2 - 3.5f), Math.round(box.y - 6f), -5, true));
	}
	
	public void treatCollision(GameActor actor){
		if(actor.getClass() == Bullet.class){
			bullets.get(bullets.indexOf((Bullet) actor, true)).visible = false;		
		}
	}

}
