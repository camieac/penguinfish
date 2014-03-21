package PenguinFish;

import java.util.LinkedList;
import java.util.Random;

enum Direction {
	SOUTH(0, 180, 1),
	NORTH(1, 0, 0),
	WEST(2, 270, 3),
	EAST(3, 90, 2),
	NORTHWEST(4, 315, 7),
	NORTHEAST(5, 45, 6),
	SOUTHWEST(6, 225, 5),
	SOUTHEAST(7, 135, 4);
	private int direction;
	private int angle;
	private int opposite;

	protected LinkedList<Direction> disabledDirections = new LinkedList<Direction>();;
	
	public int getInt(){
		return direction;
	}
	public int getAngle(){
		return angle;
	}
		
	public static Direction getRandom(){
		Random random = new Random();
		int rand = random.nextInt(7);
		return resolveDirection(rand);
	}
	private static Direction resolveDirection(int num) {
		switch(num){
		case 0: return SOUTH;
		case 1: return NORTH;
		case 2: return WEST;
		case 3: return EAST;
		case 4: return NORTHWEST;
		case 5: return NORTHEAST;
		case 6: return SOUTHWEST;
		case 7: return SOUTHEAST;
		default: return SOUTH;
		}
	}
	
	public Direction getOpposite(Direction d, Direction directionHit){
		if(directionHit == Direction.NORTH){
			switch(d){
			case NORTH: return SOUTH;
			case NORTHEAST: return SOUTHEAST;
			case NORTHWEST: return SOUTHWEST;
			}
		}
		if(directionHit == Direction.SOUTH){
			switch(d){
			case SOUTH: return NORTH;
			case SOUTHEAST: return NORTHEAST;
			case SOUTHWEST: return NORTHWEST;
			}
		}
		if(directionHit == Direction.WEST){
			switch(d){
			case WEST: return EAST;
			case SOUTHWEST: return SOUTHEAST;
			case NORTHWEST: return NORTHEAST;
			}
		}
		if(directionHit == Direction.EAST){
			switch(d){
			case EAST: return WEST;
			case NORTHEAST: return NORTHWEST;
			case SOUTHEAST: return SOUTHWEST;
			}
		}
		return d;
	}
	
	public Direction getNormalOpposite(Direction d){
		switch(d){
		case NORTH: return SOUTH;
		case NORTHEAST: return SOUTHWEST;
		case EAST: return WEST;
		case SOUTHEAST: return NORTHWEST;
		case SOUTH: return NORTH;
		case SOUTHWEST: return NORTHEAST;
		case WEST: return EAST;
		case NORTHWEST: return SOUTHEAST;
		default: return null;
		}
		
	}
	private Direction(int direction, int angle, int d){
		this.direction = direction;	
		this.angle = angle;
		this.opposite = d;
		
	}
	public void disableDirection(Direction d){
		
		if(!disabledDirections.contains(d)){
		disabledDirections.add(d);
		//System.out.println(d + " has been disabled");
		}
	}
	public void enableDirection(Direction d){
		if(!disabledDirections.contains(d)){
			disabledDirections.remove(d);
			//System.out.println(d + " has been enabled");
			}
	}
	public boolean checkDisabled(Direction d){
		boolean result = disabledDirections.contains(d);
		//System.out.println(d + " is being checked in " + disabledDirections + " and is " + result);
		return result;
		
	}
	public void clearDisabledDirections(){
		disabledDirections.clear();
	}
}
