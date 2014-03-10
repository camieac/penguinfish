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
	 
		if (imageX <= 0) {
	 		directionX = Direction.EAST;
	 	
         }	
    	 if (imageX >= (width - imageWidth)) {
    		 directionX = Direction.WEST;
    		
    	 }
    	 if (imageY <= 0) {
    		 directionY = Direction.SOUTH;

    	 }
    	 if (imageY >= (height - imageHeight)) {
    		 directionY = Direction.NORTH;
    	 }
    	
    	 Direction[] directions = new Direction[2];
         directions[0] = directionX;
         directions[1] = directionY;
    	 return directions;
	}
	
	public boolean collisionWallsAmmo(int imageX, int imageY, int imageWidth, int imageHeight){
		if (imageX + imageWidth <= 0 ||imageX >= width || imageY + imageHeight <= 0 || imageY >= height) {
	 		return true;
         }	
		else return false;
	}
	public boolean collisionBulletEnemy(Bullet bullet, Enemy enemy){
		int bulletUpperEdge = bullet.getY();
		int bulletLoweredge = bullet.getY() + bullet.getHeight();
		int bulletLeftEdge = bullet.getX();
		int bulletRightEdge = bullet.getX() + bullet.getWidth();
		
		int enemyUpperEdge = enemy.getY();
		int enemyLowerEdge = enemy.getY() + enemy.getWidth();
		int enemyLeftEdge = enemy.getX();
		int enemyRightEdge = enemy.getX() + enemy.getHeight();
		
		if(enemyUpperEdge >= bulletUpperEdge && enemyLeftEdge <= bulletLeftEdge)
		
	}
	
}
