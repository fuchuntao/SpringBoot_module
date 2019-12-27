package com.javen.controller;

import com.javen.task.AsyncTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Future;

@RestController
public class AsyncController {

    @Autowired
    AsyncTask asyncTask;

    @GetMapping("/test")
    public String test() {
        try {
            long start = System.currentTimeMillis();
            asyncTask.asyncTaskOne();
            asyncTask.asyncTaskTwo();
            asyncTask.asyncTaskThree();
            long end = System.currentTimeMillis();
            return "总耗时:" + (end - start)+"ms";
        } catch (InterruptedException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

    @GetMapping("/asyncCall")
    public String asyncCall() {
        try {
            long start = System.currentTimeMillis();
            Future<String> task = asyncTask.asyncTaskFour();
            while (true){
                if (task.isDone()){
                    break;
                }
            }
            long end = System.currentTimeMillis();
            return "总耗时:" + (end - start)+"ms";
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
