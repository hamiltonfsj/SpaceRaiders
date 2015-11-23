package com.SpaceRaiders.Game;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class HeroShip extends Ship {
	
	public boolean raidRevealed = false, overheat = false;
	public float heat;
	private Rectangle buttonL, buttonR, buttonS, buttonA;
	
	GameSceneStage game;
	
	public HeroShip(Array<Bullet> bullets, GameSceneStage game){
		
		super(bullets);
		
		buttonL = new Rectangle();
		buttonR = new Rectangle();
		buttonS = new Rectangle();
		buttonA = new Rectangle();
		
		buttonL.set(0,380, 100, 100);
		buttonR.set(700, 380, 100, 100);
		buttonS.set(150, 0, 500, 480);
		buttonA.set(0, 0, 150, 150);
		
		this.hp = 1.0f;
		this.heat = 0;
		this.game = game;
		box.set(350, 0, 50, 71);
		
	}
	
	public void displayHud(){
	}
	
	public void shot(){	
		bullets.add(new Bullet(Math.round(box.x + box.width/2 - 19), Math.round(box.y + 38), 10, 0, false));
		bullets.add(new Bullet(Math.round(box.x + box.width/2 + 12), Math.round(box.y + 38), 10, 0, false));
	}
	
	@Override
	public void update(){
		if(!overheat)
		input();
		if(heat >= 1.0f){
			overheat = true;
			heat = 1.0f;
			
		}
		
			
		heat -= 0.005f;
		
		if(heat<= 0.0f){
			overheat = false;
			heat = 0.0f;
		}
		
		for(int i=0; i<bullets.size; i++){
			if(box.overlaps(bullets.get(i).box))
				treatCollision(bullets.get(i));
		}
		
		if(hp<0)
			hp = 0;
	}
	
	public void update(SpriteBatch batch){
		
	}
	
	private void input(){

		
		// SE TOCOU BOTÃO DIREITO
		if(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D) ||
			(Gdx.input.isTouched() && buttonR.contains(new Vector2(Gdx.input.getX(), Gdx.input.getY())) )){
			box.x += 10;
		}
		 
		
		// SE TOCOU BOTÃO ESQUERDO
		if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A) ||
		(Gdx.input.isTouched() && buttonL.contains(new Vector2(Gdx.input.getX(), Gdx.input.getY())) )){
			box.x -= 10;
		}
		
		
		// SE TOCOU CENTRO DA TELA
		if(Gdx.input.justTouched() &&  buttonS.contains(new Vector2(Gdx.input.getX(), Gdx.input.getY())) ){

			if(!overheat){
				heat += 0.15;
				shot();
			
			}
		}
		
		// SE TOCOU BOTÃO RAID
		if(Gdx.input.isKeyPressed(Keys.R) || (Gdx.input.justTouched() &&  buttonA.contains(new Vector2(Gdx.input.getX(), Gdx.input.getY())))){
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
	
	public void treatCollision(GameActor actor){
		if(actor.getClass() == Bullet.class){
			Bullet act = (Bullet)actor;
			if(act.isHostile()){
			bullets.get(bullets.indexOf((Bullet) actor, true)).visible = false;
			hp -= act.dmg;
			}
		}
	}
}

	
