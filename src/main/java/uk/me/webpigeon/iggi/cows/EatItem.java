package uk.me.webpigeon.iggi.cows;

import uk.me.webpigeon.iggi.btree.AbstractBehavourNode;
import uk.me.webpigeon.util.Vector2D;
import uk.me.webpigeon.world.Entity;

public class EatItem extends AbstractBehavourNode {
	private final Double MAX_EAT_DIST = 10.0;

	@Override
	public Boolean evalBasic(Entity us) {
		Entity target = (Entity)getTableItem("eatTarget");
		if (target == null) {
			// no target set, this makes me sad :(
			return false;
		}
		
		Vector2D ourLocation = us.getLocation();
		double distance = ourLocation.dist(target.getLocation());
		if (distance > MAX_EAT_DIST) {
			return false; //can't eat that far away :(
		}
		
		//distance != null
		
		return true;
	}

}
