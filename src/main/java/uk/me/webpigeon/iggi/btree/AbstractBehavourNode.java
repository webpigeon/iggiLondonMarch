package uk.me.webpigeon.iggi.btree;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class AbstractBehavourNode implements BehavourNode {
	private BehavourNode parent;
	private List<BehavourNode> children;

	public boolean isLeaf() {
		return children.isEmpty();
	}

	public int getChildCount() {
		return children.size();
	}

	public Collection<BehavourNode> getChildren() {
		return Collections.unmodifiableList(children);
	}
	
	public void setParent(BehavourNode node) {
		this.parent = node;
	}

	protected void addChild(BehavourNode node) {
		children.add(node);
		node.setParent(this);
	}
	
	public BehavourNode getActiveNode() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String toString() {
		return "Node("+children+")";
	}

}
