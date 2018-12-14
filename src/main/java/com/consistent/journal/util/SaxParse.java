package com.consistent.journal.util;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

public class SaxParse {
	private static final Log log = LogFactoryUtil.getLog(SaxParse.class);
	
	public void getSax(){
		try {
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			
			DefaultHandler handler = new DefaultHandler() {
				boolean bfname = false;
				boolean blname = false;
				boolean bnname = false;
				boolean bsalary = false;
				
				public void startElement(String usr, String localName, String qName, Attributes attributes) throws SAXException{
					log.info("Star Element: "+ qName);
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
