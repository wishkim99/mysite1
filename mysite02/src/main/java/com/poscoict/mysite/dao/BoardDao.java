package com.poscoict.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.poscoict.mysite.vo.BoardVo;

public class BoardDao {

	//페이징
	public int pageNum(int writingNum) {
		int num = 0;
		BoardVo result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select count(no) from writing";

			pstmt = conn.prepareStatement(sql);

			//pstmt.setLong(1, no); // 첫번째 물음표에 no값 들어가게

			rs = pstmt.executeQuery();
			if (rs.next()) {

				String title = rs.getString(1);
				String contents = rs.getString(2);
				int hit = rs.getInt(3);
				String regDate = rs.getString(4);
				long userNo = rs.getLong(5);

				result = new BoardVo();
				result.setTitle(title);
				result.setContents(contents);
				result.setHit(hit);
				result.setRegDate(regDate);
				result.setUserNo(userNo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

			if(num % writingNum > 0) {
			num = (num / writingNum) + 1;
		} else {
			num = num / writingNum;
		}
		return num;
	}
	
	public boolean insert(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = "insert into board value(null, ?, ?, 0, (select g_no from (select ifnull(max(g_no)+1, 1) as g_no from board) as tmp), 1, 1, now(), ?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContents());
//			pstmt.setInt(3, vo.getHit());
//			pstmt.setInt(4, vo.getGroupNo());
//			pstmt.setInt(5, vo.getOrderNo());
//			pstmt.setInt(6, vo.getDepth());
			pstmt.setLong(3, vo.getUserNo());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<BoardVo> findAll(String kwd) {
		List<BoardVo> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
				
		try {
			conn = getConnection();
			if(kwd==null) {
			String sql = "select b.no, b.title, a.name, b.hit, b.contents, date_format(reg_date, '%Y/%m/%d %H:%i:%s') as reg_date, user_no"+
					 " from user a, board b" +
					" where a.no=b.user_no" +
					" order by b.g_no desc, b.o_no asc";
			
			pstmt = conn.prepareStatement(sql);
			}
			else {
			String sql = "select b.no, b.title, a.name, b.hit, b.contents, date_format(reg_date, '%Y/%m/%d %H:%i:%s') as reg_date, user_no"+
			 " from user a, board b" +
			" where a.no=b.user_no and title like ?"  +
			" order by b.g_no desc, b.o_no asc";
	
			pstmt = conn.prepareStatement(sql); //물음표 넣을 때 필요
			kwd='%'+kwd+'%';
			pstmt.setString(1, kwd);
			}
			
			
			
			rs = pstmt.executeQuery();

			while(rs.next()) {
				long no = rs.getLong(1);
				String title = rs.getString(2);
				String name = rs.getString(3);
				int hit = rs.getInt(4);
				String contents = rs.getString(5);
				String regDate = rs.getString(6);
				long userNo = rs.getInt(7);
				
				BoardVo vo = new BoardVo();
				vo.setNo(no);
				vo.setTitle(title);
				vo.setUserName(name);
				vo.setHit(hit);
				vo.setContents(contents);
				vo.setRegDate(regDate);
				vo.setUserNo(userNo);
				
				list.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}

	public BoardVo findByNo(Long no) {
		BoardVo result = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

			String sql = "select title, contents, hit, date_format(reg_date, '%Y/%m/%d %H:%i:%s') as reg_date, user_no from board where no=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, no); // 첫번째 물음표에 no값 들어가게

			rs = pstmt.executeQuery();
			if (rs.next()) {

				String title = rs.getString(1);
				String contents = rs.getString(2);
				int hit = rs.getInt(3);
				String regDate = rs.getString(4);
				long userNo = rs.getLong(5);

				result = new BoardVo();
				result.setTitle(title);
				result.setContents(contents);
				result.setHit(hit);
				result.setRegDate(regDate);
				result.setUserNo(userNo);
			}

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public boolean delete(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = getConnection();

			String sql = " delete" + "   from board" + "  where no=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getNo());
//			pstmt.setLong(2, vo.getUserNo());

			int count = pstmt.executeUpdate();
			result = count == 1;

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	public boolean update(BoardVo vo) {
		boolean result = false;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();

	
				String sql = "update board set title=?, contents=? where no=? ";

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, vo.getTitle());
				pstmt.setString(2, vo.getContents());
				pstmt.setLong(3, vo.getNo());

				
				int count = pstmt.executeUpdate();
				result = count == 1;

		

		} catch (SQLException e) {
			System.out.println("error:" + e);
		} finally {
			try {
				if (pstmt != null) {
					pstmt.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/webdb?characterEncoding=UTF-8&serverTimezone=UTC";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패:" + e);
		}

		return conn;
	}



}
