package kr.ac.kopo.ui;

import kr.ac.kopo.BoardServiceFactory;
import kr.ac.kopo.service.BoardService;
import kr.ac.kopo.vo.BoardVO;

public class AddBookUI extends BaseUI {
	private BoardService boardService;
	
	public AddBookUI() {
		boardService = BoardServiceFactory.getInstance();
	}
	   public void execute() throws Exception {
	        BoardVO board = new BoardVO();
	        
	        
	        	String title = scanStr("추가하실 도서의 제목을 입력하세요 : ");
	        	String isbn = scanStr("추가 하실 도서의 ISBN을 입력하세요 : ");
	        	String author = scanStr("추가 하실 도서의 작가를 입력하세요 : ");
	        	String publisher = scanStr("추가 하실 도서의 출판사를 입력하세요 : ");
	        	int qty = scanInt("추가 하실 도서의 수량을 입력하세요 : ");
	        	
	        	board.setTitle(title);
	        	board.setIsbn(isbn);
	        	board.setAuthor(author);
	        	board.setPublisher(publisher);
	        	board.setQty(qty);
	        	
	        	boardService.insertBook(board);
	        	
	        	System.out.println("책 추가가 완료되었습니다.");
	        
	

}
}
