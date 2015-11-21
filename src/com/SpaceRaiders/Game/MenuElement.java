package com.SpaceRaiders.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class MenuElement extends GameActor {
	
	public int x;
	public int y;
	public int width;
	public int height;
	
	public String action;
	public String info;
	public String type;	
	public int index;

	public Texture texture;
	
	public MenuElement(String content){

		type = content.split(", ")[0].trim();
		if(type.equals("ImageButton")){
			texture = new Texture(Gdx.files.internal(content.split(", ")[1].trim()));
		}
		
		
		info = content.split(", ")[1].trim();
		
		x = Integer.parseInt(content.split(", ")[2].trim());
		y = Integer.parseInt(content.split(", ")[3].trim());
		
		action = content.split(", ")[5].trim();
		
		width = Integer.parseInt(content.split(", ")[6].trim());
		height = Integer.parseInt(content.split(", ")[7].trim());
	}

}
