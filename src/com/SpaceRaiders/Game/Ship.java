package com.SpaceRaiders.Game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class Ship extends GameActor {

	public float hp;
	protected Array<Bullet> bullets;

	protected Vector2 position;

	public Ship(Array<Bullet> bullets){
		super();
		this.bullets = bullets;
	}
	
	public void displayHud(){		
	}
	
	public void shot(){		
	}
	
	public void update(){		
	}	
	
	private void input(){		
	}

	public void update(OrthographicCamera camera) {
		// TODO Auto-generated method stub
		
	}

}
