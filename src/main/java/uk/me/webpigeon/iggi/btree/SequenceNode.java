package uk.me.webpigeon.iggi.btree;

import uk.me.webpigeon.world.Entity;

public class SequenceNode extends AbstractBehavourNode {
	private int currentID;
	
	public SequenceNode(BehavourNode ... children) {
		super(children);
	}
	
	@Override
	public Boolean evalBasic(Entity entity) {		
		// if the current node is true (or this is the first node) we need to look at the others
		for (int i=currentID; i<getChildCount(); i++) {
			BehavourNode current = getChild(i);
			Boolean result = current.evalBasic(entity);
			System.out.println(currentID + ":"+result);
			
			// if result is null, it's still executing
			if (result == null) {
				return null;
			} else 	if (result == false) {
				return false;
			} else {
				//the node finished, we'll look at the next one next time
				currentID++;
			}
		}
		
		// all nodes executed correctly, it's time for some science!
		currentID = 0;
		return true;
	}


}
