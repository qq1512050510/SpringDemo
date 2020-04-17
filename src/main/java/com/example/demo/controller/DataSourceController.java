package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.bean.Person;

import javax.sql.DataSource;
import java.sql.SQLException;

@Controller
public class DataSourceController {
	
    //@Autowired
    //private DataSource dataSource;

    @Autowired
   // @Qualifier("hikariDataSource")
    private DataSource hikariDataSource;
    
    @Autowired
    private Person person;

    @RequestMapping("/index")
    @ResponseBody
    public String index() throws SQLException {
        //System.out.println(dataSource.getConnection());
       // System.out.println(dataSource);
        System.out.println(hikariDataSource.getConnection());
        System.out.println(hikariDataSource);
        person.speak();
        return "hello spring boot";
    }
}
