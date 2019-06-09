/*
 ���� �ܼ� �޴��� �ڹ� Swing�� �̿��Ͽ� GUI�� �����Ͻÿ�. 
 
�޴��� ���� 1. ���� (����, ���) 2. ���� (�˻�, ��ȯ)
��� �ؽ�Ʈ �ʵ忡�� ���̺��� �̿��� ������ �ʿ�.
�˻� ���� ���¸� ��Ÿ���� ���̺� ����
�Է��� ���� ��� �ؽ�Ʈ �ʵ�, ����� ���ϰ�� �ؽ�Ʈ �ʵ� ����
�˻��� �ܾ� �Է� �ؽ�Ʈ�ʵ�, ��ȯ�� �ܾ� �Է� �ؽ�Ʈ�ʵ� ����
������ �ܾ� ��ġ �Է� �ؽ�Ʈ �ʵ�( �� ��, �� ��)
�˻������ ����Ʈ�� �߰���Ŵ (��ũ�ѹ� ����, 10�� �� �ִ�)
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class GUI extends JFrame {
	private Editor edit;
	
	private MenuBar menuBar;
	private JButton searchButton;
	private JButton replaceButton;
	private JButton insertButton;
	private JButton printButton;
	private JTextField searchText;
	private JTextField replaceText;
	private JTextField iPath;
	private JTextField oPath;
	private JTextField row;
	private JTextField col;		
	private JList list;
	private JScrollPane scroll;
	private String[] results;
	private JLabel resultLabel;
	private ArrayList <String> AL;
	
	public GUI() {
		super("��ü������ �� �ǽ� 2016112127 ������");
		edit=new Editor();
		initMenu();
		initButton();
		initText();
		initIO();
		initResult();
		this.setVisible(true);
	}
	/* ��� �޴� �� initial */
	public void initMenu()
	{
		MenuItem menu1_1 = new MenuItem("����");
		MenuItem menu1_2 = new MenuItem("���");
		MenuItem menu2_1 = new MenuItem("�˻�");
		MenuItem menu2_2 = new MenuItem("��ȯ");
		Menu menu1 = new Menu("����");
		menu1.add(menu1_1);
		menu1.add(menu1_2);
		Menu menu2 = new Menu("����");
		menu2.add(menu2_1);
		menu2.add(menu2_2);
		menuBar = new MenuBar();
		menuBar.add(menu1);
		menuBar.add(menu2);
		
		this.setMenuBar(menuBar);
	}
	/* button initial + add actionlistener */
	public void initButton()
	{
		searchButton =new JButton(); 
		replaceButton =new JButton();
		insertButton = new JButton();
		printButton = new JButton();
		searchButton.setSize(60, 30);
		replaceButton.setSize(60, 30); 
		insertButton.setSize(60,30);
		printButton.setSize(120,30);
		
		searchButton.setLocation(350, 100);
		replaceButton.setLocation(350, 150);
		insertButton.setLocation(350, 200);
		printButton.setLocation(640, 65);
		
		this.add(searchButton);
		this.add(replaceButton);
		this.add(insertButton);
		this.add(printButton);
		searchButton.setText("�˻�");
		replaceButton.setText("��ȯ");
		insertButton.setText("�߰�");
		printButton.setText("�˻�������");
		/*action listener*/
		insertButton.addActionListener(new appendHandler());
		replaceButton.addActionListener(new replaceHandler());
		searchButton.addActionListener(new searchHandler());
		printButton.addActionListener(new printHandler());
	}
	/* text field initial */
	public void initText()
	{
		searchText = new JTextField();
		replaceText = new JTextField();
		iPath = new JTextField();
		oPath = new JTextField();
		row = new JTextField();
		col = new JTextField();
		
		searchText.setBounds(145,100, 195, 30);
		replaceText.setBounds(110,150, 230, 30);
		iPath.setBounds(30,300, 310, 30);
		oPath.setBounds(30,370, 310, 30);
		row.setBounds(180,200, 50, 30);
		col.setBounds(280,200, 50, 30);
	
		this.add(searchText);
		this.add(replaceText);
		this.add(iPath);
		this.add(oPath);
		this.add(row);
		this.add(col);
	}
	/* input, output in text field initialize */
	public void initIO()
	{
		JLabel search = new JLabel("�˻�(����)�� �ܾ� : ");
		JLabel convert = new JLabel("��ȯ�� �ܾ� : ");
		JLabel iPath = new JLabel("�Է��� ���ϰ��");
		JLabel oPath = new JLabel("����� ���ϰ��");
		search.setLocation(30,100);
		search.setSize(150, 30);
		convert.setLocation(30,150);
		convert.setSize(150, 30);
		iPath.setLocation(30,270);
		iPath.setSize(150, 30);
		oPath.setLocation(30,340);
		oPath.setSize(150, 30);
		this.add(search);
		this.add(convert);
		this.add(iPath);
		this.add(oPath);
		
		JLabel colLabel = new JLabel("�� : ");
		JLabel rowLabel = new JLabel("�� : ");
		colLabel.setLocation(150,200);
		colLabel.setSize(100, 30);
		rowLabel.setLocation(250,200);
		rowLabel.setSize(100, 30);
		this.add(colLabel);
		this.add(rowLabel);
	}
	/* ��� â */
	public void initResult()
	{
		JLabel buf= new JLabel("");
		list = new JList();
		scroll= new JScrollPane(list);
		scroll.setLocation(450, 100);
		scroll.setSize(310, 380);
		list.setVisibleRowCount(10);

		resultLabel = new JLabel("�˻��������");
		resultLabel.setLocation(450,480);
		resultLabel.setSize(250, 40);

		this.add(scroll);
		this.add(resultLabel);
		this.add(buf);
	}

	/* ActionListener */
	class appendHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			resultLabel.setText("�˻��������: �������Դϴ�.");
			String resultText;
			/* ��ü exception ����� �� result�� �����ֱ� ���ؼ� */
			resultText=edit.getFile(iPath.getText());
			resultLabel.setText(resultText);
			resultText=edit.append(searchText.getText(), Integer.valueOf(row.getText()),Integer.valueOf(col.getText()));
			resultLabel.setText(resultText);
			/* ����� ���� ��θ� ���� */
			resultText=edit.printToFile(oPath.getText());
			resultLabel.setText(resultText);
		}
	}
	class replaceHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			resultLabel.setText("�˻��������: �������Դϴ�.");
			String resultText;
			/* ��ü exception ����� �� result�� �����ֱ� ���ؼ� */
			resultText=edit.getFile(iPath.getText());
			resultLabel.setText(resultText);
			resultText=edit.replaceWord(searchText.getText(), replaceText.getText());
			resultLabel.setText(resultText);
			/* ����� ���� ��θ� ���� */
			resultText=edit.printToFile(oPath.getText());
			resultLabel.setText(resultText);
		}
	}
	class searchHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			resultLabel.setText("�˻��������: �������Դϴ�.");
			String resultText;
			/* ��ü exception ����� �� result�� �����ֱ� ���ؼ� */
			resultText=edit.getFile(iPath.getText());
			resultLabel.setText(resultText);
			AL=edit.findWord(searchText.getText());
			/* ����� ���� ��θ� ���� */
			resultText=edit.printToFile(oPath.getText());
			resultLabel.setText(resultText);
		}
	}
	class printHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			results=AL.toArray(new String[AL.size()]);
			remove(scroll);
			list = new JList(results);
			scroll= new JScrollPane(list);
			scroll.setLocation(450, 100);
			scroll.setSize(310, 380);
			list.setVisibleRowCount(10);
			add(scroll);
			repaint();
		}
	}
}


