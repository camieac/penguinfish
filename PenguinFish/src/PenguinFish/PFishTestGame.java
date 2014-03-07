package PenguinFish;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.Scanner;

import javax.swing.*;

public class PFishTestGame extends JPanel implements Runnable {

    private static final long serialVersionUID = 1L;
    // X and Y positions for the enemy and the player
    private int enemyNo = 10;
    Random generator = new Random();

    private int[] enemyX = new int[enemyNo];
    private int[] enemyY = new int[enemyNo];
    collision collImage = new collision();
    private int playerX = 200, playerY = 200;
    // Size of enemy and player
    private int enemySize = 10;
    private int playerSize = 30;
    Thread thread;
    int up = 5; 	// Up
    int down = -5;	// Down
    int left = -5; 	// Left
    int right = 5; 	// Right
    int heartX = generator.nextInt(500);
    int heartY = generator.nextInt(500);
    int width = 500, height = 500; // Size of the board
    int aggroRadius = 30;
    int difficulty = 2;
//	int difficulty = diff();// Game difficulty
    int life = 100;

    boolean playerUp, playerDown, playerLeft, playerRight;
    boolean game, gameOver;
    boolean[] leftRight = new boolean[enemyNo];
    boolean[] topBottom = new boolean[enemyNo];

    boolean h = false, hitOccurred = false, speedHeld;

    // Images
    private Image player01Down, player01Up, player01Left, player01Right;
    private Image player02Down, player02Up, player02Left, player02Right;
    private Image playerDead, heart01, heart02;
    private Image[] enemy = new Image[enemyNo];
	private boolean newHeart;
	private boolean[]movement = new boolean[2];
	private boolean[]movement2 = new boolean[2];
	private boolean[]movement3 = new boolean[2];
    public PFishTestGame() {
        thread = new Thread(this);
        thread.start();
        for (int i = 0; i < enemyNo; i++) {
            enemyX[i] = generator.nextInt(500);
            enemyY[i] = generator.nextInt(500);
        }
        for (int i = 0; i < enemyNo; i++) {
            leftRight[i] = generator.nextBoolean();
            topBottom[i] = generator.nextBoolean();
        }
        game = true;
    }

    // Load images
    private void loadImages() {

        // Player unhurt
        player01Down = new ImageIcon("C:\\Users\\Andrew\\Desktop\\Uni Stuff\\PFishTest-2014-03-03\\PFishTest\\CharacterDefault.png").getImage();
        player01Up = new ImageIcon("C:\\Users\\Andrew\\Desktop\\Uni Stuff\\PFishTest-2014-03-03\\PFishTest\\CharacterDefaultBack.png").getImage();
        player01Left = new ImageIcon("C:\\Users\\Andrew\\Desktop\\Uni Stuff\\PFishTest-2014-03-03\\PFishTest\\CharacterDefaultLeft.png").getImage();
        player01Right = new ImageIcon("C:\\Users\\Andrew\\Desktop\\Uni Stuff\\PFishTest-2014-03-03\\PFishTest\\CharacterDefaultRight.png").getImage();

        player02Down = new ImageIcon("C:\\Users\\Andrew\\Desktop\\Uni Stuff\\PFishTest-2014-03-03\\PFishTest\\CharacterHurt.png").getImage();
        player02Up = new ImageIcon("C:\\Users\\Andrew\\Desktop\\Uni Stuff\\PFishTest-2014-03-03\\PFishTest\\CharacterHurtBack.png").getImage();
        player02Left = new ImageIcon("C:\\Users\\Andrew\\Desktop\\Uni Stuff\\PFishTest-2014-03-03\\PFishTest\\CharacterHurtLeft.png").getImage();
        player02Right = new ImageIcon("C:\\Users\\Andrew\\Desktop\\Uni Stuff\\PFishTest-2014-03-03\\PFishTest\\CharacterHurtRight.png").getImage();

        playerDead = new ImageIcon("C:\\Users\\Andrew\\Desktop\\Uni Stuff\\PFishTest-2014-03-03\\PFishTest\\CharacterDead.png").getImage();

        heart01 = new ImageIcon("C:\\Users\\Andrew\\Desktop\\Uni Stuff\\PFishTest-2014-03-03\\PFishTest\\Heart01.png").getImage();
        heart02 = new ImageIcon("C:\\Users\\Andrew\\Desktop\\Uni Stuff\\PFishTest-2014-03-03\\PFishTest\\Heart02.png").getImage();

        for (int i = 0; i < enemyNo; i++) {
            int j = i % 6;
            enemy[i] = new ImageIcon("C:\\Users\\Andrew\\Desktop\\Uni Stuff\\PFishTest-2014-03-03\\PFishTest\\Enemy0" + j + ".png").getImage();
        }
    }

