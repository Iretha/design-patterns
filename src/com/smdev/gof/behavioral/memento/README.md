# The Memento Design Pattern 

GoF Design Patterns -> Behavioral Design Patterns

- [Example_1](https://github.com/Iretha/ebook-design-patterns/tree/master/src/com/smdev/gof/behavioral/memento) 

## What problems does it solve? Why to use it?
Allows you to restore object to it's previous state.

Glossary:
- Memento - the snapshot/ the state of the object (originator), should be immutable, so that once created, could not be changed
- Originator - the objects, whose states we are going to keep
- Caretaker - responsible for storing the states of the originator and restoring them thanks to the memento object

## When to use it?
When you need to restore a previous state of an object (to rollback/ to revert/ to undo changes).

## Pros:
- easy to restore to previous state

## Cons:
- additional operations to create/ keep memento-s (snapshots)
- increases the memory usage
- more code to maintain

## Examples from Java API
Recognizable by behavioral methods which internally changes the state of the whole instance
```
java.util.Date (the setter methods do that, Date is internally represented by a long value)
All implementations of java.io.Serializable
All implementations of javax.faces.component.StateHolder
```
## Examples
### Example 1 - How to implement it?
We can create a simple Version Control System (liteCVS). We will commit files and keep track of the changes with each
commit. Then we can list all commits and revert a state from specific commit.

1). Create the Originator
```java
/**
 * The Originator
 */
@ToString
public class TextFile {

    @Getter
    private String id;

    @Getter
    private String name;

    @Getter
    private String content;

    public TextFile(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public TextFileMemento createSnapShot(int serialNo, String commitMessage){
        return new TextFileMemento(serialNo, this.id, this.name, this.content, commitMessage);
    }

    public void writeContent(String updatedContent) {
        this.content = updatedContent;
    }
}
```
2). Create the immutable memento object
```java
/**
 * The Memento Object - should be immutable
 */
@ToString
public class TextFileMemento {

    private int serialNo;

    @Getter
    private String id;

    @Getter
    private String name;

    @Getter
    private String content;

    private String commitMessage;
    private Date commitTime;

    public TextFileMemento(int serialNo, String id, String name, String content, String commitMessage) {
        this.serialNo = serialNo;

        this.id = id;
        this.name = name;
        this.content = content;

        this.commitMessage = commitMessage;
        this.commitTime = new Date();
    }
}
```
3). Create the Caretaker
```java
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
```
4). The Demo
```java
public class _Main {

    public static void main(String[] args) {
        TextFile file1 = new TextFile("Draft1.txt");
        file1.writeContent("This is my initial content");

        TextFileVersionManager.commit(file1, "My initial version");
        TextFileVersionManager.listCommits(file1);

        file1.writeContent("Content Modified 2");
        TextFileVersionManager.commit(file1, "Version 2");
        TextFileVersionManager.listCommits(file1);

        TextFileVersionManager.restore(file1, 0);
        TextFileVersionManager.listCommits(file1);

        System.out.println("Current state of the file: " + file1);
    }
}
```
Output:
```
Commit created: Draft1.txt
Commits found: Draft1.txt
TextFileMemento(serialNo=0, id=56eeab5a-6d59-435b-8fbd-0d7f3b137ff4, name=Draft1.txt, content=This is my initial content, commitMessage=My initial version, commitTime=Mon Jul 08 18:48:17 EEST 2019)
Commit created: Draft1.txt
Commits found: Draft1.txt
TextFileMemento(serialNo=0, id=56eeab5a-6d59-435b-8fbd-0d7f3b137ff4, name=Draft1.txt, content=This is my initial content, commitMessage=My initial version, commitTime=Mon Jul 08 18:48:17 EEST 2019)
TextFileMemento(serialNo=1, id=56eeab5a-6d59-435b-8fbd-0d7f3b137ff4, name=Draft1.txt, content=Content Modified 2, commitMessage=Version 2, commitTime=Mon Jul 08 18:48:17 EEST 2019)
Rollback to: 0
No commits found: Draft1.txt
Current state of the file: TextFile(id=56eeab5a-6d59-435b-8fbd-0d7f3b137ff4, name=Draft1.txt, content=This is my initial content)
```