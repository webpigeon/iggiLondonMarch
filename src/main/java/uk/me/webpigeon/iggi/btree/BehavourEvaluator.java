package uk.me.webpigeon.iggi.btree;

import java.awt.Graphics2D;

import uk.me.webpigeon.world.Entity;

public class BehavourEvaluator {
	private BehavourNode defaultBehavour;
	private BehavourNode root;
	private Entity entity;
	private boolean isActive;
	
	public BehavourEvaluator(BehavourNode root, BehavourNode defaultBh) {
		this.root = root;
		this.defaultBehavour = defaultBh;
	}
	
	public void bind(Entity entity){
		this.entity = entity;
	}
	
	/**
	 * Find and execute the behaviour best suited for us at the moment.
	 * 
	 * When doing basic evaluation, this doesn't really need to do anything interesting.
	 * If this was more advanced transversal, storing the active node then this would
	 * be a more interesting class.
	 */
	public void tick() {
		Boolean tickResult = root.evalBasic(entity);
		
		//is not null check to guard against unboxing nulls
		if (tickResult != null && tickResult == false) {			
			//if the behavour tree failed, execute some default action;
			defaultBehavour.evalBasic(entity);
			isActive=false;
		} else {
			isActive=true;
		}
	}

	public void debugDraw(Graphics2D g2) {
		if (isActive) {
			root.debugDraw(g2);
		} else {
			defaultBehavour.debugDraw(g2);
		}
	}
	

}
