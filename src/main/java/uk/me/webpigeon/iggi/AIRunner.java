package uk.me.webpigeon.iggi;

import java.awt.Dimension;

import javax.swing.JFrame;

import uk.me.webpigeon.iggi.btree.BehavourEvaluator;
import uk.me.webpigeon.iggi.btree.BehavourNode;
import uk.me.webpigeon.iggi.cows.BehavourCow;
import uk.me.webpigeon.iggi.cows.CowFactory;
import uk.me.webpigeon.util.Vector2D;
import uk.me.webpigeon.world.DoubleWorld;
import uk.me.webpigeon.world.Entity;
import uk.me.webpigeon.world.GrassEntity;
import uk.me.webpigeon.world.World;

/**
 * AI Runner for my (cows) code only.
 */
public class AIRunner {

	public static void main(String[] args) {
		DoubleWorld world = new DoubleWorld(800, 600);
		world.addEntity(buildCows(world));
		
		for (int i=0; i<10; i++) {
			world.addEntity(buildGrass());
		}
		
		// build the game loop
		Thread gameThread = new Thread(world);
		gameThread.setName("gameThread");
		gameThread.start();
		
		//build the visualisation
		JFrame frame = buildFrame();
		frame.add(world);
		frame.pack();
		frame.setVisible(true);
	}

	protected static Entity buildCows(World world) {
		BehavourNode root = CowFactory.buildEat(world);
		BehavourEvaluator eval = new BehavourEvaluator(root);
		return new BehavourCow(50, 50, eval);
	}
	
	protected static Entity buildGrass() {
		return new GrassEntity(Vector2D.getRandomCartesian(800, 600, true));
	}
	
	private static JFrame buildFrame() {
		JFrame frame = new JFrame("Goldsmiths AI - cows");
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	}

}
