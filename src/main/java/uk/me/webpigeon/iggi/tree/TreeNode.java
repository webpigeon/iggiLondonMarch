package uk.me.webpigeon.iggi.tree;

import java.util.Collection;

public interface TreeNode <T> {
	
	public boolean isLeaf();
	
	public int getChildCount();
	public Collection<? extends TreeNode<T>> getChildren();

}
