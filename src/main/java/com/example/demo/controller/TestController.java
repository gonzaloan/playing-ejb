package com.example.demo.controller;

import com.gonmunoz.padaejbbeans.ejb.beans.HelloWorld;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ejb.EJB;

@RestController
@RequiredArgsConstructor
@Log4j2
@RequestMapping("test")
public class TestController {

    @EJB
    private HelloWorld helloWorld;


    @GetMapping("/hello")
    public String getStateless() {
        log.info("getStateless-----hello {}", helloWorld.getHelloWorld());
        return helloWorld.getHelloWorld();
    }

}
