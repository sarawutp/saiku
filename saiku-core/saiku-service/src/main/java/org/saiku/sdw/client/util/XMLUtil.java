package org.saiku.sdw.client.util;

import java.io.InputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XMLUtil {
	public static Document buildDocument(InputStream is,boolean namespace) throws Exception{
		DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
		domFactory.setNamespaceAware(namespace);
		DocumentBuilder builder = domFactory.newDocumentBuilder();
		
		return builder.parse(is);
	}
	
	public static NodeList query(Document doc,String xpathQuery) throws Exception{
		XPathFactory factory = XPathFactory.newInstance();
	    XPath xpath = factory.newXPath();
	    
	    XPathExpression expr = xpath.compile(xpathQuery);
	    Object result = expr.evaluate(doc, XPathConstants.NODESET);
	    return (NodeList) result;
	}
	
	public static Node queryNode(Document doc,String xpathQuery) throws Exception{
		XPathFactory factory = XPathFactory.newInstance();
	    XPath xpath = factory.newXPath();
	    
	    XPathExpression expr = xpath.compile(xpathQuery);
	    Object result = expr.evaluate(doc, XPathConstants.NODE);
	    return (Node) result;
	}
}
