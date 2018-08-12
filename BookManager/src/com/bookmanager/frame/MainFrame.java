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

public class MainFrame extends JFrame {

	private JPanel contentPane1;
	private JTextField authortextField;
	private JTextField typetextField;
	private JTextField bookNametextField;
	public String bookname;
	public String author;
	public String type;
	
	//商品列表
	List<BooksToRead> bookstoread = null;
	int selectedRow = -1;
	//创建dao
	BooksToReadDao btrdao = new BooksToReadDao();

	static MainFrame mainframe = new MainFrame();
	static BooksReadedFrame booksreadedframe = new BooksReadedFrame();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					mainframe.setLocationRelativeTo(null);
					mainframe.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 845, 520);
		this.setLocationRelativeTo(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportBorder(null);
		
		try {
			bookstoread = btrdao.show();
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		BooksToReadTableModel btrtm = new BooksToReadTableModel(bookstoread);
		JTable table = new JTable(btrtm);
		
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
	    table.getColumnModel().getColumn(0).setPreferredWidth(150);
	    table.getColumnModel().getColumn(1).setPreferredWidth(50);
	    table.getColumnModel().getColumn(2).setPreferredWidth(50);
	    
		scrollPane.setViewportView(table);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setEnabled(false);
		menuBar.setBorderPainted(false);
		setJMenuBar(menuBar);
		
		JMenuItem menuItem = new JMenuItem("待看书单");
		menuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menuItem.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				menuItem.setBackground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				booksreadedframe.setVisible(false);
				mainframe.setVisible(true);
			}
		});
		
		menuItem.setBackground(Color.WHITE);
		menuItem.setSelected(true);
		menuItem.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		menuBar.add(menuItem);
		
		//已看书单菜单按钮
		JMenuItem menuItem_1 = new JMenuItem("已看书单");
		menuItem_1.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		menuBar.add(menuItem_1);
		menuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menuItem_1.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				menuItem_1.setBackground(Color.WHITE);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				booksreadedframe.refreshTable(booksreadedframe.scrollPane, booksreadedframe.table);
				booksreadedframe.setVisible(true);
			}
		});
		
		JMenuItem menuItem_2 = new JMenuItem("统计");
		menuItem_2.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		menuBar.add(menuItem_2);
		menuItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menuItem_2.setBackground(Color.LIGHT_GRAY);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				menuItem_2.setBackground(Color.WHITE);
			}
		});
		
		contentPane1 = new JPanel();
		contentPane1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane1);
		
		JLabel lblNewLabel = new JLabel("Stay hungry Stay foolish");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewLabel.setIcon(new ImageIcon("/Users/liuyi/Documents/workspace/bookmanager/icons/书2.png"));
		
		//添加书籍
		JButton add = new JButton("添加");
		add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					addNewBook(scrollPane,table);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		add.setForeground(new Color(0, 0, 0));
		add.setBackground(Color.GREEN);
		add.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		
		//添加书籍到已看
		JButton yikan = new JButton("已看");
		yikan.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedRow = table.getSelectedRow();
				if(selectedRow < 0) {
					return;
				}else {
					//调出打分窗体
					bookname = bookstoread.get(selectedRow).getBookname();
					author = bookstoread.get(selectedRow).getAuthor();
					type = bookstoread.get(selectedRow).getType();
					BooksToRead btr2 = new BooksToRead(bookname, author, type);
					AddDateScoreFrame adddatascoreframe = new AddDateScoreFrame(btr2);
					adddatascoreframe.setVisible(true);
					
				}
	
			}
		});
		yikan.setForeground(Color.BLACK);
		yikan.setBackground(Color.WHITE);
		yikan.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		
		//删除选中书籍
		JButton delete = new JButton("删除");
		delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				selectedRow = table.getSelectedRow();	//获取选择行
				if(selectedRow < 0) {
					return;
				}else {
					BooksToRead deletebook = null;
					deletebook = bookstoread.get(selectedRow);		//获取选择行信息
					try {
						btrdao.deleteBook(deletebook);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					refreshTable(scrollPane,table);
				}
			}
		});
		delete.setFont(new Font("Lucida Grande", Font.PLAIN, 23));
		
		JLabel label = new JLabel("书名");
		label.setFont(new Font("Apple Symbols", Font.PLAIN, 16));
		
		JLabel label_1 = new JLabel("作者");
		label_1.setFont(new Font("Apple Symbols", Font.PLAIN, 16));
		
		JLabel label_2 = new JLabel("类型");
		label_2.setFont(new Font("Apple Symbols", Font.PLAIN, 16));
		
		authortextField = new JTextField();
		authortextField.setFont(new Font("Apple Symbols", Font.PLAIN, 16));
		authortextField.setColumns(10);
		
		typetextField = new JTextField();
		typetextField.setFont(new Font("Apple Symbols", Font.PLAIN, 16));
		typetextField.setColumns(10);
		
		bookNametextField = new JTextField();
		bookNametextField.setFont(new Font("Apple Symbols", Font.PLAIN, 16));
		bookNametextField.setColumns(10);
	
		refreshTable(scrollPane,table);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				refreshTable(scrollPane,table);
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon("/Users/liuyi/Documents/workspace/bookmanager/icons/刷新2.png"));
		
		JLabel label_3 = new JLabel("待看书单");
		label_3.setFont(new Font("Apple Chancery", Font.PLAIN, 16));
		
	    //
		GroupLayout gl_contentPane1 = new GroupLayout(contentPane1);
		gl_contentPane1.setHorizontalGroup(
			gl_contentPane1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane1.createSequentialGroup()
					.addGroup(gl_contentPane1.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane1.createSequentialGroup()
							.addGap(40)
							.addGroup(gl_contentPane1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane1.createSequentialGroup()
									.addComponent(label, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(bookNametextField, 131, 131, 131)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(authortextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(typetextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 532, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane1.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane1.createSequentialGroup()
									.addGap(66)
									.addGroup(gl_contentPane1.createParallelGroup(Alignment.LEADING)
										.addComponent(add, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
										.addComponent(yikan, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
										.addComponent(delete, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)))
								.addGroup(gl_contentPane1.createSequentialGroup()
									.addGap(99)
									.addComponent(lblNewLabel_1))))
						.addGroup(gl_contentPane1.createSequentialGroup()
							.addGap(301)
							.addComponent(lblNewLabel))
						.addGroup(gl_contentPane1.createSequentialGroup()
							.addGap(256)
							.addComponent(label_3)))
					.addContainerGap(88, Short.MAX_VALUE))
		);
		gl_contentPane1.setVerticalGroup(
			gl_contentPane1.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane1.createSequentialGroup()
					.addContainerGap()
					.addComponent(label_3)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_contentPane1.createParallelGroup(Alignment.TRAILING)
						.addGroup(Alignment.LEADING, gl_contentPane1.createSequentialGroup()
							.addComponent(yikan, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addGap(33)
							.addComponent(delete, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addGap(35)
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED))
						.addGroup(gl_contentPane1.createSequentialGroup()
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 301, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(gl_contentPane1.createParallelGroup(Alignment.BASELINE)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(add, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
						.addComponent(typetextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(authortextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
						.addComponent(bookNametextField, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addComponent(lblNewLabel)
					.addGap(21))
		);
		contentPane1.setLayout(gl_contentPane1);
		
		
	}

	//添加新书
	protected void addNewBook(JScrollPane scrollPane,JTable table) throws Exception {
		
		BooksToRead addnewbook = new BooksToRead(bookNametextField.getText(), authortextField.getText(), typetextField.getText());
		//判断是否为空
		if(bookNametextField.getText().length() == 0 || authortextField.getText().length() == 0 || typetextField.getText().length() == 0) {
			JOptionPane.showMessageDialog(null, "请输入完整信息！");
		}else {
				if(btrdao.search(addnewbook).getBookname() != null){
					JOptionPane.showMessageDialog(null, "请勿重复添加");
				}else {
						try {
							btrdao.add(addnewbook);
							refreshTable(scrollPane,table);
							JOptionPane.showMessageDialog(null, "添加成功！");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
				bookNametextField.setText(null);
				authortextField.setText(null);
				typetextField.setText(null);
		}
	}

	protected void refreshTable(JScrollPane scrollPane,JTable table) {
		//设置表格
		try {
      		bookstoread = btrdao.show();
      		BooksToReadTableModel btrtm = new BooksToReadTableModel(bookstoread);
      		table.setModel(btrtm);
    		scrollPane.setViewportView(table);
		    
  		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
  		}
		//BooksToReadTableModel btrtm = new BooksToReadTableModel(bookstoread);
		//JTable table = new JTable(btrtm);
	
		
	}
}
