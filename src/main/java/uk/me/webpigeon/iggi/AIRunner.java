package uk.me.webpigeon.iggi;

import java.awt.Dimension;

import javax.swing.JFrame;

import uk.me.webpigeon.world.DoubleWorld;

public class AIRunner {

	public static void main(String[] args) {
		DoubleWorld world = new DoubleWorld(800, 600);
		
		JFrame frame = buildFrame();
		frame.add(world);
		frame.pack();
		frame.setVisible(true);
	}

	private static JFrame buildFrame() {
		JFrame frame = new JFrame("Goldsmiths AI - cows");
		frame.setPreferredSize(new Dimension(800, 600));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	}

}
