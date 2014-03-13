package PenguinFish;

import java.util.Random;

enum Direction {
	SOUTH(0, 180),
	NORTH(1, 0),
	WEST(2, 270),
	EAST(3, 90),
	NW(4, 315),
	NE(5, 45),
	SW(6, 225),
	SE(7, 135),
	DEAD(8, 0);		
	private int direction;
	private int angle;
	
	public int getInt(){
		return direction;
	}
	public int getAngle(){
		return angle;
	}
		
	public static Direction getRandom(){
		Random random = new Random();
		int rand = random.nextInt(7);
		
		
		switch(rand){
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
	
	private Direction(int direction, int angle){
		this.direction = direction;	
		this.angle = angle;
	}
}
