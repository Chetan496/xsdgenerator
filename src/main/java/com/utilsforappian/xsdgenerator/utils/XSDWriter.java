package com.utilsforappian.xsdgenerator.utils;

import java.io.FileWriter;
import java.io.IOException;

import javanet.staxutils.IndentingXMLStreamWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

import com.utilsforappian.xsdgenerator.XSDComplexType;
import com.utilsforappian.xsdgenerator.XSDElement;

public class XSDWriter {
	
	
	private  XMLOutputFactory factory ;
	private  XMLStreamWriter writer = null ;
	
	public XSDWriter(final String xsdFilePath){
	
		factory = XMLOutputFactory.newInstance();
		try {
			writer = factory
					.createXMLStreamWriter(new FileWriter(xsdFilePath));
			writer.setPrefix("xsd", "http://www.w3.org/2001/XMLSchema");
			writer = new IndentingXMLStreamWriter(writer);
			
		} catch (XMLStreamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public  void writeElement(XSDElement element){
		
		try {
			
			writer.writeStartElement("xsd:element");
			
			writer.writeAttribute("name", element.getName());
			writer.writeAttribute("nillable", element.getNillable());
			writer.writeAttribute("type", "xsd:" + element.getType().toLowerCase());
			
			writer.writeStartElement("xsd:annotation");
			
			writer.writeStartElement("xsd:appinfo");
			writer.writeAttribute("source", "appian.jpa");
			writer.writeEndElement();  //end xsd:appinfo
			
			writer.writeEndElement(); //end xsd:annotation
			
			
			writer.writeEndElement();  //end xsd:element
			
		}  catch (XMLStreamException e) {
			System.out
					.println("An error occurred while writing to the XML Stream");
			e.printStackTrace();
		}
	}
	
	
	public void writeComplexType(XSDComplexType complexType, String nameSpace){
		
		try {
			writer.writeStartElement("xsd:schema");
			
			writer.writeAttribute("xmlns:xsd", "http://www.w3.org/2001/XMLSchema");
			writer.writeAttribute("xmlns:tns", nameSpace);
			writer.writeAttribute("targetNamespace", nameSpace);
			
			writer.writeStartElement("xsd:complexType");
			
			writer.writeAttribute("name", complexType.getName());
			
			writer.writeStartElement("xsd:annotation");//annotation for complexType
			
			writer.writeStartElement("xsd:appinfo");
			writer.writeAttribute("source", "appian.jpa");
			writer.writeEndElement(); //end xsd:appinfo
			
			writer.writeEndElement(); //end xsd:annotation for complexType
			
			
			writer.writeStartElement("xsd:sequence");
			for(XSDElement element: complexType.getSequence().getListOfXSDElement()){
				writeElement(element) ;
			}
			writer.writeEndElement(); //end sequence
			
			writer.writeEndElement(); //end complexType
			
			writer.writeEndElement(); //end xsd:schema
			
			writer.writeEndDocument(); //end document
			writer.flush();
			writer.close();
			
		} catch (XMLStreamException e) {
			System.out
			.println("An error occurred while writing to the XML Stream");
	e.printStackTrace();
		}
		
		
	}
	
	
	
}
