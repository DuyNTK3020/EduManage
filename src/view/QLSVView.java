package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.QLSVModel;
import model.ThiSinh;
import model.Tinh;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import javax.swing.Action;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.border.BevelBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.Component;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import controller.QLSVController;

import javax.swing.JRadioButton;
import java.awt.Color;

public class QLSVView extends JFrame {

	private JPanel contentPane;
	public QLSVModel model;
	public JTextField textField_MaThiSinh_TimKiem;
	public JTable table;
	public JTextField textField_ID;
	public JTextField textField_HoVaTen;
	public JTextField textField_NgaySinh;
	public JTextField textField_Mon2;
	public JTextField textField_Mon3;
	public JTextField textField_Mon1;
	public ButtonGroup btn_gioiTinh;
	public JComboBox comboBox_queQuan;
	public JRadioButton rdbtnMale;
	public JRadioButton rdbtnFemale;
	public JComboBox comboBox_queQuan_timKiem;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QLSVView frame = new QLSVView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public QLSVView() {
		this.setTitle("Candidates management system");
		this.model = new QLSVModel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 750, 530);
		this.setIconImage(Toolkit.getDefaultToolkit().createImage(QLSVView.class.getResource("icon_title.png")));

		ActionListener action = new QLSVController(this);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);

		JMenuItem menuOpen = new JMenuItem("Open");
		menuOpen.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLSVView.class.getResource("icon_open.png"))));
		menuOpen.addActionListener(action);
		menuFile.add(menuOpen);

		JMenuItem menuSave = new JMenuItem("SaveFile");
		menuSave.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLSVView.class.getResource("icon_save.png"))));
		menuSave.addActionListener(action);
		menuFile.add(menuSave);

		JMenuItem menuExit = new JMenuItem("Exit");
		menuExit.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLSVView.class.getResource("icon_exit.png"))));
		menuExit.addActionListener(action);
		
		JSeparator separator_3 = new JSeparator();
		menuFile.add(separator_3);
		menuFile.add(menuExit);

		JMenu menuAbout = new JMenu("About");
		menuAbout.addActionListener(action);
		menuBar.add(menuAbout);

		JMenuItem menuAboutMe = new JMenuItem("About Me");
		menuAboutMe.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage(QLSVView.class.getResource("icon_about.png"))));
		menuAboutMe.addActionListener(action);
		menuAbout.add(menuAboutMe);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblHomeTown = new JLabel("Place of birth");
		lblHomeTown.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblHomeTown.setBounds(30, 10, 80, 30);
		contentPane.add(lblHomeTown);

		JLabel lblMThi = new JLabel("ID Number");
		lblMThi.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMThi.setBounds(250, 10, 80, 30);
		contentPane.add(lblMThi);

		textField_MaThiSinh_TimKiem = new JTextField();
		textField_MaThiSinh_TimKiem.setColumns(10);
		textField_MaThiSinh_TimKiem.setBounds(330, 11, 160, 30);
		contentPane.add(textField_MaThiSinh_TimKiem);

		JButton btnTim = new JButton("Search");
		btnTim.addActionListener(action);
		btnTim.setBounds(510, 10, 100, 30);
		contentPane.add(btnTim);

		comboBox_queQuan_timKiem = new JComboBox();
		ArrayList<Tinh> listTinh = Tinh.getDSTinh();
		comboBox_queQuan_timKiem.addItem("");
		for (Tinh tinh : listTinh) {
			comboBox_queQuan_timKiem.addItem(tinh.getTenTinh());
		}
		comboBox_queQuan_timKiem.setBounds(110, 10, 120, 30);
		contentPane.add(comboBox_queQuan_timKiem);

		JLabel lblDanhSachThiSinh = new JLabel("Candidate list");
		lblDanhSachThiSinh.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDanhSachThiSinh.setBounds(30, 50, 120, 30);
		contentPane.add(lblDanhSachThiSinh);

		table = new JTable();
		table.setColumnSelectionAllowed(true);
		table.setModel(new DefaultTableModel(new Object[][] {},
				new String[] { "ID Number", "Full name", "Place of birth", "Date of birth",
						"Gender", "Maths", "Science", "English" }));

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(30, 80, 690, 185);
		contentPane.add(scrollPane);

		JLabel lblPlaceOfBirth = new JLabel("Place of birth");
		lblPlaceOfBirth.setBounds(30, 370, 80, 20);
		contentPane.add(lblPlaceOfBirth);

		JLabel lblIdNumber = new JLabel("ID Number");
		lblIdNumber.setBounds(30, 310, 80, 20);
		contentPane.add(lblIdNumber);

		textField_ID = new JTextField();
		textField_ID.setColumns(10);
		textField_ID.setBounds(110, 310, 185, 20);
		contentPane.add(textField_ID);

		JLabel lanel_HoVaTen = new JLabel("Full name");
		lanel_HoVaTen.setBounds(30, 340, 80, 20);
		contentPane.add(lanel_HoVaTen);

		textField_HoVaTen = new JTextField();
		textField_HoVaTen.setColumns(10);
		textField_HoVaTen.setBounds(110, 340, 185, 20);
		contentPane.add(textField_HoVaTen);

		JLabel lblDateOfBirth = new JLabel("Date of birth");
		lblDateOfBirth.setBounds(30, 400, 80, 20);
		contentPane.add(lblDateOfBirth);

		textField_NgaySinh = new JTextField();
		textField_NgaySinh.setColumns(10);
		textField_NgaySinh.setBounds(110, 400, 185, 20);
		contentPane.add(textField_NgaySinh);

		JLabel lblMon_2 = new JLabel("Science score");
		lblMon_2.setBounds(330, 370, 70, 20);
		contentPane.add(lblMon_2);

		textField_Mon2 = new JTextField();
		textField_Mon2.setColumns(10);
		textField_Mon2.setBounds(400, 370, 120, 20);
		contentPane.add(textField_Mon2);

		JLabel lblMon_3 = new JLabel("English score");
		lblMon_3.setBounds(330, 400, 70, 20);
		contentPane.add(lblMon_3);

		textField_Mon3 = new JTextField();
		textField_Mon3.setColumns(10);
		textField_Mon3.setBounds(400, 400, 120, 20);
		contentPane.add(textField_Mon3);

		JLabel lblInformation = new JLabel("Information");
		lblInformation.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblInformation.setBounds(30, 280, 120, 20);
		contentPane.add(lblInformation);

		comboBox_queQuan = new JComboBox();
		comboBox_queQuan.addItem("");
		for (Tinh tinh : listTinh) {
			comboBox_queQuan.addItem(tinh.getTenTinh());
		}
		comboBox_queQuan.setBounds(110, 370, 185, 20);
		contentPane.add(comboBox_queQuan);

		textField_Mon1 = new JTextField();
		textField_Mon1.setColumns(10);
		textField_Mon1.setBounds(400, 340, 120, 20);
		contentPane.add(textField_Mon1);

		JLabel lblMon_1 = new JLabel("Maths score");
		lblMon_1.setBounds(330, 340, 70, 20);
		contentPane.add(lblMon_1);

		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(330, 310, 70, 20);
		contentPane.add(lblGender);

		rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(400, 310, 60, 20);
		contentPane.add(rdbtnMale);

		rdbtnFemale = new JRadioButton("Female");
		rdbtnFemale.setBounds(460, 310, 60, 20);
		contentPane.add(rdbtnFemale);

		btn_gioiTinh = new ButtonGroup();
		btn_gioiTinh.add(rdbtnMale);
		btn_gioiTinh.add(rdbtnFemale);

		JButton btnThem = new JButton("Clear");
		btnThem.addActionListener(action);
		btnThem.setBounds(465, 440, 110, 20);
		contentPane.add(btnThem);

		JButton btnXoa = new JButton("Remove");
		btnXoa.addActionListener(action);
		btnXoa.setBounds(30, 440, 110, 20);
		contentPane.add(btnXoa);

		JButton btnCapNhat = new JButton("Update");
		btnCapNhat.addActionListener(action);
		btnCapNhat.setBounds(175, 440, 110, 20);
		contentPane.add(btnCapNhat);

		JButton btnLuu = new JButton("Save");
		btnLuu.addActionListener(action);
		btnLuu.setBounds(320, 440, 110, 20);
		contentPane.add(btnLuu);

		JButton btnHuyBo = new JButton("Reset all");
		btnHuyBo.addActionListener(action);
		btnHuyBo.setBounds(610, 440, 110, 20);
		contentPane.add(btnHuyBo);

		JButton btn_HuyTim = new JButton("Cancel");
		btn_HuyTim.addActionListener(action);
		btn_HuyTim.setBounds(620, 10, 100, 30);
		contentPane.add(btn_HuyTim);

		JSeparator separator = new JSeparator();
		separator.setBounds(20, 50, 710, 1);
		contentPane.add(separator);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(20, 275, 710, 2);
		contentPane.add(separator_1);

		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(20, 430, 710, 2);
		contentPane.add(separator_2);

		this.setVisible(true);
	}
	
	public void xoaFormTim() {
		textField_MaThiSinh_TimKiem.setText("");
		comboBox_queQuan_timKiem.setSelectedIndex(-1);
	}

	public void xoaForm() {
		textField_ID.setText("");
		textField_HoVaTen.setText("");
		textField_MaThiSinh_TimKiem.setText("");
		textField_NgaySinh.setText("");
		textField_Mon1.setText("");
		textField_Mon2.setText("");
		textField_Mon3.setText("");
		comboBox_queQuan.setSelectedIndex(-1);
		btn_gioiTinh.clearSelection();
	}
	
	public void clear() {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		model_table.setRowCount(0);
		this.model.clear();
	}

	public void themThiSinhVaoTable(ThiSinh ts) {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		model_table.addRow(new Object[] { ts.getMaThiSinh() + "", ts.getTenThiSinh(), ts.getQueQuan().getTenTinh(),
				ts.getNgaySinh().format(formatter) + "", (ts.isGioiTinh() ? "Male" : "Female"), ts.getDiemMon1() + "",
				ts.getDiemMon2() + "", ts.getDiemMon3() + "", });
	}

	public ThiSinh getThiSinhDangChon() {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		int i_row = table.getSelectedRow();

		int maThiSinh = Integer.valueOf(model_table.getValueAt(i_row, 0) + "");
		String tenThiSinh = model_table.getValueAt(i_row, 1) + "";
		Tinh tinh = Tinh.getTinhByTen(model_table.getValueAt(i_row, 2) + "");
		String s_ngaySinh = model_table.getValueAt(i_row, 3) + "";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate ngaySinh = LocalDate.parse(s_ngaySinh, formatter);
		String textGioiTinh = model_table.getValueAt(i_row, 4) + "";
		boolean gioiTinh = textGioiTinh.equals("Male");
		float diemMon1 = Float.valueOf(model_table.getValueAt(i_row, 5) + "");
		float diemMon2 = Float.valueOf(model_table.getValueAt(i_row, 6) + "");
		float diemMon3 = Float.valueOf(model_table.getValueAt(i_row, 7) + "");

		ThiSinh ts = new ThiSinh(maThiSinh, tenThiSinh, tinh, ngaySinh, gioiTinh, diemMon1, diemMon2, diemMon3);
		return ts;
	}

	public void hienThiThongTinThiSinhDaChon() {
		ThiSinh ts = getThiSinhDangChon();

		this.textField_ID.setText(ts.getMaThiSinh() + "");
		this.textField_HoVaTen.setText(ts.getTenThiSinh() + "");
		this.comboBox_queQuan.setSelectedItem(ts.getQueQuan().getTenTinh());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String s_ngaySinh = ts.getNgaySinh().format(formatter);
		this.textField_NgaySinh.setText(s_ngaySinh + "");
		if (ts.isGioiTinh()) {
			rdbtnMale.setSelected(true);
		} else {
			rdbtnFemale.setSelected(true);
		}
		this.textField_Mon1.setText(ts.getDiemMon1() + "");
		this.textField_Mon2.setText(ts.getDiemMon2() + "");
		this.textField_Mon3.setText(ts.getDiemMon3() + "");
	}

	public void thucHienXoa() {
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		int i_row = table.getSelectedRow();
		int luaChon = JOptionPane.showConfirmDialog(this, "Are you sure you want to remove this candidate?");
		if (luaChon == JOptionPane.YES_OPTION) {
			ThiSinh ts = getThiSinhDangChon();
			this.model.delete(ts);
			model_table.removeRow(i_row);
		}

	}

	public void thucHienThemThiSinh() {
		// Get du lieu
		int maThiSinh = Integer.valueOf(this.textField_ID.getText());
		String tenThiSinh = this.textField_HoVaTen.getText();
		int queQuan = this.comboBox_queQuan.getSelectedIndex() - 1;
		Tinh tinh = Tinh.getTinhById(queQuan);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate ngaySinh = LocalDate.parse(this.textField_NgaySinh.getText(), formatter);
		boolean gioiTinh = true;
		if (this.rdbtnMale.isSelected()) {
			gioiTinh = true;
		} else if (this.rdbtnFemale.isSelected()) {
			gioiTinh = false;
		}
		float diemMon1 = Float.valueOf(this.textField_Mon1.getText());
		float diemMon2 = Float.valueOf(this.textField_Mon2.getText());
		float diemMon3 = Float.valueOf(this.textField_Mon3.getText());

		ThiSinh ts = new ThiSinh(maThiSinh, tenThiSinh, tinh, ngaySinh, gioiTinh, diemMon1, diemMon2, diemMon3);
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		if (this.model.kiemTraTonTai(ts)) {
			this.model.deleteByID(ts.getMaThiSinh());
			this.model.insert(ts);
			int soLuongDong = model_table.getRowCount();
			for (int i = 0; i < soLuongDong; i++) {
				String id = model_table.getValueAt(i, 0) + "";
				if (id.equals(ts.getMaThiSinh() + "")) {
					model_table.setValueAt(ts.getMaThiSinh() + "", i, 0);
					model_table.setValueAt(ts.getTenThiSinh() + "", i, 1);
					model_table.setValueAt(ts.getQueQuan().getTenTinh() + "", i, 2);
					model_table.setValueAt(ts.getNgaySinh().format(formatter) + "", i, 3);
					model_table.setValueAt((ts.isGioiTinh() ? "Male" : "Female"), i, 4);
					model_table.setValueAt(ts.getDiemMon1() + "", i, 5);
					model_table.setValueAt(ts.getDiemMon2() + "", i, 6);
					model_table.setValueAt(ts.getDiemMon3() + "", i, 7);
				}
			}
			this.model.inDanhSach();
		} else {
			this.model.insert(ts);
			this.themThiSinhVaoTable(ts);
		}
	}

	public void thucHienTim() {
		// Goi ham huy tim kiem
		this.thucHienTaiLaiDuLieu();

		// Thuc hien tim kiem
		int queQuan = this.comboBox_queQuan_timKiem.getSelectedIndex() - 1;
		String maThiSinhTimKiem = this.textField_MaThiSinh_TimKiem.getText();
		DefaultTableModel model_table = (DefaultTableModel) table.getModel();
		int soLuongDong = model_table.getRowCount();

		Set<Integer> idCuaThiSinhCanXoa = new TreeSet<Integer>();
		if (queQuan >= 0) {
			Tinh tinhDaChon = Tinh.getTinhById(queQuan);
			for (int i = 0; i < soLuongDong; i++) {
				String tenTinh = model_table.getValueAt(i, 2) + "";
				String id = model_table.getValueAt(i, 0) + "";
				if (!tenTinh.equals(tinhDaChon.getTenTinh())) {

					idCuaThiSinhCanXoa.add(Integer.valueOf(id));
				}
			}
		}

		if (maThiSinhTimKiem.length() > 0) {
			for (int i = 0; i < soLuongDong; i++) {
				String id = model_table.getValueAt(i, 0) + "";
				if (!id.equals(maThiSinhTimKiem)) {
					idCuaThiSinhCanXoa.add(Integer.valueOf(id));
				}
			}
		}

		for (Integer idCanXoa : idCuaThiSinhCanXoa) {
			soLuongDong = model_table.getRowCount();
			for (int i = 0; i < soLuongDong; i++) {
				String idTrongTable = model_table.getValueAt(i, 0) + "";
				if (idTrongTable.equals(idCanXoa.toString())) {
					try {
						model_table.removeRow(i);
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
	}

	public void thucHienTaiLaiDuLieu() {
		while (true) {
			DefaultTableModel model_table = (DefaultTableModel) table.getModel();
			int soLuongDong = model_table.getRowCount();
			if (soLuongDong == 0)
				break;
			else
				try {
					model_table.removeRow(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		for (ThiSinh ts : this.model.getDsThiSinh()) {
			this.themThiSinhVaoTable(ts);
		}
	}

	public void hienThiAbout() {
		JOptionPane.showMessageDialog(this, "Candidates management system");
	}

	public void thoatKhoiChuongTrinh() {
		int luaChon = JOptionPane.showConfirmDialog(this, "Do you want to exit?", "Exit",
				JOptionPane.YES_NO_OPTION);
		if (luaChon == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public void saveFile(String path) {
		try {
			this.model.setTenFile(path);
			FileOutputStream fos = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			for (ThiSinh ts : this.model.getDsThiSinh()) {
				oos.writeObject(ts);
			}
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void thucHienSaveFile() {
		if (this.model.getTenFile().length() > 0) {
			saveFile(this.model.getTenFile());
		} else {
			JFileChooser fc = new JFileChooser();
			int returnVal = fc.showSaveDialog(this);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
				saveFile(file.getAbsolutePath());
			}
		}
	}

	public void openFile(File file) {
		ArrayList<ThiSinh> ds = new ArrayList<ThiSinh>();
		try {
			this.model.setTenFile(file.getAbsolutePath());
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			ThiSinh ts = null;
			while ((ts = (ThiSinh) ois.readObject()) != null) {
				ds.add(ts);
			}
			ois.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		this.model.setDsThiSinh(ds);
	}

	public void thucHienOpenFile() {
		JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(this);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			openFile(file);
			thucHienTaiLaiDuLieu();
		}
	}
}