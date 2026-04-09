package models;

import java.util.Date;

public class BookLedger {
    private final String ledgerId;
    private User user;
    private BookCopy bookCopy;
    private Date issueDt;
    private Date dueDt;
    private Date returnDt;

    public BookLedger(String ledgerId,
                      User user,
                      BookCopy bookCopy,
                      Date issueDt,
                      Date dueDt,
                      Date returnDt){

        this.ledgerId = ledgerId;
        this.user = user;
        this.bookCopy = bookCopy;
        this.issueDt = issueDt;
        this.dueDt = dueDt;
        this.returnDt = returnDt;
    }

    public BookCopy getBookCopy(){
        return bookCopy;
    }

    public Date getIssueDt(){
        return issueDt;
    }

    public Date getDueDt() {
        return dueDt;
    }

    public Date getReturnDt() {
        return returnDt;
    }
    public void setReturnDt(Date returnDt){
        return;
    }
}
