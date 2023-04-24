package kr.ac.kopo.ui;

import java.util.List;

import kr.ac.kopo.service.BoardService;
import kr.ac.kopo.vo.BorrowVO;

public class AdminBookListUI extends BaseUI {
	private BoardService boardService;

	public AdminBookListUI() {
		boardService = new BoardService();
	}
	@Override
	public void execute() throws Exception {
		
		List<BorrowVO> list = boardService.getbookList();

		if (list.isEmpty()) {
			System.out.println("도서 목록이 없습니다.");
			return;
		}
		System.out.println("제목 \t\tISBN\t작가\t출판사");
		System.out.println("----------------------------------------------------");
		for (BorrowVO borrow : list) {
			System.out.printf("%s\t\t%s\t%s\t%s%n", borrow.getTitle(), borrow.getIsbn(), borrow.getAuthor(),
					borrow.getPublisher());
		}

	}

}
