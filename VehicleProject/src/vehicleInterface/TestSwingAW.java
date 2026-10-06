/*
 * TestSwingAW.java 3 May 2022
 *
 *
 * �Copyright 2022 your_name your_surname Joan S�culi  
 * Email: jseculi@escoladeltreball.org
 *
 * This is free software, licensed under the GNU General Public License v3.
 * See http://www.gnu.org/licenses/gpl.html for more information.
 */
package vehicleInterface;

import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;

public class TestSwingAW extends JFrame{

	private JFrame frame;
	private JButton btnMoveX;
	private DrawCanvas canvas;
	private int x = 1;
	private int y = 1;
	private int petrol = 100;
	private JButton btnMoveY;
	private JLabel lblPetrol2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TestSwingAW window = new TestSwingAW();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TestSwingAW() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 641, 485);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		canvas = new DrawCanvas();
		canvas.setBounds(149, 31, 440, 387);
		frame.getContentPane().add(canvas);
		
		
		frame.getContentPane().add(canvas);
		canvas.setVisible(true);
	
		
		
		btnMoveX = new JButton("Move X");
		btnMoveX.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (petrol >= 10) {
					petrol -= 10;
					lblPetrol2.setText(petrol + "");
					x += 10;
					canvas.repaint();
				}
				
			}
		});
		btnMoveX.setBounds(30, 43, 85, 21);
		frame.getContentPane().add(btnMoveX);
		
		btnMoveY = new JButton("Move Y");
		btnMoveY.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (petrol >= 10) {
					petrol -= 10;
					lblPetrol2.setText(petrol + "");
					y += 10;
					canvas.repaint();
				}
			}
		});
		btnMoveY.setBounds(30, 89, 85, 21);
		frame.getContentPane().add(btnMoveY);
		
		JLabel lblPetrol = new JLabel("Petrol:");
		lblPetrol.setBounds(30, 346, 45, 13);
		frame.getContentPane().add(lblPetrol);
		
		lblPetrol2 = new JLabel("100");
		lblPetrol2.setBounds(89, 346, 45, 13);
		frame.getContentPane().add(lblPetrol2);
		
		
	}

	class DrawCanvas extends JPanel {	
		
		public Dimension getPreferredSize() {
			return new Dimension(500, 500);
		}
		
		@Override
		public void paintComponent(Graphics g) {
			 super.paintComponent(g);
			setBackground(Color.GREEN);
			g.setColor(Color.YELLOW);
			g.fillRect(x, y, 10, 10);
			
			canvas.requestFocus();
			repaint();
		}
	}
}
