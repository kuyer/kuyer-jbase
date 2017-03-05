package io.github.kuyer.jbase.ui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class SwingThread {
	
	private static int index = 0;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("SwingThread");
		frame.setSize(400, 280);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel label = new JLabel();
		label.setForeground(Color.RED);
		label.setBackground(Color.BLUE);
		JButton button = new JButton();
		button.setText("开始");
		
		frame.setLayout(null);
		frame.add(label);
		frame.add(button);
		
		button.setBounds(10, 10, 280, 40);
		label.setBounds(10, 60, 280, 40);
		
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new Thread(new Runnable() {
					@Override
					public void run() {
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						//label.setText("hello"+(index++));
						/*SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								label.setText("hello"+(index++));
							}});*/
						try {
							SwingUtilities.invokeAndWait(new Runnable() {
								@Override
								public void run() {
									label.setText("hello"+(index++));
								}});
						} catch (InvocationTargetException | InterruptedException e) {
							e.printStackTrace();
						}
					}}).start();
			}});
		
		frame.setVisible(true);
	}

}
