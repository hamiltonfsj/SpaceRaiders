package com.SpaceRaiders.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Array;

public class GameSceneStage extends GameScene {
	
	private int currentGroup;
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
	
	//Temporário
	public GameSceneStage() {
		super();
		ship = new HeroShip();
	}

	public void renderPause(){		
	}

	@Override
	public void begin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0f, 0f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(0.5f, 0, 0, 1);
		shapeRenderer.rect(ship.box.x, ship.box.y, ship.box.width, ship.box.height);
		shapeRenderer.end();
		
		batch.begin();
		font.draw(batch, "Você está numa fase! Pressione Q para ir para o menu", 50, Gdx.graphics.getHeight()-20);
		batch.end();
		
		ship.update();
		
	}

}
