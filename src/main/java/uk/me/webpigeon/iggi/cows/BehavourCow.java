package uk.me.webpigeon.iggi.cows;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import uk.me.webpigeon.iggi.btree.BehavourEvaluator;
import uk.me.webpigeon.joseph.cow.Property;
import uk.me.webpigeon.util.Vector2D;
import uk.me.webpigeon.world.Entity;

public class BehavourCow extends Entity {
	private final Rectangle cowSize;
	private final BehavourEvaluator evaluator;
	
	public BehavourCow(double x, double y, BehavourEvaluator evaluator) {
		this.location = new Vector2D(x, y, true);
		this.health = 100;
		this.evaluator = evaluator;
		evaluator.bind(this);
		this.cowSize = new Rectangle(-10, -10, 20, 20);
	}
	
	@Override
	public void update() {
		evaluator.tick();
		super.update();
	}

	@Override
	public void drawLocal(Graphics2D g2) {		
		Vector2D location = getLocation();
		g2.setColor(Color.WHITE);
		g2.fillRect(cowSize.x, cowSize.y, cowSize.width, cowSize.height);
	}
	
	public void debugDraw(Graphics2D g2) {
		Vector2D myLocation = getLocation();
		int sightRadius = (int)getValue(Property.SIGHT_RANGE, 50);
		
		g2.setColor(Color.WHITE);
		g2.drawOval((int)myLocation.x - sightRadius, (int)myLocation.y - sightRadius, sightRadius * 2, sightRadius * 2);
	}
	
	public int getZIndex() {
		return 10;
	}
	
}
