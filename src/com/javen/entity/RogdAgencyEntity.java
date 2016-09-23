package com.javen.entity;

public class RogdAgencyEntity {
	private int id;
	private int memberNumber; 
	private String enterpriseName;
	
	
	
	public RogdAgencyEntity(int id, String enterpriseName) {
		super();
		this.id = id;
		this.enterpriseName = enterpriseName;
	}
	public RogdAgencyEntity() {
		super();
	}
	public RogdAgencyEntity(int id, int memberNumber, String enterpriseName) {
		super();
		this.id = id;
		this.memberNumber = memberNumber;
		this.enterpriseName = enterpriseName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMemberNumber() {
		return memberNumber;
	}
	public void setMemberNumber(int memberNumber) {
		this.memberNumber = memberNumber;
	}
	public String getEnterpriseName() {
		return enterpriseName;
	}
	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}
	@Override
	public String toString() {
		return "RogdAgencyEntity [id=" + id + ", memberNumber=" + memberNumber
				+ ", enterpriseName=" + enterpriseName + "]";
	}
	
	
	
}