    // Difficulty
    public int diff() {
        Scanner input = new Scanner(System.in);
        System.out.println("Set difficulty level: 1 to 5 : ");
        int d = Integer.parseInt(input.nextLine());
        return d;
    }

    // Interface
    public void paintComponent(Graphics gc) {
        setOpaque(false);
        super.paintComponent(gc);
        loadImages();
        Graphics2D g2d = (Graphics2D) gc;
        if (newHeart){
 
        	g2d.drawImage(heart01, heartX, heartY, this);
        }
        
        // Draw enemy
        for (int i = 0; i < enemyNo; i++) {
            int j = i;
            if (i > 5) {
                j = i % 6;
            }
            g2d.drawImage(enemy[j], enemyX[i], enemyY[i], this);
        }

        // Draw player
        if (life >= 50) {
            if (playerUp == true) {
                g2d.drawImage(player01Up, playerX, playerY, this);
            } else if (playerDown == true) {
                g2d.drawImage(player01Down, playerX, playerY, this);
            } else if (playerLeft == true) {
                g2d.drawImage(player01Left, playerX, playerY, this);
            } else if (playerRight == true) {
                g2d.drawImage(player01Right, playerX, playerY, this);
            } else {
                g2d.drawImage(player01Down, playerX, playerY, this);
            }
        }
        if (life < 50 && life > 0) {
            if (playerUp == true) {
                g2d.drawImage(player02Up, playerX, playerY, this);
            } else if (playerDown == true) {
                g2d.drawImage(player02Down, playerX, playerY, this);
            } else if (playerLeft == true) {
                g2d.drawImage(player02Left, playerX, playerY, this);
            } else if (playerRight == true) {
                g2d.drawImage(player02Right, playerX, playerY, this);
            } else {
                g2d.drawImage(player02Down, playerX, playerY, this);
            }
        }

        // Draw instructions
        gc.setColor(Color.black);
        gc.drawString("Avoid the red enemy!", 10, 10);

        // Draw player's life
        gc.drawString("Life: ", width - 100, 10);
        if (life == 100) {
            g2d.drawImage(heart01, width - 75, 1, this);
        }
        if (life < 100 && life >= 90) {
            g2d.drawImage(heart02, width - 75, 1, this);
        }
        if (life >= 80) {
            g2d.drawImage(heart01, width - 65, 1, this);
        }
        if (life < 80 && life >= 70) {
            g2d.drawImage(heart02, width - 65, 1, this);
        }
        if (life >= 60) {
            g2d.drawImage(heart01, width - 55, 1, this);
        }
        if (life < 60 && life >= 50) {
            g2d.drawImage(heart02, width - 55, 1, this);
        }
        if (life >= 40) {
            g2d.drawImage(heart01, width - 45, 1, this);
        }
        if (life < 40 && life >= 30) {
            g2d.drawImage(heart02, width - 45, 1, this);
        }
        if (life >= 20) {
            g2d.drawImage(heart01, width - 35, 1, this);
        }
        if (life < 20 && life >= 10) {
            g2d.drawImage(heart02, width - 35, 1, this);
        }
        if (life < 10) {
            gc.setColor(Color.red);
            gc.drawString("DEAD!", width - 65, 10);
        }

        // Draw "Game Over" screen when life = 0
        if (gameOver) {
            g2d.drawImage(playerDead, playerX, playerY, this);
            gc.setColor(Color.black);
            gc.drawString("Game Over", (width / 2) - 25, height / 2);
        }
    }

//    public void timeForNewHeart(){
//        int randHeart = generator.nextInt(60000);
//        long newTime = System.currentTimeMillis();
//        if (newTime - startTime > randHeart){
//        	newHeart = true;
//        }
//    }
    
    //Move the enemy
    public void drawEnemy(int x, int y, int j) {
        enemyX[j] = x;
        enemyY[j] = y;
   
        repaint();
    }

