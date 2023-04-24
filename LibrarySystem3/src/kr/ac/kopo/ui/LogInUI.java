package kr.ac.kopo.ui;

import kr.ac.kopo.BoardServiceFactory;
import kr.ac.kopo.dao.BoardDAO;
import kr.ac.kopo.service.BoardService;
import kr.ac.kopo.vo.BoardVO;
import kr.ac.kopo.vo.User;

public class LogInUI extends BaseUI {


	private BoardService service;

	public LogInUI() {
		service = BoardServiceFactory.getInstance();
	}

	@Override
	public void execute() throws Exception {
		String id = scanStr("아이디 : ");
		String pw = scanStr("패스워드 : ");
		BoardVO board = service.selectBoard(id, pw);
		User user = new User(id);

		if (board != null) {
			System.out.println("로그인에 성공하였습니다.");
			boolean loggedIn = true;
			while (loggedIn) {
				int type = scanInt("1.도서검색 \n2.도서대출 \n3.도서대출목록출력 \n4.도서반납 \n5.마이페이지 \n0.로그아웃 : ");
				switch (type) {
				case 1:
					BookSearchUI bookSearchUI = new BookSearchUI();
					bookSearchUI.execute();
					break;

				case 2:
				    RentaBookUI rentaBook = new RentaBookUI(user.getId());
				    rentaBook.execute();
				    break;
				case 3:
					BorrowBookListUI borrowBookListUI = new BorrowBookListUI();
					borrowBookListUI.execute();
					break;
				case 4:
					BoardDAO boardDAO = new BoardDAO();
					ReturnBookUI returnBookUI = new ReturnBookUI(boardDAO);
					returnBookUI.execute();
					break;
				case 5:
					MemInfoSelectionUI memInfoSelectionUI = new MemInfoSelectionUI();
					memInfoSelectionUI.execute();
					break;

				case 0:
					loggedIn = false;
					System.out.println("로그아웃 되었습니다.");
					break;
				default:
					System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
					break;
				}
			}
		} else {
			System.out.println("아이디나 패스워드가 잘못되었습니다.");
		}
	}
}
