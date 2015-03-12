package uk.me.webpigeon.iggi.btree;

public class SequenceNode extends AbstractBehavourNode {
	private int currentID;
	
	@Override
	public Boolean evalBasic() {		
		// if the current node is true (or this is the first node) we need to look at the others
		for (int i=currentID; i<children.size(); i++) {
			BehavourNode current = children.get(currentID);
			Boolean result = current.evalBasic();
			
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
