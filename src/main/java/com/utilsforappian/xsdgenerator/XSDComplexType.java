package com.utilsforappian.xsdgenerator;

public class XSDComplexType {
	
	private String name; 
	private XSDSequence sequence ;
	
	public XSDComplexType(String name) {
		super();
		this.name = name;
	}
	
	public void setSequence(XSDSequence sequence){
		this.sequence = sequence ;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public XSDSequence getSequence() {
		return sequence;
	}

	@Override
	public String toString() {
		return "XSDComplexType [name=" + name + ", sequence=" + sequence + "]";
	}
	
	
	
}
