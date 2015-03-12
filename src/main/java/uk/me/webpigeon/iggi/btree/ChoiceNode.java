package uk.me.webpigeon.iggi.btree;

import java.awt.Graphics2D;

import uk.me.webpigeon.world.Entity;

public class ChoiceNode extends AbstractBehavourNode {
	private int currentID;
	
	public ChoiceNode(BehavourNode ... children) {
		super(false, children);
	}
	
	public ChoiceNode(boolean createTable, BehavourNode ... children) {
		super(createTable, children);
	}
	
	@Override
	public Boolean evalBasic(Entity entity) {		
		// if the current node is true (or this is the first node) we need to look at the others
		for (int i=currentID; i<getChildCount(); i++) {
			BehavourNode current = getChild(i);
			Boolean result = current.evalBasic(entity);
			
			// if result is null, it's still executing
			if (result == null) {
				return null;
			} else 	if (result == true) {
				resetNode();
				return true;
			} else {
				//the node finished, we'll look at the next one next time
				currentID++;
			}
		}
		
		// all nodes failed to execute correctly
		resetNode();
		return false;
	}

	public void resetNode() {
		currentID = 0;
	}
	
	@Override
	public void debugDraw(Graphics2D g) {
		BehavourNode current = getChild(currentID);
		current.debugDraw(g);	
	}

	@Override
	public double utilityScore() {
		double maxUtil = -Double.MAX_VALUE;
		for (int i=0;i<getChildCount();i++) {
			BehavourNode node = getChild(i);
			double util = node.utilityScore();
			if (util > maxUtil) {
				maxUtil = util;
			}
		}
		
		return maxUtil;
	}


}
