package kr.ac.kopo.ui;

import java.util.Scanner;

import kr.ac.kopo.dao.BoardDAO;

public class MemInfoList extends MemInfoSelectionUI {

	private Scanner sc;
	private String user_id;
	private BoardDAO boardDAO;
	
	public MemInfoList(String user_id) {
		this.user_id = user_id;
		this.boardDAO = new BoardDAO();
	}
	
	public void execute() {
		Scanner sc = new Scanner(System.in);
		System.out.println("아이디를 한번 더 입력하세요 : ");
		String user_id = sc.nextLine();
		
		BoardDAO boardDao = new BoardDAO();
		try {boolean getinfoList = boardDao.meminfoList(user_id);
		if (getinfoList) {
			System.out.println("회원님의 개인 정보는 다음과 같습니다.");
			
		}else {
			System.out.println("입력하신 아이디로 조회가 안됩니다.");
		}
		
		}catch (Exception e) {
			System.out.println("오류 발생");
			e.printStackTrace();
			
		}
		
		}
	}

