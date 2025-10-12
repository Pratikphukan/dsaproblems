package com.dsaproblems.DSAProblems.advancedJava;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class Author implements Cloneable {
    public String name;

    // Constructor to initialize author name
    public Author(String name) {
        this.name = name;
    }

    // Implement clone method
    @Override
    public Object clone() throws CloneNotSupportedException {
        // This simply creates a shallow copy, which is sufficient because the only field is a string
        return super.clone();
    }
}

class Book implements Cloneable {
    public String title;
    public Author author;

    // Constructor to initialize the Book with title and Author
    public Book(String title, Author author) {
        this.title = title;
        this.author = author;
    }

    // Override clone method for Book
    @Override
    protected Object clone() throws CloneNotSupportedException {
        // Shallow copy of Book object
        Book clonedBook = (Book) super.clone();
        // Deep copy of the Author object to ensure independence between clones
        clonedBook.author = (Author) author.clone();
        return clonedBook;
    }
}

public class BookCloneTest {

    @org.junit.jupiter.api.Test
    public void testDeepClone() throws CloneNotSupportedException {
        // Create an original Author and Book
        Author author = new Author("Original Author");
        Book originalBook = new Book("Original Title", author);

        // Clone the original book
        Book clonedBook = (Book) originalBook.clone();

        // Assert that the books are not the same object (shallow clone check)
        assertNotSame(originalBook, clonedBook);

        // Assert that the author objects are not the same, confirming deep copy on author field
        assertNotSame(originalBook.author, clonedBook.author);

        // Assert that modifying the clone's author name doesn't affect the original
        clonedBook.author.name = "Modified Author";
        assertEquals("Original Author", originalBook.author.name);
    }

    @org.junit.jupiter.api.Test
    public void testClonedTitleReference() throws CloneNotSupportedException {
        // Create an original Book
        Author author = new Author("Author Name");
        Book originalBook = new Book("Same Title", author);

        // Clone the book
        Book clonedBook = (Book) originalBook.clone();

        // Since title is immutable (String), it can be the same reference
        assertEquals(originalBook.title, clonedBook.title);
    }

    // Additional test: Verify that cloned book remains independent when author is changed later.
    @org.junit.jupiter.api.Test
    public void testIndependentAuthorAfterCloning() throws CloneNotSupportedException {
        // Arrange: create original book instance
        Author author = new Author("Author One");
        Book originalBook = new Book("Book Title", author);

        // Act: clone the book and modify the author's name in the clone
        Book clonedBook = (Book) originalBook.clone();
        clonedBook.author.name = "Author Two";

        // Assert: original book's author remains unchanged
        assertEquals("Author One", originalBook.author.name);
        assertEquals("Author Two", clonedBook.author.name);
    }

    // Edge test: Clone after altering the author to ensure behavior is consistently deep copy
    @Test
    public void testCloneAfterAuthorModification() throws CloneNotSupportedException {
        // Arrange: create the original book
        Author author = new Author("Initial Author");
        Book originalBook = new Book("Initial Title", author);

        // Act: modify original author and then clone
        originalBook.author.name = "Updated Author";
        Book clonedBook = (Book) originalBook.clone();

        // Assert: cloned author's name should match the modified name at clone time
        assertEquals("Updated Author", clonedBook.author.name);
    }
}
