package uk.me.webpigeon.iggi.cows;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import uk.me.webpigeon.iggi.btree.BehavourEvaluator;
import uk.me.webpigeon.util.Vector2D;
import uk.me.webpigeon.world.Entity;

public class BehavourCow extends Entity {
	private Rectangle cowSize;
	private BehavourEvaluator evaluator;
	
	public BehavourCow(BehavourEvaluator evaluator) {
		this.evaluator = evaluator;
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
		g2.fillRect((int)(cowSize.x + location.x), (int)(cowSize.y + location.y), cowSize.width, cowSize.height);
	}
	
}
