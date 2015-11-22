package com.SpaceRaiders.Game;

import com.badlogic.gdx.math.Rectangle;

public abstract class GameActor {

	public Rectangle box; //public temporariamente
	public float speedY;
	
	
	public GameActor(){
		box = new Rectangle();
	}

	public void update() {
	}

	public void updateAllActors() {
	}

}