    // Get the pane width and height
   
   
    // Receive the key pressed
    public void keyPressed(KeyEvent evt) {
        if (life > 0) {
            switch (evt.getKeyCode()) {

                // Start moving player
                case KeyEvent.VK_W:
                    playerUp = true;
                    break;
                case KeyEvent.VK_UP:
                	playerUp = true;
                	break;
                case KeyEvent.VK_S:
                    playerDown = true;
                    break;
                case KeyEvent.VK_DOWN:
                	playerDown = true;
                	break;
                case KeyEvent.VK_A:
                    playerLeft = true;
                    break;
                case KeyEvent.VK_LEFT:
                	playerLeft = true;
                	break;
                case KeyEvent.VK_D:
                    playerRight = true;
                    break;
                case KeyEvent.VK_RIGHT:
                	playerRight = true;
                	break;
                case KeyEvent.VK_SPACE:
                	speedHeld = true;
                	break;
            }
        }
    }

    // Receive the key released
    public void keyReleased(KeyEvent evt) {
        if (life > 0) {
            switch (evt.getKeyCode()) {

                // Stop moving player
                case KeyEvent.VK_W:
                    playerUp = false;
                    break;
                case KeyEvent.VK_UP:
                	playerUp = false;
                	break;
                case KeyEvent.VK_S:
                    playerDown = false;
                    break;
                case KeyEvent.VK_DOWN:
                	playerDown = false;
                	break;
                case KeyEvent.VK_A:
                    playerLeft = false;
                    break;
                case KeyEvent.VK_LEFT:
                	playerLeft = false;
                	break;
                case KeyEvent.VK_D:
                    playerRight = false;
                    break;
                case KeyEvent.VK_RIGHT:
                	playerRight = false;
                	break;
                case KeyEvent.VK_SPACE:
                	speedHeld = false;
                	break;
            }
        }
    }

    // Move player
    public void moverPlayer() {
        if (playerUp == true && playerY >= 15) {
             if (speedHeld){
            	 playerY += 2*down;
            }
             else playerY += down;
        }
        if (playerDown == true && playerY <= (this.getHeight() - playerSize)) {
        	if (speedHeld){
           	 playerY += 2*up;
           }
        	else playerY += up;
        }
        if (playerLeft == true && playerX >= 0) {
        	if (speedHeld){
           	 playerX += 2*left;
           }
        	else playerX += left;
        }
        if (playerRight == true && playerX <= (this.getWidth() - playerSize)) {
        	if (speedHeld){
           	 playerX += 2*right;
           }
        	else playerX += right;
        }
        drawPlayer(playerX, playerY);
    }

    // Player's position
    public void drawPlayer(int x, int y) {
        this.playerX = x;
        this.playerY = y;
        repaint();
    }

    // Player loses health
    public void deductHealth() {
        //life = life - 10;
        hitOccurred = true;
    }

