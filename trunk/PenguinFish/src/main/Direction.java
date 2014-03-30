package main;

import java.util.LinkedList;
import java.util.Random;

/**
 * @author Andrew J. Rigg, Cameron A. Craig, Euan Mutch, Duncan Robertson,
 *         Stuart Thain
 * 
 */
public enum Direction {
	/**
	 * 
	 */
	EAST(2, 90),
	/**
	 * 
	 */
	NORTH(0, 0),
	/**
	 * 
	 */
	NORTHEAST(1, 45),
	/**
	 * 
	 */
	NORTHWEST(7, 315),
	/**
	 * 
	 */
	SOUTH(4, 180),
	/**
	 * 
	 */
	SOUTHEAST(3, 135),
	/**
	 * 
	 */
	SOUTHWEST(5, 225),
	/**
	 * 
	 */
	WEST(6, 270);

	/**
	 * @return
	 */
	public static Direction getRandom() {
		Random random = new Random();
		int rand = random.nextInt(7);
		return resolveDirection(rand);
	}
	protected static Direction resolveDirection(int num) {
		switch (num) {
		case 0:
			return NORTH;
		case 1:
			return NORTHEAST;
		case 2:
			return EAST;
		case 3:
			return SOUTHEAST;
		case 4:
			return SOUTH;
		case 5:
			return SOUTHWEST;
		case 6:
			return WEST;
		case 7:
			return NORTHWEST;
		default:
			return SOUTH;
		}
	}
	protected int angle;

	protected int direction;;

	protected LinkedList<Direction> disabledDirections = new LinkedList<Direction>();

	protected int opposite;

	private Direction(int direction, int angle) {
		this.direction = direction;
		this.angle = angle;

	}

	/**
	 * @param d
	 * @return
	 */
	public boolean checkDisabled(Direction d) {
		boolean result = disabledDirections.contains(d);
		// System.out.println(d + " is being checked in " + disabledDirections +
		// " and is " + result);
		return result;

	}

	/**
	 * 
	 */
	public void clearDisabledDirections() {
		disabledDirections.clear();
	}

	/**
	 * @param d
	 */
	public void disableDirection(Direction d) {

		if (!disabledDirections.contains(d)) {
			disabledDirections.add(d);
			// System.out.println(d + " has been disabled");
		}
	}

	/**
	 * @param d
	 */
	public void enableDirection(Direction d) {
		if (!disabledDirections.contains(d)) {
			disabledDirections.remove(d);
			// System.out.println(d + " has been enabled");
		}
	}

	/**
	 * @return
	 */
	public int getAngle() {
		return angle;
	}

	/**
	 * @return
	 */
	public int getInt() {
		return direction;
	}

	/**
	 * @param d
	 * @return
	 */
	public Direction getNormalOpposite(Direction d) {
		switch (d) {
		case NORTH:
			return SOUTH;
		case NORTHEAST:
			return SOUTHWEST;
		case EAST:
			return WEST;
		case SOUTHEAST:
			return NORTHWEST;
		case SOUTH:
			return NORTH;
		case SOUTHWEST:
			return NORTHEAST;
		case WEST:
			return EAST;
		case NORTHWEST:
			return SOUTHEAST;
		default:
			return null;
		}

	}

	/**
	 * @param d
	 * @param directionHit
	 * @return
	 */
	public Direction getOpposite(Direction d, Direction directionHit) {
		if (directionHit == Direction.NORTH) {
			switch (d) {
			case NORTH:
				return SOUTH;
			case NORTHEAST:
				return SOUTHEAST;
			case NORTHWEST:
				return SOUTHWEST;
			default:
				break;
			}
		}
		if (directionHit == Direction.SOUTH) {
			switch (d) {
			case SOUTH:
				return NORTH;
			case SOUTHEAST:
				return NORTHEAST;
			case SOUTHWEST:
				return NORTHWEST;
			default:
				break;
			}
		}
		if (directionHit == Direction.WEST) {
			switch (d) {
			case WEST:
				return EAST;
			case SOUTHWEST:
				return SOUTHEAST;
			case NORTHWEST:
				return NORTHEAST;
			default:
				break;
			}
		}
		if (directionHit == Direction.EAST) {
			switch (d) {
			case EAST:
				return WEST;
			case NORTHEAST:
				return NORTHWEST;
			case SOUTHEAST:
				return SOUTHWEST;
			default:
				break;
			}
		}
		return d;
	}
}
