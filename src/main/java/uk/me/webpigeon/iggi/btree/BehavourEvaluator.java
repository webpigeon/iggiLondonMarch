package uk.me.webpigeon.iggi.btree;

public class BehavourEvaluator {
	private BehavourNode root;
	
	public BehavourEvaluator(BehavourNode root) {
		this.root = root;
	}
	
	/**
	 * Find and execute the behaviour best suited for us at the moment.
	 * 
	 * When doing basic evaluation, this doesn't really need to do anything interesting.
	 * If this was more advanced transversal, storing the active node then this would
	 * be a more interesting class.
	 */
	public void tick() {
		Boolean tickResult = root.evalBasic();
		System.out.println("debug: "+tickResult);
	}
	

}
