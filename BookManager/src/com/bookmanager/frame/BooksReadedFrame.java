package com.bookmanager.frame;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

import com.bookmanager.dao.BooksReadedDao;
import com.bookmanager.dao.BooksToReadDao;

import model.BooksBeReaded;
import model.BooksBeReadedTableModel;
import model.BooksToRead;
import model.BooksToReadTableModel;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JMenuBar;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JList;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.JTable;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.util.List;

import javax.swing.ListSelectionModel;
import javax.swing.JTextField;
import javax.swing.DropMode;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.nio.channels.SelectableChannel;

public class BooksReadedFrame extends JFrame {

	private JPanel contentPane1;
	
	//商品列表
	List<BooksBeReaded> booksbereaded = null;
	int selectedRow = -1;
	//创建dao
	BooksReadedDao brddao = new BooksReadedDao();
	static BooksReadedFrame booksreadedframe = new BooksReadedFrame();
	static MainFrame mainframe = new MainFrame();
	
	BooksBeReadedTableModel brdtm = new BooksBeReadedTableModel(booksbereaded);
	public JTable table = new JTable(brdtm);
	public JScrollPane scrollPane = new JScrollPane();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					booksreadedframe.setLocationRelativeTo(null);
//					booksreadedframe.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public BooksReadedFrame() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 706, 520);
		setLocationRelativeTo(null);

		
		scrollPane.setViewportBorder(null);
		
		try {
			booksbereaded = brddao.show();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		//设置table
		//设置表头
		table.getTableHeader().setFont(new Font("Apple Symbols", Font.PLAIN, 13));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getTableHeader().setPreferredSize(new Dimension(1, 26));	
		table.setFont(new Font("Apple Chancery", Font.PLAIN, 14));
		table.setRowHeight(30);
		//居中对齐
		DefaultTableCellRenderer r1   = new DefaultTableCellRenderer();   
		r1.setHorizontalAlignment(JLabel.CENTER);   
		table.setDefaultRenderer(Object.class, r1);
		//表头居中
		DefaultTableCellRenderer  renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
        renderer.setHorizontalAlignment(renderer.CENTER);
        //设置列宽
	    table.getColumnModel().getColumn(0).setPreferredWidth(200);
	    table.getColumnModel().getColumn(1).setPreferredWidth(50);
	    table.getColumnModel().getColumn(2).setPreferredWidth(50);
	    table.getColumnModel().getColumn(3).setPreferredWidth(50);
	    table.getColumnModel().getColumn(4).setPreferredWidth(20);
	    
		scrollPane.setViewportView(table);
		
		contentPane1 = new JPanel();
		contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane1);
		
		JLabel lblNewLabel = new JLabel("Stay hungry Stay foolish");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel.setIcon(new ImageIcon("/Users/liuyi/Documents/workspace/bookmanager/icons/书2.png"));
	
		refreshTable(scrollPane,table);
		
		JLabel label_3 = new JLabel("已看书单");
		label_3.setFont(new Font("Apple Chancery", Font.PLAIN, 20));
		GroupLayout gl_contentPane1 = new GroupLayout(contentPane1);
		gl_contentPane1.setHorizontalGroup(
			gl_contentPane1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane1.createSequentialGroup()
					.addContainerGap(247, Short.MAX_VALUE)
					.addComponent(lblNewLabel)
					.addGap(223))
				.addGroup(Alignment.LEADING, gl_contentPane1.createSequentialGroup()
					.addGap(66)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 557, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(73, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPane1.createSequentialGroup()
					.addGap(297)
					.addComponent(label_3)
					.addContainerGap(319, Short.MAX_VALUE))
		);
		gl_contentPane1.setVerticalGroup(
			gl_contentPane1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane1.createSequentialGroup()
					.addGap(7)
					.addComponent(label_3)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 359, GroupLayout.PREFERRED_SIZE)
					.addGap(29)
					.addComponent(lblNewLabel)
					.addContainerGap())
		);
		contentPane1.setLayout(gl_contentPane1);
		
		
	}

	protected void refreshTable(JScrollPane scrollPane,JTable table) {
		//设置表格
		try {
      		booksbereaded = brddao.show();
      		BooksBeReadedTableModel btrtm = new BooksBeReadedTableModel(booksbereaded);
      		table.setModel(btrtm);
    		scrollPane.setViewportView(table);
		    
  		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
  		}
	
		
	}
}
