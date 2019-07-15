package com.smdev.gof.behavioral.template_method.example_1;

public class _Main {

    public static void main(String[] args) {
        MiniResumeTemplate miniResume = new MiniResumeTemplate();
        miniResume.export("Jon Snow", "8", "acting");
        miniResume.export("Jane Dow", "5", "content writing");

        System.out.println("\n----------------------------");

        ShortStoryTemplate shortStory = new ShortStoryTemplate();
        shortStory.export("white", "horse", "standing", "tree");
    }
}
