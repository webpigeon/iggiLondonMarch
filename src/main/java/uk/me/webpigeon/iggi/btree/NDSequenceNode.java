package uk.me.webpigeon.iggi.btree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NDSequenceNode extends SequenceNode {
	private List<Integer> ordering;
	
	public NDSequenceNode(BehavourNode ... children) {
		this(false, children);
	}
	
	public NDSequenceNode(boolean createTable, BehavourNode ... children) {
		super(createTable, children);
		this.ordering = new ArrayList<Integer>(getChildCount());
		for (int i=0; i<getChildCount(); i++) {
			ordering.add(i);
		}
	}
	
	@Override
	public BehavourNode getChild(int id) {
		Integer realID = ordering.get(id);
		return super.getChild(realID);
	}
	
	@Override
	public void resetNode() {
		Collections.shuffle(ordering);
		super.resetNode();
	}

}
