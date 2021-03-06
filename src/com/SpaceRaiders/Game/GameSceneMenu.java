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

public class GameSceneMenu extends GameScene {

	private int indexMenu;
	
	private Texture background;
	private Array<MenuElement> menuElements;
	 
	public boolean wire;
	public int index;
	public int numberOfButtons;
	
	
	
	public GameSceneMenu(FileHandle scene) {
		super(scene);		
		buildMenu(scene);
		shapeRenderer = new ShapeRenderer();
		batch = new SpriteBatch();
		
		background = new Texture(Gdx.files.internal("Images/Space.png"));
		
	}
	
	//Tempor�rio//
	public GameSceneMenu() {
		super();
		
		//background = new Texture(Gdx.files.internal())))
	}
	
	public void buildMenu(FileHandle scene){
		
		
		
		
		menuElements = new Array<MenuElement>();
		
		for(int i = 0; i<scene.readString().split(";").length; i++){
			if(scene.readString().split(";")[i].split(", ")[0].trim().equals("@menu")){
				System.out.println("passei pelo menu");
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
		
		/* com
		Gdx.gl.glClearColor(0.2f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		
		batch.begin();
		font.draw(batch, "Voc� est� num menu! Toque/Clique para ir para uma fase", 50, 50);
		batch.end();
		*/
		
		
		Gdx.gl.glClearColor(0f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);	
		batch.setProjectionMatrix(game.camera.combined);
		batch.begin();
		batch.draw(background, 0, 0);
		batch.end();

		batch.setColor(1, 1, 1, 1);
		batch.begin(); //Start Batch
		
		for(int i = 0; i<menuElements.size; i++){
			if(menuElements.get(i).type.equals("Button")){
				if(menuElements.get(i).index==index){
					//batch.draw(select, menuElements.get(i).x, menuElements.get(i).y);
					font.draw(batch, menuElements.get(i).info, menuElements.get(i).x , menuElements.get(i).y+17);
				}
				
				else{
					font.draw(batch, menuElements.get(i).info, menuElements.get(i).x, menuElements.get(i).y+17);	
				}
			}
			else if(menuElements.get(i).type.equals("Label")){
				font.draw(batch, menuElements.get(i).info, menuElements.get(i).x, menuElements.get(i).y+17);
			}
			else if(menuElements.get(i).type.equals("ImageButton")){
				batch.draw(menuElements.get(i).texture, menuElements.get(i).x, menuElements.get(i).y);
			}
			else if(menuElements.get(i).type.equals("Image")){
				batch.draw(menuElements.get(i).texture, menuElements.get(i).x, menuElements.get(i).y);
			}
		}
		
		
		
		
		batch.end();
		
		
		
		//Shows Wireframe
		if(wire){
			shapeRenderer.setAutoShapeType(true);
					shapeRenderer.begin();
						for(int i = 0; i<menuElements.size; i++){
							if(menuElements.get(i).type.equals("Button"))shapeRenderer.setColor(new Color(1, 0, 1, 1));
							else if(menuElements.get(i).type.equals("Label"))shapeRenderer.setColor(new Color(0, 1, 0, 1));
							else if(menuElements.get(i).type.equals("ImageButton"))shapeRenderer.setColor(new Color(1, 1, 0, 1));
							else shapeRenderer.setColor(new Color(1.0f, 0, 0, 1));
							shapeRenderer.rect(menuElements.get(i).x, menuElements.get(i).y, menuElements.get(i).width, menuElements.get(i).height);
						}
					shapeRenderer.end();
				}
				
		
		//inputs
		if(Gdx.input.isTouched()){
			System.out.println("click");
			for(int i = 0; i<menuElements.size; i++){
				
				Vector3 touchPos = new Vector3();
		        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
				game.camera.unproject(touchPos);
				
				
				
				if(touchPos.x > menuElements.get(i).x
				&& touchPos.y > menuElements.get(i).y
				&& touchPos.x < menuElements.get(i).x + menuElements.get(i).width 
				&& touchPos.y < menuElements.get(i).y + menuElements.get(i).height){
					System.out.println("algo Aconteceu");
					index = menuElements.get(i).index;
					if(menuElements.get(i).action.equals("muteMusic")){
						System.out.println("mutou music");
					}
					else if(menuElements.get(i).action.equals("muteSound")){
						System.out.println("mutou som");
					}
					else if(menuElements.get(i).action.equals("Sair")){
						Gdx.app.exit();
					}
					else if(menuElements.get(i).action.trim().equals("")){
						System.out.println("mutou som");
					}
					else{
						game.loadScene(menuElements.get(i).action);	
					}
				}
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
			
		
	}

}
