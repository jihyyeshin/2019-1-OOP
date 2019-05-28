import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class LoginFrame extends JFrame{
	public static final String BTN_LOGIN_TEXT = "Login";

	JTextField tf_id = new JTextField(10);
	JPasswordField pf_pwd = new JPasswordField(8);
	JTextArea ta_info = new JTextArea(3, 25);
	JButton btn_login = new JButton(BTN_LOGIN_TEXT);
	JPanel pn_content = new JPanel();
	//JList list = new JList();
	ActionListener listener = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			String data = "ID: " + tf_id.getText();
			data += "\nPW: " + String.valueOf(pf_pwd.getPassword());
			ta_info.setText(data);

		}
	};
	
	public LoginFrame(String title) {
		super(title);
		pn_content.add(new JLabel("아이디를 입력하세요"));
		pn_content.add(tf_id);
		pn_content.add(new JLabel("비밀번호를 입력하세요"));
		pn_content.add(pf_pwd);
		pn_content.add(ta_info);
		pn_content.add(btn_login);
		pn_content.setLayout(new FlowLayout());
		btn_login.addActionListener(listener);
		add(pn_content);
	}

	public static void main(String[] args) {
		LoginFrame f = new LoginFrame("로그인 화면");
		f.setSize(320, 200);
		f.setDefaultCloseOperation(EXIT_ON_CLOSE);
		f.setVisible(true);
	}

}
