package io.github.kuyer.jbase.ui;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UiDemo {
	
	public static boolean isDraging = false;
	public static int xlocation = 0;
	public static int ylocation = 0;
	
	/** 设置图标 **/
	public static Image getIcon(String filename) {
		return Toolkit.getDefaultToolkit().getImage(filename);
	}
	
	/** 关闭窗口 **/
	public static void closeWindow(JFrame frame) {
		int result = JOptionPane.showConfirmDialog(frame, "Are you sure close?", "Tip", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null);
		if(result == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	
	/** 增加窗口事件 **/
	public static void addWindowEvent(JFrame frame) {
		frame.addWindowListener(new WindowListener() {
			@Override
			public void windowOpened(WindowEvent e) {
			}
			@Override
			public void windowClosing(WindowEvent e) {
				closeWindow(frame);
			}
			@Override
			public void windowClosed(WindowEvent e) {
			}
			@Override
			public void windowIconified(WindowEvent e) {
			}
			@Override
			public void windowDeiconified(WindowEvent e) {
			}
			@Override
			public void windowActivated(WindowEvent e) {
			}
			@Override
			public void windowDeactivated(WindowEvent e) {
			}});
	}
	
	/** 移动窗口 **/
	public static void addWindowMove(JFrame frame) {
		frame.addMouseListener(new MouseListener() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
			@Override
			public void mousePressed(MouseEvent e) {
				isDraging = true;
				xlocation = e.getX();
				ylocation = e.getY();
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				isDraging = false;
			}
			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}});
		frame.addMouseMotionListener(new MouseMotionListener() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(isDraging) {
					int x = frame.getLocation().x;
					int y = frame.getLocation().y;
					frame.setLocation(x+e.getX()-xlocation, y+e.getY()-ylocation);
				}
			}
			@Override
			public void mouseMoved(MouseEvent e) {
			}});
	}
	
	/** 增加关闭按钮 **/
	public static void addExitBtn(JFrame frame) {
		JButton exitBtn = new JButton();
		exitBtn.setText("Exit");
		exitBtn.setBounds(new Rectangle(440, 20, 80, 32));
		exitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				closeWindow(frame);
			}});
		frame.add(exitBtn);
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("UI");//设置名称
		frame.setLayout(null);//不设置布局
		frame.setIconImage(getIcon(""));//设置图标
		//frame.setResizable(false);//禁止拉伸或放大操作
		//frame.setUndecorated(true);//不要边框
		frame.setAlwaysOnTop(true);//总是在最前面
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);//最大化
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭窗口，结束运行
		frame.setSize(540, 280);//设置宽高度
		frame.setLocationRelativeTo(null);//居中显示
		frame.setVisible(true);//显示窗体
		
		addWindowEvent(frame);
		addWindowMove(frame);
		
		addExitBtn(frame);
		
		JTextField urlEdit = new JTextField();
		urlEdit.setBounds(new Rectangle(20, 20, 400, 32));
		frame.add(urlEdit);
	}

}
