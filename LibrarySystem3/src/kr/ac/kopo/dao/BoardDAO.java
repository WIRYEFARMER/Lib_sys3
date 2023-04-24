package kr.ac.kopo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.ac.kopo.util.ConnectionFactory;
import kr.ac.kopo.vo.BoardVO;
import kr.ac.kopo.vo.BorrowVO;
import kr.ac.kopo.vo.User;

//오라클 DB t_board에 게시글 CRUD하는 DAO클래스

public class BoardDAO {
	private List<BoardVO> boardList;
	private List<BorrowVO> borrowList;

	

	public BoardDAO() {
		borrowList = new ArrayList<>();
	}
	
	public void callmemInfo(User user) {
		
	}

	public void insertBoard(BoardVO board) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into lib_sys(id, pw, name, birth, email, phone ) ");
		sql.append("values( ? , ? , ? , ? , ? , ? ) ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, board.getId());
			pstmt.setString(2, board.getPw());
			pstmt.setString(3, board.getName());
			pstmt.setString(4, board.getBirth());
			pstmt.setString(5, board.getEmail());
			pstmt.setString(6, board.getPhone());
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void insertBook(BoardVO board) {
		StringBuilder sql = new StringBuilder();
		sql.append("insert into book_info(title, isbn, author, publisher, qty ) ");
		sql.append("values( ? , ? , ? , ? , ? ) ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString());) {
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getIsbn());
			pstmt.setString(3, board.getAuthor());
			pstmt.setString(4, board.getPublisher());
			pstmt.setInt(5, board.getQty());
			
			pstmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void adminBook(BoardVO board) {
		String sql = "SELECT * FROM book_info";
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.executeQuery();
	}catch (Exception e) {
		e.printStackTrace();
	}
}
	
	public void delBook(BoardVO board) {
		String sql = "DELETE FROM book_info WHERE isbn = ?";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, board.getIsbn());

			pstmt.executeUpdate();

			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	public BoardVO selectBoardById(String id, String pw) {
		BoardVO board = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select id, pw ");
		sql.append("from lib_sys ");
		sql.append("where id = ? and pw = ?");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String id1 = rs.getString("id");
				String pw1 = rs.getString("pw");
				board = new BoardVO();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return board;
	}
	
	public BoardVO selectAdminById(String id, String pw) {
		BoardVO board = null;
		StringBuilder sql = new StringBuilder();
		sql.append("select id, pw ");
		sql.append("from admin_db ");
		sql.append("where id = ? and pw = ?");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			ResultSet rs = pstmt.executeQuery();

			if (rs.next()) {
				String id1 = rs.getString("id");
				String pw1 = rs.getString("pw");
				board = new BoardVO();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return board;
	}

	public List<BoardVO> searchBookByKeyword(String keyword) {
		List<BoardVO> bookList = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			String sql = "SELECT * FROM book_info WHERE isbn LIKE ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BoardVO book = new BoardVO();
				book.setIsbn(rs.getString("isbn"));
				book.setTitle(rs.getString("title"));
				book.setAuthor(rs.getString("author"));
				book.setPublisher(rs.getString("publisher"));
				bookList.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bookList;
	}

	public List<BoardVO> searchBook(String keyword) {
		List<BoardVO> bookList = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM book_info WHERE isbn ?");
		ResultSet rs = null;

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

			pstmt.setString(1, "%" + keyword + "%");
			pstmt.executeQuery();

			bookList = new ArrayList<>();
			while (rs.next()) {
				BoardVO book = new BoardVO();
				book.setIsbn(rs.getString("isbn"));
			}
		} catch (Exception e) {
			e.printStackTrace();

		}
		return bookList;
	}

	/*public void updateQty(String isbn) {
		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement("UPDATE book_info SET qty = qty - 1 WHERE isbn = ?")) {
			pstmt.setString(1, isbn);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}*/



	public List<BoardVO> showBookList(String keyword) {
		List<BoardVO> list = new ArrayList<>();
		ConnectionFactory connFactory = new ConnectionFactory();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM book_info ");
		sql.append("WHERE title LIKE ? OR publisher LIKE ?");
		try (Connection conn = connFactory.getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					BoardVO vo = new BoardVO();
					vo.setIsbn(rs.getString("isbn"));
					vo.setTitle(rs.getString("title"));
					vo.setAuthor(rs.getString("author"));
					vo.setPublisher(rs.getString("publisher"));
					vo.setQty(rs.getInt("qty"));
					list.add(vo);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	/*public void updateQty(String isbn, int qty) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE book_info SET qty = ? WHERE isbn = ?");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

			pstmt.setInt(1, qty);
			pstmt.setString(2, isbn);
			pstmt.executeUpdate();
		} catch (Exception e) {
			throw e;
		}
	}*/

	public boolean isIdExist(String id) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM lib_sys WHERE id = ?");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			int count = rs.getInt(1);

			return count > 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	public boolean isIsbnExist(String isbn) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) FROM book_info WHERE isbn = ?");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
			pstmt.setString(1, isbn);
			ResultSet rs = pstmt.executeQuery();

			rs.next();
			int count = rs.getInt(1);

			return count > 0;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	


	public void updateBoard(BoardVO board) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE lib_sys SET pw = ?, phone = ? WHERE id = ? ");

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

			pstmt.setString(1, board.getPw());
			pstmt.setString(2, board.getPhone());
			pstmt.setString(3, board.getId());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	public boolean withdrawMember(String pw) {
		String sql = "UPDATE lib_sys SET pw = ?";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, pw);

			int affectedRows = pstmt.executeUpdate();

			return affectedRows > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean isPasswordCorrect(String id, String password) {
		String sql = "SELECT pw FROM lib_sys WHERE id = ?";

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				String dbPassword = rs.getString("pw");
				return dbPassword.equals(password);
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<BoardVO> selectBookList(String keyword) {
		ResultSet rs = null;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM book_info WHERE isbn LIKE ? OR title LIKE ? OR author LIKE ? OR publisher LIKE ?");
		List<BoardVO> bookList = new ArrayList<>();

		try (Connection conn = new ConnectionFactory().getConnection();
				PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			pstmt.setString(3, "%" + keyword + "%");
			pstmt.setString(4, "%" + keyword + "%");
			rs = pstmt.executeQuery();

			while (rs.next()) {
				String isbn = rs.getString("isbn");
				String title = rs.getString("title");
				String author = rs.getString("author");
				String publisher = rs.getString("publisher");

				BoardVO book = new BoardVO();
				book.setIsbn(isbn);
				book.setTitle(title);
				book.setAuthor(author);
				book.setPublisher(publisher);

				bookList.add(book);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return bookList;
	}



	
	/*public List<BorrowVO> getBorrowList(String id) throws Exception {
	    List<BorrowVO> list = new ArrayList<>();
	    String sql = "SELECT b.id, b.isbn, bi.title, bi.author, bi.publisher, b.user_id, b.return_date, sysdate as borrow_date \r\n FROM borrowed_books b \r\n JOIN book_info bi ON b.isbn = bi.isbn \r\n WHERE b.user_id = ? \r\n ORDER BY borrow_date DESC ";

	    try (Connection conn = new ConnectionFactory().getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery()) {

	        while (rs.next()) {
	            BorrowVO borrow = new BorrowVO();
	            borrow.setUserId(rs.getString("user_id"));
	            borrow.setIsbn(rs.getString("isbn"));
	            borrow.setTitle(rs.getString("title"));
	            borrow.setAuthor(rs.getString("author"));
	            borrow.setPublisher(rs.getString("publisher"));
	            borrow.setBorrowDate(rs.getString("borrow_date"));
	            borrow.setReturnDate(rs.getString("return_date"));

	            list.add(borrow);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list;
	}*/
	public List<BorrowVO> getBorrowList(String id) throws Exception {
	    List<BorrowVO> list = new ArrayList<>();
	    String sql = "SELECT b.id, b.isbn, bi.title, bi.author, bi.publisher, b.user_id, b.return_date, sysdate as borrow_date \r\n FROM borrowed_books b \r\n JOIN book_info bi ON b.isbn = bi.isbn \r\n WHERE b.user_id = ? \r\n ORDER BY borrow_date DESC ";

	    try (Connection conn = new ConnectionFactory().getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	       
	        pstmt.setString(1, id);

	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	            BorrowVO borrow = new BorrowVO();
	            borrow.setUserId(rs.getString("user_id"));
	            borrow.setIsbn(rs.getString("isbn"));
	            borrow.setTitle(rs.getString("title"));
	            borrow.setAuthor(rs.getString("author"));
	            borrow.setPublisher(rs.getString("publisher"));
	            borrow.setBorrowDate(rs.getString("borrow_date"));
	            borrow.setReturnDate(rs.getString("return_date"));

	            list.add(borrow);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	
	public List<BorrowVO> getbookList() throws Exception {
	    List<BorrowVO> list = new ArrayList<>();
	    String sql = "SELECT * from book_info ";

	    try (Connection conn = new ConnectionFactory().getConnection();
	         PreparedStatement pstmt = conn.prepareStatement(sql)) {

	       
	        ResultSet rs = pstmt.executeQuery();

	        while (rs.next()) {
	            BorrowVO borrow = new BorrowVO();
	            borrow.setTitle(rs.getString("title"));
	            borrow.setIsbn(rs.getString("isbn"));
	            borrow.setAuthor(rs.getString("author"));
	            borrow.setPublisher(rs.getString("publisher"));
	            
	            list.add(borrow);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return list;
	}
	


	    public boolean returnBook(String isbn) {
	        StringBuilder updateQtySqlBuilder = new StringBuilder();
	        updateQtySqlBuilder.append("UPDATE book_info SET qty = qty + 1 WHERE isbn = ?");
	        String updateQtySql = updateQtySqlBuilder.toString();
	        
	        StringBuilder deleteFromListSqlBuilder = new StringBuilder();
	        deleteFromListSqlBuilder.append("DELETE FROM borrowed_books WHERE isbn = ?"); // rented_books 테이블에서 책 정보를 삭제합니다.
	        String deleteFromListSql = deleteFromListSqlBuilder.toString();

	        try (Connection con = new ConnectionFactory().getConnection();
	             PreparedStatement updateQtyStmt = con.prepareStatement(updateQtySql);
	             PreparedStatement deleteFromListStmt = con.prepareStatement(deleteFromListSql)) {

	            con.setAutoCommit(false);

	            // Update book quantity
	            updateQtyStmt.setString(1, isbn);
	            int updateQtyResult = updateQtyStmt.executeUpdate();

	            // Remove book from the list
	            deleteFromListStmt.setString(1, isbn);
	            int deleteFromListResult = deleteFromListStmt.executeUpdate();

	            if (updateQtyResult > 0 && deleteFromListResult > 0) {
	                con.commit();
	                return true;
	            } else {
	                con.rollback();
	                return false;
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	    public BoardVO getBook(String isbn) {
	        String sql = "SELECT * FROM book_info WHERE isbn = ?";
	        BoardVO book = null;

	        try (Connection con = new ConnectionFactory().getConnection();
	             PreparedStatement pstmt = con.prepareStatement(sql)) {
	            pstmt.setString(1, isbn);
	            ResultSet rs = pstmt.executeQuery();

	            if (rs.next()) {
	                book = new BoardVO();
	                book.setIsbn(rs.getString("isbn"));
	                book.setTitle(rs.getString("title"));
	                book.setAuthor(rs.getString("author"));
	                book.setPublisher(rs.getString("publisher"));
	                book.setQty(rs.getInt("qty"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return book;
	    }


	    
	  /*  public boolean rentBook(String isbn, String user_id) {
	        boolean isSuccess = false;
	        
	        String sql = "INSERT INTO borrowed_books (\r\n user_id,\r\n isbn,\r\nborrow_date,\r\nreturn_date\r\n) values (? ,? , sysdate, sysdate + 7);";
	        
	        try (
	        		Connection con = new ConnectionFactory().getConnection();
		             PreparedStatement pstmt = con.prepareStatement(sql)
		         ){
	        	pstmt.setString(1, user_id);
	        	pstmt.setString(2, isbn);
	        	
	        	int cnt = pstmt.executeUpdate();
	        	
	        	if(cnt != 0) isSuccess = true;
	        	
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }

	        /*
	        try {
	            conn = new ConnectionFactory().getConnection();
	            conn.setAutoCommit(false);

	            // update the quantity of the book with the given ISBN
	            String sql1 = "UPDATE book_info SET qty = qty - 1 WHERE isbn = ? AND qty > 0";
	            pstmt1 = conn.prepareStatement(sql1);
	            pstmt1.setString(1, isbn);
	            int affectedRows = pstmt1.executeUpdate();

	            // insert a new row into the borrowed_books table
	            String sql2 = "INSERT INTO borrowed_books (user_id, isbn, borrow_date, return_date) SELECT ?, ?, sysdate, sysdate + INTERVAL '7' DAY FROM DUAL WHERE NOT EXISTS (SELECT * FROM borrowed_books WHERE isbn = ?)";
	            pstmt2 = conn.prepareStatement(sql2);
	            pstmt2.setString(1, user_id);
	            pstmt2.setString(2, isbn);
	            pstmt2.setString(3, isbn);
	            int rowsInserted = pstmt2.executeUpdate();

	            if (affectedRows > 0 && rowsInserted > 0) {
	                isSuccess = true;
	            }

	            conn.commit();
	        } catch (Exception e) {
	            e.printStackTrace();
	            try {
	                conn.rollback();
	            } catch (Exception e1) {
	                e1.printStackTrace();
	            }
	        } finally {
	            try {
	                if (pstmt1 != null) pstmt1.close();
	                if (pstmt2 != null) pstmt2.close();
	                if (conn != null) conn.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	             */
//	        return isSuccess;
//}

public boolean rentBook(String isbn, String user_id) throws Exception {
    String sql = "INSERT INTO borrowed_books (user_id, isbn, borrow_date, return_date) VALUES (?, ?, sysdate, sysdate + 7)";
    
    try (Connection conn = new ConnectionFactory().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        
        // book_info 테이블에서 도서 수량을 감소시킵니다.
        String query = "UPDATE book_info SET qty = qty - 1 WHERE isbn = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, isbn);
            int cnt = stmt.executeUpdate();
            
            if (cnt == 0) {
                System.out.println("재고가 없습니다.");
                return false;
            }
        }

        // borrowed_books 테이블에 대출 정보를 추가합니다.
        pstmt.setString(1, user_id);
        pstmt.setString(2, isbn);
        int cnt = pstmt.executeUpdate();

        return cnt > 0;
        
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
}
public boolean meminfoList(String user_id) throws Exception {

    String sql = "SELECT id, NAME, BIRTH, EMAIL, PHONE FROM LIB_SYS WHERE id = ? ";

    try (Connection conn = new ConnectionFactory().getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        pstmt.setString(1, user_id);

        try (ResultSet rs = pstmt.executeQuery()) {

            System.out.println("===== 회원 정보 =====");

            if (!rs.next()) {
                System.out.println("존재하지 않는 회원입니다.");
                return false;
            }

            String id = rs.getString("id");
            String name = rs.getString("name");
            String birth = rs.getString("birth");
            String email = rs.getString("email");
            String phone = rs.getString("phone");
            System.out.printf(" 아이디: %s\n 이름: %s\n 생년월일: %s\n 이메일: %s\n 연락처: %s\n",
                    id, name, birth, email, phone);
        }

        String query = "SELECT b.user_id, b.isbn, i.title, b.borrow_date, b.return_date FROM borrowed_books b INNER JOIN book_info i ON b.isbn = i.isbn where b.user_id = ? ";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user_id);
            try (ResultSet rs2 = stmt.executeQuery()) {
                if (!rs2.next()) {
                    System.out.println("빌려간 도서가 없습니다.");
                    return false;
                }
                System.out.println("===== 대출 도서 정보 =====");
                do {
                    String userId = rs2.getString("user_id");
                    String isbn = rs2.getString("isbn");
                    String title = rs2.getString("title");
                    String borrowDate = rs2.getString("borrow_date");
                    String returnDate = rs2.getString("return_date");
                    System.out.printf("아이디 : %s\n ISBN : %s\n 책 제목: %s\n 빌린 날짜: %s\n 반납 날짜: %s\n",
                            userId, isbn, title, borrowDate, returnDate);
                } while (rs2.next());
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
        return false;
    }
    return true;
}




}


	

