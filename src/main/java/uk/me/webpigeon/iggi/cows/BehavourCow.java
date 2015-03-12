package uk.me.webpigeon.iggi.cows;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.EnumSet;

import uk.me.webpigeon.iggi.btree.BehavourEvaluator;
import uk.me.webpigeon.joseph.cow.Property;
import uk.me.webpigeon.util.Vector2D;
import uk.me.webpigeon.world.Entity;
import uk.me.webpigeon.world.Tag;

public class BehavourCow extends Entity {
	private final Rectangle cowSize;
	private final BehavourEvaluator evaluator;
	
	public BehavourCow(double x, double y, BehavourEvaluator evaluator) {
		super(x, y, EnumSet.of(Tag.AGENT, Tag.COW));
		this.evaluator = evaluator;
		evaluator.bind(this);
		this.cowSize = new Rectangle(-10, -10, 20, 20);
	}
	
	@Override
	public void update() {
		processLifeFunctions();
		evaluator.tick();
		super.update();
	}
	
	protected void processLifeFunctions() {
		// if we are are a hungry cow, we take damage
		double saturation = getValue(Property.SATURATION, 1000);
		if (saturation <= 0) {
			health--;
		}
		
		saturation = saturation * getValue(Property.METABOLISM, 0.01);
		setValue(Property.SATURATION, saturation);
		
		double currentAge = getValue(Property.AGE, 0);
		double maxAge = getLimit(Property.AGE, 10000);
		if (currentAge >= maxAge) {
			health = 0;
		} else {
			setValue(Property.AGE, currentAge+1);
		}
	}

	@Override
	public void drawLocal(Graphics2D g2) {	
		
		float healthDec = health / 100f;
		
		Vector2D location = getLocation();
		g2.setColor(new Color(healthDec, healthDec, healthDec));
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
