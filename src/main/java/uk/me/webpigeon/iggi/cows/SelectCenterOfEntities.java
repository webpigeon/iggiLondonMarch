package uk.me.webpigeon.iggi.cows;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import uk.me.webpigeon.iggi.btree.AbstractBehavourNode;
import uk.me.webpigeon.iggi.btree.BehavourNode;
import uk.me.webpigeon.joseph.cow.Property;
import uk.me.webpigeon.util.Vector2D;
import uk.me.webpigeon.world.Entity;
import uk.me.webpigeon.world.World;

public class SelectCenterOfEntities extends AbstractBehavourNode {
	private World world;
	private Class<? extends Entity> targetType;
	
	public SelectCenterOfEntities(World world, Class<? extends Entity> type) {
		this.targetType = type;
		this.world = world;
	}

	@Override
	public Boolean evalBasic(Entity entity) {
		//get all the entities of the required type within our sight range
		double sightRange = entity.getValue(Property.SIGHT_RANGE, 50);
		List<Entity> entityList = world.getNearEntities(entity, sightRange);
		entityList = WorldUtils.filterByType(entityList, targetType);
		if (entityList.isEmpty()) {
			return false;
		}
		
		Vector2D centerOfMass = new Vector2D(0, 0, true);
		
		for (Entity e : entityList) {
			Vector2D location = e.getLocation();
			centerOfMass.add(location);
		}
		
		centerOfMass.divide(entityList.size());
		
		BehavourNode parent = getParent();
		if (parent == null) {
			throw new RuntimeException("I have no parent node!");
		}
		
		parent.setTableItem("targetEntity", null);
		parent.setTableItem("targetPosition", centerOfMass);
		return true;
	}
	
}