package kr.ac.kopo.service;
import java.util.List;

import kr.ac.kopo.dao.BoardDAO;
import kr.ac.kopo.vo.BoardVO;
import kr.ac.kopo.vo.BorrowVO;

public class BoardService {

    private BoardDAO boardDao;

    public BoardService() {
        boardDao = new BoardDAO();
    }

    public List<BoardVO> selectBookList(String keyword) {
        return boardDao.selectBookList(keyword);
    }
    public void delBook(BoardVO board) throws Exception {
        boardDao.delBook(board);
    }
    public void insertBoard(BoardVO board) {
        boardDao.insertBoard(board);
    }
    public void insertBook(BoardVO board) {
        boardDao.insertBook(board);
    }
    public void adminBook(BoardVO board) {
    	boardDao.adminBook(board);
    }

    public BoardVO selectBoard(String id, String pw) {
        BoardVO board = boardDao.selectBoardById(id, pw);
        return board;
    }
    
    public BoardVO selectAdminBoard(String id, String pw) {
        BoardVO board = boardDao.selectAdminById(id, pw);
        return board;
    }

    public void login(String id, String pw) {
        BoardVO board = selectBoard(id, pw);

        if (board != null) {
            // 로그인 성공
            // 다음 페이지로 이동하는 코드 작성
        } else {
            // 로그인 실패
            System.out.println("아이디나 비밀번호가 일치하지 않습니다.");
        }
    }

    public boolean isRented(String isbn) {
        
        return false;
    }

    public boolean isIdExist(String id) throws Exception {
        return boardDao.isIdExist(id);
    }
    public boolean isIsbnExist(String isbn) throws Exception {
        return boardDao.isIsbnExist(isbn);
    }

    public void updateBoard(BoardVO board) throws Exception {
        boardDao.updateBoard(board);
    }

    public boolean withdrawMember(String pw) throws Exception {
        return boardDao.withdrawMember(pw);
    }


    public boolean isPasswordCorrect(String id, String password) {
       
        return false;
    }

    public List<BoardVO> showBookList(String id) {
        List<BoardVO> bookList = boardDao.showBookList(id);
        return bookList;
    }
    public List<BorrowVO> getBorrowList(String id) throws Exception {
        return boardDao.getBorrowList(id);
    }
    public List<BorrowVO> getbookList() throws Exception {
        return boardDao.getbookList();
    }



    
    
    

}