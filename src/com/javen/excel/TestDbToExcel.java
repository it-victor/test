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
			 
			   // 创建可写入的Excel工作簿
			   String fileName = "D://book9.xls";
			   File file=new File(fileName);
			   if (!file.exists()) {
				   file.createNewFile();
			   }
			   //以fileName为文件名来创建一个Workbook
			   wwb = Workbook.createWorkbook(file);

			   // 创建工作表
			   WritableSheet ws = wwb.createSheet("Test Shee 1", 0);
			   
			   //查询数据库中所有的数据
			   List<RogdAgencyEntity> list= RogdAgencyService.getAllByDb();
			   //要插入到的Excel表格的行号，默认从0开始
			   Label labelId= new Label(0, 0, "编号(id)");//表示第
			   Label labelName= new Label(1, 0, "企业名称(name)");
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
			 
			  //写进文档
			   wwb.write();
			  // 关闭Excel工作簿对象
			   wwb.close();
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
