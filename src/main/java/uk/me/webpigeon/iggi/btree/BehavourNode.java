package uk.me.webpigeon.iggi.btree;

import uk.me.webpigeon.iggi.tree.TreeNode;

public interface BehavourNode extends TreeNode<Boolean> {
	
	/**
	 * Used by the nodes to ensure that everything works correctly.
	 * 
	 * @param node the parent node of this node.
	 */
	public void setParent(BehavourNode node);
	
	/**
	 * Basic transversal of the node.
	 * 
	 * This is quite inefficient as the whole tree must be transversed every
	 * tick, but it's easy to implement and it's thursday...
	 * 
	 * True means the node executed successfully
	 * False means the node failed to execute successfully
	 * null means the node (or a child node) is still executing
	 * 
	 */
	public Boolean evalBasic();

}
