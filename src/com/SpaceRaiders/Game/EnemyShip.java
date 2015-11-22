package com.SpaceRaiders.Game;

import com.badlogic.gdx.utils.Array;

public class EnemyShip extends Ship {

	public int behaviour;
	public Array<ShipData> data;
	
	public EnemyShip(Array<Bullet> bullets) {
		super(bullets);
		data = new Array<ShipData>();
		
		box.set(50, 200, 80, 100);
		data.add(new ShipData(this));
		data.add(new ShipData(this));
		data.add(new ShipData(this));
		data.add(new ShipData(this));
		data.add(new ShipData(this));
		data.add(new ShipData(this));
	}

	@Override
	public void update() {
	}

}
