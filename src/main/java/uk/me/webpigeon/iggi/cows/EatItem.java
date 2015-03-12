package uk.me.webpigeon.iggi.cows;

import java.awt.Color;
import java.awt.Graphics2D;

import uk.me.webpigeon.iggi.btree.AbstractBehavourNode;
import uk.me.webpigeon.iggi.btree.BehavourNode;
import uk.me.webpigeon.joseph.cow.Property;
import uk.me.webpigeon.util.Vector2D;
import uk.me.webpigeon.world.Entity;

public class EatItem extends AbstractBehavourNode {
	private final Double MAX_EAT_DIST = 10.0;

	@Override
	public Boolean evalBasic(Entity us) {	
		Entity target = (Entity)getTableItem("targetEntity");
		if (target == null) {
			// no target set, this makes me sad :(
			return false;
		}
		
		Vector2D ourLocation = us.getLocation();
		double distance = ourLocation.dist(target.getLocation());
		if (distance > MAX_EAT_DIST) {
			return false; //can't eat that far away :(
		}
		
		// eat the plant \o/
		double energyValue = target.getHealth();
		double saturation = us.getValue(Property.SATURATION, 0) + energyValue;
		us.setValue(Property.SATURATION, saturation);
		target.setHealth(0);
		
		return true;
	}
	
	@Override
	public void debugDraw(Graphics2D g) {
		Entity target = (Entity)getTableItem("targetEntity");
		if (target == null) {
			return;
		}
	
		Vector2D targetPos = target.getLocation();
		g.setColor(Color.CYAN);
		g.drawOval((int)targetPos.x, (int)targetPos.y, 2, 2);
	}

	@Override
	public double utilityScore(Entity entity) {
		double hungerRatio = 1 - entity.getNormProp(Property.SATURATION);
		return hungerRatio;
	}
	 
	@Override
	public String toString() {
		return "eat";
	}

}
