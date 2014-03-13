package PenguinFish;

class Collision {
	int width;
	int height;  
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
	
	public boolean collisionWallsAmmo(Bullet b){
		int imageX = b.getX();
		 int imageY = b.getY();
		 int imageWidth = b.getWidth();
		 int imageHeight = b.getHeight();
		 
		if (imageX + imageWidth <= 0 ||imageX >= width || imageY + imageHeight <= 0 || imageY >= height) {
	 		return true;
         }	
		else return false;
	}
	public boolean collisionBulletEnemy(Bullet bullet, Enemy enemy){
		int enemyX = enemy.getX()+(enemy.getWidth()/2);
		int enemyY = enemy.getY()+(enemy.getHeight()/2);
		
		int bulletX = bullet.getX()+(bullet.getWidth()/2);
		int bulletY = bullet.getY()+(bullet.getHeight()/2);
		
		int radiusX = enemy.getWidth() + bullet.getWidth();
		int radiusY = enemy.getHeight() + bullet.getHeight();
		
		if (Math.abs(enemyX - bulletX) <= radiusX/2 && Math.abs(enemyY - bulletY) <= radiusY/2){
			return true;
		}
		else return false;
	}
}
