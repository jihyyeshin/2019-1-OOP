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
		Gui=new GUIEdit(); // GUIEdit ����!
	}
	
	static class Editor implements Runnable // EditorŬ����! Runnable�������̽��� �̿��Ͽ� �����带 ����
	{	
		private String search;	 //�˻��� �ܾ ������ ��Ʈ��
		private String filename; //��θ� ������ ��Ʈ��
		
		public void run() //�ܾ�˻��� �����ϰ� ����� GUI�� �ݿ�
		{
			try {
				Gui.condition.setText("�˻��������: �˻����Դϴ�.");
				Gui.AL=searchWord(); //�˻�������� �����Ѵ�.
				ArrayList<String>temp =new ArrayList<String>();	//JList�� ����� �ֱ����� ���� ArrayList
				
				for(int i=0; i<Gui.AL.size();i++)
				{
					temp.add(Gui.AL.get(i).getLineNo()+"����"+Gui.AL.get(i).getIndexNo()+"��°");
				}
				Gui.result=temp.toArray(new String[temp.size()] );	//ArrayList->Array
				Gui.setResult();	// ���â ���
				Gui.setRecent();	// �ֱ� �˻���� ���
				Gui.condition.setText("�˻��������: �Ϸ�Ǿ����ϴ�.");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public Editor() //������
		{
			search="";
			filename="";
		}
		public Editor(String s,String f)//�˻��Ҵܾ�� �˻��� �ּҸ� �޴� ������
		{
			search=s;
			filename=f;
		}
		public void replaceWord(String replace,String output)throws IOException 
		{ //�˻��� �ܾ ��ȯ�Ͽ� ���ϴ� ��ο� ���!
			search=" "+search+" ";
			replace=" "+replace+" ";
			int linecount=0;
			boolean firstcheck=false; //ù��°�� üũ�ϱ�����
	    	String line ="";
	    	String newline;
	    	
			File f=new File(filename);
	        MyBufferedReader buf = new MyBufferedReader(f);
			BufferedWriter out;
			out=new BufferedWriter(new FileWriter(output));
			
	        while(true)
	        {
	        	newline = buf.readLine();//1���� ����
	        	if(newline==null)	// �� �̻� ���� ���� ����������~
	        		break;
	        	else
	        	{
	            	line=line+newline+"$";	// ���峡��$�� �߰���
	        		if(linecount==0)
	        			line=" "+line;
	        		linecount++;
	                newline = buf.readLine(); //1���� �а�
	                if(newline==null)	//�� �̻� ���� ���� ������~
	                {
	                	line=line.replaceAll("$", "");	//���峡�� �޾Ҵ�$�� ���� \n�� ������ �ܾ� �˻�����
	                	line=line.replaceAll(search,replace);
	                    while(line.length()>128)
	                    {
	                    	if(firstcheck==false) //ù�����ϰ�� ���� ���� �������� ���Ͽ� �Է�
	                    	{
	                    		line=line.substring(1);
	                      		firstcheck=true;
	                    	}
	                    	out.write(line.substring(0, 128));	//128�ھ� �����
	                    	out.newLine();						//128�� ���� �ٹٲ�
	                    	line=line.substring(128);			//���Ͽ� �� ���ڵ��� ����
	                    }
	                	out.write(line);	//���� 128�� �̸��� �ܾ���� ���Ͽ� �Է�
	                	out.newLine();		
	                	line="";
	                }
	                else	//2���� �������
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
			//�˻��� �ܾ��� ��ġ�� ã�Ƽ� ��ȯ
			boolean flag=true; // ������ line���� Ȯ������ ����
			int lineNo=1;
			int i,j;
			ArrayList <CSearchItem> AL=new ArrayList <CSearchItem> ();	//�ܾ������� ������ ArrayList
			FileReader fr = new FileReader(filename);
			BufferedReader Br = new BufferedReader(fr);  
			
			char[] pattern = search.toCharArray();  // ������ �迭�� ��������
			char[] currentLine, nextLine = null; 	 // ���� �ٰ� ���� ���� ���� �迭
			String line = null; 					 // buffer �� ���� 1 line�� ������ ����
			int substringLength = pattern.length;    // ã�� �ܾ��� ���̸� ����
			
			line = Br.readLine(); 					 // textfile�� 1 line �� ����
			currentLine = line.toCharArray();
			while(flag)
			{
				if((line=Br.readLine())==null) // ���� ���������� ���~
					flag = false;
				
				else{
					nextLine = line.toCharArray();  // ���� line read
					currentLine = appendSubArray(currentLine, nextLine, substringLength); 
					// nextline�� �Ϻκ��� ���� �迭�� ����
				}
				
				for(i=0; i<currentLine.length-pattern.length+1; ++i)  // Pattern matching �κ�
				{
					for(j=0; j< pattern.length; ++j)
						if(pattern[j]!=currentLine[j+i])
							break;
					
					if(j==pattern.length) // 1�� ���ϸ�Ī �Ϸ�
					{
						if(i==0){ // ù string�� ��Ī�� ��� �����̽� ��ġ ��� �ʿ� X
	         				CSearchItem a=new CSearchItem();	//��ü����
	        				a.setIndexNo(i+1);					//�ε����� set
	        				a.setLineNo(lineNo);				//������ set
							AL.add(a);							//ArrayList�� ����
						}
							
						else if(currentLine[i-1]==' ') // ���� ��������
						{
							if(i+j < currentLine.length && currentLine[i+j]==' ' )  
							{// ������������     
	            				CSearchItem a=new CSearchItem();	//��ü����
	            				a.setIndexNo(i+1);					//�ε����� set
	            				a.setLineNo(lineNo);				//������ set
								AL.add(a);							//ArrayList�� ����
							}
							else if(i+j >= currentLine.length && flag == false)          // ������ ��, ������ �ܾ��� ���
							{
	            				CSearchItem a=new CSearchItem();	//��ü����
	            				a.setIndexNo(i+1);					//�ε����� set
	            				a.setLineNo(lineNo);				//������ set
								AL.add(a);							//ArrayList�� ����
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
				returnArr[i] = current[i];        // �պκ��� currentLine �������� ���� ä��
			}
			if(next.length < length)			 // ã�� �ܾ��� ���̰� nextLine���� ���� ���
				for(; i<current.length + next.length; ++i)
				{
					returnArr[i] = next[i-current.length]; // next �� 0�� �ε������� �� returnArr�� �ٿ� ����
				}
			for(; i<current.length+length; ++i)   // ã�� �ܾ nextLine���� ���� ������, ã�� �ܾ���� ��ŭ�� ����
				returnArr[i] = next[i-current.length];
			
			return returnArr;
		}
		public void printWord(ArrayList <CSearchItem> result,String output) throws IOException
		{
			if(result.size()!=0)	//����� �˻��� ��ŭ
			{
				BufferedWriter out;
				out=new BufferedWriter(new FileWriter(output));//�����
				out.write(search+"�� �˻������ ��"+result.size()+"���Դϴ�");	//�˻������ ���Ͽ� ���ش�.
				out.newLine();
				for(int i=0;i<result.size();i++)
				{
					out.write(result.get(i).getLineNo()+"����"+result.get(i).getIndexNo()+"��°");
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
			String temp;		//������ ������ �ҷ��� ��Ʈ��
			String allWord="";	//������ �ٵ��� �̾���� ��Ʈ��
			while(true)
			{
				temp=buf.readLine();
				if(temp==null)//�� �̻� ���� ���� ������~
					break;
				else if(linecount==row)	//���ϴ� ���̸�
				{
					allWord+=temp.substring(0, col-1)+word+temp.substring(col-1);//���� �ش���ġ�� ����
					linecount++;	//�ټ� ����
				}
				else	//���ϴ� ���� �ƴϸ�~
				{
					allWord+=temp;	//allWord�� �״�� ������
					linecount++;	//�ټ� ����
				}
				if(allWord.length()>128)	//allWord�� ���ڰ� ������ �Ѿ��
				{
					out.write(allWord.substring(0, 128));	//������ ���Ͽ� ���ְ�
					out.newLine();							//�ٹٲ�
					allWord=allWord.substring(128);			//���ظ�ŭ allWord���� ����
				}
			}
			out.write(allWord);	//���� ���ڵ� ���Ͽ� ����
			buf.close();
			out.close();
		}
	}
	@SuppressWarnings("serial")
	
	static class GUIEdit extends JFrame // GUIEdotŬ����!
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
		
		public GUIEdit() //������	
		{
			//������, JFrame�� ����ְ� ����ϴ� �ʵ尪���� �ʱ�ȭ�ϴ� �޼ҵ�� ȣ��
			super("��ü01��_�ڻ���_2013111997");
			this.setSize(800,700); //â ũ�� ����
			this.setLayout(null);
	
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
			//�̺�Ʈ���
			menuItem1.addActionListener(new inputLocHandler());
			menuItem2.addActionListener(new outputLocHandler());
			menuItem3.addActionListener(new searchHandler());
			menuItem4.addActionListener(new convertHandler());
			
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
			//�̺�Ʈó��
			searchButton.addActionListener(new searchHandler());
			convertButton.addActionListener(new convertHandler());
			insertButton.addActionListener(new insertHandler());
			printButton.addActionListener(new printHandler());
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
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public void setResult()
		{
			//�ɰ��� ����Ʈ�� �ٽ� ������ش�
			this.remove(scrol);
			list= new JList(result);
			list.setVisibleRowCount(10);	//��â�� 10������
			scrol = new JScrollPane(list);
			scrol.setLocation(100, 290);
			scrol.setSize(310, 200);
			add(scrol);
			this.repaint();
		}
		@SuppressWarnings("unchecked")
		public void setRecent()
		{
			//�ֱٰ˻� ����� set���ش�
			listModel.addElement(searchText.getText());
			this.repaint();
		}
		class searchHandler implements ActionListener	//�˻� �ڵ鷯
		{
			public void actionPerformed(ActionEvent e) {
				//�˻�������º���, �˻����ּ� ����, �˻��Ҵܾ�����,
				if(searchText.getText().equals(""))//�˻��Ҵܾ���� �˻��� ������
				{
					//�ƹ��ϵ���������
				}
				else
				{
					Editor edit = new Editor(searchText.getText(),inLoc.getText());
					Thread t = new Thread(edit);	//�����屸��
					t.start();	//�����带 ���� �˻�, ����ڴ� �˻��߿��� �ٸ� �۾� ����
				}
			}
		}
		class convertHandler implements ActionListener	//��ȯ �ڵ鷯
		{
			public void actionPerformed(ActionEvent e) {
				edit=new Editor(searchText.getText(),inLoc.getText());
				try {
					edit.replaceWord(convertText.getText(), outLoc.getText());	//edit�� replaceWord()���
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				condition.setText("�˻��������: �Ϸ�Ǿ����ϴ�.");
			}
		}
		class insertHandler implements ActionListener	//���� �ڵ鷯
		{
			public void actionPerformed(ActionEvent e) {
				condition.setText("�˻��������: �������Դϴ�.");
				edit=new Editor(searchText.getText(),inLoc.getText());
				try {
					edit.insertWord(searchText.getText(), Integer.valueOf(row.getText()),
							Integer.valueOf(col.getText()), outLoc.getText());	
					//edit�� insertWord()���
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				condition.setText("�˻��������: �Ϸ�Ǿ����ϴ�.");
			}
		}
		class printHandler implements ActionListener	//��� �ڵ鷯
		{
			public void actionPerformed(ActionEvent e) {
				try {
					edit=new Editor(searchText.getText(),inLoc.getText());
					edit.printWord(AL, outLoc.getText());
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				condition.setText("�˻��������: ��¿Ϸ�Ǿ����ϴ�.");
			}
		}
		class inputLocHandler implements ActionListener //�޴���-���� �ڵ鷯
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
		class outputLocHandler implements ActionListener	//�޴���-��� �ڵ鷯
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
		try	//try catch���
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
