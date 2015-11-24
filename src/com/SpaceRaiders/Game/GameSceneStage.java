package com.SpaceRaiders.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class GameSceneStage extends GameScene {
	private boolean pause;
	
	public int currentGroup, scanCount;
	private int enemiesCount, score;
	
	private HeroShip ship;
	private EnemyShip enemyTest, eB, eC, eD;
	public OrthographicCamera camera; //WTf
	private Texture imgRaidOn, imgRaidOff;
	private Texture imgBackground;
	private Texture imgShip;
	private Texture imgEnemyShip, imgEnemyShipB;
	private Texture imgShipDataA, imgShipDataB;
	private Texture imgShot;
	private int offset;

	private Texture imgHeatBar, imgLifeBar, imgArrowOff, imgArrowOn;
	
	public Array<Array<EnemyShip>> enemies;
	private Array<Bullet> bullets;

	public GameSceneStage(FileHandle scene) {
		super(scene);
		
		pause = false;
		
		score = 0;
		scanCount = 3;
		//Carregando Imagens
		imgBackground = new Texture(Gdx.files.internal("Space.png"));
		imgShip = new Texture(Gdx.files.internal("Ship.png"));
		imgShot = new Texture(Gdx.files.internal("Bullet.png"));
		imgRaidOn = new Texture(Gdx.files.internal("RAIDon2.png"));
		imgRaidOff = new Texture(Gdx.files.internal("RAIDoff2.png"));
		imgEnemyShip = new Texture(Gdx.files.internal("Raid.png"));
		imgEnemyShipB = new Texture(Gdx.files.internal("Raid.png"));
		imgShipDataA = new Texture(Gdx.files.internal("Bit_0.png"));
		imgShipDataB = new Texture(Gdx.files.internal("Bit_1.png"));
		
		imgHeatBar = new Texture(Gdx.files.internal("SAquecimento.png"));
		imgLifeBar = new Texture(Gdx.files.internal("Vida.png"));
		imgArrowOff = new Texture(Gdx.files.internal("Setas.png"));
		imgArrowOn = new Texture(Gdx.files.internal("SetasConfirma.png"));
		
		bullets = new Array<Bullet>();
		ship = new HeroShip(bullets, this);
		enemyTest = new EnemyShip(bullets, 4, 0);
		eB = new EnemyShip(bullets, 4, 1);
		eC = new EnemyShip(bullets, 4, 2);
		
		///Enchendo o vetor de inimigos
		enemies = new Array<Array<EnemyShip>>();
		
		for(int i = 0; i<scene.readString().split(";").length; i++){
			if(scene.readString().split(";")[i].split(", ")[0].trim().equals("@menu")){
				System.out.println("passei pelo menu");
			}
			else if(scene.readString().split(";")[i].split(", ")[0].trim().equals("@stage")){
				System.out.println("passei pelo cabeçalho");
			}
			else{
				enemies.add(new Array<EnemyShip>());
				for(int j = 0; j<Integer.parseInt(scene.readString().split(";")[i].split(",")[1].trim()); j++){
					
					enemies.get(i-1).add(
						new EnemyShip(
								bullets,
								Integer.parseInt(scene.readString().split(";")[i].split(",")[0].trim()), 
								j));
					enemies.get(i-1).get(j).setHorde(enemies.get(i-1));
				}
			}
			
			System.out.println();
			
			//----------------------------------------------------------------------------------------
			
			
			
			
			
			/*
			enemies.add(new Array<EnemyShip>());
			enemies.get(0).add(enemyTest);
			enemies.get(0).add(eB);
			enemies.get(0).add(eC);
			enemies.get(0).get(0).setHorde(enemies.get(0));
			enemies.get(0).get(1).setHorde(enemies.get(0));
			enemies.get(0).get(2).setHorde(enemies.get(0));
			*/
			
			currentGroup = 0;
		}
	}
	
	//Temporário
	public GameSceneStage() {
		super();
		
		pause = false;
		
		score = 0;
		scanCount = 3;
		//Carregando Imagens
		imgBackground = new Texture(Gdx.files.internal("Space.png"));
		imgShip = new Texture(Gdx.files.internal("Ship.png"));
		imgShot = new Texture(Gdx.files.internal("Bullet.png"));
		imgRaidOn = new Texture(Gdx.files.internal("RAIDon2.png"));
		imgRaidOff = new Texture(Gdx.files.internal("RAIDoff2.png"));
		imgEnemyShip = new Texture(Gdx.files.internal("Raid.png"));
		imgEnemyShipB = new Texture(Gdx.files.internal("Raid.png"));
		imgShipDataA = new Texture(Gdx.files.internal("Bit_0.png"));
		imgShipDataB = new Texture(Gdx.files.internal("Bit_1.png"));
		
		imgHeatBar = new Texture(Gdx.files.internal("SAquecimento.png"));
		imgLifeBar = new Texture(Gdx.files.internal("Vida.png"));
		imgArrowOff = new Texture(Gdx.files.internal("Setas.png"));
		imgArrowOn = new Texture(Gdx.files.internal("SetasConfirma.png"));
		
		enemies = new Array<Array<EnemyShip>>();
		bullets = new Array<Bullet>();
		ship = new HeroShip(bullets, this);
		enemyTest = new EnemyShip(bullets, 9, 0);
		eB = new EnemyShip(bullets, 9, 1);
		eC = new EnemyShip(bullets, 9, 2);
		eD = new EnemyShip(bullets, 9, 3);
		enemies.add(new Array<EnemyShip>());
		enemies.get(0).add(enemyTest);
		enemies.get(0).add(eB);
		enemies.get(0).add(eC);
		enemies.get(0).add(eD);
		enemies.get(0).get(0).setHorde(enemies.get(0));
		enemies.get(0).get(1).setHorde(enemies.get(0));
		enemies.get(0).get(2).setHorde(enemies.get(0));
		enemies.get(0).get(3).setHorde(enemies.get(0));
		
		currentGroup = 0;
	}

	public void renderPause(){		
	}

	@Override
	public void begin() {
		// TODO Auto-generated method stub
		
	}

	public void update(GameApplication game) {
		//Despausado
		
		if (!pause){
			
			// TODO Auto-generated method stub
			//Gdx.gl.glClearColor(0f, 0f, 0.2f, 1);
			//Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
	
			//batch.setColor(0,1,0,1);
			batch.setProjectionMatrix(game.camera.combined);
			shapeRenderer.setProjectionMatrix(game.camera.combined);
			batch.begin();
			shapeRenderer.setAutoShapeType(true);
			shapeRenderer.begin();
			shapeRenderer.set(ShapeRenderer.ShapeType.Filled);
			
			
			Rectangle boxA;
			Rectangle boxB;
			offset+=3;
			if(offset==480)offset = 0;
			
			batch.draw(imgBackground, 0,-offset);
			batch.draw(imgBackground, 0, 480-offset);
			
			for(int i=0; i<bullets.size; i++){
				boxA = bullets.get(i).box;
				
				if(bullets.get(i).speedY>0)
				batch.draw(imgShot, boxA.x, boxA.y);
				else
					batch.draw(imgShot, boxA.x, boxA.y, imgShot.getWidth(), imgShot.getHeight(), 0, 0, imgShot.getWidth(), imgShot.getHeight(), false, true);
	
			}
	
			for(int i=0; i<enemies.get(currentGroup).size; i++){
				boxA = enemies.get(currentGroup).get(i).box;
				if(enemies.get(currentGroup).get(i).behaviour == 2 || enemies.get(currentGroup).get(i).behaviour == 3
						|| enemies.get(currentGroup).get(i).behaviour == 4 )
				batch.draw(imgEnemyShipB, boxA.x, boxA.y);
				else
					batch.draw(imgEnemyShip, boxA.x, boxA.y);
				for(int j=0; j<enemies.get(currentGroup).get(i).data.size; j++){
					boxB = enemies.get(currentGroup).get(i).data.get(j).box;
					float hp = enemies.get(currentGroup).get(i).data.get(j).hp;
					if(enemies.get(currentGroup).get(i).data.get(j).data == 0)
						batch.draw(imgShipDataA, boxB.x, boxB.y, boxB.width, boxB.height*hp);
					else
						batch.draw(imgShipDataB, boxB.x, boxB.y, boxB.width, boxB.height);
					
				}
			}
			
			batch.draw(imgShip, ship.box.x, ship.box.y);
			
			if(!ship.overheat)
			shapeRenderer.setColor(Color.ORANGE);
			
			else
			shapeRenderer.setColor(Color.RED);
			shapeRenderer.rect(5, 125, 11, 190*ship.heat );
			
			
			batch.draw(imgHeatBar, 0, 120);
			
			shapeRenderer.setColor(Color.GREEN);
			shapeRenderer.rect(133, 460, 186*ship.hp, 13 );
			batch.draw(imgArrowOff, 0, 0);
			batch.draw(imgLifeBar, 125, 455);
			batch.draw(imgArrowOff, 800 - imgArrowOff.getWidth(), 0, imgArrowOff.getWidth(), imgArrowOff.getHeight(), 0, 0, imgArrowOff.getWidth(), imgArrowOff.getHeight(), true, false);
			
			if(ship.raidRevealed){		
			batch.draw(imgRaidOn, 0, 480 - imgRaidOn.getHeight());
			font.draw(batch, Integer.toString(enemies.get(0).get(0).behaviour), 25, 360);
			}
			else{
				batch.draw(imgRaidOff, 0, 480 - imgRaidOff.getHeight());	
			}
	
			
			batch.end();
			shapeRenderer.end();
		
			ship.update(game.camera);
			for(int i=0; i<bullets.size; i++){
				bullets.get(i).update();
				
			}
			
			for(int i=0; i<enemies.get(currentGroup).size; i++){
				enemies.get(currentGroup).get(i).update();
				for(int j=0; j<enemies.get(currentGroup).get(i).data.size; j++){
					enemies.get(currentGroup).get(i).data.get(j).update();
				}
				
			}
			
			
			for(int i=0; i<bullets.size; i++){
				if(!bullets.get(i).visible){
					bullets.removeIndex(i);
					score += 5;
					i--;
					
				}
			}
			
			for(int i=0; i<enemies.get(currentGroup).size; i++){
				if(!enemies.get(currentGroup).get(i).visible){
					score += enemies.get(currentGroup).get(i).score;
					
					enemies.get(currentGroup).removeIndex(i);
					score += 30;
					i--;
				}
			}
			
			//-----------Verificar-----------
			
			//Chamar próxima horda
			int cont = 0;
			for(int i=0; i<enemies.get(currentGroup).size; i++){
				if(enemies.get(currentGroup).get(i).visible){
					cont++;
				}
			}
			if(cont==0){
				currentGroup++;
			}
			
			//Chamar gameOver
			if(ship.hp<=0){
				game.loadScene("Scenes/GameOver.scn");
			}
			
		}
		
		
		
		
		
		//=======================Pausar====================
		else{
			/*if Dentro do botão 1
			pause = false;
			 */
			
			/*if Dentro do botão 2
			 * 
			//game.makeScene("Scenes/MainMenu.scn"); 
			 */
			
		}
		
		if(Gdx.input.isKeyJustPressed(Keys.P)){
			pause = !pause;
		}
		
	}

}
