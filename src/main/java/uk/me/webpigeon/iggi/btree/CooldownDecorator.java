package uk.me.webpigeon.iggi.btree;

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

}
