package library;

import models.*;
import service.LibraryService;

import java.util.Date;

public class Main {

  public static void main(String[] args) {

    // 1️⃣ Create service (single instance)
    LibraryService libraryService = new LibraryService();

    // 2️⃣ Create users
    Student s1 = new Student("S1", "Sneha", "9999999999", "10", "A");
    Faculty f1 = new Faculty("F1", "Prof Rao", "8888888888");

    // 3️⃣ Create books
    Book book1 = new Book("B1", "Harry Potter", "JK Rowling", "Fantasy");

    // 4️⃣ Create copies
    BookCopy c1 = new BookCopy("C1", book1, false);
    BookCopy c2 = new BookCopy("C2", book1, false);

    // 5️⃣ Add copies to library
    libraryService.addBookCopy(book1, c1);
    libraryService.addBookCopy(book1, c2);

    // 6️⃣ Issue book to student
    boolean issued = libraryService.issueBook(
            "B1",
            s1,
            new Date(),
            new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000)
    );

    System.out.println("Book issued to student: " + issued);

    // 7️⃣ Issue another copy to faculty
    boolean issued2 = libraryService.issueBook(
            "B1",
            f1,
            new Date(),
            new Date(System.currentTimeMillis() + 7L * 24 * 60 * 60 * 1000)
    );

    System.out.println("Book issued to faculty: " + issued2);

    // 8️⃣ Try issuing when no copies left
    boolean issued3 = libraryService.issueBook(
            "B1",
            s1,
            new Date(),
            new Date()
    );

    System.out.println("Third issue attempt (should fail): " + issued3);

    // 9️⃣ Return book
    boolean returned = libraryService.returnBook(
            "C1",
            s1,
            new Date()
    );

    System.out.println("Book returned: " + returned);

    // 🔟 Try issuing again after return
    boolean issued4 = libraryService.issueBook(
            "B1",
            s1,
            new Date(),
            new Date()
    );

    System.out.println("Issue after return: " + issued4);
  }
}