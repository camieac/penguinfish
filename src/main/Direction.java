package main;

import java.util.LinkedList;
import java.util.Random;

public enum Direction {
	NORTH(1, 0),
	NORTHEAST(5, 45),
	EAST(3, 90),
	SOUTHEAST(7, 135),
	SOUTH(0, 180),
	SOUTHWEST(6, 225),
	WEST(2, 270),
	NORTHWEST(4, 315);

	protected int direction;
	protected int angle;
	protected int opposite;

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
	protected static Direction resolveDirection(int num) {
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
			default:
				break;
			}
		}
		if(directionHit == Direction.SOUTH){
			switch(d){
			case SOUTH: return NORTH;
			case SOUTHEAST: return NORTHEAST;
			case SOUTHWEST: return NORTHWEST;
			default:
				break;
			}
		}
		if(directionHit == Direction.WEST){
			switch(d){
			case WEST: return EAST;
			case SOUTHWEST: return SOUTHEAST;
			case NORTHWEST: return NORTHEAST;
			default:
				break;
			}
		}
		if(directionHit == Direction.EAST){
			switch(d){
			case EAST: return WEST;
			case NORTHEAST: return NORTHWEST;
			case SOUTHEAST: return SOUTHWEST;
			default:
				break;
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
	private Direction(int direction, int angle){
		this.direction = direction;	
		this.angle = angle;
		
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
