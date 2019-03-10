package isdos;


import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class App
{
    public static double calcMean(double[] i) {
        if (i[1] <= 0) return 0;
        return i[0]/i[1];
    }
    public static void main( String[] args ) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(args[0]);
            NodeList nodes = doc.getElementsByTagName("action");

            double[] meanArg = {0, 0};
            double[] meanRes = {0, 0};

            for (int i = 0; i < nodes.getLength(); i++) {

                Node node =  nodes.item(i);
                NodeList childNodes = node.getChildNodes();

                for (int j = 0; j < childNodes.getLength(); j++) {

                    Node item = childNodes.item(j);
                    if (item == null) {
                        meanArg[1]++;
                        meanRes[1]++;
                    } else if (item.getNodeName().equals("argumentsList")) {
                        for (int k = 0; k < item.getChildNodes().getLength(); k++) {
                            if (item.getChildNodes().item(k).getNodeName().equals("argument")) {
                                meanArg[0]++;
                            }
                        }
                    } else if (item.getNodeName().equals("resourcesList")) {
                        for (int k = 0; k < item.getChildNodes().getLength(); k++) {
                            if (item.getChildNodes().item(k).getNodeName().equals("resource")) {
                                meanRes[0]++;
                            }
                        }
                    }
                }
                meanArg[1]++;
                meanRes[1]++;
            }

            System.out.println("Amount of 'action' nodes : " + nodes.getLength());
            System.out.println("Mean arguments: " + calcMean(meanArg));
            System.out.println("Mean resources: " + calcMean(meanRes));

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
