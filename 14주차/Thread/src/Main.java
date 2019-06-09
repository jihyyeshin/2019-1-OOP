public class Main{
	public static void main(String[] args) {
		/* thread 滴俺 积己 */
		EditorThread1 t1=new EditorThread1();	
		EditorThread2 t2=new EditorThread2();	
		/* 角青 */
		t1.run();
		t2.run();
	}
}

class EditorThread1 extends Editor implements Runnable{//searchword角青
	@Override
	public void run() {
		// TODO Auto-generated method stub
		getFile();
		searchWord();
	}
	
}

class EditorThread2 extends Editor implements Runnable{//replaceword角青

	@Override
	public void run() {
		// TODO Auto-generated method stub
		getFile();
		replaceWord();
	}
	
}