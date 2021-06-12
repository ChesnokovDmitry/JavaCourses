package com.javarush.task.task33.task3308;

import javax.xml.bind.annotation.XmlMixed;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@XmlType(name = "shop")
@XmlRootElement
public class Shop {
    public Goods goods = new Goods();

    @XmlMixed
    public int count = 12;

    @XmlMixed
    public double profit = 123.4;

    @XmlMixed
    public String[] secretData = new String[] {"String1", "String2", "String3", "String4", "String5"};

    @XmlType(name = "goods")
    @XmlRootElement
    public static class Goods {
        @XmlMixed
        public List<String> names = new ArrayList<>(Arrays.asList("S1", "S2"));

        public Goods() {
        }
    }
}
