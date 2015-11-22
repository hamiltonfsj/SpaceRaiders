package com.SpaceRaiders.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class HeroShip extends Ship {
	
	public HeroShip(Array<Bullet> bullets){
		super(bullets);
		box.set(0, 0, 80, 100);
	}
	
	public void displayHud(){
	}
	
	public void shot(){	
		bullets.add(new Bullet(Math.round(box.x + box.width/2), Math.round(box.y + box.height), 2f));
	}
	
	@Override
	public void update(){
		input();
	}
	
	public void update(SpriteBatch batch){
		
	}
	
	private void input(){
		
		// SE TOCOU BOTÃO DIREITO
		if(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)){
			box.x += 10;
		}
		 
		
		// SE TOCOU BOTÃO ESQUERDO
		if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)){
			box.x -= 10;
		}
		
		
		// SE TOCOU CENTRO DA TELA
		if(Gdx.input.justTouched()){
			shot();
		}
		
		// SE TOCOU BOTÃO RAID
		if(Gdx.input.isKeyPressed(Keys.R)){
			analyze();
		}
		
		
		// CORREÇÃO DE POSIÇÃO NA TELA
		
		if(box.x + box.width>Gdx.graphics.getWidth()){
			box.x = Gdx.graphics.getWidth() - box.width;
		}
		
		if(box.x < 0){
			box.x = 0;
		}
	
	}
	
	private void analyze(){
		
	}
}
	
