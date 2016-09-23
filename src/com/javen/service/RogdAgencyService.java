package com.javen.service;

import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;

import com.javen.db.DBhepler;
import com.javen.entity.RogdAgencyEntity;


public class RogdAgencyService {
	/**
	 * 查询rogd_agency表中所有的数据
	 * @return 
	 */
	public static List<RogdAgencyEntity> getAllByDb(){
		List<RogdAgencyEntity> list=new ArrayList<RogdAgencyEntity>();
		try {
			DBhepler db=new DBhepler();
			String sql="select * from rogd_agency";
			ResultSet rs= db.Search(sql, null);
			while (rs.next()) {
				int id=rs.getInt("id");
				String enterpriseName=rs.getString("enterprise_name");
				int memberNumber=rs.getInt("member_number");
				list.add(new RogdAgencyEntity(id,  memberNumber,  enterpriseName) );
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 查询指定目录中电子表格中所有的数据
	 * @param file 文件完整路径
	 * @return
	 */
	public static List<RogdAgencyEntity> getAllByExcel(String file){
		List<RogdAgencyEntity> list=new ArrayList<RogdAgencyEntity>();
		try {
			Workbook rwb=Workbook.getWorkbook(new File(file));
//			Sheet rs=rwb.getSheet("Test Shee 1");//或者rwb.getSheet(0)
			Sheet rs=rwb.getSheet(0);
			int clos=rs.getColumns();//得到所有的列
			int rows=rs.getRows();//得到所有的行
			
			System.out.println(clos+" rows:"+rows);
			for (int i = 1; i < rows; i++) {//列数
				for (int j = 0; j < clos; j++) {//行数
					//第一个是列数，第二个是行数
					String id=rs.getCell(j++, i).getContents();//默认最左边编号也算一列 所以这里得j++
					String name=rs.getCell(j++, i).getContents();
					
					list.add(new RogdAgencyEntity(Integer.parseInt(id), name));
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return list;
		
	}
	
	/**
	 * 通过Id判断是否存在
	 * @param id
	 * @return
	 */
	public static boolean isExist(int id){
		try {
			DBhepler db=new DBhepler();
			ResultSet rs=db.Search("select * from rogd_agency where id=?", new String[]{id+""});
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean isExistbyName(String  name){
		try {
			DBhepler db=new DBhepler();
			ResultSet rs=db.Search("select * from rogd_agency where enterprise_name=?", new String[]{name});
			if (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		List<RogdAgencyEntity> all=getAllByDb();
		for (RogdAgencyEntity RogdAgencyEntity : all) {
			System.out.println(RogdAgencyEntity.toString());
		}
		
		System.out.println(isExist(1));
		
	}
	
}
