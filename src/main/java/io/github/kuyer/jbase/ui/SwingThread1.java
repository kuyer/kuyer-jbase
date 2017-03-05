package io.github.kuyer.jbase.ui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;

public class SwingThread1 extends JPanel {

	private static final long serialVersionUID = 2708527353901420259L;

	JProgressBar pbar;

	static int min = 0;
	static int max = 100;
	
	public SwingThread1() {
		pbar = new JProgressBar();
		pbar.setMinimum(min);
		pbar.setMaximum(max);
		add(pbar);
	
		JFrame frame = new JFrame("Progress Bar Example");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setContentPane(this);
		frame.pack();
		frame.setVisible(true);
		frame.setSize(400, 300);
	
		for (int i = min; i <= max; i++) {
			final int percent = i;
			try {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						updateBar(percent);
					}});
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
		}
	}

	public void updateBar(int newValue) {
		pbar.setValue(newValue);
	}

	public static void main(String args[]) {
		new SwingThread1();
	}

}
