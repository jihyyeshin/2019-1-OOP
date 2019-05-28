import java.awt.*;
import javax.swing.*;
public class JFrameTest {
	public static void main(String[] args) {
		JFrame simpleFrame=new JFrame("Simple Frame");
		simpleFrame.setSize(300, 500);
		simpleFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		simpleFrame.setVisible(true);
		
		JFrame SecondFrame=new MyFrame("Second Frame");
		SecondFrame.setLayout(new FlowLayout());
	}
}
class MyFrame extends JFrame{
	public MyFrame(String text) {
		super(text);
		setSize(300, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		JButton btn_one=new JButton("Button1");
		JButton btn_two=new JButton("Button2");
		JButton btn_three=new JButton("Button3");
		
		add(btn_one);
		add(btn_two);
		add(btn_three);

	}
}
