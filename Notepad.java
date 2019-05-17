import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.nio.channels.NonWritableChannelException;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import java.awt.GridBagConstraints;
import javax.swing.JTextArea;
import javax.swing.text.TabableView;
import javax.swing.JScrollBar;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JScrollPane;

public class Notepad
{

	private JFrame frame;
	private String filenameString; 
	private String copyString;
	private String pasteString;
	private String cutString;
	private JTextArea textArea;
	
	/**
	 * @wbp.nonvisual location=7,-13
	 */
	private final JLabel label = DefaultComponentFactory.getInstance().createTitle("New JGoodies title");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Notepad window = new Notepad();
					window.frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Notepad() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{	
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("记事本");
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.YELLOW);
		menuBar.setBackground(Color.YELLOW);
		frame.setJMenuBar(menuBar);
		
		JMenu menu = new JMenu("\u6587\u4EF6");
		menuBar.add(menu);
		
		/*----------打开文件------*/
		JMenuItem menuItem = new JMenuItem("\u6253\u5F00");
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					FileDialog fileDialog = new FileDialog(frame,"打开文件",FileDialog.LOAD);
					fileDialog.setVisible(true);
					File f = null;
					f = new File(fileDialog.getDirectory()+fileDialog.getFile());
					for(int i = 0; i <= f.length(); i++)
					{
						char [] ch = new char[100];
						try {
							FileReader fReader = new FileReader(f);
							fReader.read(ch);
							String str = new String(ch);
							textArea.setText(str);	
							
						}catch (Exception e) {
							// TODO: handle exception
							System.err.println(e);
						}
					}
				}
		});
		menu.add(menuItem);
		/*----------打开文件end--------*/
		
		/*---------保存---------------*/
		JMenuItem menuItem_1 = new JMenuItem("\u4FDD\u5B58");
		menuItem_1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		menuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(filenameString == null)
					{
						filenameString = JOptionPane.showInputDialog("请输入文件名","java");
						FileOutputStream fout = new FileOutputStream(filenameString + "txt");
						byte bb[] = textArea.getText().getBytes();
						fout.write(bb);
						fout.close();
						JOptionPane.showMessageDialog(null, "已保存");
					}
					else {
						filenameString = JOptionPane.showInputDialog("请输入文件名","java");
						FileOutputStream fout = new FileOutputStream(filenameString + "txt");
						byte bb[] = textArea.getText().getBytes();
						fout.write(bb);
						fout.close();
						JOptionPane.showMessageDialog(null, "已保存");
					}
				}catch (Exception e) {
					// TODO: handle exception
					System.err.println(e);
				}
			}
			
		});
		menu.add(menuItem_1);
		/*---------保存 end-------------*/
		
		/*--------关闭-----------------*/
		JMenuItem menuItem_3 = new JMenuItem("\u5173\u95ED");
		menuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		menu.add(menuItem_3);
		/*----------关闭end--------------*/
		
		
		/*---------另存为-------------*/
		JMenuItem menuItem_2 = new JMenuItem("\u53E6\u5B58\u4E3A");
		menuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileDialog fileDialog = new FileDialog(frame,"打开文件",FileDialog.SAVE);
				fileDialog.setVisible(true);
				try {
					filenameString = fileDialog.getDirectory() + fileDialog.getName();
					FileOutputStream fout = new FileOutputStream(filenameString + "txt");
					byte bb[] = textArea.getText().getBytes();
					fout.write(bb);
					fout.close();
					JOptionPane.showMessageDialog(null, "已保存");				
				}catch (Exception e) {
					// TODO: handle exception
					System.err.println(e);
				}
			}
		});
		menu.add(menuItem_2);
		/*-------另存为  end ----------
		
		JMenuItem menuItem_3 = new JMenuItem("\u5173\u95ED");
		menu.add(menuItem_3);
		
		/*新建*/
		JMenuItem menuItem_4 = new JMenuItem("\u65B0\u5EFA");
		menuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!(textArea.getText().equals("")))
				{
					Object [] options = {"确定","取消"};
					int response = JOptionPane.
							showOptionDialog(null, "你是否保存","提  示", JOptionPane.YES_NO_OPTION
									, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
					if(response == 0)
					{
						try {
							FileDialog saveFileDialog = new FileDialog(frame,"保存",FileDialog.SAVE);
							saveFileDialog.setVisible(true);
							filenameString = saveFileDialog.getDirectory() + saveFileDialog.getFile();
							FileOutputStream fOutputStream = new FileOutputStream(filenameString);
							byte bb[] = textArea.getText().getBytes();
							fOutputStream.write(bb);
							fOutputStream.close();
							JOptionPane.showMessageDialog(null, "已保存");
							textArea.setText("");
						}catch (Exception e) {
							// TODO: handle exception
							System.err.println(e);
						}
					}
					if(response == 1)
					{
						JOptionPane.showMessageDialog(null, "你选择了取消");
						textArea.setText("");
					}
				}
			}
		});
		menuItem_4.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		menu.add(menuItem_4);
		/*------新建 end ----*/
		
		/*--------------------编辑菜单-------------------*/
		JMenu menu_1 = new JMenu("\u7F16\u8F91");
		menuBar.add(menu_1);
		
		/*----------复制---------*/
		JMenuItem menuItem_5 = new JMenuItem("\u590D\u5236");
		menuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copyString = textArea.getSelectedText();
			}
		});
		menuItem_5.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
		menu_1.add(menuItem_5);
		
		/*----------粘贴---------*/
		JMenuItem menuItem_6 = new JMenuItem("\u7C98\u8D34");
		menuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea.insert(copyString,textArea.getCaretPosition());
			}
		});
		menuItem_6.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_MASK));
		menu_1.add(menuItem_6);
		
		/*----------剪切--------*/
		JMenuItem menuItem_7 = new JMenuItem("\u526A\u5207");
		menuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				copyString = textArea.getSelectedText();
				textArea.replaceSelection("");
			}
		});
		menuItem_7.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		menu_1.add(menuItem_7);
		
		/*---------版本------*/
		JMenuItem menuItem_8 = new JMenuItem("\u7248\u672C");
		menuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(frame, "Note Pad 1.0");
			}
		});
		menu_1.add(menuItem_8);
		
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{273, 0};
		gridBagLayout.rowHeights = new int[]{286, 33, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		textArea = new JTextArea();
		textArea.setBackground(Color.PINK);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.insets = new Insets(0, 0, 5, 0);
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridx = 0;
		gbc_textArea.gridy = 0;
		frame.getContentPane().add(textArea, gbc_textArea);
		textArea.setLineWrap(true);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 1;
		frame.getContentPane().add(panel, gbc_panel);
		
		/*-------------保存按钮-------*/
		JButton button = new JButton("\u4FDD\u5B58");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
						if(filenameString == null)
						{
							filenameString = JOptionPane.showInputDialog("请输入文件名","java");
							FileOutputStream fout = new FileOutputStream(filenameString + "txt");
							byte bb[] = textArea.getText().getBytes();
							fout.write(bb);
							fout.close();
							JOptionPane.showMessageDialog(null, "已保存");
						}
						else {
							filenameString = JOptionPane.showInputDialog("请输入文件名","java");
							FileOutputStream fout = new FileOutputStream(filenameString + "txt");
							byte bb[] = textArea.getText().getBytes();
							fout.write(bb);
							fout.close();
							JOptionPane.showMessageDialog(null, "已保存");
						}
				}catch (Exception e) {
					// TODO: handle exception
					System.err.println(e);
				}				
			}
		});
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel.add(button);
		
		/*---------关闭按钮------*/
		JButton button_1 = new JButton("\u5173\u95ED");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		panel.add(button_1);
		
	}
	
}