    public void run() {
        while (true) {
        	
        	long recentTime = System.currentTimeMillis();
            if (game) {
                for (int i = 0; i < enemyNo; i++) {
                    // Move enemy to the right
                    if (leftRight[i]==true) {
                        enemyX[i] += (right * (generator.nextInt(20)) / 10);
                       
                    } // Move enemy to the left
                    if (leftRight[i]==false) {
                        enemyX[i] += (left * (generator.nextInt(20)) / 10);
                        
                    }

                    // Move enemy down
                    if (topBottom[i]==true) {
                        enemyY[i] += (up * (generator.nextInt(20)) / 10);
                        
                    }
                    if (topBottom[i]==false) {
                        // Move enemy up
                        enemyY[i] += (down * (generator.nextInt(20)) / 10);
                       
                    }
                }
                
                ///Enemy Hits walls
                int a = 0;
                for (int i = 0; i < enemyNo; i++) {
                	movement[0]= leftRight[i];
                	movement[1]= topBottom[i];
                	collImage.collisionWalls(enemyX[i], enemyY[i], enemySize, enemySize, movement);
                	leftRight[i] = movement[0];
                	topBottom[i] = movement[1];
                	 if (Math.abs(enemyX[i]-playerX) >= 80  && Math.abs(enemyY[i]-playerY) >= 80){
                		 a++;
                	 }
                	 if (a == enemyNo){
                		 hitOccurred = false;
                		 a = 0;
                	 }
                }
                
                ///////////////////// Aggro
                if (hitOccurred) {
                    for (int i = 0; i < enemyNo; i++) {
                        // Move enemy to the right
                        if (leftRight[i]) {
                            if (enemyX[i] >= (playerX + playerSize + aggroRadius)) {
                                leftRight[i] = false;
                            }
                        } // Move enemy to the left
                        else {
                            if (enemyX[i] <= (playerX - (playerSize + aggroRadius))) {
                                leftRight[i] = true;
                            }
                        }

                        // Move enemy down
                        if (topBottom[i]) {
                            if (enemyY[i] >= (playerY + playerSize + aggroRadius)) {
                                topBottom[i] = false;
                            }
                        } else {
                            // Move enemy up
                            if (enemyY[i] <= (playerY - (playerSize + aggroRadius))) {
                                topBottom[i] = true;
                            }
                        }
                    }
                }
                ///////////////////////

                for (int i = 0; i < enemyNo; i++) {
                    drawEnemy(enemyX[i], enemyY[i], i);
                }
                // Difficulty
                try {
                    switch (difficulty) {
                        case 1:
                            Thread.sleep(70);
                            break;
                        case 2:
                            Thread.sleep(60);
                            break;
                        case 3:
                            Thread.sleep(50);
                            break;
                        case 4:
                            Thread.sleep(40);
                            break;
                        case 5:
                            Thread.sleep(20);
                            break;
                    }
                } catch (InterruptedException ex) {
                }

                // Move player
                moverPlayer();

                // Game over
                // When life reaches zero, the game will end
                if (life == 0) {
                    game = false;
                    gameOver = true;
                }

                if (playerX + playerSize >= heartX && playerX <= heartX + enemySize && playerY + playerSize >= heartY && playerY <= heartY + enemySize){
                	life += 10;
                	newHeart = false;
                }
                
                for (int i = 0; i < enemyNo; i++) {
                    // Enemy hits player on the right
                	movement3[0] = leftRight[i];
                	movement3[1] = topBottom[i];
                	collImage.collisionObjects(enemyX[i], enemyY[i], enemySize, enemySize, playerX, playerY, playerSize, playerSize, movement3);
                	if (movement3[0] == leftRight[i] && movement3[1] == topBottom[i]){
                		h = false;
                	}
                	else{h = true;}
                	leftRight[i] = movement3[0];
                	topBottom[i] = movement3[1];

                
//                    if (enemyX[i] >= (playerX + playerSize - 5) && enemyX[i] <= (playerX + playerSize) && enemyY[i] >= (playerY - enemySize) && enemyY[i] <= (playerY + playerSize)) {
//                        leftRight[i] = true;
//                        h = true;
//                    }
//
//                    // Enemy hits player on the left
//                    if (enemyX[i] >= (playerX - enemySize) && enemyX[i] <= (playerX - enemySize + 5) && enemyY[i] >= (playerY - enemySize) && enemyY[i] <= (playerY + playerSize)) {
//                        leftRight[i] = false;
//                        h = true;
//                    }
//
//                    // Enemy hits player on the bottom
//                    if (enemyY[i] >= (playerY + playerSize - 5) && enemyY[i] <= (playerY + playerSize) && enemyX[i] >= (playerX - enemySize) && enemyX[i] <= (playerX + playerSize)) {
//                        topBottom[i] = true;
//                        h = true;
//                    }
//
//                    // Enemy hits player on the top
//                    if (enemyY[i] >= (playerY - enemySize) && enemyY[i] <= (playerY - enemySize + 5) && enemyX[i] >= (playerX - enemySize) && enemyX[i] <= (playerX + playerSize)) {
//                        topBottom[i] = false;
//                        h = true;
//                    }
                    if (h) {
                        deductHealth();
                        h = false;
                    }
                }

                // Enemy hits enemy
                for (int i = 0; i < enemyNo; i++) {
                    for (int j = 0; j < enemyNo; j++) {
                    	if(i != j){
                         	movement2[0]= leftRight[i];
                        	movement2[1]= topBottom[i];
                    		collImage.collisionObjects(enemyX[i], enemyY[i], enemySize, enemySize, enemyX[j], enemyY[j], enemySize, enemySize, movement2);
                        	leftRight[i] = movement2[0];
                        	topBottom[i] = movement2[1];
                        }
            		}
                }
            }
        }
    }
}
