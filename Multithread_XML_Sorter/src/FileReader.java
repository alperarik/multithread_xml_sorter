import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class FileReader {
	public static ArrayList<Number> readXML(String fileName, boolean readCount){
		ArrayList<Number> numbers = new ArrayList<Number>();
		try{
			File fXmlFile = new File(fileName);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder;
				dBuilder = dbFactory.newDocumentBuilder();

			Document doc = dBuilder.parse(fXmlFile);
			
			NodeList nList = doc.getElementsByTagName("sayiList");
			
			for (int i = 0; i < nList.getLength(); i++) {

				Node nNode = nList.item(i);
							
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					
					Element eElement = (Element) nNode;
					
					for(int j = 0 ; j < eElement.getElementsByTagName("sayi").getLength(); j++){
						String number = eElement.getElementsByTagName("sayi").item(j).getTextContent();
						
						Number tmp = new Number(number);
						
						if(readCount){
							String count = eElement.getElementsByTagName("sayi").item(j).getAttributes().getNamedItem("tekrarAdet").getNodeValue();
							tmp.setCount(count);
						}
						addNumber(numbers, tmp);
					}
						
				}
			}
			
			
		}
		
		catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    		
		return numbers;
	}
	
	private static void addNumber(ArrayList<Number> numbers, Number number){

		for(int i = 0; i < numbers.size(); i++){
			if(numbers.get(i).getNumber() == number.getNumber()){
				numbers.get(i).incrementCount();
				return;
			}
		}
		
		numbers.add(number);
	}
}
