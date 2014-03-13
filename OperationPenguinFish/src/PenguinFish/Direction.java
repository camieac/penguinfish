package PenguinFish;

import java.util.Random;

enum Direction {
	NORTH(),
	NORTHEAST(),
	EAST(),
	SOUTHEAST(),
	SOUTH(),
	SOUTHWEST(),
	WEST(),
	NORTHWEST(),
	NONE();
	
	
	private Direction(){
		
	}
	public Direction getRandom(){
		Random random = new Random();
		int rand = random.nextInt(4);
		switch(rand){
		case 0: return NORTH;
		case 1: return SOUTH;
		case 2: return WEST;
		case 3: return EAST;
		default: return null;
		}
		
	}
	public static Direction getRandomX(){
		Random random = new Random();
		int rand = random.nextInt(2);
		switch(rand){
		
		case 0: return WEST;
		case 1: return EAST;
		default: return WEST;
		}
		
	}
	public static Direction getRandomY(){
		Random random = new Random();
		int rand = random.nextInt(2);
		switch((rand)){
		case 0: return NORTH;
		case 1: return SOUTH;
		default: return NORTH;
		}
		
	}
	
	public static void main(String[] args){
		Direction n = Direction.SOUTH;
		System.out.println(n.getRandomY());
		System.out.println(n.getRandomX());
		System.out.println(n.getRandom());
		System.out.println();
	}
}
