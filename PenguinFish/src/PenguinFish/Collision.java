package PenguinFish;

class Collision {
	int width;;   //need getter and setter for the width and heights of the console window
	int height;   //make sure it's the active part if the window and not the entire window
	public Collision(int width, int height){
		this.width = width;
		this.height = height;
	}
	public Direction[] collisionObjects(int image1X, int image1Y, int image1Width, int image1Height, int image2X, int image2Y, int image2Width, int image2Height, Direction directionX,Direction directionY){
            if (image1X >= (image2X + image2Width - image2Width/2) && image1X <= (image2X + image2Width) && image1Y >= (image2Y - image1Height) && image1Y <= (image2Y + image2Height)) {
                directionX = Direction.EAST;
            }
        
            if (image1X >= (image2X - image1Width) && image1X <= (image2X - image1Width + image2Width/2) && image1Y >= (image2Y - image1Height) && image1Y <= (image2Y + image2Height)) {
                directionX = Direction.WEST;
            }

            if (image1Y >= (image2Y + image2Height - image2Height/2) && image1Y <= (image2Y + image1Height) && image1X >= (image2X - image1Width) && image1X <= (image2X + image2Width)) {
                directionY = Direction.SOUTH;
            }

            if (image1Y >= (image2Y - image1Height) && image1Y <= (image2Y - image1Height + image2Height/2) && image1X >= (image2X - image1Width) && image1X <= (image2X + image2Width)) {
                directionY = Direction.NORTH;
            }
            Direction[] directions = new Direction[2];
            directions[0] = directionX;
            directions[1] = directionY;
                return directions;
            }
	
	public Direction[] collisionWalls(int imageX, int imageY, int imageWidth, int imageHeight, Direction directionX,Direction directionY){
	 	boolean testHit = false;
		if (imageX <= 0) {
	 		directionX = Direction.EAST;
	 		testHit = true;
         }	
    	 if (imageX >= (width - imageWidth)) {
    		 directionX = Direction.WEST;
    		 testHit = true;
    	 }
    	 if (imageY <= 0) {
    		 directionY = Direction.SOUTH;
    		 testHit = true;
    	 }
    	 if (imageY >= (height - imageHeight)) {
    		 directionY = Direction.NORTH;
    		 testHit = true;
    	 }
    	 if(testHit) System.out.println("Enemy hit wall");
    	 Direction[] directions = new Direction[2];
         directions[0] = directionX;
         directions[1] = directionY;
    	 return directions;
	}
	
}
