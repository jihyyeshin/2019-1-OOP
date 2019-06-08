import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class Main {

	static GUIEdit Gui;
	
	public static void main(String[] args)throws IOException,FileNotFoundException {
		Gui=new GUIEdit(); // GUIEdit 생성!
	}
	
	static class Editor implements Runnable // Editor클래스! Runnable인터페이스를 이용하여 스레드를 구현
	{	
		private String search;	 //검색할 단어를 저장할 스트링
		private String filename; //경로를 저장할 스트링
		
		public void run() //단어검색을 수행하고 결과를 GUI에 반영
		{
			try {
				Gui.condition.setText("검색진행상태: 검색중입니다.");
				Gui.AL=searchWord(); //검색결과들을 저장한다.
				ArrayList<String>temp =new ArrayList<String>();	//JList에 결과를 넣기위해 만든 ArrayList
				
				for(int i=0; i<Gui.AL.size();i++)
				{
					temp.add(Gui.AL.get(i).getLineNo()+"줄의"+Gui.AL.get(i).getIndexNo()+"번째");
				}
				Gui.result=temp.toArray(new String[temp.size()] );	//ArrayList->Array
				Gui.setResult();	// 결과창 출력
				Gui.setRecent();	// 최근 검색결과 출력
				Gui.condition.setText("검색진행상태: 완료되었습니다.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public Editor() //생성자
		{
			search="";
			filename="";
		}
		public Editor(String s,String f)//검색할단어와 검색할 주소를 받는 생성자
		{
			search=s;
			filename=f;
		}
		public void replaceWord(String replace,String output)throws IOException 
		{ //검색한 단어를 변환하여 원하는 경로에 출력!
			search=" "+search+" ";
			replace=" "+replace+" ";
			int linecount=0;
			boolean firstcheck=false; //첫번째줄 체크하기위함
	    	String line ="";
	    	String newline;
	    	
			File f=new File(filename);
	        MyBufferedReader buf = new MyBufferedReader(f);
			BufferedWriter out;
			out=new BufferedWriter(new FileWriter(output));
			
	        while(true)
	        {
	        	newline = buf.readLine();//1줄을 읽음
	        	if(newline==null)	// 더 이상 읽을 줄이 없을때까지~
	        		break;
	        	else
	        	{
	            	line=line+newline+"$";	// 문장끝에$를 추가함
	        		if(linecount==0)
	        			line=" "+line;
	        		linecount++;
	                newline = buf.readLine(); //1줄을 읽고
	                if(newline==null)	//더 이상 읽을 줄이 없으면~
	                {
	                	line=line.replaceAll("$", "");	//문장끝에 달았던$를 지워 \n을 포함한 단어 검색가능
	                	line=line.replaceAll(search,replace);
	                    while(line.length()>128)
	                    {
	                    	if(firstcheck==false) //첫라인일경우 앞의 띄어쓰기 제거한후 파일에 입력
	                    	{
	                    		line=line.substring(1);
	                      		firstcheck=true;
	                    	}
	                    	out.write(line.substring(0, 128));	//128자씩 끊어쓰기
	                    	out.newLine();						//128자 이후 줄바꿈
	                    	line=line.substring(128);			//파일에 쓴 글자들을 제거
	                    }
	                	out.write(line);	//남은 128자 미만의 단어들을 파일에 입력
	                	out.newLine();		
	                	line="";
	                }
	                else	//2줄을 받은경우
	                {
	                	linecount++;
	                   	line=line+newline+"$";
	                	line=line.replaceAll("$", "");
	                	line=line.replaceAll(search,replace);
	                }
	                while(line.length()>128)
	                {
	                	if(firstcheck==false)
	                	{
	                		line=line.substring(1);
	                		firstcheck=true;
	                	}
	                	out.write(line.substring(0, 128));
	                	out.newLine();
	                	line=line.substring(128);
	                }	
	        	}
	        }
	        buf.close();
	        out.close();
		}
		public ArrayList <CSearchItem> searchWord()throws IOException{
			//검색할 단어의 위치를 찾아서 반환
			boolean flag=true; // 마지막 line인지 확인해줄 변수
			int lineNo=1;
			int i,j;
			ArrayList <CSearchItem> AL=new ArrayList <CSearchItem> ();	//단어정보를 저장할 ArrayList
			FileReader fr = new FileReader(filename);
			BufferedReader Br = new BufferedReader(fr);  
			
			char[] pattern = search.toCharArray();  // 문자형 배열에 저장해줌
			char[] currentLine, nextLine = null; 	 // 현재 줄과 다음 줄을 읽을 배열
			String line = null; 					 // buffer 로 부터 1 line을 저장할 변수
			int substringLength = pattern.length;    // 찾을 단어의 길이를 저장
			
			line = Br.readLine(); 					 // textfile의 1 line 을 읽음
			currentLine = line.toCharArray();
			while(flag)
			{
				if((line=Br.readLine())==null) // 만약 마지막줄일 경우~
					flag = false;
				
				else{
					nextLine = line.toCharArray();  // 다음 line read
					currentLine = appendSubArray(currentLine, nextLine, substringLength); 
					// nextline의 일부분을 붙인 배열을 생성
				}
				
				for(i=0; i<currentLine.length-pattern.length+1; ++i)  // Pattern matching 부분
				{
					for(j=0; j< pattern.length; ++j)
						if(pattern[j]!=currentLine[j+i])
							break;
					
					if(j==pattern.length) // 1차 패턴매칭 완료
					{
						if(i==0){ // 첫 string과 매칭될 경우 스페이스 위치 계산 필요 X
	         				CSearchItem a=new CSearchItem();	//객체생성
	        				a.setIndexNo(i+1);					//인덱스를 set
	        				a.setLineNo(lineNo);				//라인을 set
							AL.add(a);							//ArrayList에 저장
						}
							
						else if(currentLine[i-1]==' ') // 왼쪽 공백조사
						{
							if(i+j < currentLine.length && currentLine[i+j]==' ' )  
							{// 우측공백조사     
	            				CSearchItem a=new CSearchItem();	//객체생성
	            				a.setIndexNo(i+1);					//인덱스를 set
	            				a.setLineNo(lineNo);				//라인을 set
								AL.add(a);							//ArrayList에 저장
							}
							else if(i+j >= currentLine.length && flag == false)          // 마지막 줄, 마지막 단어일 경우
							{
	            				CSearchItem a=new CSearchItem();	//객체생성
	            				a.setIndexNo(i+1);					//인덱스를 set
	            				a.setLineNo(lineNo);				//라인을 set
								AL.add(a);							//ArrayList에 저장
							}
						}
					}
				}
				lineNo++;
				currentLine = nextLine;
			}
			fr.close();
			return AL;
		}
		public char[] appendSubArray(char[] current, char[] next, int length) 
		{
			char[] returnArr = new char[current.length + next.length];
			int i;
			for(i=0; i<current.length; ++i)
			{
				returnArr[i] = current[i];        // 앞부분을 currentLine 내용으로 먼저 채움
			}
			if(next.length < length)			 // 찾을 단어의 길이가 nextLine보다 길은 경우
				for(; i<current.length + next.length; ++i)
				{
					returnArr[i] = next[i-current.length]; // next 의 0번 인덱스부터 쭉 returnArr에 붙여 넣음
				}
			for(; i<current.length+length; ++i)   // 찾을 단어가 nextLine보다 길지 않으면, 찾을 단어길이 만큼만 붙임
				returnArr[i] = next[i-current.length];
			
			return returnArr;
		}
		public void printWord(ArrayList <CSearchItem> result,String output) throws IOException
		{
			if(result.size()!=0)	//결과로 검색된 만큼
			{
				BufferedWriter out;
				out=new BufferedWriter(new FileWriter(output));//덮어쓰기
				out.write(search+"의 검색결과는 총"+result.size()+"개입니다");	//검색결과를 파일에 써준다.
				out.newLine();
				for(int i=0;i<result.size();i++)
				{
					out.write(result.get(i).getLineNo()+"줄의"+result.get(i).getIndexNo()+"번째");
					out.newLine();
				}
				out.close();
			}
		}
		public void insertWord(String word,int row,int col,String output) throws IOException
		{
			File f=new File(filename);
	        MyBufferedReader buf = new MyBufferedReader(f);
			BufferedWriter out=new BufferedWriter(new FileWriter(output));
			int linecount=1;
			String temp;		//파일의 한줄을 불러올 스트링
			String allWord="";	//파일의 줄들을 이어붙일 스트링
			while(true)
			{
				temp=buf.readLine();
				if(temp==null)//더 이상 읽을 줄이 없으면~
					break;
				else if(linecount==row)	//원하는 행이면
				{
					allWord+=temp.substring(0, col-1)+word+temp.substring(col-1);//줄의 해당위치에 삽입
					linecount++;	//줄수 증가
				}
				else	//원하는 행이 아니면~
				{
					allWord+=temp;	//allWord에 그대로 붙혀줌
					linecount++;	//줄수 증가
				}
				if(allWord.length()>128)	//allWord의 글자가 한줄이 넘어가면
				{
					out.write(allWord.substring(0, 128));	//한줄을 파일에 써주고
					out.newLine();							//줄바꿈
					allWord=allWord.substring(128);			//써준만큼 allWord에서 제거
				}
			}
			out.write(allWord);	//남은 글자들 파일에 써줌
			buf.close();
			out.close();
		}
	}
	@SuppressWarnings("serial")
	
	static class GUIEdit extends JFrame // GUIEdot클래스!
	{
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
		private Editor edit;
		private ArrayList <CSearchItem> AL;
		
		public GUIEdit() //생성자	
		{
			//생성자, JFrame을 띄워주고 사용하는 필드값들을 초기화하는 메소드들 호출
			super("객체01반_박상혁_2013111997");
			this.setSize(800,700); //창 크기 설정
			this.setLayout(null);
	
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
			//이벤트등록
			menuItem1.addActionListener(new inputLocHandler());
			menuItem2.addActionListener(new outputLocHandler());
			menuItem3.addActionListener(new searchHandler());
			menuItem4.addActionListener(new convertHandler());
			
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
			//이벤트처리
			searchButton.addActionListener(new searchHandler());
			convertButton.addActionListener(new convertHandler());
			insertButton.addActionListener(new insertHandler());
			printButton.addActionListener(new printHandler());
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
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public void setResult()
		{
			//걸과를 리스트로 다시 출력해준다
			this.remove(scrol);
			list= new JList(result);
			list.setVisibleRowCount(10);	//한창에 10개까지
			scrol = new JScrollPane(list);
			scrol.setLocation(100, 290);
			scrol.setSize(310, 200);
			add(scrol);
			this.repaint();
		}
		@SuppressWarnings("unchecked")
		public void setRecent()
		{
			//최근검색 결과를 set해준다
			listModel.addElement(searchText.getText());
			this.repaint();
		}
		class searchHandler implements ActionListener	//검색 핸들러
		{
			public void actionPerformed(ActionEvent e) {
				//검색진행상태변경, 검색할주소 전송, 검색할단어전송,
				if(searchText.getText().equals(""))//검색할단어없이 검색을 누를때
				{
					//아무일도하지않음
				}
				else
				{
					Editor edit = new Editor(searchText.getText(),inLoc.getText());
					Thread t = new Thread(edit);	//스레드구현
					t.start();	//스레드를 통해 검색, 사용자는 검색중에도 다른 작업 가능
				}
			}
		}
		class convertHandler implements ActionListener	//변환 핸들러
		{
			public void actionPerformed(ActionEvent e) {
				edit=new Editor(searchText.getText(),inLoc.getText());
				try {
					edit.replaceWord(convertText.getText(), outLoc.getText());	//edit의 replaceWord()사용
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				condition.setText("검색진행상태: 완료되었습니다.");
			}
		}
		class insertHandler implements ActionListener	//삽입 핸들러
		{
			public void actionPerformed(ActionEvent e) {
				condition.setText("검색진행상태: 진행중입니다.");
				edit=new Editor(searchText.getText(),inLoc.getText());
				try {
					edit.insertWord(searchText.getText(), Integer.valueOf(row.getText()),
							Integer.valueOf(col.getText()), outLoc.getText());	
					//edit의 insertWord()사용
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				condition.setText("검색진행상태: 완료되었습니다.");
			}
		}
		class printHandler implements ActionListener	//출력 핸들러
		{
			public void actionPerformed(ActionEvent e) {
				try {
					edit=new Editor(searchText.getText(),inLoc.getText());
					edit.printWord(AL, outLoc.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				condition.setText("검색진행상태: 출력완료되었습니다.");
			}
		}
		class inputLocHandler implements ActionListener //메뉴바-열기 핸들러
		{
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileLoc = new JFileChooser();
				fileLoc.setMultiSelectionEnabled(false);
				if(fileLoc.showOpenDialog(fileLoc) == JFileChooser.APPROVE_OPTION)
				{
					String temp=fileLoc.getSelectedFile().toString();
					inLoc.setText(temp);
				}
			}
		}
		class outputLocHandler implements ActionListener	//메뉴바-출력 핸들러
		{
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileLoc = new JFileChooser();
				fileLoc.setMultiSelectionEnabled(false);
				if(fileLoc.showOpenDialog(fileLoc) == JFileChooser.APPROVE_OPTION)
				{
					String temp=fileLoc.getSelectedFile().toString();
					outLoc.setText(temp);
				}
			}
		}
	}
}
class CSearchItem
{
	private int lineNo;
	private int indexNo;
	public int getIndexNo()
	{
		return indexNo;
	}
	public int getLineNo()
	{
		return lineNo;
	}
	public void setIndexNo(int indexNo)
	{
		this.indexNo=indexNo;
	}
	public void setLineNo(int lineNo)
	{
		this.lineNo=lineNo;
	}
}
class MyBufferedReader extends BufferedReader
{	
	private File file;
	public MyBufferedReader(File file) throws FileNotFoundException
	{
		super(new FileReader(file));
		this.file = file;
	}
	public MyBufferedReader(Reader reader) 
	{
		super(reader);
	}
	public String readLine() throws IOException
	{
		String s=null;
		try	//try catch사용
		{
			s = super.readLine();
	    }
		catch(Exception e)
		{
			e.printStackTrace();
		}
		if(s==null)
		{
			this.resetToFirst();
			return null; 
		}
			return s;
	}
	public MyBufferedReader resetToFirst() throws IOException
	{  
		MyBufferedReader newbuf = new MyBufferedReader(file);
		return newbuf;
	}
}
