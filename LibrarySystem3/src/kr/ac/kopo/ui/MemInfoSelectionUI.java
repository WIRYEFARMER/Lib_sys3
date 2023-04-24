package kr.ac.kopo.ui;

import kr.ac.kopo.BoardServiceFactory;
import kr.ac.kopo.service.BoardService;
import kr.ac.kopo.vo.BoardVO;
import kr.ac.kopo.vo.User;

public class MemInfoSelectionUI extends LogInUI {

	private BoardService service;

	public MemInfoSelectionUI() {
		service = BoardServiceFactory.getInstance();
	}

	@Override
	public void execute() throws Exception {
		System.out.println("회원정보 수정 페이지로 이동합니다.");
		System.out.println("개인정보 보호를 위해 아이디와 패스워드를 다시 입력 부탁드립니다.");
		String id = scanStr("아이디 : ");
		String pw = scanStr("패스워드 : ");
		BoardVO board = service.selectBoard(id, pw);
		User user = new User(id);
		if (board != null) {
			System.out.println("로그인에 성공하였습니다.");
			boolean loggedIn = true;
			while (loggedIn) {
				int type = scanInt("1.회원정보수정 \n2.회원정보조회 및 도서대출목록 \n3.회원탈퇴 \n0.로그아웃 : ");
				switch (type) {
				case 1:
					MemberInfoUI memberInfoUI = new MemberInfoUI();
					memberInfoUI.execute();
					break;

				case 2:
					MemInfoList meminfoList = new MemInfoList(user.getId());
					meminfoList.execute();
					break;
				case 3:
					WithdrawUI withdrawUI = new WithdrawUI();
					withdrawUI.execute();
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
