package uk.me.webpigeon.iggi.cows;

import uk.me.webpigeon.iggi.btree.AbstractBehavourNode;
import uk.me.webpigeon.steering.SteeringBehaviour;
import uk.me.webpigeon.util.Vector2D;
import uk.me.webpigeon.world.Entity;

public class WanderAbout extends AbstractBehavourNode {
	private SteeringBehaviour behavour;
	
	public WanderAbout(SteeringBehaviour behavour) {
		this.behavour = behavour;
	}

	@Override
	public Boolean evalBasic(Entity us) {
		behavour.bind(us);
		
		Vector2D force = behavour.process();
		us.setVelocity(force);
		
		return true;
	}

}
