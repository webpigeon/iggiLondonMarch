package uk.me.webpigeon.iggi.cows;

import java.awt.Graphics2D;
import java.util.List;

import uk.me.webpigeon.iggi.btree.AbstractBehavourNode;
import uk.me.webpigeon.joseph.cow.Property;
import uk.me.webpigeon.steering.SeekBehaviour;
import uk.me.webpigeon.steering.SteeringBehaviour;
import uk.me.webpigeon.steering.TargetedBehavour;
import uk.me.webpigeon.util.Vector2D;
import uk.me.webpigeon.world.Entity;
import uk.me.webpigeon.world.Tag;

/**
 * Wrapper around a steering behaviour.
 */
public class RunAway extends AbstractBehavourNode {
	private static final Double EPSILON = 1.0;
	private TargetedBehavour steering;
	private double targetDistance;
	
	private boolean isBound;
	
	private Vector2D oldTarget;
	
	public RunAway(TargetedBehavour steering, double targetDistance) {
		this.steering = steering;
		this.targetDistance = targetDistance;
	}

	@Override
	public Boolean evalBasic(Entity us) {	
		if (!isBound) {
			steering.bind(us);
			isBound = true;
		}
		
		Vector2D target = (Vector2D)getTableItem("targetPosition");
		if (target == null) {
			// no target set, this makes me sad :(
			return false;
		}
		
		if (oldTarget == null || !oldTarget.equals(target)) {
			steering.setTarget(target);
			oldTarget = target;
		}
		
		// find out if we're close enouph
		double distance = target.dist(us.getLocation());
		if (distance >= targetDistance) {
			us.setVelocity(new Vector2D(0, 0));
			return true;
		}
		
		//we're not close enough, we need to move
		Vector2D force = steering.process();
		us.setVelocity(force);
		
		return null;
	}


	@Override
	public void debugDraw(Graphics2D g) {
		if (isBound) {
			steering.debugDraw(g);
		}
	}

	@Override
	public double utilityScore(Entity entity) {
		Vector2D target = (Vector2D)getTableItem("targetPosition");
		if (target == null) {
			// no target set, this makes me sad :(
			return 0;
		}
		
		return target.dist(entity.getLocation()) / targetDistance;
	}
}
