package uk.me.webpigeon.iggi;

import java.awt.Dimension;

import javax.swing.JFrame;

import uk.me.webpigeon.iggi.btree.AbstractBehavourNode;
import uk.me.webpigeon.iggi.btree.BehavourEvaluator;
import uk.me.webpigeon.iggi.cows.BehavourCow;
import uk.me.webpigeon.world.DoubleWorld;
import uk.me.webpigeon.world.Entity;

/**
 * AI Runner for my (cows) code only.
 */
public class AIRunner {

	public static void main(String[] args) {
		DoubleWorld world = new DoubleWorld(800, 600);
		world.addEntity(buildCows());
		
		JFrame frame = buildFrame();
		frame.add(world);
		frame.pack();
		frame.setVisible(true);
	}

	protected static Entity buildCows() {
		AbstractBehavourNode root = new AbstractBehavourNode();
		BehavourEvaluator eval = new BehavourEvaluator(root);
		return new BehavourCow(eval);
	}
	
	private static JFrame buildFrame() {
		JFrame frame = new JFrame("Goldsmiths AI - cows");
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	}

}
