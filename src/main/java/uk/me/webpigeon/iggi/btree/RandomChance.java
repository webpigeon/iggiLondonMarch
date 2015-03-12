package uk.me.webpigeon.iggi.btree;

import java.awt.Graphics2D;
import java.util.Random;

import uk.me.webpigeon.world.Entity;

public class RandomChance extends AbstractBehavourNode {
	private Random r;
	private double chance;
	
	public RandomChance(double chance) {
		this.chance = chance;
		this.r = new Random();
	}

	@Override
	public Boolean evalBasic(Entity us) {
		double randomNumber = r.nextDouble();
		return randomNumber <= chance;
	}

	@Override
	public void debugDraw(Graphics2D g) {
	}
}
