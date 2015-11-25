package com.SpaceRaiders.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class GameSceneCut extends GameScene {
	private int indexScene;
	
	private Texture background;
	private Texture backgroundText;
	private Array<MenuElement> menuElements;
	private MenuElement skipBtn;
	
	public int BGfactor;
	 
	public boolean wire;
	public int index;
	public int numberOfButtons;
	public Array<Texture> scenes;
	
	private int countdown;
	
	public GameSceneCut(FileHandle scene) {
		super(scene);		
		buildMenu(scene);
		shapeRenderer = new ShapeRenderer();
		skipBtn = new MenuElement("ImageButton, Images/SkipButton.png, 455, 340, 1, Scenes/StageSelect.scn, 350, 100");
		batch = new SpriteBatch();
		scenes = new Array<Texture>();
		background = new Texture(Gdx.files.internal("Images/CutScene/Back.png"));
		backgroundText = new Texture(Gdx.files.internal("Images/CutScene/TextBox.png"));
		
	}
	
	//Temporário//
	public GameSceneCut() {
		super();
		
		//background = new Texture(Gdx.files.internal())))
	}
	
	public void buildMenu(FileHandle scene){
		
		indexScene = 0;	
		
		menuElements = new Array<MenuElement>();
		
		
		for(int i = 0; i<scene.readString().split(";").length; i++){
			if(scene.readString().split(";")[i].split(", ")[0].trim().equals("@menu")){
				System.out.println("passei pelo menu");
			}
			else if(scene.readString().split(";")[i].split(", ")[0].trim().equals("@cut")){
				System.out.println("passei por uma cut");
			}
			else{
				menuElements.add(new MenuElement(scene.readString().split(";")[i]));
			}
			Array<String> a = new Array<String>();
			for(int j=0; j<scene.readString().split(";")[i].split(", ").length; j++){	
				a.add(scene.readString().split(";")[i].split(", ")[j].trim());
				
			}
		}
		
	}
	
	public void renderMenu(){		
	}


	@Override
	public void begin() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(GameApplication game) {
		// TODO Auto-generated method stub
			
		
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(game.camera.combined);
		batch.begin();
		BGfactor-=1;
		if(BGfactor<-800)
			BGfactor=0;
		
		batch.draw(background, BGfactor+800, 0);
		batch.draw(background, BGfactor, 0);
		batch.draw(backgroundText, 0, 0);
		batch.draw(skipBtn.texture, 720, 400, 70, 70);
		
		batch.end();
		
		batch.setColor(1, 1, 1, 1);
		batch.begin(); //Start Batch
		
		
		batch.draw(menuElements.get(indexScene).texture, menuElements.get(indexScene).x, menuElements.get(indexScene).y);
		
		batch.end();
		
		

		
		//inputs
		if(Gdx.input.isTouched() && countdown==0){
			System.out.println("click");
			indexScene++;
			countdown=20;
			if(indexScene>=menuElements.size){
				game.loadScene("Scenes/Stage1.scn");
			}
			Vector3 touchPos = new Vector3();
	        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			game.camera.unproject(touchPos);
			if(touchPos.x>720 && touchPos.y > 400){
				game.loadScene("Scenes/Stage1.scn");
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.W)){
			wire = !wire;
		}
		
		
		if(Gdx.input.isKeyJustPressed(Keys.UP)){
			if(index>1){
				index --;
			}else{
				index=numberOfButtons;
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.DOWN)){
			if(index<numberOfButtons){
				index ++;
			}else{
				index=1;
			}
		}
		
		
		
		if(Gdx.input.isKeyJustPressed(Keys.A)){
			for(int i=0; i<menuElements.size; i++){
				if(menuElements.get(i).index == index){
					if(menuElements.get(i).action.equals("Sair")){
						Gdx.app.exit();
					}
					else{
						System.out.println(menuElements.get(i).action);
						game.loadScene(menuElements.get(i).action);
						index = 1;
						break;	
					}
					
				}
			}
		
		}
		
		if(countdown>0) countdown--;
			
		
	}

}
