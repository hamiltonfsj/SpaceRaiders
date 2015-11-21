package com.SpaceRaiders.Game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameApplication extends ApplicationAdapter {
	private int frameCount;
	private FileHandle sceneSource;
	private GameScene gameScene;

	
	boolean menu; //temporário
	

	public GameApplication getGameApplication() {
		return this;
	}
	
	@Override
	public void create(){
		frameCount = 0;
		loadScene("MainMenu.scn");
		menu = true;
	}
	
	@Override
	public void render(){
		frameCount += 1;
		Gdx.gl.glClearColor(0f, 0f, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gameScene.update(this);
		
		
		if(Gdx.input.isKeyJustPressed(Keys.Q)){
			loadScene();
		}
	}
	
	public void loadScene(String sceneSource){
		
		FileHandle scene = Gdx.files.internal(sceneSource);
		
		if(scene.readString().split("\n")[0].trim().equals("@menu;")){
			gameScene = new GameSceneMenu(scene);
			menu = true;
		}
	}
	
	//temporário
	public void loadScene(){
		if(menu)gameScene = new GameSceneStage();
		else gameScene = new GameSceneMenu();
		menu = !menu;
	}

	public GameScene getCurrentScene() {
		return gameScene;
	}

	public int getFrameCount() {
		return frameCount;
	}
	
	
	

}
