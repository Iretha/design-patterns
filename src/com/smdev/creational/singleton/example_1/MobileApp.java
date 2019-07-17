package com.smdev.creational.singleton.example_1;

import lombok.Getter;
import lombok.ToString;

@ToString
public class MobileApp {

    @Getter
    private String appName;

    public MobileApp(String appName) {
        this.appName = appName;
    }

    public void runInBackground(){
        System.out.println(this.appName + " is running in background!");
    }

    public void moveToFocus(){
        System.out.println(this.appName + " is on focus!");
    }

    public void moveToBackground(){
        System.out.println(this.appName + " is moved to background!");
    }

    public void stop(){
        System.out.println(this.appName + " is stopped!");
    }
}
