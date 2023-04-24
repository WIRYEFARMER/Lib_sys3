package kr.ac.kopo.ui;

import kr.ac.kopo.BoardServiceFactory;
import kr.ac.kopo.service.BoardService;
import kr.ac.kopo.vo.BoardVO;

public class DelBookUI extends BaseUI {
	
private BoardService boardService;
    

    public DelBookUI() {
        boardService = BoardServiceFactory.getInstance();

}
    @Override
    public void execute() throws Exception {
        BoardVO board = new BoardVO();
        
        while (true) {
        	String isbn = scanStr("삭제하실 도서의 isbn번호를 입력하세요 : ");
        	if (boardService.isIsbnExist(isbn)) {
        		System.out.println("삭제하실 도서가 선택되었습니다. 선택한 도서를 삭제합니다");
        		board.setIsbn(isbn);
        		boardService.delBook(board);
        		break;
        		
        		
        	}else {
        		System.out.println("존재 하지 않는 도서입니다.");
        		break;
        	}
        }
    }

    
}
