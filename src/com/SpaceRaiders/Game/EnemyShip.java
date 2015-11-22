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
		
	}

}
