package net.lostsocket.ld42.components;

import net.lostsocket.ld42.entities.NPC;
import net.lostsocket.ld42.entities.Player;
import net.lostsocket.ld42.maths.Maths;

public class CowardAI extends NPCAI{

	public CowardAI(NPC npc) {
		super(npc);
	}

	@Override
	protected void customUpdate(float delta) {
		if(currentState == State.CHASING_ZOMBIE) {
			protectingPlayer();
		}else {
			followingPlayer(delta);
		}
	}
	
	private void followingPlayer(float delta) {
		float distanceToPlayer = distanceToTarget(Player.instance.transform);
		if(distanceToPlayer < 10) {
			currentState = State.CHASING_ZOMBIE;
		}else {
			npc.transform.lookAt(Player.instance.transform.position.x, Player.instance.transform.position.y);
			npc.transform.moveForward(npc.speed * delta);
		}
	}
	
	private void protectingPlayer() {
		
		float distanceToPlayer = distanceToTarget(Player.instance.transform);
		if(distanceToPlayer > 30) {
			currentState = State.FINDING_PLAYER;
		}else {
			if(target == null || !target.isAlive) {
				target = findClosestZombieToPlayer();
			}else {
				if(distanceToTarget(target.transform) < 10) {
					currentState = State.FINDING_PLAYER;
				}else {
					if(npc.currentWeapon.isShootingAvaliable()) {
						npc.transform.setRotation(Maths.getRandomFloat(-90, 90));
						npc.currentWeapon.tryShootNPC();
					}
				}
			}

		}
	}
	
	@Override
	public void dispose() {
	}

}