public class Main{
	public static void main(String[] args) {
		/* thread �ΰ� ���� */
		EditorThread1 t1=new EditorThread1();	
		EditorThread2 t2=new EditorThread2();	
		/* ���� */
		t1.run();
		t2.run();
	}
}

class EditorThread1 extends Editor implements Runnable{//searchword����
	@Override
	public void run() {
		// TODO Auto-generated method stub
		getFile();
		searchWord();
	}
	
}

class EditorThread2 extends Editor implements Runnable{//replaceword����

	@Override
	public void run() {
		// TODO Auto-generated method stub
		getFile();
		replaceWord();
	}
	
}