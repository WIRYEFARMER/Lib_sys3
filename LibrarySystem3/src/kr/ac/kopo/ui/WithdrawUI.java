package kr.ac.kopo.ui;

import kr.ac.kopo.BoardServiceFactory;
import kr.ac.kopo.service.BoardService;
import kr.ac.kopo.vo.BoardVO;

public class WithdrawUI extends BaseUI {
	private BoardService boardService;

	public WithdrawUI() {
		boardService = BoardServiceFactory.getInstance();
	}

	@Override
	public void execute() throws Exception {
		System.out.println("회원 탈퇴 화면입니다.");
		String id = scanStr("탈퇴 하실 아이디를 입력하세요 : ");
		String pw = scanStr("패스워드를 입력하세요 : ");

		BoardVO board = boardService.selectBoard(id, pw);

		if (board != null) {
			boolean loggedIn = true;
			while (loggedIn) {
				int choice = scanInt("확실합니까? 1. 회원탈퇴 2. 취소");
				switch (choice) {
				case 1:
					if (boardService.withdrawMember(id)) {
						System.out.println("회원탈퇴가 완료되었습니다.");
						loggedIn = false;
					} else {
						System.out.println("회원탈퇴에 실패했습니다. 다시 시도해주세요.");
					}
					break;
				case 2:
					loggedIn = false;
					System.out.println("회원탈퇴가 취소되었습니다.");
					break;
				default:
					System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
					break;
				}
			}
		} else {
			System.out.println("아이디나 패스워드가 잘못되었습니다. 다시 시도해주세요.");
		}
	}
}
