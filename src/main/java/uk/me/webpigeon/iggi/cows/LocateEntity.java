package uk.me.webpigeon.iggi.cows;

import uk.me.webpigeon.world.Entity;
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
public class LocateEntity extends AbstractBehavourNode {
	private World world;
	private Class<? extends Entity> targetType;
	
	public LocateEntity(World world, Class<? extends Entity> type) {
		this.targetType = type;
		this.world = world;
	}

	@Override
	public Boolean evalBasic(Entity entity) {
		Entity target = world.getNearestEntityOfType(entity, targetType);
		if (target == null) {
			return false;
		}
		
		BehavourNode parent = getParent();
		if (parent == null) {
			throw new RuntimeException("I have no parent node!");
		}
		
		parent.setTableItem("foodTarget", target);
		parent.setTableItem("steerTarget", target.getLocation());
		return true;
	}

}
