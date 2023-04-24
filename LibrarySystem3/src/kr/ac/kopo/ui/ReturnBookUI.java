package kr.ac.kopo.ui;
import java.util.Scanner;

import kr.ac.kopo.dao.BoardDAO;

public class ReturnBookUI extends LogInUI {
    private BoardDAO boardDAO;

    public ReturnBookUI(BoardDAO boardDAO) {
        this.boardDAO = boardDAO;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("도서 반납을 시작합니다.");
        System.out.print("반납할 도서의 ISBN 번호를 입력하세요: ");
        String isbn = scanner.nextLine();

        boolean success = boardDAO.returnBook(isbn);

        if (success) {
            System.out.println("도서 반납이 성공적으로 완료되었습니다.");
        } else {
            System.out.println("도서 반납에 실패하였습니다. ISBN 번호를 확인하고 다시 시도하세요.");
        }
    }
}
