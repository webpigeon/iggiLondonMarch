package uk.me.webpigeon.iggi.btree;

import uk.me.webpigeon.world.Entity;

public class ChoiceNode extends AbstractBehavourNode {
	private int currentID;
	
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
				currentID = 0;
				return true;
			} else {
				//the node finished, we'll look at the next one next time
				currentID++;
			}
		}
		
		// all nodes failed to execute correctly
		currentID = 0;
		return false;
	}


}
