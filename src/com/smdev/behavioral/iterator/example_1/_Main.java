package com.smdev.behavioral.iterator.example_1;

public class _Main {

    public static void main(String[] args) {
        BookCollection collection = new BookCollectionImpl();
        collection.add(new Book("The Ruin of Kings /A Chorus of Dragons, #1/ by Jenn Lyons", BookGenre.FANTASY));
        collection.add(new Book("Black Leopard, Red Wolf (The Dark Star Trilogy)", BookGenre.FANTASY));
        collection.add(new Book("The Bird King by G. Willow Wilson - Goodreads", BookGenre.FANTASY));
        collection.add(new Book("The Strength In Our Scars, by Bianca Sparacino", BookGenre.POETRY));
        collection.add(new Book("End of Watch /Bill Hodges Trilogy, #3/ by Stephen King", BookGenre.MYSTERY));
        collection.add(new Book("The Bird King by G. Willow Wilson - Goodreads", BookGenre.MYSTERY));

        System.out.println("========== Keyword Iterator - \"King\" =========");
        BookIterator keywordIt = collection.keywordIterator("King");
        traverseCollection(keywordIt);

        System.out.println("========== Genre Iterator - \"Mystery\" =========");
        BookIterator mysteryIt = collection.genreIterator(BookGenre.MYSTERY);
        traverseCollection(mysteryIt);

        System.out.println("========== Genre Iterator - \"Fantasy\" =========");
        BookIterator fantasyIt = collection.genreIterator(BookGenre.FANTASY);
        traverseCollection(fantasyIt);
    }

    private static void traverseCollection(BookIterator iterator) {
        Book book;
        while (iterator.hasNext()) {
            book = iterator.next();
            if (book != null) {
                System.out.println(book);
            }
        }
        System.out.println();
    }
}
