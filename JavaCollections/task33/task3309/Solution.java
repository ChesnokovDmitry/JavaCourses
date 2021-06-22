package com.javarush.task.task33.task3309;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Комментарий внутри xml
*/

public class Solution {
    public static String toXmlWithComment(Object obj, String tagName, String comment) throws ParserConfigurationException, TransformerException, JAXBException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setCoalescing(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.newDocument();

        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(obj, document);

        document.getDocumentElement().normalize();

        NodeList nodeList = document.getDocumentElement().getElementsByTagName("*");

        if (nodeList.getLength() == 0) {
            return "";
        } else {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Element element1 = (Element) nodeList.item(i);

                if (element1.getNodeName().equalsIgnoreCase(tagName)) {
                    Comment comment1 = document.createComment(comment);
                    element1.getParentNode().insertBefore(comment1, element1);
                    element1.getParentNode().insertBefore(document.createTextNode("\n"), element1);
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.STANDALONE, "no");
            DOMSource source = new DOMSource(document);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            transformer.transform(source, result);

            return result.getWriter().toString();
        }
    }

    @XmlType(name = "home")
    @XmlRootElement
    public static class Home {
        @XmlElement
        List<String> apartment = new ArrayList<>(Arrays.asList("    Apartment 1  ", "    Apartment 2  ", "    Apartment 3  "));
        @XmlElement
        int number = 1;
        @XmlElement
        char aChar = 'q';

        public Home() {
        }
    }

    public static void main(String[] args) throws JAXBException, ParserConfigurationException, IOException, SAXException, TransformerException {
        Home home = new Home();

        System.out.println(toXmlWithComment(home, "apartment", "for rent"));
    }
}