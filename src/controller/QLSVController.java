package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;

import model.ThiSinh;
import model.Tinh;
import view.QLSVView;

public class QLSVController implements ActionListener {
	public QLSVView view;

	public QLSVController(QLSVView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cm = e.getActionCommand();
//		JOptionPane.showMessageDialog(view, "Bạn vừa nhấn vào: "+cm);
		if (cm.equals("Clear")) {
			this.view.xoaForm();
		} else if (cm.equals("Save")) {
			try {
				this.view.thucHienThemThiSinh();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		} else if (cm.equals("Update")) {
			this.view.hienThiThongTinThiSinhDaChon();
		} else if (cm.equals("Remove")) {
			this.view.thucHienXoa();
		} else if (cm.equals("Reset all")) {
			this.view.clear();
		} else if (cm.equals("Search")) {
			this.view.thucHienTim();
		} else if (cm.equals("Cancel")) {
			this.view.thucHienTaiLaiDuLieu();
			this.view.xoaFormTim();
		} else if (cm.equals("About Me")) {
			this.view.hienThiAbout();
		} else if (cm.equals("Exit")) {
			this.view.thoatKhoiChuongTrinh();
		} else if (cm.equals("SaveFile")) {
			this.view.thucHienSaveFile();
		} else if (cm.equals("Open")) {
			this.view.thucHienOpenFile();
		}
	}

}
