package com.example.demo.utils;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;

import java.util.ArrayList;
import java.util.List;

public class OOMTest {
    public static void main(String[] args) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        /*while(i<1000){
            i++;
            stringBuilder.append(System.currentTimeMillis());
        }*/
        List<JText> list = new ArrayList<>();
        for( ; ; ) {
            list.add(new JText());
            System.gc();
        }
    }

}
class JText{
    String name;
}