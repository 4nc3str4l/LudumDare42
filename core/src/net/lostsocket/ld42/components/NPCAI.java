package net.lostsocket.ld42.components;

import com.badlogic.gdx.math.Vector2;

import net.lostsocket.ld42.GameManager;
import net.lostsocket.ld42.components.AbstractComponent;
import net.lostsocket.ld42.components.IUpdatable;
import net.lostsocket.ld42.entities.NPC;
import net.lostsocket.ld42.entities.Player;
import net.lostsocket.ld42.entities.Zombie;
import net.lostsocket.ld42.maths.Transform;

public abstract class NPCAI extends AbstractComponent implements IUpdatable {
	
	protected NPC npc;

	protected enum State { FINDING_PLAYER, CHASING_ZOMBIE }
	protected State currentState = State.FINDING_PLAYER;
	
	public Zombie target = null;
	
	public NPCAI(NPC npc) {
		this.npc = npc;
	}
	
	protected abstract void customUpdate(float delta);
	
	@Override
	public void update(float delta) {
		
		if(GameManager.instance.isWarmingUp())
			return;
		
		if (!npc.isAlive || GameManager.instance.currentState != GameManager.GameState.PLAYING)
			return;
		
		customUpdate(delta);

	}
	
	protected float distanceToTarget(Transform transform) {
		return Vector2.dst(
				npc.transform.position.x,
				npc.transform.position.y, 
				transform.position.x,
				transform.position.y);
	}
	
	public Zombie findClosestZombieToPlayer() {
		float minDistance = 100000;
		Zombie temp = null;
		Zombie choosen = null;
		for(int i = 0; i < GameManager.instance.aliveZombies.size(); ++i) {
			temp = GameManager.instance.aliveZombies.get(i);
			float currentDistance = Vector2.dst2(
					Player.instance.transform.position.x,
					Player.instance.transform.position.y,
					temp.transform.position.x,
					temp.transform.position.y);
			if(currentDistance < minDistance) {
				choosen = temp;
				minDistance = currentDistance;
			}
		}
		return choosen;
	}
	
	
}
