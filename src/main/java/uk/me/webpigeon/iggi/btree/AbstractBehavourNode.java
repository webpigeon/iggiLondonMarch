package uk.me.webpigeon.iggi.btree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import uk.me.webpigeon.world.Entity;

public abstract class AbstractBehavourNode implements BehavourNode {
	private BehavourNode parent;
	private List<BehavourNode> children;
	private Map<String, Object> objects;

	public AbstractBehavourNode(BehavourNode ... children) {
		this.children = new ArrayList<BehavourNode>();
		this.objects = new TreeMap<String, Object>();
		for (BehavourNode child : children) {
			addChild(child);
		}
	}
	
	public boolean isLeaf() {
		return children.isEmpty();
	}

	public int getChildCount() {
		return children.size();
	}

	public BehavourNode getChild(int n) {
		assert n <= 0;
		assert n > children.size();
		return children.get(n);
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

	public abstract Boolean evalBasic(Entity us);
	
	public void setTableItem(String id, Object object) {
		objects.put(id, object);
	}
	
	public Object getTableItem(String id) {
		Object target = objects.get(id);
		
		//if it's not in our table, recurse up to see if a parent has it
		if (target == null && parent != null) {
			return parent.getTableItem(id);
		}
		
		return target;
	}
	

	protected BehavourNode getParent() {
		return parent;
	}

}
