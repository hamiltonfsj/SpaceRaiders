package com.SpaceRaiders.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class GameSceneMenu extends GameScene {

	private int indexMenu;
	
	private Texture background;
	private Texture select;
	private Array<Array<String>> menuElements;
	
	
	
	public GameSceneMenu(FileHandle scene) {
		super(scene);		
	}
	
	
	//Temporário//
	public GameSceneMenu() {
		super();
		
		//background = new Texture(Gdx.files.internal())))
	}
	
	public void buildMenu(){		
	}
	
	public void renderMenu(){		
	}


	@Override
	public void begin() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update() {
		// TODO Auto-generated method stub
		Gdx.gl.glClearColor(0.2f, 0f, 0f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);		
		batch.begin();
		font.draw(batch, "Você está num menu! Pressione Q para ir para uma fase", 50, 50);
		batch.end();
		
	}

}
