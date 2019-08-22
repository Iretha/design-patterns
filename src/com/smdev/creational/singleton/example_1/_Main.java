package com.smdev.creational.singleton.example_1;

public class _Main {

    public static void main(String[] args) {

        MobileDeviceClassSingleton myPhone = MobileDeviceClassSingleton.getInstance();

        myPhone.runApp("FACEBOOK", true);
        myPhone.runApp("GMAIL", true);
        myPhone.runApp("SOME-MUSIC-PLAYER", false);
        myPhone.focusApp("SOME-MUSIC-PLAYER");
        myPhone.stopApp("SOME-MUSIC-PLAYER");
        myPhone.focusApp("FACEBOOK");

    }
}
