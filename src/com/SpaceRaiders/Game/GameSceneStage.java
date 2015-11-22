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
	private EnemyShip enemyTest;
	private OrthographicCamera camera;
	private Texture imgShip;
	private Texture imgEnemyShip;
	private Texture imgShipData;
	private Texture imgShot;
	private Array<Array<EnemyShip>> enemies;
	private Array<Bullet> bullets;

	public GameSceneStage(FileHandle scene) {
		super(scene);
	}
	
	//Temporário
	public GameSceneStage() {
		super();
		enemies = new Array<Array<EnemyShip>>();
		bullets = new Array<Bullet>();
		ship = new HeroShip(bullets);
		enemyTest = new EnemyShip(bullets);
		enemies.add(new Array<EnemyShip>());
		enemies.get(0).add(enemyTest);
		
		currentGroup = 0;
	}

	public void renderPause(){		
	}

	@Override
	public void begin() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(GameApplication game) {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0f, 0f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		shapeRenderer.begin(ShapeType.Filled);
		shapeRenderer.setColor(0.5f, 0, 0, 1);
		shapeRenderer.rect(ship.box.x, ship.box.y, ship.box.width, ship.box.height);
		for(int i=0; i<bullets.size; i++){
			shapeRenderer.rect(bullets.get(i).box.x, bullets.get(i).box.y, bullets.get(i).box.width, bullets.get(i).box.height);

		}
		
		for(int i=0; i<enemies.get(currentGroup).size; i++){
			shapeRenderer.rect(enemies.get(currentGroup).get(i).box.x, enemies.get(currentGroup).get(i).box.y, enemies.get(currentGroup).get(i).box.width, enemies.get(currentGroup).get(i).box.height);
			for(int j=0; j<enemies.get(currentGroup).get(i).data.size; j++){
				shapeRenderer.setColor(0.5f, 1, 0, 1);
				shapeRenderer.rect(enemies.get(currentGroup).get(i).data.get(j).centerX,enemies.get(currentGroup).get(i).data.get(j).centerY,enemies.get(currentGroup).get(i).data.get(j).box.width,enemies.get(currentGroup).get(i).data.get(j).box.height);
				
				shapeRenderer.setColor(0.5f, 0, 0, 1);
				shapeRenderer.rect(enemies.get(currentGroup).get(i).data.get(j).box.x,enemies.get(currentGroup).get(i).data.get(j).box.y,enemies.get(currentGroup).get(i).data.get(j).box.width,enemies.get(currentGroup).get(i).data.get(j).box.height);
			}
		}
		shapeRenderer.end();
		
		batch.begin();
		
		for(int i=0; i<enemies.get(currentGroup).size; i++){
			for(int j=0; j<enemies.get(currentGroup).get(i).data.size; j++){
				batch.setColor(1, 1, 1, 1);
				font.draw(batch, Integer.toString(enemies.get(currentGroup).get(i).data.get(j).data), enemies.get(currentGroup).get(i).data.get(j).box.x -2 + enemies.get(currentGroup).get(i).data.get(j).box.width/2, enemies.get(currentGroup).get(i).data.get(j).box.y + 5 + enemies.get(currentGroup).get(i).data.get(j).box.height/2);
				
			}
		}
		
		batch.end();
		
		ship.update();
		for(int i=0; i<bullets.size; i++){
			bullets.get(i).update();
			
		}
		
		for(int i=0; i<enemies.get(currentGroup).size; i++){
			enemies.get(currentGroup).get(i).update();
			for(int j=0; j<enemies.get(currentGroup).get(i).data.size; j++){
				enemies.get(currentGroup).get(i).data.get(j).update();
			}
			
		}
		
		
	}

}
