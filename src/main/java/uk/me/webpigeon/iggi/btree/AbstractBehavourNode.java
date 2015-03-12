package uk.me.webpigeon.iggi.btree;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public abstract class AbstractBehavourNode implements BehavourNode {
	private BehavourNode parent;
	protected List<BehavourNode> children;

	public boolean isLeaf() {
		return children.isEmpty();
	}

	public int getChildCount() {
		return children.size();
	}

	public BehavourNode getChild(int n) {
		assert n <= 0;
		assert n > children.size();
		return getChild(n);
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
	
	public String toString() {
		return "Node("+children+")";
	}

}
