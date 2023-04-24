/*package kr.ac.kopo.ui;

import java.util.Scanner;

import kr.ac.kopo.dao.BoardDAO;
import kr.ac.kopo.vo.BoardVO;

public class BookListUI extends LogInUI {
    private Scanner sc;
    private BoardDAO dao;

    public BookListUI() {
        sc = new Scanner(System.in);
        dao = new BoardDAO();
    }

    public void execute() {
        System.out.print("검색할 도서명을 입력하세요: ");
        String title = sc.next();

        BoardVO book = dao.SearchBook();
        if (book != null) {
            System.out.println("도서명: " + book.getTitle());
            System.out.println("저자명: " + book.getAuthor());
            System.out.println("출판사: " + book.getPublisher());
            System.out.println("재고 수량: " + book.getQty());
        } else {
            System.out.println("찾으시는 책이 없습니다.");
        }
    }
}
*/