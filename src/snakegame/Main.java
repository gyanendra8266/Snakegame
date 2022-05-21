package snakegame;
import java.awt.Color;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame f=new JFrame("Snake Game");
		f.setBounds(10,10,905,700);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		GamePannel panel=new GamePannel();
		panel.setBackground(Color.DARK_GRAY);
		f.add(panel);
		
		f.setVisible(true);
			

	}

}
