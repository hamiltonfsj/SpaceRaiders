package com.SpaceRaiders.Game;


import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class GameScene {
	
	protected BitmapFont font;
	protected ShapeRenderer shapeRenderer;
	protected SpriteBatch batch;
	

	public GameScene(FileHandle scene){	
		batch = new SpriteBatch();
		font = new BitmapFont();
		shapeRenderer = new ShapeRenderer();
	}
	
	//Temporário
	public GameScene(){
		batch = new SpriteBatch();
		font = new BitmapFont();
		shapeRenderer = new ShapeRenderer();
	}
	
	abstract public void begin();
	
	abstract public void update(GameApplication game);

}
