package uk.me.webpigeon.iggi.btree;

import java.awt.Graphics2D;

import uk.me.webpigeon.world.Entity;

public class UtilitySelect extends AbstractBehavourNode {
	private BehavourNode best;
	
	public UtilitySelect(BehavourNode ... children) {
		this(false, children);
	}
	
	public UtilitySelect(boolean hasTable, BehavourNode ... children) {
		super(hasTable, children);
		this.best = null;
	}

	@Override
	public void debugDraw(Graphics2D g) {
		if (best != null) {
			best.debugDraw(g);
		}
	}

	@Override
	public Boolean evalBasic(Entity us) {
		if (best == null) {
			best = selectBest();
		}
		
		Boolean result = best.evalBasic(us);
		if (result != null) {
			best = null;
			return result;
		}
		
		return null;
	}
	
	private BehavourNode selectBest() {
		BehavourNode bestNode = null;
		double bestScore = -Double.MAX_VALUE;
		
		for (int i=0; i<getChildCount(); i++) {
			BehavourNode node = getChild(i);
			double score = getChild(i).utilityScore();
			if (bestScore < score) {
				bestNode = node;
				bestScore = score;
			}
		}
		return bestNode;
	}

	@Override
	public double utilityScore() {
		if (best == null) {
			best = selectBest();
		}
		return best.utilityScore();
	}

}
