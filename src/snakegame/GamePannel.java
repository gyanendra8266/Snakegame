package snakegame;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;


class GamePannel extends JPanel implements ActionListener,KeyListener {
	private int[] snakexlenght=new int[750]; //snake x length maximum
	private int[] snakeylength=new int[750];//snake y length maximum
	private int lengthofsnake=3; //initial length of snake
	
	private int[] xPos= {25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,
			             500,525,550,575,600,625,650,675,700,725,750,775,800,825,850};
	private int[] yPos= {75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,
			            500,525,550,575,600,625};
	
	private Random random=new Random();
	private int enemyX,enemyY;
	
	
	
	private boolean left=false;
	private boolean right=true;
	private boolean  up=false;
	private boolean down=false;
	private int moves=0;
	
	private ImageIcon title= new ImageIcon(getClass().getResource("snaketitle.jpg"));
	private ImageIcon leftmouth= new ImageIcon(getClass().getResource("leftmouth.png"));
	private ImageIcon rightmouth= new ImageIcon(getClass().getResource("rightmouth.png"));
	private ImageIcon upmouth= new ImageIcon(getClass().getResource("upmouth.png"));
	private ImageIcon downmouth= new ImageIcon(getClass().getResource("downmouth.png"));
	private ImageIcon snakeimage= new ImageIcon(getClass().getResource("snakeimage.png"));
	private ImageIcon enemy= new ImageIcon(getClass().getResource("enemy.png"));
	private Timer timer;
	private int delay=150;  //set the timer or speed of a snake 
	private int score=0;
	private boolean gameOver=false;
	 GamePannel()
	 {
	addKeyListener(this);
	setFocusable(true);
	setFocusTraversalKeysEnabled(true);
	timer=new Timer(delay,this);
	timer.start();
	newenemy();
	}
	 // Draw the food of snake
	 private void newenemy() 
	   {
	    enemyX=xPos[random.nextInt(34)];
		enemyY=yPos[random.nextInt(23)];	
		}
	
    

	@Override
	 public void paint(Graphics g)
	 {
		 super.paint(g);
		 g.setColor(Color.WHITE);
		 g.drawRect(24, 10, 851, 55);
		 g.drawRect(24, 74, 851, 576);
		 title.paintIcon(this,g,25,11);
		 g.setColor(Color.BLACK);//second rectangle color
		 g.fillRect(25, 75, 850, 575);
		 
		 //starting position of the image
		 if(moves==0) 
		 {
			 snakexlenght[0]=100;
			 snakexlenght[1]=75;
			 snakexlenght[2]=50;
			 
			 snakeylength[0]=100;
			 snakeylength[1]=100;
			 snakeylength[2]=100;
		 }
		 
		 //draw all forth mouth of the image
		 if(left)
		 {
			 leftmouth.paintIcon(this, g, snakexlenght[0],snakeylength[0] );
			 
		 }
		 if(right)
		 {    
	       rightmouth.paintIcon(this, g, snakexlenght[0],snakeylength[0] );
			
		 }
		 if(up)
		 {
			 upmouth.paintIcon(this, g, snakexlenght[0],snakeylength[0] );
		 }
		 if(down)
		 {
			 downmouth.paintIcon(this, g, snakexlenght[0],snakeylength[0] );
		 }
		 for(int i=1;i<lengthofsnake;i++)
		 {
			 snakeimage.paintIcon(this,g, snakexlenght[i],snakeylength[i]);
		 
	       }
		 enemy.paintIcon(this,g,enemyX,enemyY);
		 //This method shows the message of game Over
		 if(gameOver) 
		 {
			g.setColor(Color.RED);
			g.setFont(new Font("Arial",Font.BOLD,50));
			g.drawString("Game Over",300,300);
			g.setFont(new Font("Arial",Font.PLAIN,20));
			g.drawString("Press SPACE to Restart",320,350);
		 }
		 
		   g.setColor(Color.GREEN);
			g.setFont(new Font("Arial",Font.BOLD,14));
			g.drawString("Score : "+score,750,30);
			g.drawString("Length : "+lengthofsnake,750,50);
		 g.dispose();
	 }
        @Override
		public void actionPerformed(ActionEvent e)
        {
        	
        for(int i=lengthofsnake-1;i>0;i--)
        {
        	snakexlenght[i]	=snakexlenght[i-1];
        	snakeylength[i]	=snakeylength[i-1];
        }
        
        if(left) {
		snakexlenght[0]=snakexlenght[0]-25;
			 }
		if(right) {
		snakexlenght[0]=snakexlenght[0]+25;
		 }
		 if(up) {
		snakeylength[0]=snakeylength[0]-25;
		 }
	    if(down) {
	    snakeylength[0]=snakeylength[0]+25;
			}
	    if(snakexlenght[0]>850)snakexlenght[0]=25;
	    if(snakexlenght[0]<25)snakexlenght[0]=850;
	    
	    if(snakeylength[0]>625)snakeylength[0]=75;
	    if(snakeylength[0]<75)snakeylength[0]=625;
	    
	    collidesWithEnemy1();
	    collidesWithBody();
	    repaint();
       }
        
       private void collidesWithBody()
       {
			for(int i=lengthofsnake-1;i>0;i--) 
			{
             if(snakexlenght[i]==snakexlenght[0]&&snakeylength[i]==snakeylength[0]) 
             {
            	timer.stop(); 
            	gameOver=true;
             }
			}
			
		}

	//This method is used to show the snake ate  the enemy
		private void collidesWithEnemy1() {
			if(snakexlenght[0]==enemyX && snakeylength[0]==enemyY)
			{
				newenemy();
				lengthofsnake++;
				score++;
			}
			
		}
        // Turn the position of snake press arrow keys
		@Override
		public void keyPressed(KeyEvent e) {
			
			if(e.getKeyCode()==KeyEvent.VK_SPACE) {
		       restart();
			}
			if(e.getKeyCode()==KeyEvent.VK_LEFT && (!right)) {
				left=true;
				right=false;
				up=false;
				down=false;
				 moves++;
			}
			if(e.getKeyCode()==KeyEvent.VK_RIGHT && (!left)) {
				left=false;
				right=true;
				up=false;
				down=false;
				 moves++;
			}if(e.getKeyCode()==KeyEvent.VK_UP && (!down)) {
				left=false;
				right=false;
				up=true;
				down=false;
				 moves++;
			}if(e.getKeyCode()==KeyEvent.VK_DOWN && (!up)) {
				left=false;
				right=false;
				up=false;
				down=true;
				 moves++;
				 }
			}
		
		private void restart() {
			gameOver=false;
			moves=0;
			score=0;
			lengthofsnake=3;
			left=false;
			right=true;
			up=false;
			down=false;
			timer.start();
			repaint();
			
		}
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		
}
		 
		 
	 
		 
		
		 
		 
	 
	 


