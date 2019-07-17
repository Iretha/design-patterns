package com.smdev.behavioral.mediator.example_1;

public class _Main {

    public static void main(String[] args) {

        String cloudAccount = "smdev.cloud";

        Mediator mediator = new MediatorCloud();

        // add 2 files => connect to the cloud
        Colleague homePc = new ClientWindows("homePc", mediator);
        homePc.addFile("WorkFlow_Example.txt");
        homePc.addFile("DraftLetter.doc");
        homePc.connect(cloudAccount);
        System.out.println();

        // connect to cloud => got synced =>  add 3 more files
        Colleague notebook1 = new ClientMacOs("notebook1", mediator);
        notebook1.connect(cloudAccount);
        notebook1.addFile("CV.pdf");
        notebook1.addFile("Motivation1.doc");
        notebook1.addFile("Motivation2.doc");
        System.out.println();

        // connect to cloud => got synced =>  add 1 more file
        Colleague workPc = new ClientWindows("workPc", mediator);
        workPc.connect(cloudAccount);
        workPc.addFile("Homework.doc");

        // connect to clod => got synced
        Colleague phone = new ClientAndroid("mobile phone", mediator);
        phone.connect(cloudAccount);
    }
}
