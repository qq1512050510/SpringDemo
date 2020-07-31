package com.example.demo.controller;

import com.example.demo.configuration.ServerConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.example.demo.bean.Person;

import javax.sql.DataSource;
import java.sql.SQLException;

@CrossOrigin(allowCredentials = "true")
@Controller
@RequestMapping("/database")
@Api(value = "database-api", tags = {"数据库接口"})
public class DataSourceController {

    private static final Logger log = LoggerFactory.getLogger(DataSourceController.class);
    //@Autowired
    //private DataSource dataSource;

    @Autowired
    // @Qualifier("hikariDataSource")
    private DataSource hikariDataSource;

    @Autowired
    private ServerConfig serverConfig;

    @Autowired
    private Person person;

    @RequestMapping(value = "/index",method = {RequestMethod.GET,RequestMethod.POST})
    @ResponseBody
    public String index() throws SQLException {
        //System.out.println(dataSource.getConnection());
        // System.out.println(dataSource);
        System.out.println(hikariDataSource.getConnection());
        System.out.println(hikariDataSource);
        person.speak();
        return "hello spring boot";
    }

    @RequestMapping(value = "/url",method = {RequestMethod.GET,RequestMethod.POST})
    //@ApiOperation(value = "")
    //@ApiImplicitParam(name="getUrl",value = "获取服务器地址",dataType = "String",allowMultiple = false)
    @ResponseBody
    public String getUrl(@RequestParam String param) {
        log.info(param);
        return serverConfig.getUrl();
    }
}
