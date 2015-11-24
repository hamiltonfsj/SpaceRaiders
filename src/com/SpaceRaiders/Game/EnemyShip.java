package com.SpaceRaiders.Game;

import com.badlogic.gdx.utils.Array;

public class EnemyShip extends Ship {

	public int behaviour, score;
	private float speed=1, dmg=0.1f;
	public float restoreRate = 0.003f;
	private int limitR, limitL, label;
	public Array<ShipData> data;
	public Array<EnemyShip> horde;
	
	public float time;
	
	public EnemyShip(Array<Bullet> bullets, int behaviour, int label) {
		super(bullets);
		data = new Array<ShipData>();
		visible = true;
		this.behaviour = behaviour;
		this.label = label;
		hp = 1.0f;
		box.set(180 + label*130, 350, 48, 42);
		
		switch(behaviour){
		case 0:
			score = 15;
			speed = 2;
			dmg = 0.3f;	
			restoreRate = 0;
			break;
		case 1:
			score = 20;
			break;
		case 10:
			score = 25;
			speed = 2;
			dmg = 0.3f;
			break;
		case 5:
			score = 30;
			restoreRate = 0.005f;
			break;
		case 6:
			score = 50;
			restoreRate = 0.001f;
			break;
		case 2:
			score = 10;
			break;
		case 3:
			score = 20;
			break;
		case 4:
			score = 30;
			break;
		
		
		}
		
		limitR = (int) (box.x + 80);
		limitL = (int) (box.x - 80);
		
		data.add(new ShipData(this));
		data.add(new ShipData(this));
		data.add(new ShipData(this));
		data.add(new ShipData(this));
		data.add(new ShipData(this));
		data.add(new ShipData(this));
	}

	@Override
	public void update() {
		box.x += speed*(65/horde.size)*(float) Math.sin(time/100f*3f)/20;
		time+= 4f;
		
		//speed = *
		
		/*
		box.x += speed;
		if(box.x > limitR || box.x <limitL)
			speed *= -1;
		
		*/
		
		if((int)(time) %23 == 0)
			shot();
		
		for(int i=0; i<bullets.size; i++){
			if(box.overlaps(bullets.get(i).box))
				treatCollision(bullets.get(i));
			for(int j=0; j<data.size; j++){
			if(data.get(j).box.overlaps(bullets.get(i).box)){
				data.get(j).treatCollision(bullets.get(i));
			}
			}
		}
		
		if(behaviour == 1){
			for(int i = 0; i<data.size; i++){
				data.get(i).restore = false;
				for (int j = 0; j < horde.size; j++){
					if(horde.get(j).data.get(i).hp >= 0.25)
						data.get(i).restore = true;
					
				}

			}
		}
		
		
		if(behaviour == 10){
			for(int i = 0; i<data.size; i++){
				data.get(i).restore = false;
				int j;
				if(label<horde.size/2)
					j=0;
				else
					j = horde.size/2;
				int aux = j+horde.size/2;
				
				for (; j < aux; j++){
					if(j!=label){
					if(horde.get(j).data.get(i).hp >= 0.25)
						data.get(i).restore = true;
					}
					
				}

			}
		}
		
		if(behaviour == 9){
			for(int i = 0; i<data.size; i++){
				data.get(i).restore = false;
				
				if(label<horde.size/2){
					if(horde.get(label + horde.size/2).data.get(i).hp >=0.25)
					data.get(i).restore = true;
					
				}
				if(label>=horde.size/2){
					if(horde.get(label - horde.size/2).data.get(i).hp >=0.25)
					data.get(i).restore = true;
					
				}
				
			}
			
		}
		
		

		if(behaviour == 5 || behaviour == 3 || behaviour == 4){
			for(int i=0; i<data.size; i++){
				int count = 0;
				for(int j=0; j<horde.size; j++){

					if(horde.get(j).data.get(i).hp <= 0.25)
						count++;
					
				}
				if(count>= 2)
					data.get(i).restore = false;
			}
		}
		
		if(behaviour == 6){
			for(int i=0; i<data.size; i++){
				int count = 0;
				for(int j=0; j<horde.size; j++){

					if(horde.get(j).data.get(i).hp <= 0.25)
						count++;
					
				}
				if(count>= 3)
					data.get(i).restore = false;
			}
		}
		
		if(behaviour == 4){
			if(Math.random() < 0.3f){
				if(speed == 0)
					speed = 1;
			}
			
			if(Math.random()<0.0025f){
				speed *= -1;
			}
			if(Math.random()<0.0000003f){
					speed = 0;
			}
		}
		
		hp = 0;
		for(int i=0; i<data.size; i++){
			hp += data.get(i).hp;
		}
		
		if(hp<0)
			hp = 0;
		
		if(hp==0)
			visible = false;
		
	}
	
