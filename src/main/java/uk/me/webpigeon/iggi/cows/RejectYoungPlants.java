package uk.me.webpigeon.iggi.cows;

import uk.me.webpigeon.util.Vector2D;
import uk.me.webpigeon.world.Entity;
import uk.me.webpigeon.world.GrassEntity;
import uk.me.webpigeon.world.Tag;
import uk.me.webpigeon.world.World;
import uk.me.webpigeon.iggi.btree.AbstractBehavourNode;
import uk.me.webpigeon.iggi.btree.BehavourNode;

/**
 * Locate an entity of a given type.
 * 
 * If an entity with the given type is found, put the entity in the
 * parent node's symbol table and return true, if it is not return 
 * false and don't alter the parent's symbol table.
 */
public class RejectYoungPlants extends BaseTargetSelection {
	
	public RejectYoungPlants(World world) {
		super(world, Tag.GRASS);
	}

	@Override
	protected double getScore(Entity us, Entity target) {
		GrassEntity grass = (GrassEntity)target;
		if (grass.getAge() < 200) {
			return -Double.MAX_VALUE;
		}
		
		Vector2D ourPos = us.getLocation();
		Vector2D theirPos = target.getLocation();
		return -ourPos.dist(theirPos) + grass.getAge();
	}

}
