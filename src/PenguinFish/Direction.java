package PenguinFish;

import java.util.Random;

enum Direction {
	SOUTH(0, 180, 1),
	NORTH(1, 0, 0),
	WEST(2, 270, 3),
	EAST(3, 90, 2),
	NW(4, 315, 7),
	NE(5, 45, 6),
	SW(6, 225, 5),
	SE(7, 135, 4);
	private int direction;
	private int angle;
	private int opposite;
	
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
		case 4: return NW;
		case 5: return NE;
		case 6: return SW;
		case 7: return SE;
		default: return SOUTH;
		}
	}
	
	public Direction getOpposite(Direction d){
		return resolveDirection(d.opposite);
	}
	
	private Direction(int direction, int angle, int d){
		this.direction = direction;	
		this.angle = angle;
		this.opposite = d;
		
	}
}
