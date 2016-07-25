package com.utilsforappian.xsdgenerator;

import java.util.ArrayList;

public class XSDSequence {
	
	private ArrayList<XSDElement> listOfXSDElement  ;

	public XSDSequence() {
		super();
		this.listOfXSDElement = new ArrayList<XSDElement>();
	}
	
	public void addElement(XSDElement element){
		this.listOfXSDElement.add(element) ;
	}

	public ArrayList<XSDElement> getListOfXSDElement() {
		return listOfXSDElement;
	}


	@Override
	public String toString() {
		return "XSDSequence [listOfXSDElement=" + listOfXSDElement + "]";
	}
	
	
	
	
}
