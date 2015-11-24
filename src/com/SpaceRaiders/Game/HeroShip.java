package com.SpaceRaiders.Game;



import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
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
		box.set(350, 10, 50, 71);
		
	}
	
	public void displayHud(){
	}
	
	public void shot(){	
		bullets.add(new Bullet(Math.round(box.x + box.width/2 - 19), Math.round(box.y + 38), 10, 0, false));
		bullets.add(new Bullet(Math.round(box.x + box.width/2 + 12), Math.round(box.y + 38), 10, 0, false));
	}
	
	@Override
	public void update(OrthographicCamera camera){
		if(!overheat)
		input(camera);
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
	
	private void input(OrthographicCamera camera){
		
		
		// SE TOCOU BOTÃO DIREITO
		if(
			(Gdx.input.isTouched())){
			Vector3 touchPos = new Vector3();
	        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			if(buttonR.contains(new Vector2(touchPos.x, 480 - touchPos.y)))
				box.x += 10;
		}
		 
		if(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D))
			box.x += 10;
		
		
		// SE TOCOU BOTÃO ESQUERDO
		if(
		(Gdx.input.isTouched())){
			Vector3 touchPos = new Vector3();
	        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touchPos);
			if(buttonL.contains(new Vector2(touchPos.x, 480 - touchPos.y)))
			box.x -= 10;
		}
		
		if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A))
			box.x -= 10; 
		
		
		// SE TOCOU CENTRO DA TELA
		if(Gdx.input.justTouched() &&  buttonS.contains(new Vector2(Gdx.input.getX(), Gdx.input.getY())) ){

			if(!overheat){
				heat += 0.05;
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
			hp -= act.dmg/10;
			}
		}
	}
}

	
