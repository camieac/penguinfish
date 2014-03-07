package PenguinFish;

import java.awt.Image;

public class collision {
	int width = 500;    //need getter and setter for the width and heights of the console window
	int height = 500;   //make sure it's the active part if the window and not the entire window
	
	public boolean[] collisionObjects(int image1X, int image1Y, int image1Width, int image1Height, int image2X, int image2Y, int image2Width, int image2Height, boolean [] velocities){
            if (image1X >= (image2X + image2Width - image2Width/2) && image1X <= (image2X + image2Width) && image1Y >= (image2Y - image1Height) && image1Y <= (image2Y + image2Height)) {
                velocities[0] = true;
            }
        
            if (image1X >= (image2X - image1Width) && image1X <= (image2X - image1Width + image2Width/2) && image1Y >= (image2Y - image1Height) && image1Y <= (image2Y + image2Height)) {
                velocities[0] = false;
            }

            if (image1Y >= (image2Y + image2Height - image2Height/2) && image1Y <= (image2Y + image1Height) && image1X >= (image2X - image1Width) && image1X <= (image2X + image2Width)) {
                velocities[1] = true;
            }

            if (image1Y >= (image2Y - image1Height) && image1Y <= (image2Y - image1Height + image2Height/2) && image1X >= (image2X - image1Width) && image1X <= (image2X + image2Width)) {
                velocities[1] = false;
            }
                return velocities;
            }
	
	public boolean[] collisionWalls(int imageX, int imageY, int imageWidth, int imageHeight, boolean[]velocities){
	 	 if (imageX <= 0) {
	 		velocities[0] = true;
         }	
    	 if (imageX >= (width - imageWidth)) {
    		 velocities[0] = false;
    	 }
    	 if (imageY <= 0) {
    		 velocities[1] = true;
    	 }
    	 if (imageY >= (height - imageHeight)) {
    		 velocities[1] = false;
    	 }
    	 return velocities;
	}
	
}
