import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MyMain extends JFrame {
	public static void main(String[] args) {
		/* GUI ���� */
		GUI f = new GUI();
		f.setSize(800, 600);
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setVisible(true);
	}
}

class GUI extends JFrame {
	private MenuBar menuBar;
	private JButton searchButton;
	private JButton convertButton;
	private JButton insertButton;
	private JButton printButton;
	private JTextField searchText;
	private JTextField convertText;
	private JTextField inLoc;
	private JTextField outLoc;
	private JTextField row;
	private JTextField col;		
	@SuppressWarnings("rawtypes")
	private JList list;
	private JScrollPane scrol;
	private String[] result;
	private JLabel condition;
	@SuppressWarnings("rawtypes")
	private DefaultListModel listModel;
	@SuppressWarnings("rawtypes")
	private JList recentList;
	private JScrollPane recentSearch;
	private ArrayList <CSearchItem> AL;
	public GUI() {
		super("��ü������ �� �ǽ� 2016112127 ������");

		initMenuBar();  //�ʵ尪 �ʱ�ȭ
		initButton();
		initTextField();
		initIOlabel();
		initResult();
		initCondition();
		initRecentSearch();
		this.setVisible(true);
	}
	public void initMenuBar()
	{
		//�޴��� - �޴� - ������
		MenuItem menuItem1 = new MenuItem("����");
		MenuItem menuItem2 = new MenuItem("���");
		MenuItem menuItem3 = new MenuItem("�˻�");
		MenuItem menuItem4 = new MenuItem("��ȯ");
		//�޴��� - �޴�
		Menu menu1 = new Menu("����");
		menu1.add(menuItem1);
		menu1.add(menuItem2);
		Menu menu2 = new Menu("����");
		menu2.add(menuItem3);
		menu2.add(menuItem4);
		//�޴���
		menuBar = new MenuBar();
		menuBar.add(menu1);
		menuBar.add(menu2);
		
		this.setMenuBar(menuBar);
	}
	public void initButton()
	{
		//��ư �ʱ�ȭ
		searchButton =new JButton(); 
		convertButton =new JButton();
		insertButton = new JButton();
		printButton = new JButton();
		searchButton.setSize(60, 30); // ��ư�� ������ ���� 
		convertButton.setSize(60, 30); 
		insertButton.setSize(60,30);
		printButton.setSize(120,30);
		
		searchButton.setLocation(350, 100); // ��ư�� ��ġ ����
		convertButton.setLocation(350, 150);
		insertButton.setLocation(350, 200);
		printButton.setLocation(640, 65);
		
		this.add(searchButton);
		this.add(convertButton);
		this.add(insertButton);
		this.add(printButton);
		searchButton.setText("�˻�");
		convertButton.setText("��ȯ");
		insertButton.setText("����");
		printButton.setText("�˻�������");

	}
	public void initTextField()
	{
		//�ؽ�Ʈ�ʵ�
		searchText = new JTextField();
		convertText = new JTextField();
		inLoc = new JTextField();
		outLoc = new JTextField();
		row = new JTextField();
		col = new JTextField();
		
		searchText.setBounds(110,100, 230, 30);  //�ؽ�Ʈ�ʵ� ��ġ�� ũ������
		convertText.setBounds(110,150, 230, 30);
		inLoc.setBounds(30,300, 310, 30);
		outLoc.setBounds(30,370, 310, 30);
		row.setBounds(180,200, 50, 30);
		col.setBounds(280,200, 50, 30);
	
		this.add(searchText);
		this.add(convertText);
		this.add(inLoc);
		this.add(outLoc);
		this.add(row);
		this.add(col);
	}
	public void initIOlabel()
	{
		//���̺�
		JLabel search = new JLabel("�˻��Ҵܾ� : ");
		JLabel convert = new JLabel("��ȯ�Ҵܾ� : ");
		JLabel inLoc = new JLabel("�Է������ϰ��");
		JLabel outLoc = new JLabel("��������ϰ��");
		search.setLocation(30,100);
		search.setSize(150, 30);
		convert.setLocation(30,150);
		convert.setSize(150, 30);
		inLoc.setLocation(30,270);
		inLoc.setSize(150, 30);
		outLoc.setLocation(30,340);
		outLoc.setSize(150, 30);
		this.add(search);
		this.add(convert);
		this.add(inLoc);
		this.add(outLoc);
		
		JLabel colLabel = new JLabel("�� : ");
		JLabel rowLabel = new JLabel("�� : ");
		colLabel.setLocation(150,200);
		colLabel.setSize(100, 30);
		rowLabel.setLocation(250,200);
		rowLabel.setSize(100, 30);
		this.add(colLabel);
		this.add(rowLabel);
	}
	@SuppressWarnings("rawtypes")
	public void initResult()
	{
		//���â
		JLabel resultLabel= new JLabel("�˻����â");
		resultLabel.setBounds(450,70, 100, 30);
		this.add(resultLabel);
		list = new JList();
		scrol= new JScrollPane(list);
		scrol.setLocation(450, 100);
		scrol.setSize(310, 380);
		list.setVisibleRowCount(10);
		this.add(scrol);
	}
	public void initCondition()
	{
		//�˻�������·��̺�
		condition = new JLabel("�˻��������");
		condition.setLocation(450,480);
		condition.setSize(250, 40);
		this.add(condition);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initRecentSearch()
	{
		//�ֱ� �˻�
		listModel=new DefaultListModel();
		recentList=new JList(listModel);	//�ֱ� �˻�� listModel�� �־��ش�.
		JLabel recentLabel=new JLabel("�ֱٰ˻���");
		recentLabel.setBounds(30, 420, 100, 40);
		this.add(recentLabel);
		recentSearch= new JScrollPane(recentList);
		recentSearch.setLocation(30, 460);
		recentSearch.setSize(250, 100);
		this.add(recentSearch);
	}
}