package com.bookmanager.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.bookmanager.dao.BooksReadedDao;

import model.BooksBeReaded;
import model.BooksToRead;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AddDateScoreFrame extends JFrame {

	private JPanel contentPane;
	private JTextField datetextField;	//设置成public方便条用
	private JTextField scoretextField;

	int selectedRow = -1;
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					AddDateScoreFrame frame = new AddDateScoreFrame(null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public AddDateScoreFrame(BooksToRead bookstoread) {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		setLocationRelativeTo(null);
		
		JLabel label = new JLabel("看完日期");
		label.setFont(new Font("Apple Chancery", Font.PLAIN, 18));
		
		JLabel label_1 = new JLabel("评       分");
		label_1.setFont(new Font("Apple Chancery", Font.PLAIN, 18));
		
		datetextField = new JTextField();
		datetextField.setColumns(10);
		
		scoretextField = new JTextField();
		scoretextField.setColumns(10);
		
		//添加确认
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//获取输入信息
				String date = datetextField.getText();
				String score = scoretextField.getText();
					//打包成BooksBeReaded实体
					if(date.length() == 0 || score.length() == 0) {
						JOptionPane.showMessageDialog(null, "请输入完整");
					}else {
						try {
							BooksBeReaded booksbrd = new BooksBeReaded(bookstoread.getBookname(), bookstoread.getAuthor(), bookstoread.getType(), date, score);
							BooksReadedDao brddao = new BooksReadedDao();
							brddao.add(booksbrd);
							//添加完后要删除
							
							setVisible(false);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
			}
		});
		lblNewLabel.setIcon(new ImageIcon("/Users/liuyi/Documents/workspace/bookmanager/icons/确定-2.png"));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon("/Users/liuyi/Documents/workspace/bookmanager/icons/删除 关闭 叉.png"));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_contentPane.createSequentialGroup()
					.addContainerGap(95, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(37)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addComponent(datetextField, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE)
						.addComponent(scoretextField, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 155, GroupLayout.PREFERRED_SIZE))
					.addGap(81))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(136)
					.addComponent(lblNewLabel)
					.addGap(57)
					.addComponent(lblNewLabel_1)
					.addContainerGap(122, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(46)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(datetextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label))
					.addGap(45)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(scoretextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
					.addGap(32)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblNewLabel_1)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 56, GroupLayout.PREFERRED_SIZE))
					.addGap(21))
		);
		contentPane.setLayout(gl_contentPane);
	}
}
