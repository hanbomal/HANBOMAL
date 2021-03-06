package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import model.BoardTypeVO;
import model.BoardVO;
import dao.MybatisConnector;

public class BoardDAO extends MybatisConnector {
	private final String namespace = "board";
	private static BoardDAO instance = new BoardDAO();

	public static BoardDAO getInstance() {
		return instance;
	}

	SqlSession sqlSession;
	public List<BoardTypeVO> getTypeList(String group) {
		sqlSession = sqlSession();
		Map<String, String> map = new HashMap<>();
		map.put("group", group);
		List<BoardTypeVO> li = sqlSession.selectList(namespace + ".getTypeList", map);
		sqlSession.close();
		return li;
	}
	
	public void addBoard(BoardTypeVO board) {
		sqlSession = sqlSession();
		sqlSession.insert(namespace + ".addBoard", board);
		sqlSession.commit();
		sqlSession.close();
	}

	public int deleteBoard(String boardid,String group) {
		sqlSession = sqlSession();
		Map map = new HashMap<>();
		map.put("boardid", boardid);
		map.put("studynum", group);
		int deleteNum = sqlSession.delete(namespace + ".deleteBoard", map);
		sqlSession.commit();
		sqlSession.close();
		return deleteNum;
	}
	
	public int deletePosition(String id,String group) {
		sqlSession = sqlSession();
		Map map = new HashMap<>();
		map.put("studynum", group);
		map.put("id", id);
		int deleteNum = sqlSession.delete(namespace + ".deletePosition", map);
		sqlSession.commit();
		sqlSession.close();
		return deleteNum;
	}
	
	public BoardTypeVO getBoardType(String boardid, String group) {
		sqlSession = sqlSession();
		Map map = new HashMap<>();
		map.put("boardid", boardid);
		map.put("group", group);
		BoardTypeVO boardType = sqlSession.selectOne(namespace + ".getBoardType", map);
		sqlSession.commit();
		sqlSession.close();
		return boardType;
	}
	
	public int getArticleCount(String boardid,String group) {
		int x = 0;
		sqlSession = sqlSession();
		Map<String, String> map = new HashMap<String, String>();
		map.put("boardid", boardid);
		map.put("num", group);
		x = sqlSession.selectOne(namespace + ".getArticleCount", map);
		sqlSession.close();
		return x;
	}
	
	public List getArticles(int startRow, int endRow, String boardid, String group) {
		sqlSession = sqlSession();
		Map map = new HashMap();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("boardid", boardid);
		map.put("group", group);
		List li = sqlSession.selectList(namespace + ".getArticles", map);
		sqlSession.close();
		return li;
	}

	public void insertArticle(BoardVO article) {
		sqlSession = sqlSession();
		Map map = new HashMap<>();
		map.put("studynum", article.getStudynum());
		map.put("boardid", article.getBoardid());
		int number = sqlSession.selectOne(namespace + ".getNextNumber",map);
		article.setNum(number+1);
		System.out.println("11111111111111"+article);
		sqlSession.insert(namespace + ".insertBoard", article);
		sqlSession.commit();
		sqlSession.close();
	}

	public BoardVO getArticle(int num,String group, String boardid, String chk) {
		sqlSession = sqlSession();
		Map map = new HashMap<>();
		map.put("num", num);
		map.put("boardid", boardid);
		map.put("group", group);
		if(chk.equals("content")) {
			sqlSession.update(namespace + ".addReadCount", map);
		}
		BoardVO article = sqlSession.selectOne(namespace + ".getArticle", map);
		sqlSession.commit();
		sqlSession.close();
		return article;
	}

	public int updateArticle(BoardVO article) {
		sqlSession = sqlSession();
		int updateCount = sqlSession.update(namespace + ".updateArticle", article);
		sqlSession.commit();
		sqlSession.close();
		return updateCount;
	}

	public int updateBoardType(BoardTypeVO board) {
		sqlSession = sqlSession();
		int updateCount = sqlSession.update(namespace + ".updateBoardType", board);
		sqlSession.commit();
		sqlSession.close();
		return updateCount;
	}
	public int updatePosition(String id, String groupposition,String studynum) {
		sqlSession = sqlSession();
		Map<String, String> map = new HashMap<>();
		map.put("studynum", studynum);
		map.put("id", id);
		map.put("groupposition", groupposition);
		int updateCount = sqlSession.update(namespace + ".updatePosition", map);
		sqlSession.commit();
		sqlSession.close();
		return updateCount;
	}
	
	public int deleteArticle(int num, String passwd) {
		sqlSession = sqlSession();
		Map map = new HashMap<>();
		map.put("num", num);
		map.put("passwd", passwd);
		int deleteNum = sqlSession.delete(namespace + ".deleteArticle", map);
		sqlSession.commit();
		sqlSession.close();
		return deleteNum;
	}
	

	public int getNextBoardid(String group) {
		int x = 0;
		sqlSession = sqlSession();
		Map<String, String> map = new HashMap<String, String>();
		map.put("group", group);
		
		x = sqlSession.selectOne(namespace + ".getNextBoardid", map);
		sqlSession.close();
		return x;
	}

}
