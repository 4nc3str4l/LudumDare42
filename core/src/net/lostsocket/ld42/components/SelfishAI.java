package net.lostsocket.ld42.components;

import com.badlogic.gdx.math.Vector2;

import net.lostsocket.ld42.GameManager;
import net.lostsocket.ld42.entities.NPC;
import net.lostsocket.ld42.entities.Zombie;

public class SelfishAI extends NPCAI{

	public SelfishAI(NPC npc) {
		super(npc);
	}

	@Override
	protected void customUpdate(float delta) {
		
		if(target == null || !target.isAlive) {
			target = findClosestZombieToMe();
		}
		else {
			npc.transform.lookAt(target.transform.position.x, target.transform.position.y);
			
			if(distanceToTarget(target.transform) < 100) {
				npc.currentWeapon.tryShootNPC();
			}else {
				npc.transform.moveForward(npc.speed * delta);
			}
		}
	}
	
	public Zombie findClosestZombieToMe() {
		float minDistance = 100000;
		Zombie temp = null;
		Zombie choosen = null;
		for(int i = 0; i < GameManager.instance.aliveZombies.size(); ++i) {
			temp = GameManager.instance.aliveZombies.get(i);
			float currentDistance = Vector2.dst2(
					npc.transform.position.x,
					npc.transform.position.y,
					temp.transform.position.x,
					temp.transform.position.y);
			if(currentDistance < minDistance) {
				choosen = temp;
				minDistance = currentDistance;
			}
		}
		return choosen;
	}
	
	@Override
	public void dispose() {
	}

}
