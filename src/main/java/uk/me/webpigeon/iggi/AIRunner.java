package uk.me.webpigeon.iggi;

import java.awt.Dimension;

import javax.swing.JFrame;

import uk.me.webpigeon.iggi.btree.BehavourEvaluator;
import uk.me.webpigeon.iggi.btree.BehavourNode;
import uk.me.webpigeon.iggi.cows.BehavourCow;
import uk.me.webpigeon.iggi.cows.CowFactory;
import uk.me.webpigeon.joseph.CowPopulationManager;
import uk.me.webpigeon.util.Vector2D;
import uk.me.webpigeon.world.DoubleWorld;
import uk.me.webpigeon.world.Entity;
import uk.me.webpigeon.world.EntityFactory;
import uk.me.webpigeon.world.GrassEntity;
import uk.me.webpigeon.world.World;

/**
 * AI Runner for my (cows) code only.
 */
public class AIRunner {

	public static void main(String[] args) {
		DoubleWorld.DEBUG_DRAW = true;
		DoubleWorld world = new DoubleWorld(800, 600);
		
		world.addComponent(buildOld(5, world));
		buildCows(5, world);
		buildGrass(100, world);
			
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

	protected static CowPopulationManager buildOld(int n, World world) {
		CowPopulationManager pop = new CowPopulationManager(n);
	    pop.addMoreCows(n, world);
	    return pop;
	}
	
	protected static void buildCows(int n, World world) {
		for (int i=0; i<n; i++) {
			Entity cow = CowFactory.buildCow(world);
			
			//this will set the values to match those of the util cows
			EntityFactory.setGenome(cow, EntityFactory.buildDefaultGenome());
			world.addEntity(cow);
		}
	}
	
	protected static void buildGrass(int n, World world) {
		for (int i=0; i<n; i++) {
			world.addEntity(new GrassEntity(Vector2D.getRandomCartesian(800, 600, true)));
		}
	}
	
	private static JFrame buildFrame() {
		JFrame frame = new JFrame("Goldsmiths AI - cows");
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	}

}
