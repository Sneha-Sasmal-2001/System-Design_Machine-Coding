package models;

//copies for a single type of book
public class BookCopy {
    private final String copyId;
    private Book book;
    private boolean isIssued;

public BookCopy(String copyId, Book book, boolean isIssued){
        this.copyId = copyId;
        this.book = book;
        this.isIssued = isIssued;
    }

    public boolean getIsIssued(){
        return isIssued;
    }
    public void setIssued(boolean isIssued){
        return;
    }

    public String getCopyId(){
        return copyId;
    }

    public Book getBook(){
        return book;
    }
}
