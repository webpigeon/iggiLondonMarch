package uk.me.webpigeon.iggi.cows;

import java.awt.Graphics2D;

import uk.me.webpigeon.iggi.btree.AbstractBehavourNode;
import uk.me.webpigeon.steering.SteeringBehaviour;
import uk.me.webpigeon.util.Vector2D;
import uk.me.webpigeon.world.Entity;

public class WanderAbout extends AbstractBehavourNode {
	private SteeringBehaviour behavour;
	private boolean isBound;
	
	public WanderAbout(SteeringBehaviour behavour) {
		this.behavour = behavour;
	}

	@Override
	public Boolean evalBasic(Entity us) {
		if (!isBound) {
			behavour.bind(us);
			isBound=true;
		}
		
		Vector2D force = behavour.process();
		us.setVelocity(force);
		
		return true;
	}

	@Override
	public void debugDraw(Graphics2D g) {
		if (isBound) {
			behavour.debugDraw(g);
		}
	}

}
