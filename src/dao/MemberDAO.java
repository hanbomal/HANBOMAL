package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;


import model.MemberVO;
import dao.MybatisConnector;;


public class MemberDAO extends MybatisConnector {
		private final String namespace="member";
		SqlSession sqlSession;
	
	
		private static MemberDAO instance = new MemberDAO();
		private String id;
		private MemberDAO() {
			
		} 
		
		public static MemberDAO getInstance() {
			return instance;
		}
	
		public static Connection getConnection() {
			Connection conn = null;
			try {
				String jdbcUrl = "jdbc:oracle:thin:@localhost:1521:orcl";
				String dbId = "scott";
				String dbpwd = "tiger";
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection(jdbcUrl, dbId, dbpwd);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return conn;
		}
		
	
		public void close (Connection conn, ResultSet rs, PreparedStatement pstmt) {
			if(conn!=null) try {conn.close();} catch(SQLException ex) {}
			if(rs!=null) try {rs.close();} catch(SQLException ex) {}
			if(pstmt!=null) try {pstmt.close();} catch (SQLException e) {}
		}
		
		
		public void insertMember(MemberVO member) {
			String sql="";
			Connection conn = getConnection();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			int number=0;
			try {
				pstmt = conn.prepareStatement("select memberSer.nextval from dual");
				rs = pstmt.executeQuery();
				if (rs.next())
					number = rs.getInt(1);
				else number = 1; 
				
				sql = "insert into member(num,listid, memberid, passwd, nickname, passwdq, passwdkey, joindate, lastdate)";
				sql += "values(?,?,?,?,?,?,?, sysdate, sysdate)";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, number);
				pstmt.setString(2, member.getListid());
				pstmt.setString(3, member.getMemberid());
				pstmt.setString(4, member.getPasswd());
				pstmt.setString(5, member.getNickname());
				pstmt.setString(6, member.getPasswdq());
				pstmt.setString(7, member.getPasswdkey());
				
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				close(conn, rs, pstmt);
			}
			
		}
		

		public MemberVO getMember(String memberid) {
		
			sqlSession= sqlSession();
			
			Map map = new HashMap();
			
			map.put("memberid", memberid);
			
			
			MemberVO member=sqlSession.selectOne(namespace+ ".getMember" ,map);
			
			sqlSession.commit();
			sqlSession.close();
			
			return member;  //when  you need update or want to see your info
			//you can see your info
			
		}
		
		public int updateMember (MemberVO member) {
			
			sqlSession= sqlSession();
			int chk = sqlSession.update(namespace+".updateMember", member);
			sqlSession.commit();
			sqlSession.close();
			
			return chk;
		
			
			
		}
		
		
		public int deleteMember(String memberid,String passwd) {
			
			sqlSession= sqlSession();
			Map map = new HashMap();
			
			map.put("passwd", passwd);
			map.put("memberid", memberid);
			int chk = sqlSession.delete(namespace+".deleteMember", map);
			sqlSession.commit();
			sqlSession.close();
			
			return chk;
			
		}
		
public int beforeCheck(String memberid,String passwd) {
			
			sqlSession= sqlSession();
			Map<String, String> map = new HashMap<>();
			
			
			map.put("passwd", passwd);
			map.put("memberid", memberid);
			
			String chk = sqlSession.selectOne(namespace+".checkMember", map);
			
			if(chk!=null) {
				if(chk.equals(passwd)) {return 1;}
				
			}else
			
			sqlSession.commit();
			sqlSession.close();
			
			return -1;
			
		}
		 
		 
		public int login(String memberid, String password) {
			sqlSession=sqlSession();
			Map<String, String> map = new HashMap<>();
			map.put("memberid", memberid);
			String chk=sqlSession.selectOne(namespace+".login",map);
			if(chk!=null) {
				if(chk.equals(password)) {return 1;}
				else {return 0;}
			}
			sqlSession.close();
			return -1; 
		}
		
//for find passwd //1.input id -->get passwdq --->if passwdkey ok -->go to passwd board
	   
	}

