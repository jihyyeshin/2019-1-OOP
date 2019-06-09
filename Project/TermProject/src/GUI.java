/*
 기존 콘솔 메뉴를 자바 Swing을 이용하여 GUI로 구현하시오. 
 
메뉴바 구현 1. 파일 (열기, 출력) 2. 편집 (검색, 변환)
모든 텍스트 필드에는 레이블을 이용한 설명이 필요.
검색 진행 상태를 나타내는 레이블 존재
입력할 파일 경로 텍스트 필드, 출력할 파일경로 텍스트 필드 존재
검색할 단어 입력 텍스트필드, 변환할 단어 입력 텍스트필드 존재
삽입할 단어 위치 입력 텍스트 필드( 몇 행, 몇 열)
검색결과는 리스트에 추가시킴 (스크롤바 존재, 10개 줄 최대)
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
		super("객체지향언어 및 실습 2016112127 신지혜");
		edit=new Editor();
		initMenu();
		initButton();
		initText();
		initIO();
		initResult();
		this.setVisible(true);
	}
	/* 상단 메뉴 바 initial */
	public void initMenu()
	{
		MenuItem menu1_1 = new MenuItem("열기");
		MenuItem menu1_2 = new MenuItem("출력");
		MenuItem menu2_1 = new MenuItem("검색");
		MenuItem menu2_2 = new MenuItem("변환");
		Menu menu1 = new Menu("파일");
		menu1.add(menu1_1);
		menu1.add(menu1_2);
		Menu menu2 = new Menu("편집");
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
		searchButton.setText("검색");
		replaceButton.setText("변환");
		insertButton.setText("추가");
		printButton.setText("검색결과출력");
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
		JLabel search = new JLabel("검색(삽입)할 단어 : ");
		JLabel convert = new JLabel("변환할 단어 : ");
		JLabel iPath = new JLabel("입력할 파일경로");
		JLabel oPath = new JLabel("출력할 파일경로");
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
		
		JLabel colLabel = new JLabel("행 : ");
		JLabel rowLabel = new JLabel("열 : ");
		colLabel.setLocation(150,200);
		colLabel.setSize(100, 30);
		rowLabel.setLocation(250,200);
		rowLabel.setSize(100, 30);
		this.add(colLabel);
		this.add(rowLabel);
	}
	/* 결과 창 */
	public void initResult()
	{
		JLabel buf= new JLabel("");
		list = new JList();
		scroll= new JScrollPane(list);
		scroll.setLocation(450, 100);
		scroll.setSize(310, 380);
		list.setVisibleRowCount(10);

		resultLabel = new JLabel("검색진행상태");
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
			resultLabel.setText("검색진행상태: 진행중입니다.");
			String resultText;
			/* 전체 exception 결과를 다 result에 보여주기 위해서 */
			resultText=edit.getFile(iPath.getText());
			resultLabel.setText(resultText);
			resultText=edit.append(searchText.getText(), Integer.valueOf(row.getText()),Integer.valueOf(col.getText()));
			resultLabel.setText(resultText);
			/* 출력할 파일 경로를 지정 */
			resultText=edit.printToFile(oPath.getText());
			resultLabel.setText(resultText);
		}
	}
	class replaceHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			resultLabel.setText("검색진행상태: 진행중입니다.");
			String resultText;
			/* 전체 exception 결과를 다 result에 보여주기 위해서 */
			resultText=edit.getFile(iPath.getText());
			resultLabel.setText(resultText);
			resultText=edit.replaceWord(searchText.getText(), replaceText.getText());
			resultLabel.setText(resultText);
			/* 출력할 파일 경로를 지정 */
			resultText=edit.printToFile(oPath.getText());
			resultLabel.setText(resultText);
		}
	}
	class searchHandler implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			resultLabel.setText("검색진행상태: 진행중입니다.");
			String resultText;
			/* 전체 exception 결과를 다 result에 보여주기 위해서 */
			resultText=edit.getFile(iPath.getText());
			resultLabel.setText(resultText);
			AL=edit.findWord(searchText.getText());
			/* 출력할 파일 경로를 지정 */
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


