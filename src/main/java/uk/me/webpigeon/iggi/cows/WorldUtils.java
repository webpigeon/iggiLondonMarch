package uk.me.webpigeon.iggi.cows;

import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import uk.me.webpigeon.world.Entity;
import uk.me.webpigeon.world.Tag;
import uk.me.webpigeon.world.World;

public class WorldUtils {
	
	public static List<Entity> filterByType(List<Entity> entities, Tag type) {
		Predicate<Entity> p = (Entity e) -> e.hasTag(type);
		return filterEntities(entities, p);
	}
	
	public static List<Entity> filterEntities(List<Entity> entities, Predicate<Entity> p) {
		return entities.stream().filter(p).collect(Collectors.toList());
	}

}
