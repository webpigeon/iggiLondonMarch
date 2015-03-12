package uk.me.webpigeon.iggi.cows;

import uk.me.webpigeon.iggi.btree.AbstractBehavourNode;
import uk.me.webpigeon.steering.SeekBehaviour;
import uk.me.webpigeon.steering.SteeringBehaviour;
import uk.me.webpigeon.util.Vector2D;
import uk.me.webpigeon.world.Entity;

/**
 * Wrapper around a steering behaviour.
 */
public class SteerTowards extends AbstractBehavourNode {
	private static final Double MIN_DIST = 1.0;
	private SeekBehaviour steering;
	
	private Vector2D oldTarget;
	
	public SteerTowards(SeekBehaviour steering) {
		this.steering = steering;
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
		if (distance <= MIN_DIST) {
			return true;
		}
		System.out.println(this+": "+distance);
		
		//we're not close enouph, we need to move
		Vector2D force = steering.process();
		us.setVelocity(force);
	
		
		return null;
	}


}
