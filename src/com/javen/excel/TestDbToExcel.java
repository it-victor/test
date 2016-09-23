package com.javen.excel;

import java.io.File;
import java.util.List;

import com.javen.des.DES;
import com.javen.entity.RogdAgencyEntity;
import com.javen.service.RogdAgencyService;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class TestDbToExcel {

	public static void main(String[] args) {
		try {
			WritableWorkbook wwb = null;
			 
			   // ������д���Excel������
			   String fileName = "D://book9.xls";
			   File file=new File(fileName);
			   if (!file.exists()) {
				   file.createNewFile();
			   }
			   //��fileNameΪ�ļ���������һ��Workbook
			   wwb = Workbook.createWorkbook(file);

			   // ����������
			   WritableSheet ws = wwb.createSheet("Test Shee 1", 0);
			   
			   //��ѯ���ݿ������е�����
			   List<RogdAgencyEntity> list= RogdAgencyService.getAllByDb();
			   //Ҫ���뵽��Excel�����кţ�Ĭ�ϴ�0��ʼ
			   Label labelId= new Label(0, 0, "���(id)");//��ʾ��
			   Label labelName= new Label(1, 0, "��ҵ����(name)");
//			   Label labelSex= new Label(2, 0, "(sex)");
//			   Label labelNum= new Label(3, 0, "(num)");
			   
			   ws.addCell(labelId);
			   ws.addCell(labelName);
			   for (int i = 0; i < list.size(); i++) {
				   
				   Label labelId_i= new Label(0, i+1, list.get(i).getId()+"");
				   String name = list.get(i).getEnterpriseName();
				   String key ="aaa1122@#!#$";
				   DES crypt = new DES(key);
			        String encname="";
					try {
						 encname = crypt.decrypt(name);
					} catch (Exception e) {
						e.printStackTrace();
					}
				   
				   Label labelName_i= new Label(1, i+1, encname);
				   ws.addCell(labelId_i);
				   ws.addCell(labelName_i);
			   }
			 
			  //д���ĵ�
			   wwb.write();
			  // �ر�Excel����������
			   wwb.close();
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
