package uk.me.webpigeon.iggi.cows;

import uk.me.webpigeon.iggi.btree.BehavourNode;
import uk.me.webpigeon.iggi.btree.SequenceNode;
import uk.me.webpigeon.steering.SeekBehaviour;
import uk.me.webpigeon.world.GrassEntity;
import uk.me.webpigeon.world.World;

public class CowFactory {
	
	public static BehavourNode buildEat(World world) {
		BehavourNode findFood = new LocateEntity(world, GrassEntity.class);
		BehavourNode moveTowards = new SteerTowards(new SeekBehaviour(null));
		//BehavourNode eatFood = null;
		
		return new SequenceNode(findFood, moveTowards/*, eatFood*/);
	}
	
	public static BehavourNode buildWander() {
		return null;
	}

}
