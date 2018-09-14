import java.io.File;
import java.util.ArrayList;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class FileGenerator implements Runnable{

	private String fileName;
	private Random random;
	
	public FileGenerator(String fileName) {
		this.fileName = fileName;
		random =  new Random();
	}
	
	public void run() {
		int number;
	    ArrayList<Number> randomNumbers = new ArrayList<Number>();
		
	    //Creating random numbers
		for(int  i = 0; i < Constants.SIZE; i++){
			
			number = random.nextInt(Constants.RANGE) + 1;
			randomNumbers.add(new Number(number));
		}
		
		//Random numbers are writing to XML file
		this.fileName = "randomNumbers-" + fileName + ".xml";
		createXML(this.fileName, randomNumbers, false);
		System.out.println("Thread " + fileName + " has done!");
	}

	public static void createXML(String fileName, ArrayList<Number> numbers, boolean writeCount){
		
		try{
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			
			Document doc = docBuilder.newDocument();
			Element sayiList = doc.createElement("sayiList");
			doc.appendChild(sayiList);
			
			for(int i = 0; i < numbers.size(); i++){
				String number = String.valueOf(numbers.get(i).getNumber());
			
				Element sayi = doc.createElement("sayi");
				sayiList.appendChild(sayi);
				sayi.setAttribute("index", String.valueOf(i + 1));
				if(writeCount){
					sayi.setAttribute("tekrarAdet", String.valueOf(numbers.get(i).getCount()));
				}
				sayi.appendChild(doc.createTextNode(number));
			}
			
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(fileName));
			
			transformer.transform(source, result);
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		catch (TransformerException e) {
			e.printStackTrace();
		  }
	}
}
