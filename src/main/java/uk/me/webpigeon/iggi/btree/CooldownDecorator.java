package uk.me.webpigeon.iggi.btree;

import java.awt.Graphics2D;

import uk.me.webpigeon.world.Entity;

public class CooldownDecorator extends AbstractBehavourNode {
	private int period;
	private long lastFired;
	
	public CooldownDecorator(int period, BehavourNode child) {
		super(child);
		this.period = period;
		this.lastFired = System.currentTimeMillis();
	}

	@Override
	public Boolean evalBasic(Entity us) {
		long currTime = System.currentTimeMillis();
		if (currTime - lastFired < period) {
			return false;
		}
		
		Boolean result = getChild(0).evalBasic(us);
		if (result != null) {
			lastFired = currTime;
		}
		
		return result;
	}
	
	@Override
	public void debugDraw(Graphics2D g) {
		getChild(0).debugDraw(g);	
	}

	@Override
	public double utilityScore(Entity entity) {
		return getChild(0).utilityScore(entity);
	}

}
