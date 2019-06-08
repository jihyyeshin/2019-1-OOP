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
		/* GUI 지정 */
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
		super("객체지향언어 및 실습 2016112127 신지혜");

		initMenuBar();  //필드값 초기화
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
		//메뉴바 - 메뉴 - 아이템
		MenuItem menuItem1 = new MenuItem("열기");
		MenuItem menuItem2 = new MenuItem("출력");
		MenuItem menuItem3 = new MenuItem("검색");
		MenuItem menuItem4 = new MenuItem("변환");
		//메뉴바 - 메뉴
		Menu menu1 = new Menu("파일");
		menu1.add(menuItem1);
		menu1.add(menuItem2);
		Menu menu2 = new Menu("편집");
		menu2.add(menuItem3);
		menu2.add(menuItem4);
		//메뉴바
		menuBar = new MenuBar();
		menuBar.add(menu1);
		menuBar.add(menu2);
		
		this.setMenuBar(menuBar);
	}
	public void initButton()
	{
		//버튼 초기화
		searchButton =new JButton(); 
		convertButton =new JButton();
		insertButton = new JButton();
		printButton = new JButton();
		searchButton.setSize(60, 30); // 버튼의 사이즈 조절 
		convertButton.setSize(60, 30); 
		insertButton.setSize(60,30);
		printButton.setSize(120,30);
		
		searchButton.setLocation(350, 100); // 버튼의 위치 조절
		convertButton.setLocation(350, 150);
		insertButton.setLocation(350, 200);
		printButton.setLocation(640, 65);
		
		this.add(searchButton);
		this.add(convertButton);
		this.add(insertButton);
		this.add(printButton);
		searchButton.setText("검색");
		convertButton.setText("변환");
		insertButton.setText("삽입");
		printButton.setText("검색결과출력");

	}
	public void initTextField()
	{
		//텍스트필드
		searchText = new JTextField();
		convertText = new JTextField();
		inLoc = new JTextField();
		outLoc = new JTextField();
		row = new JTextField();
		col = new JTextField();
		
		searchText.setBounds(110,100, 230, 30);  //텍스트필드 위치와 크기조정
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
		//레이블
		JLabel search = new JLabel("검색할단어 : ");
		JLabel convert = new JLabel("변환할단어 : ");
		JLabel inLoc = new JLabel("입력할파일경로");
		JLabel outLoc = new JLabel("출력할파일경로");
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
		
		JLabel colLabel = new JLabel("행 : ");
		JLabel rowLabel = new JLabel("열 : ");
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
		//결과창
		JLabel resultLabel= new JLabel("검색결과창");
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
		//검색진행상태레이블
		condition = new JLabel("검색진행상태");
		condition.setLocation(450,480);
		condition.setSize(250, 40);
		this.add(condition);
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void initRecentSearch()
	{
		//최근 검색
		listModel=new DefaultListModel();
		recentList=new JList(listModel);	//최근 검색어에 listModel을 넣어준다.
		JLabel recentLabel=new JLabel("최근검색어");
		recentLabel.setBounds(30, 420, 100, 40);
		this.add(recentLabel);
		recentSearch= new JScrollPane(recentList);
		recentSearch.setLocation(30, 460);
		recentSearch.setSize(250, 100);
		this.add(recentSearch);
	}
}