package uk.me.webpigeon.iggi.cows;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.List;

import uk.me.webpigeon.iggi.btree.AbstractBehavourNode;
import uk.me.webpigeon.joseph.cow.Property;
import uk.me.webpigeon.util.Vector2D;
import uk.me.webpigeon.world.Entity;
import uk.me.webpigeon.world.Tag;
import uk.me.webpigeon.world.World;

public class ProduceBabyCow extends AbstractBehavourNode {
	private World world;
	
	public ProduceBabyCow(World world) {
		this.world = world;
	}

	@Override
	public Boolean evalBasic(Entity us) {
		
		//create a new cow in any direction, within 50 units of us
		Vector2D nearbyPos = Vector2D.getRandomPolar(Math.toRadians(360), 0, 50, true);
		nearbyPos.add(us.getLocation());
		Entity newCow = CowFactory.buildCow(nearbyPos.x, nearbyPos.y, world);
		world.addEntity(newCow);
		
		return true;
	}
	
	@Override
	public void debugDraw(Graphics2D g) {
	}

	@Override
	public double utilityScore(Entity entity) {
		double sightRange = entity.getValue(Property.SIGHT_RANGE, 1);
		
		List<Entity> cows = world.getNearEntities(entity, sightRange, Tag.COW);
		List<Entity> food = world.getNearEntities(entity, sightRange, Tag.GRASS);
		
		return food.size() / cows.size();
	}


}
