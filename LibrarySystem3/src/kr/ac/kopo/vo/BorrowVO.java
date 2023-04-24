package kr.ac.kopo.vo;

import java.util.List;

import kr.ac.kopo.dao.BoardDAO;

public class BorrowVO {

	private String id;
	private String userId;
	private String isbn;
	private String title;
	private String author;
	private String publisher;
	private int qty;
	private String borrowDate;
	private String returnDate;
	BoardDAO boardDao = new BoardDAO();

	public BorrowVO(String id, String userId, String isbn, String title, String author, String publisher, int qty,
			String borrowDate, String returnDate) {
		this.id = id;
		this.userId = userId;
		this.isbn = isbn;
		this.title = title;
		this.author = author;
		this.publisher = publisher;
		this.qty = qty;
		this.borrowDate = borrowDate;
		this.returnDate = returnDate;

	}

	public String getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}

	public void getBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}

	public void setBorrowDate(String borrowDate) {
		this.borrowDate = borrowDate;
	}

	public String getBorrowDate() {
		return borrowDate != null ? borrowDate.toString() : "";
	}

	public BorrowVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public List<BorrowVO> getBorrowList(String id) throws Exception {
		return boardDao.getBorrowList(id);
	}

	@Override
	public String toString() {
		return "BorrowVO [id=" + id + ", userId=" + userId + ", isbn=" + isbn + ", title=" + title + ", author="
				+ author + ", publisher=" + publisher + ", qty=" + qty + ", borrowDate=" + borrowDate + "]";
	}

}
