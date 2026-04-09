package service;

import models.Book;
import models.BookCopy;
import models.BookLedger;
import models.User;

import java.util.*;

public class LibraryService {

    private int maxBooksPerUser = 3;

    // All copies in system
    private Map<String, List<BookCopy>> bookCopiesMap = new HashMap<>();

    // userId -> list of active ledgers
    private Map<String, List<BookLedger>> userRecords = new HashMap<>();

    // all ledger entries (history) -> this is whole ledger book. bookledger is a single entry in the physical ledger book
    private List<BookLedger> ledgerBook = new ArrayList<>();


    // ✅ Add Book Copy
    public void addBookCopy(Book book, BookCopy copy) {
        bookCopiesMap.putIfAbsent(book.getId(), new ArrayList<>());
        bookCopiesMap.get(book.getId()).add(copy);
    }


    // ✅ Issue Book
    public boolean issueBook(String bookId, User user, Date issueDate, Date dueDate) {

        // 1. Get all copies of that book
        List<BookCopy> copies = bookCopiesMap.get(bookId);
        if (copies == null) return false;

        // 2. Find available copy
        BookCopy availableCopy = null;
        for (BookCopy copy : copies) {
            if (!copy.getIsIssued()) {
                availableCopy = copy;
                break;
            }
        }

        if (availableCopy == null) return false;

        // 3. Check user limit
        userRecords.putIfAbsent(user.getId(), new ArrayList<>());
        if (userRecords.get(user.getId()).size() >= maxBooksPerUser) {
            return false;
        }

        // 4. Mark issued
        availableCopy.setIssued(true);

        // 5. Create ledger
        String ledgerId = UUID.randomUUID().toString();
        BookLedger ledger = new BookLedger(
                ledgerId,
                user,
                availableCopy,
                issueDate,
                dueDate,
                null
        );

        // 6. Store ledger
        ledgerBook.add(ledger);
        userRecords.get(user.getId()).add(ledger);

        return true;
    }


    // ✅ Return Book
    public boolean returnBook(String copyId, User user, Date returnDate) {

        List<BookLedger> userLedgers = userRecords.get(user.getId());
        if (userLedgers == null) return false; //means the user has never taken any book

        for (BookLedger ledger : userLedgers) {
            if (ledger.getBookCopy().getCopyId().equals(copyId)
                    && ledger.getReturnDt() == null) { //if the user has taken the copy && it is not yet returned

                // mark returned
                ledger.setReturnDt(returnDate);
                ledger.getBookCopy().setIssued(false);

                return true;
            }
        }

        return false;
    }
}