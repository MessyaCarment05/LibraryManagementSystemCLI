package librarycli;

import java.time.LocalDateTime;
import java.util.*;
public class Transaction {
	private Member user;
	private MeetingRoom borrow;
	private String TransactionType;
	private LocalDateTime TransactionDate = LocalDateTime.now();
	private int TransactionID;
	public Transaction(Member user, MeetingRoom borrow, String transactionType, LocalDateTime transactionDate,int transactionID)
	{
		super();
		this.user = user;
		this.borrow = borrow;
		TransactionType = transactionType;
		TransactionDate = transactionDate;
		TransactionID = transactionID;
	}
	public Member getUser() {
		return user;
	}
	public void setUser(Member user) {
		this.user = user;
	}
	public MeetingRoom getBorrow() {
		return borrow;
	}
	public void setBorrow(MeetingRoom borrow) {
		this.borrow = borrow;
	}
	public String getTransactionType() {
		return TransactionType;
	}
	public void setTransactionType(String transactionType) {
		TransactionType = transactionType;
	}
	public LocalDateTime getTransactionDate() {
		return TransactionDate;
	}
	public void setTransactionDate(LocalDateTime transactionDate) {
		TransactionDate = transactionDate;
	}
	public int getTransactionID() {
		return TransactionID;
	}
	public void setTransactionID(int transactionID) {
		TransactionID = transactionID;
	}
	public void viewsTransaction() {	
	
		System.out.printf("| %-13s | %-15s | %-20s | %-15s | %-15s | %-30s |\n", this.TransactionID, this.TransactionType, user.getName(),user.getUserID(), borrow.getName(), this.TransactionDate);
	}

	
}

