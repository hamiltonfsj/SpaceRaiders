package com.SpaceRaiders.Game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameApplication extends ApplicationAdapter {
	private int frameCount;
	private FileHandle sceneSource;
	private GameScene gameScene;
	private Texture fatecLogo;
	private SpriteBatch fadeBatch;
	private float fadeFactor;
	boolean menu; //temporário
	
	public OrthographicCamera camera;
	

	public GameApplication getGameApplication() {
		return this;
	}
	
	@Override
	public void create(){
		frameCount = 0;
		fadeFactor = 0;
		fatecLogo = new Texture(Gdx.files.internal("Images/fateclogo.png"));
		fadeBatch = new SpriteBatch();
		loadScene("Scenes/MainMenu.scn");
		//gameScene = new GameSceneStage();
		//menu = true;
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 800, 480);
	}
	
	@Override
	public void render(){
		camera.update();
		frameCount += 1;
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		if(frameCount>250){
			gameScene.update(this);
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.Q)){
			loadScene();
		}
		
		
		if(frameCount<180){
			fadeBatch.setColor(1, 1, 1, fadeFactor);
			fadeBatch.setProjectionMatrix(camera.combined);
			fadeBatch.begin();
			fadeBatch.draw(fatecLogo, 0, 0);
			fadeBatch.end();
			if(frameCount<60){
				fadeFactor+=(1f/60);
			}
			if(frameCount>120 && frameCount<180){
				fadeFactor-=(1f/60);
			}
		}
	}
	
	public void loadScene(String sceneSource){
		
		FileHandle scene = Gdx.files.internal(sceneSource);
		
		if(scene.readString().split("\n")[0].trim().equals("@menu;")){
			gameScene = new GameSceneMenu(scene);
			menu = true;
		}
		if(scene.readString().split("\n")[0].trim().equals("@cut;")){
			gameScene = new GameSceneCut(scene);
			menu = true;
		}
		if(scene.readString().split("\n")[0].trim().equals("@stage;")){
			gameScene = new GameSceneStage(scene);
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
