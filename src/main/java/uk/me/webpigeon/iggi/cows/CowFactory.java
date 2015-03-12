package uk.me.webpigeon.iggi.cows;

import uk.me.webpigeon.iggi.btree.BehavourEvaluator;
import uk.me.webpigeon.iggi.btree.BehavourNode;
import uk.me.webpigeon.iggi.btree.ChoiceNode;
import uk.me.webpigeon.iggi.btree.CooldownDecorator;
import uk.me.webpigeon.iggi.btree.RandomChance;
import uk.me.webpigeon.iggi.btree.SequenceNode;
import uk.me.webpigeon.joseph.cow.Property;
import uk.me.webpigeon.steering.FleeBehaviour;
import uk.me.webpigeon.steering.SeekBehaviour;
import uk.me.webpigeon.steering.WanderingBehaviour;
import uk.me.webpigeon.util.Vector2D;
import uk.me.webpigeon.world.Entity;
import uk.me.webpigeon.world.GrassEntity;
import uk.me.webpigeon.world.Tag;
import uk.me.webpigeon.world.World;

public class CowFactory {
	
	public static BehavourNode buildEat(World world) {
		BehavourNode findOldFood = new RejectYoungPlants(world);
		BehavourNode findAnyFood = new TargetClosest(world, Tag.GRASS);
		BehavourNode foodSelectionPolicy = new ChoiceNode(false, findOldFood, findAnyFood);
		
		BehavourNode moveTowards = new SteerTowards(new SeekBehaviour(null), 1);
		BehavourNode eatFood = new EatItem();
		
		return new SequenceNode(foodSelectionPolicy, moveTowards, eatFood);
	}
	
	public static BehavourNode buildWander() {
		return new WanderAbout(new WanderingBehaviour());
	}
	
	public static BehavourNode buildAvoidHunters(World world) {
		BehavourNode centerFinder = new SelectCenterOfEntities(world, Tag.HUNTER);
		BehavourNode moveAway = new SteerTowards(new FleeBehaviour(null), 50);
		return new SequenceNode(centerFinder, moveAway);
	}
	
	public static BehavourNode buildHeard(World world) {
		BehavourNode centerFinder = new SelectCenterOfEntities(world, Tag.COW);
		BehavourNode moveTowards = new SteerTowards(new SeekBehaviour(null), 1);
		return new SequenceNode(centerFinder, moveTowards);
	}
	
	public static BehavourNode buildFoodDriver(World world) {
		BehavourNode centerFinder = new SelectCenterOfEntities(world, Tag.GRASS);
		BehavourNode moveTowards = new SteerTowards(new SeekBehaviour(null), 1);
		return new SequenceNode(centerFinder, moveTowards);
	}
	
	public static BehavourNode buildBreed(World world) {
		BehavourNode breed = new ProduceBabyCow(world);
		return new SequenceNode(new RandomChance(0.7), new CooldownDecorator(5000, breed));
	}
	
	public static BehavourNode buildRootNode(World world) {
		BehavourNode foodFinding = new ChoiceNode(buildEat(world), buildFoodDriver(world));
		BehavourNode choice = new ChoiceNode(buildAvoidHunters(world), foodFinding);
		
		return new SequenceNode(true, choice, buildBreed(world));
	}
	
	
	public static Entity buildCow(double x, double y, World world){
		BehavourEvaluator eval = new BehavourEvaluator(buildRootNode(world), buildWander());
		Entity cow =  new BehavourCow(x, y, eval);
		cow.setValue(Property.SIGHT_RANGE, 100.0);
		cow.setValue(Property.SATURATION, 1000.0);
		cow.setValue(Property.METABOLISM, 1.0);
		
		return cow;
	}
	
	public static Entity buildCow(World world){
		Vector2D location = Vector2D.getRandomCartesian(800, 600, true);
		return buildCow(location.x, location.y, world);
	}

}
