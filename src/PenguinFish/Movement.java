package PenguinFish;

class Movement {
	private double x, y;

	private Direction direction;

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	private double distance;

	public Movement(double x, double y) {
		this.x = x;
		this.y = y;
		setupDirection(x, y);
		setupDistance(x, y);
	}

	private void setupDistance(double x, double y) {
		if (x == 0)
			distance = Math.abs(y);
		else
			distance = Math.abs(x);
	}

	private void setupDirection(double x, double y) {
		if (x == 0 && y < 0) {
			this.direction = Direction.NORTH;
		} else if (x > 0 && y < 0) {
			this.direction = Direction.NORTHEAST;
		} else if (x > 0 && y == 0) {
			this.direction = Direction.EAST;
		} else if (x > 0 && y > 0) {
			this.direction = Direction.SOUTHEAST;
		} else if (x == 0 && y > 0) {
			this.direction = Direction.SOUTH;
		} else if (x < 0 && y > 0) {
			this.direction = Direction.SOUTHWEST;
		} else if (x < 0 && y == 0) {
			this.direction = Direction.WEST;
		} else if (x < 0 && y < 0) {
			this.direction = Direction.NORTHWEST;
		} else if (x == 0 && y == 0) {
			this.direction = Direction.NONE;
		}
	}

	public Movement(Direction direction, double distance) {
		this.direction = direction;
		this.distance = distance;
		switch (direction) {
		case NORTH:

			this.x = 0;
			this.y = -this.distance;

			break;
		case NORTHEAST:

			this.x = this.distance * Math.sqrt(distance);
			this.y = -this.distance * Math.sqrt(distance);

			break;
		case EAST:

			this.x = this.distance;
			this.y = 0;

			break;
		case SOUTHEAST:

			this.x = this.distance * Math.sqrt(distance);
			this.y = this.distance * Math.sqrt(distance);

			break;
		case SOUTH:

			this.x = 0;
			this.y = this.distance;

			break;
		case SOUTHWEST:

			this.x = -this.distance * Math.sqrt(distance);
			this.y = this.distance * Math.sqrt(distance);

			break;
		case WEST:

			this.x = -this.distance;
			this.y = 0;

			break;
		case NORTHWEST:

			this.x = -this.distance * Math.sqrt(distance);
			this.y = -this.distance * Math.sqrt(distance);

			break;
		case NONE:
			this.x = 0;
			this.y = 0;
			break;
		}

	}

	public double getX() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
		setupDirection(this.x, y);
		setupDistance(this.x, y);
	}

	public double getY() {
		return this.y;
	}

	public void setY(double y) {
		this.y = y;
		setupDirection(x, this.y);
		setupDistance(x, this.y);
	}

	public void setZero() {
		this.direction = this.direction.NONE;
		this.distance = 0;
		this.x = 0;
		this.y = 0;
	}

	public static void main(String[] args){
		/*TESTING PART 1*/
		Movement movement = new Movement(0,0);
		boolean part1Pass = true;
		if(!(movement.getDirection() == Direction.NONE)){
			part1Pass = false;
		}
		movement.setX(5);
		movement.setY(5);
		if(!(movement.getDirection() == Direction.SOUTHEAST)){
			part1Pass = false;
		}
		movement.setX(-5);
		movement.setY(-5);
		if(!(movement.getDirection() == Direction.NORTHWEST)){
			part1Pass = false;
		}
		movement.setX(0);
		movement.setY(5);
		if(!(movement.getDirection() == Direction.SOUTH)){
			part1Pass = false;
		}
		
		movement.setZero();
		if(!(movement.getDirection() == Direction.NONE)){
			part1Pass = false;
		}
		System.out.println("Part1\nTesting (x,y) ==> Direction convertion\nResult: " + part1Pass);
			
		}
	}

