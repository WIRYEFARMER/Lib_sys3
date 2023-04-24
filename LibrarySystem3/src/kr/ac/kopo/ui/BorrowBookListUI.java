package kr.ac.kopo.ui;

import java.util.List;
import java.util.Scanner;

import kr.ac.kopo.service.BoardService;
import kr.ac.kopo.vo.BorrowVO;

public class BorrowBookListUI extends LogInUI {
    private BoardService boardService;

    public BorrowBookListUI() {
        boardService = new BoardService();
    }

    @Override
    public void execute() throws Exception {
        Scanner sc = new Scanner(System.in);
        System.out.print("조회할 아이디를 입력하세요 : ");
        String id = sc.nextLine();

        System.out.println("대여 목록을 출력합니다.");

        List<BorrowVO> list = boardService.getBorrowList(id);
        if (list.isEmpty()) {
            System.out.println("대여 목록이 존재하지 않습니다.");
            return;
        }

        System.out.println("ID\tISBN\t\t대여일자\t\t\t반납일자");
        System.out.println("-------------------------------------------------------");
        for (BorrowVO borrow : list) {
            System.out.printf("%s\t%s\t%s\t%s%n", borrow.getUserId(), borrow.getIsbn(), borrow.getBorrowDate(),
                    borrow.getReturnDate());
        }
    }
}

