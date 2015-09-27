package com.SpaceRaiders.Game;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class GameSceneStage extends GameScene {
	
	private int actualGroup;
	private int enemiesCount;
	
	private HeroShip ship;
	private OrthographicCamera camera;
	private Texture imgShip;
	private Texture imgEnemyShip;
	private Texture imgShipData;
	private Texture imgShot;
	private Array<Array<EnemyShip>> enemies;

	public GameSceneStage(FileHandle scene) {
		super(scene);
	}

	public void renderPause(){		
	}

}
