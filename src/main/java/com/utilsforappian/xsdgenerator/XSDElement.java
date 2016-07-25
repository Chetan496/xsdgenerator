package com.utilsforappian.xsdgenerator;

public class XSDElement {

	private String name;
	private String nillable;
	private String type;
	
	public String getName() {
		return name;
	}
	public String getNillable() {
		return nillable;
	}
	public String getType() {
		return type;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public void setNillable(String nillable) {
		this.nillable = nillable;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return "XSDElement [name=" + name + ", nillable=" + nillable
				+ ", type=" + type + "]";
	}
	
	
	
}
