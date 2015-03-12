package uk.me.webpigeon.iggi.btree;

import java.awt.Graphics2D;

import uk.me.webpigeon.world.Entity;

public class SequenceNode extends AbstractBehavourNode {
	private int currentID;
	
	public SequenceNode(BehavourNode ... children) {
		super(children);
	}
	
	public SequenceNode(boolean hasTable, BehavourNode ... children) {
		super(hasTable, children);
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
			} else 	if (result == false) {
				resetNode();
				return false;
			} else {
				//the node finished, we'll look at the next one next time
				currentID++;
			}
		}
		
		// all nodes executed correctly, it's time for some science!
		resetNode();
		return true;
	}

	public void resetNode() {
		this.currentID = 0;
	}
	
	@Override
	public void debugDraw(Graphics2D g) {
		BehavourNode current = getChild(currentID);
		current.debugDraw(g);	
	}

	@Override
	public double utilityScore() {
		double total = -Double.MAX_VALUE;
		for (int i=0;i<getChildCount();i++) {
			BehavourNode node = getChild(i);
			total += node.utilityScore();
		}
		
		return total / getChildCount();
	}
	
}
