package com.SpaceRaiders.Game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameApplication extends ApplicationAdapter {
	
	private int frameCount;
	
	private FileHandle scene;
	private GameScene gameScene;
	

	public GameApplication getGameApplication() {
		return this;
	}

	public GameScene getActualScene() {
		return gameScene;
	}

	public int getFrameCount() {
		return frameCount;
	}

}
