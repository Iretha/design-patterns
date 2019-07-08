package com.smdev.gof.behavioral.memento.example_1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Caretaker
 */
public final class TextFileVersionManager {

    private static Map<String, List<TextFileMemento>> states = new HashMap<>();

    private TextFileVersionManager() {
    }

    public static void commit(TextFile file, String commitMessage) {
        List<TextFileMemento> snapshots = states.get(file.getId());
        if (snapshots == null) {
            snapshots = new ArrayList<>();
            states.put(file.getId(), snapshots);
        }

        states.get(file.getId()).add(file.createSnapShot(snapshots.size(), commitMessage));

        System.out.println("Commit created: " + file.getName());
    }

    public static void listCommits(TextFile file) {
        List<TextFileMemento> commits = states.get(file.getId());
        if (commits == null || commits.isEmpty()) {
            System.out.println("No commits found: " + file.getName());
        } else {

            System.out.println("Commits found: " + file.getName());
            for (TextFileMemento commit : commits) {
                System.out.println(commit);
            }
        }
    }

    public static void restore(TextFile file, int commitSerialNo) {
        List<TextFileMemento> commits = states.get(file.getId());
        if (commits != null && commitSerialNo > -1) {
            TextFileMemento versionToRestore = commits.get(commitSerialNo);
            file.writeContent(versionToRestore.getContent());

            states.put(file.getId(), commits.subList(0, commitSerialNo));

            System.out.println("Rollback to: " + commitSerialNo);
        }
    }
}
