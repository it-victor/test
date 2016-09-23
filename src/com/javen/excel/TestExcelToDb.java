package com.javen.excel;

import java.util.List;

import com.javen.db.DBhepler;
import com.javen.des.DES;
import com.javen.entity.RogdAgencyEntity;
import com.javen.service.RogdAgencyService;


public class TestExcelToDb {
	public static void main(String[] args) {
		//得到表格中所有的数据
		List<RogdAgencyEntity> listExcel=RogdAgencyService.getAllByExcel("d://book5.xls");
		/*//得到数据库表中所有的数据
		List<RogdAgencyEntity> listDb=StuService.getAllByDb();*/
		
		DBhepler db=new DBhepler();
		
		for (RogdAgencyEntity ra : listExcel) {
			int id=ra.getId();
			String name = ra.getEnterpriseName();
//			DESEncrypt enc = new DESEncrypt();
//			byte[] p12_01 = enc.createEncryptor(name);
//			String encname =new String(p12_01);
			  String key ="aaa1122@#!#$";
	        DES crypt = new DES(key);
	        String encname="";
			try {
				 encname = crypt.encrypt(name);
				 System.out.println((new StringBuilder("Encode:")).append(crypt.encrypt(name)));
			     System.out.println((new StringBuilder("Decode:")).append(crypt.decrypt(encname)));
					
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (!RogdAgencyService.isExist(id)  && !RogdAgencyService.isExistbyName(encname)  ) {
				//不存在就添加
				String sql="insert into rogd_agency (enterprise_name,del_flag,organization_number) values(?,?,?)";
				String[] str=new String[]{encname,0+"",System.currentTimeMillis()+""};
				db.AddU(sql, str);
			}else {
				//存在就不更新
				System.out.println(ra.toString()+"数据库中已经存在，不在导入");
				
				//存在就更新
//				String sql="update rogd_agency set name=? where id=?";
//				String[] str=new String[]{ra.getEnterpriseName(),id+""};
//				db.AddU(sql, str);
			}
		}
	}
}
