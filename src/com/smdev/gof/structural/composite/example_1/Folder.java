package com.smdev.gof.structural.composite.example_1;

import java.util.ArrayList;
import java.util.List;

public class Folder extends File {

    private List<Entry> children = new ArrayList<>();

    public Folder(String name) {
        super(name);
    }

    public void addChild(Entry child) {
        this.children.add(child);
        child.setName(getName() + "\\" + child.getName());
    }

    @Override
    public void ls(boolean recursive) {
        System.out.println(getName());
        for (Entry c : this.children) {
            if(recursive){
                c.ls(true);
            }else{
                System.out.println(c.getName());
            }
        }
    }
}

