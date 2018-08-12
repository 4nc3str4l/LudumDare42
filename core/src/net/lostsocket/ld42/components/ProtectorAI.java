package net.lostsocket.ld42.components;

import net.lostsocket.ld42.entities.NPC;
import net.lostsocket.ld42.entities.Player;

public class ProtectorAI extends NPCAI{

	public ProtectorAI(NPC npc) {
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
		if(distanceToPlayer < 20) {
			currentState = State.CHASING_ZOMBIE;
		}else {
			npc.transform.lookAt(Player.instance.transform.position.x, Player.instance.transform.position.y);
			npc.transform.moveForward(npc.speed * delta);
		}
	}
	
	private void protectingPlayer() {
		
		float distanceToPlayer = distanceToTarget(Player.instance.transform);
		if(distanceToPlayer > 200) {
			currentState = State.FINDING_PLAYER;
		}else {
			if(target == null || !target.isAlive) {
				target = findClosestZombieToPlayer();
			}else {
				npc.transform.lookAt(target.transform.position.x, target.transform.position.y);
				if(distanceToTarget(target.transform) < 100) {
					npc.currentWeapon.tryShootNPC();
				}
			}

		}
	}
	
	@Override
	public void dispose() {
	}

}
