package uk.me.webpigeon.iggi.cows;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import uk.me.webpigeon.iggi.btree.AbstractBehavourNode;
import uk.me.webpigeon.iggi.btree.BehavourNode;
import uk.me.webpigeon.joseph.cow.Property;
import uk.me.webpigeon.util.Vector2D;
import uk.me.webpigeon.world.Entity;
import uk.me.webpigeon.world.Tag;
import uk.me.webpigeon.world.World;

public abstract class BaseTargetSelection extends AbstractBehavourNode {
	private World world;
	private Tag targetType;
	
	public BaseTargetSelection(World world, Tag type) {
		this.targetType = type;
		this.world = world;
	}

	@Override
	public Boolean evalBasic(Entity entity) {
		//get all the entities of the required type within our sight range
		double sightRange = entity.getValue(Property.SIGHT_RANGE, 50);
		List<Entity> entityList = world.getNearEntities(entity, sightRange);
		entityList = WorldUtils.filterByType(entityList, targetType);
		
		Entity target = getBest(entity, entityList);
		if (target == null) {
			return false;
		}
		
		BehavourNode parent = getParent();
		if (parent == null) {
			throw new RuntimeException("I have no parent node!");
		}
		
		parent.setTableItem("targetEntity", target);
		parent.setTableItem("targetPosition", target.getLocation());
		return true;
	}
	
	private Entity getBest(Entity us, List<Entity> entities) {
		
		double bestScore = -Double.MAX_VALUE;
		Entity bestEntity = null;
		
		for (Entity entity : entities) {
			double score = getScore(us, entity);
			if (bestScore < score) {
				bestScore = score;
				bestEntity = entity;
			}
		}
		
		return bestEntity;
	}
	
	protected abstract double getScore(Entity us, Entity target);
}
