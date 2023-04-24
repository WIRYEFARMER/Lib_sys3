package kr.ac.kopo.ui;

import kr.ac.kopo.BoardServiceFactory;
import kr.ac.kopo.service.BoardService;
import kr.ac.kopo.vo.BoardVO;

public class MemberInfoUI extends LogInUI {

	private BoardService boardService;

	public MemberInfoUI() {
		boardService = BoardServiceFactory.getInstance();
	}

	public void execute() throws Exception {
	    String id = scanStr("변경할 회원의 아이디를 입력하세요: ");

	    if (!boardService.isIdExist(id)) {
	        System.out.println("해당 아이디의 회원이 존재하지 않습니다.");
	        return;
	    }

	    BoardVO board = new BoardVO();
	    board.setId(id);

	    String pw = scanStr("새로운 비밀번호를 입력하세요 : ");
	    String phone = scanStr("새로운 휴대폰 번호를 입력하세요 : ");

	    
	    board.setPw(pw != null && !pw.isEmpty() ? pw : null);
	    board.setPhone(phone != null && !phone.isEmpty() ? phone : null);

	    boardService.updateBoard(board);
	    System.out.println("회원 정보가 변경되었습니다.");
	}
}
