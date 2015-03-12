package uk.me.webpigeon.iggi.cows;

import uk.me.webpigeon.iggi.btree.BehavourEvaluator;
import uk.me.webpigeon.iggi.btree.BehavourNode;
import uk.me.webpigeon.iggi.btree.ChoiceNode;
import uk.me.webpigeon.iggi.btree.CooldownDecorator;
import uk.me.webpigeon.iggi.btree.RandomChance;
import uk.me.webpigeon.iggi.btree.SequenceNode;
import uk.me.webpigeon.steering.SeekBehaviour;
import uk.me.webpigeon.steering.WanderingBehaviour;
import uk.me.webpigeon.util.Vector2D;
import uk.me.webpigeon.world.Entity;
import uk.me.webpigeon.world.GrassEntity;
import uk.me.webpigeon.world.World;

public class CowFactory {
	
	public static BehavourNode buildEat(World world) {
		BehavourNode findOldFood = new RejectYoungPlants(world, GrassEntity.class);
		BehavourNode findAnyFood = new TargetClosest(world, GrassEntity.class);
		BehavourNode foodSelectionPolicy = new ChoiceNode(false, findOldFood, findAnyFood);
		
		BehavourNode moveTowards = new SteerTowards(new SeekBehaviour(null));
		BehavourNode eatFood = new EatItem();
		
		return new SequenceNode(foodSelectionPolicy, moveTowards, eatFood);
	}
	
	public static BehavourNode buildWander() {
		return new WanderAbout(new WanderingBehaviour());
	}
	
	public static BehavourNode buildBreed(World world) {
		BehavourNode breed = new ProduceBabyCow(world);
		return new SequenceNode(new RandomChance(0.7), new CooldownDecorator(5000, breed));
	}
	
	public static BehavourNode buildRootNode(World world) {
		return new SequenceNode(true, buildEat(world), buildBreed(world), buildWander());
	}
	
	
	public static Entity buildCow(double x, double y, World world){
		BehavourEvaluator eval = new BehavourEvaluator(buildRootNode(world), buildWander());
		return new BehavourCow(x, y, eval);
	}
	
	public static Entity buildCow(World world){
		Vector2D location = Vector2D.getRandomCartesian(800, 600, true);
		return buildCow(location.x, location.y, world);
	}

}
