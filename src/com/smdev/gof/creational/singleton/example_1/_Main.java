package com.smdev.gof.creational.singleton.example_1;

public class _Main {

    public static void main(String[] args) {

        MobileDevice myPhone = MobileDevice.getInstance();

        myPhone.runApp("FACEBOOK", true);
        myPhone.runApp("GMAIL", true);
        myPhone.runApp("SOME-MUSIC-PLAYER", false);
        myPhone.focusApp("SOME-MUSIC-PLAYER");
        myPhone.stopApp("SOME-MUSIC-PLAYER");
        myPhone.focusApp("FACEBOOK");

    }
}
