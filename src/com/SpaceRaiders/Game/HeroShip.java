package com.SpaceRaiders.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class HeroShip extends Ship {
	
	public boolean raidRevealed = false, overheat = false;
	public float heat;
	
	GameSceneStage game;
	
	public HeroShip(Array<Bullet> bullets, GameSceneStage game){
		
		
		super(bullets);
		this.heat = 0;
		this.game = game;
		box.set(0, 0, 50, 71);
		
	}
	
	public void displayHud(){
	}
	
	public void shot(){	
		bullets.add(new Bullet(Math.round(box.x + box.width/2 - 3.5f), Math.round(box.y + box.height), 10));
	}
	
	@Override
	public void update(){
		input();
		if(heat >= 1.0f)
			overheat = true;
		
			
		heat -= 0.005f;
		
		if(heat<= 0.0f){
			overheat = false;
			heat = 0.0f;
		}
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
			if(!overheat){
				heat += 0.25;
				shot();
			
			}
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
		if(!raidRevealed && game.scanCount>0){
		raidRevealed = true;
		game.scanCount--;
		}
	}
}
	
