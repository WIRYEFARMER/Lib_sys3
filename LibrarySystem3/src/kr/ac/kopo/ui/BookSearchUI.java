package kr.ac.kopo.ui;

import java.util.List;

import kr.ac.kopo.BoardServiceFactory;
import kr.ac.kopo.service.BoardService;
import kr.ac.kopo.vo.BoardVO;

public class BookSearchUI extends LogInUI {

    private BoardService boardService;

    public BookSearchUI() {
        boardService = BoardServiceFactory.getInstance();
    }

    @Override
    public void execute() throws Exception {
        String keyword = scanStr("검색어 : ");

        List<BoardVO> bookList = boardService.showBookList(keyword);
        

        System.out.printf("총 %d권의 도서가 검색되었습니다.%n", bookList.size());
        System.out.println("ISBN \t 책제목 \t작가 \t출판사 \t\t 재고");
        for (BoardVO book : bookList) {
            System.out.printf("%s\t%s\t%s\t%s\t%d%n", book.getIsbn(), book.getTitle(), book.getAuthor(),
                    book.getPublisher(), book.getQty());
        }

        }
    }


