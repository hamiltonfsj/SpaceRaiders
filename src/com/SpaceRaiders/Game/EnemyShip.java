package com.SpaceRaiders.Game;

import com.badlogic.gdx.utils.Array;

public class EnemyShip extends Ship {

	public int behaviour, score;
	private float speed=1, dmg=0.1f;
	public float restoreRate = 0.003f;
	private int limitR, limitL, label;
	public Array<ShipData> data;
	public Array<EnemyShip> horde;
	
	public EnemyShip(Array<Bullet> bullets, int behaviour, int label) {
		super(bullets);
		data = new Array<ShipData>();
		visible = true;
		this.behaviour = behaviour;
		this.label = label;
		hp = 1.0f;
		box.set(250 + label*140, 320, 48, 42);
		
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
			restoreRate = 0.01f;
			break;
		case 6:
			score = 50;
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
		box.x += speed;
		
		if(box.x > limitR || box.x <limitL)
			speed *= -1;
		
		if(box.x%30 == 0)
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
		
		if(behaviour == 5){
			for(int i = 0; i<data.size; i++){
				int count = 0;
				data.get(i).restore = true;
				for (int j = 0; j < data.size; j++){

					if(data.get(j).hp <= 0.25)
						count++;

					if(count>=2){
						data.get(i).restore = false;
					}
				}
			}
			
		}
		
		if(behaviour == 3 || behaviour == 4){
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
		
		if(behaviour == 1){
			if(label!=0){
				for(int i=0;i<data.size;i++){

					data.get(i).data = horde.get(0).data.get(i).data;
					
					System.out.println("Aqui!");
					System.out.println(data.get(i).data);
					System.out.println(horde.get(0).data.get(i).data);
				}
				
				
			}
			
			
		}
		
		if(behaviour == 5){
			int oneQty=0;
			for(int i=0; i< data.size-1; i++){
				if(data.get(i).data == 1)
					oneQty++;
			}
			if(oneQty%2 == 0)
			data.get(data.size-1).data = 0;
			else
			data.get(data.size-1).data = 1;	
		}
		
		if(behaviour == 6){
			
			
		}
		
		if(behaviour == 2){
			
		}
		
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
		
	}
}

