package kr.ac.kopo.ui;
import java.util.Scanner;
import kr.ac.kopo.dao.BoardDAO;

public class RentaBookUI extends BaseUI {
    private Scanner sc;
    

	private String user_id;
	private BoardDAO boardDAO;

	public RentaBookUI(String user_id) {
	    this.user_id = user_id;
	    this.boardDAO = new BoardDAO();
	}
    public void execute() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("대출할 도서의 ISBN 번호를 입력하세요: ");
        String isbn = sc.nextLine();
        
        BoardDAO boardDao = new BoardDAO();
        try {
            boolean isSuccess = boardDao.rentBook(isbn, user_id);
            
            if (isSuccess) {
                System.out.println("도서 대출이 성공적으로 처리되었습니다.");
            } else {
                System.out.println("도서 대출에 실패했습니다.");
            }
        } catch (Exception e) {
            System.out.println("오류가 발생했습니다.");
            e.printStackTrace();
        }
    }
}


/*
import java.util.Scanner;

import kr.ac.kopo.dao.BoardDAO;
import kr.ac.kopo.vo.BoardVO;
import kr.ac.kopo.vo.User;

public class RentaBookUI extends BaseUI {
    private Scanner sc;
    

	private String user_id;
	private BoardDAO boardDAO;

    public RentaBookUI(String user_id) {
        this.user_id = user_id;
        boardDAO = new BoardDAO();
        sc = new Scanner(System.in);
    }
	public void execute() throws Exception {
		System.out.println("도서 대여를 진행합니다.");
		System.out.print("대여할 도서의 ISBN을 입력하세요: ");
		String isbn = sc.next();

		BoardVO book = boardDAO.getBook(isbn);
		if (book == null) {
			System.out.println("존재하지 않는 책입니다.");
			return;
		}

		int qty = book.getQty();
		if (qty == 0) {
			System.out.println("현재 대여 가능한 도서가 없거나 이미 대여중입니다.");
			return;
		}

		System.out.println("도서명: " + book.getTitle());
		System.out.println("작가명: " + book.getAuthor());
		System.out.println("출판사: " + book.getPublisher());
		System.out.println("대여 가능 수량: " + qty);

		System.out.print("대여하시겠습니까? (Y/N) ");
		String answer = sc.next();
		if (answer.equalsIgnoreCase("Y")) {
		    boolean success = boardDAO.rentBook(isbn,user_id);
		    if (success) {
		        System.out.println("도서 대여가 완료되었습니다.");
		    } else {
		        System.out.println("도서 대여에 실패하였습니다.");
		    }
		} else {
		    System.out.println("도서 대여가 취소되었습니다.");
		}
	}
}*/
