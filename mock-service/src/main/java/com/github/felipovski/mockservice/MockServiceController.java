package com.github.felipovski.mockservice;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MockServiceController {

    @GetMapping
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/test")
    public String test() {
        return "Test!";
    }
}
