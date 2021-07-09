package com.javarush.task.task34.task3409;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/* 
Настраиваем логгер
*/

public class Solution {
    private static final Logger loggerProperties = LoggerFactory.getLogger(Solution.class);
    private static final Logger loggerXML = LoggerFactory.getLogger(Solution.class);

    public static void main(String args[]) throws IOException {
        String logProperties = "4.JavaCollections/src/" + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/log4j.properties";
        String logXML = "4.JavaCollections/src/" + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/log4j.xml";

        Path path = Paths.get(logProperties).toAbsolutePath();
        try (InputStream is = new FileInputStream(path.toFile())) {
            Properties properties = new Properties();
            properties.load(is);

            PropertyConfigurator.configure(properties);

            loggerProperties.error("Properties. Hello error");
            loggerProperties.warn("Properties. Warn warn!");
            loggerProperties.info("Properties. Information");
            loggerProperties.debug("Properties. Debug");
        }

        Path path1 = Paths.get(logXML).toAbsolutePath();
        try (InputStream is = new FileInputStream(path1.toFile())) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder;
            try {
                builder = factory.newDocumentBuilder();
                Document document = builder.parse(is);

                DOMConfigurator.configure(document.getDocumentElement());

                loggerXML.error("XML. Hello error");
                loggerXML.warn("XML. Warn warn!");
                loggerXML.info("XML. Information");
                loggerXML.debug("XML. Debug");
            } catch (ParserConfigurationException | SAXException e) {
                e.printStackTrace();
            }
        }
    }
}
