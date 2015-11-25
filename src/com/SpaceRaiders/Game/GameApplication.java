package com.SpaceRaiders.Game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
	public Music musicaMenu;
	public Music musicaFase;
	public Music musicaCut;
	public Sound SClick;
	public int musicaTocando;
	
	
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

		musicaMenu = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Menu.mp3"));
		musicaCut = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Cut.mp3"));
		musicaFase = Gdx.audio.newMusic(Gdx.files.internal("Sounds/Stage.mp3"));
		musicaMenu.setLooping(true);
		musicaCut.setLooping(true);
		musicaFase.setLooping(true);
		SClick = Gdx.audio.newSound(Gdx.files.internal("Sounds/click.wav"));
		musicaTocando = -1;
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
		SClick.play();
		FileHandle scene = Gdx.files.internal(sceneSource);
		
		if(scene.readString().split("\n")[0].trim().equals("@menu;")){
			gameScene = new GameSceneMenu(scene);
			menu = true;
			if(musicaTocando!=0)
				musicaMenu.play();
				musicaCut.stop();
				musicaFase.stop();
			musicaTocando=0;
		}
		if(scene.readString().split("\n")[0].trim().equals("@cut;")){
			gameScene = new GameSceneCut(scene);
			menu = true;
			if(musicaTocando!=1)
				musicaCut.play();
			musicaFase.stop();
			musicaMenu.stop();
			musicaTocando=1;
			
		}
		if(scene.readString().split("\n")[0].trim().equals("@stage;")){
			gameScene = new GameSceneStage(scene);
			menu = true;
			musicaMenu.stop();
			if(musicaTocando!=2)
				musicaFase.play();
			musicaMenu.stop();
			musicaCut.stop();
			musicaTocando=2;
			
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
