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
	 * Evaluate what node should currently be evaluated.
	 * 
	 * @return the treenode which is currently active
	 */
	public BehavourNode getActiveNode();

}
