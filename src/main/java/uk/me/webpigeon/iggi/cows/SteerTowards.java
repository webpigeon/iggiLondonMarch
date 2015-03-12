package uk.me.webpigeon.iggi.cows;

import uk.me.webpigeon.iggi.btree.AbstractBehavourNode;
import uk.me.webpigeon.steering.SeekBehaviour;
import uk.me.webpigeon.steering.SteeringBehaviour;
import uk.me.webpigeon.steering.TargetedBehavour;
import uk.me.webpigeon.util.Vector2D;
import uk.me.webpigeon.world.Entity;

/**
 * Wrapper around a steering behaviour.
 */
public class SteerTowards extends AbstractBehavourNode {
	private static final Double EPSILON = 1.0;
	private TargetedBehavour steering;
	private double targetDistance;
	
	private Vector2D oldTarget;
	
	public SteerTowards(TargetedBehavour steering, double targetDistance) {
		this.steering = steering;
		this.targetDistance = targetDistance;
	}

	@Override
	public Boolean evalBasic(Entity us) {		
		Vector2D target = (Vector2D)getTableItem("targetPosition");
		if (target == null) {
			// no target set, this makes me sad :(
			return false;
		}
		
		if (oldTarget == null || !oldTarget.equals(target)) {
			steering.bind(us);
			steering.setTarget(target);
			oldTarget = target;
		}
		
		// find out if we're close enouph
		double distance = target.dist(us.getLocation());
		if (Math.abs(targetDistance - distance) <= EPSILON) {
			us.setVelocity(new Vector2D(0, 0));
			return true;
		}
		
		//we're not close enough, we need to move
		Vector2D force = steering.process();
		us.setVelocity(force);
		
		return null;
	}


}
