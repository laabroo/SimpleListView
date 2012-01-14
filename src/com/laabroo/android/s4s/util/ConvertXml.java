package com.laabroo.android.s4s.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import android.util.Log;

public class ConvertXml {
	private final static String TAG = "ConvertXml";

	public final static Document XmlFromString(String xml) {

		Document document = null;
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory
				.newInstance();
		try {
			DocumentBuilder documentBuilder = builderFactory
					.newDocumentBuilder();
			InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(new StringReader(xml));
			document = documentBuilder.parse(inputSource);

		} catch (ParserConfigurationException e) {
			Log.i(TAG, e.getMessage());

		} catch (SAXException e) {
			Log.i(TAG, e.getMessage());
		} catch (IOException e) {
			Log.i(TAG, e.getMessage());
		}
		return document;
	}

	public final static String getElementValue(Node element) {
		Node kid;
		if (element != null) {
			if (element.hasChildNodes()) {
				for (kid = element.getFirstChild(); kid != null; kid = kid
						.getNextSibling()) {
					if (kid.getNodeType() == Node.TEXT_NODE) {
						return kid.getNodeValue();
					}
				}
			}
		}
		return "";

	}

	public static String getXML(String param) {
		String line = null;
		String url = "http://search.4shared.com/network/searchXml.jsp?q="
				+ param + "&searchCategory=music";
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpGet httpGet = new HttpGet(url);
			HttpResponse httpResponse = httpClient.execute(httpGet);
			HttpEntity httpEntity = httpResponse.getEntity();
			line = EntityUtils.toString(httpEntity);
		} catch (UnsupportedEncodingException e) {
			Log.i(TAG, e.getMessage());
		} catch (MalformedURLException e) {
			Log.i(TAG, e.getMessage());
		} catch (IOException e) {
			Log.i(TAG, e.getMessage());
		}
		return line;
	}

	public static int numResult(Document document) {
		Node result = document.getDocumentElement();
		int rest = -1;

		try {
			rest = Integer.valueOf(result.getAttributes().getNamedItem("count")
					.getNodeValue());

		} catch (Exception e) {
			rest = -1;
			Log.i(TAG, e.getMessage());
		}
		return rest;
	}

	public static String getValue(Element item, String str) {
		NodeList n = item.getElementsByTagName(str);
		return ConvertXml.getElementValue(n.item(0));
	}
}
