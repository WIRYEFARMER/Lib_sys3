package kr.ac.kopo.ui;

import kr.ac.kopo.BoardServiceFactory;
import kr.ac.kopo.service.BoardService;
import kr.ac.kopo.vo.BoardVO;
import kr.ac.kopo.vo.User;

public class AdminLogInUI extends BaseUI {
	
	private BoardService service;

	public AdminLogInUI() {
		service = BoardServiceFactory.getInstance();
	}

	@Override
	public void execute() throws Exception {
		String id = scanStr("관리자아이디 : ");
		String pw = scanStr("패스워드 : ");
		BoardVO board = service.selectAdminBoard(id, pw);
		User user = new User(id);

		if (board != null) {
			System.out.println("관리자 로그인에 성공하였습니다.");
			boolean loggedIn = true;
			while (loggedIn) {
				int type = scanInt("1.도서추가 \n2.도서삭제 \n3.도서목록출력 \n0.로그아웃 : ");
				switch (type) {
				case 1:
					AddBookUI addBookUI = new AddBookUI();
					addBookUI.execute();
					break;

				case 2:
					DelBookUI delBookUI = new DelBookUI();		
					delBookUI.execute();
				    break;
				case 3:
					AdminBookListUI adminBookListUI = new AdminBookListUI();
					adminBookListUI.execute();
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