	public void shot(){	
		bullets.add(new Bullet(Math.round(box.x + box.width/2 - 3.5f), Math.round(box.y - 6f), -5, dmg, true));
	}
	
	public void treatCollision(GameActor actor){
		if(actor.getClass() == Bullet.class){
			bullets.get(bullets.indexOf((Bullet) actor, true)).visible = false;		
		}
	}
	
	public void setHorde(Array<EnemyShip> horde){
		this.horde = horde;
		
		// Efetua o mirroring
		if(behaviour == 1){
			if(label!=0){
				for(int i=0;i<data.size;i++){

					data.get(i).data = horde.get(0).data.get(i).data;
				}
				
				
			}
			
			
		}
		
		if(behaviour == 5){
			
				int aux = 0;
				
				
				// Calcula o bit de paridade p.
			for(int i=0; i< data.size; i++){
				int oneQty=0;
				for(int j=0; j<horde.size; j++){

					if(j!=aux){
						if(horde.get(j).data.get(i).data == 1)
							oneQty++;
					}
					
				}
				if(oneQty%2 ==0)
					horde.get(aux).data.get(i).data = 0;
				else
					horde.get(aux).data.get(i).data = 1;
				aux++;
				if(aux>=horde.size)
					aux = 0;
				
			}
		}
		
		if(behaviour == 6){
			int aux = 0;
			int auxB = 1;
			
			// Calcula bit de paridade p. No cálculo, não conta o bit q (auxB)
		for(int i=0; i< data.size; i++){
			int oneQty=0;
			for(int j=0; j<horde.size; j++){

				if(j!=aux && j!=auxB){
					if(horde.get(j).data.get(i).data == 1)
						oneQty++;
				}
				
			}
			if(oneQty%2 ==0)
				horde.get(aux).data.get(i).data = 0;
			else
				horde.get(aux).data.get(i).data = 1;
			aux++;
			auxB++;
			if(aux>=horde.size)
				aux = 0;
			if(auxB>=horde.size)
				aux = 0;
		}
			
		}
		
		
		// "Monta" a última nave com bits de paridade
		if(behaviour == 3 || behaviour == 4){
			if(label == horde.size-1){
				for(int i=0; i<data.size; i++){
					int oneQty = 0;
					for(int j=0; j<horde.size-1; j++){

						if(horde.get(j).data.get(i).data == 1)
							oneQty++;
						
					}
					if(oneQty%2 == 0)
						data.get(i).data = 0;
					else
						data.get(i).data = 1;	
				}
			}
		}
		
		if (behaviour == 10){
			
			if(label!=0 && label<horde.size/2){
				for(int i=0;i<data.size;i++){

					data.get(i).data = horde.get(0).data.get(i).data;
				}
				
				
			}
			System.out.println("Lab: " + label);
			System.out.println("S/2: " + horde.size/2);
			if(label!=horde.size/2 && label>horde.size/2){
				
				for(int i=0;i<data.size;i++){

					data.get(i).data = horde.get(horde.size/2).data.get(i).data;
				}
			}
			
		}
		
		if (behaviour == 9){
			if(label>=horde.size/2){
				for(int i=0;i<data.size;i++){

					data.get(i).data = horde.get(label - horde.size/2).data.get(i).data;
				}
				
				
			}
		}
		
	}
}
