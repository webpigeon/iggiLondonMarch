package uk.me.webpigeon.iggi;

import java.awt.Dimension;

import javax.swing.JFrame;

import uk.me.webpigeon.piers.HunterAgent;
import uk.me.webpigeon.piers.HunterVillage;
import uk.me.webpigeon.util.Vector2D;
import uk.me.webpigeon.world.DoubleWorld;
import uk.me.webpigeon.world.Entity;

/**
 * AI runner with both mine and pier's AI code.
 */
public class AIRunnerBoth {
	public static void main(String[] args) {
		
		//build the world
		DoubleWorld world = new DoubleWorld(800, 600);
		world.addEntity(buildHunterVillager());
		AIRunner.buildGrass(100, world);
		AIRunner.buildCows(10, world);
		
		// build the game loop
		Thread gameThread = new Thread(world);
		gameThread.setName("gameThread");
		gameThread.start();
		
		// Build the frame
		JFrame frame = buildFrame();
		frame.add(world);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Add Pier's AIs to hunt my poor cows :(
	 * 
	 * @return Pier's hunter village
	 */
	private static Entity buildHunterVillager() {
        HunterAgent.initialiseBehaviour();
        HunterAgent.initialiseSensors();
        return new HunterVillage(new Vector2D(250, 250));
	}
	
	private static JFrame buildFrame() {
		JFrame frame = new JFrame("Goldsmiths AI - cows");
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	}
}
