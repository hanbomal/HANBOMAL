package dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dao.MybatisConnector;
import model.RelationVO;

public class RelationDAO extends MybatisConnector{
	private static RelationDAO instance = new RelationDAO();
	private RelationDAO() {
	}
	public static RelationDAO getInstance() {
		return instance;
	}
	
	private final String namespace="relation";
	SqlSession sqlSession;
	
	public int requestCount(String memberid) {
		int x = 0;
		sqlSession=sqlSession();
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberid", memberid);
		x=sqlSession.selectOne(namespace+".requestCount", map);
		sqlSession.close();
		return x;
		}
	
	public int responseCount(String memberid) {
		int x = 0;
		sqlSession=sqlSession();
		Map<String, String> map = new HashMap<String, String>();
		map.put("memberid", memberid);
		x=sqlSession.selectOne(namespace+".responseCount", map);
		sqlSession.close();
		return x;
	}
	
	public void requestJoin(String memberid,String studynum, String studyName, String nickname,
			 String leader) {
		sqlSession=sqlSession();
		Map<String, String> map = new HashMap<>();
		map.put("memberid", memberid);
		map.put("studynum", studynum);
		map.put("studyName", studyName);
		map.put("nickname", nickname);
		map.put("leader", leader);
		sqlSession.insert(namespace+".requestJoin",map);
		sqlSession.commit();
		sqlSession.close();
	}
	public void cancelJoin(String memberid, String studyName) {
		sqlSession=sqlSession();
		Map<String, String> map = new HashMap<>();
		map.put("memberid", memberid);
		map.put("studyName", studyName);
		sqlSession.delete(namespace+".cancelJoin",map);
		sqlSession.commit();
		sqlSession.close();
	}
	public List requestList(int startRow, int endRow,String memberid) {
		sqlSession=sqlSession();
		Map<String, Object> map = new HashMap<>();
		map.put("memberid", memberid);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		List<RelationVO> li=sqlSession.selectList(namespace+".requestList",map);
		sqlSession.close();
		return li;
	}
	public List responseList(int startRow, int endRow, String memberid) {
		sqlSession=sqlSession();
		Map<String, Object> map = new HashMap<>();
		map.put("memberid", memberid);
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		List<RelationVO> li=sqlSession.selectList(namespace+".responseList",map);
		sqlSession.close();
		return li;
	}
	
	
	public void answerNo(String memberid, String leader, String studyName) {
		sqlSession=sqlSession();
		Map<String, String> map = new HashMap<>();
		map.put("memberid", memberid);
		map.put("leader", leader);
		map.put("studyName", studyName);
		sqlSession.update(namespace+".answerNo",map);
		sqlSession.commit();
		sqlSession.close();
	}
	
	public void answerYes(String memberid, String leader, String studyName) {
		sqlSession=sqlSession();
		Map<String, String> map = new HashMap<>();
		map.put("memberid", memberid);
		map.put("leader", leader);
		map.put("studyName", studyName);
		sqlSession.update(namespace+".answerYes",map);
		sqlSession.update(namespace+".peopleCountUp",map);
		sqlSession.commit();
		sqlSession.close();
	}
	
	public List getJoinMemberList(String studyName) {
		sqlSession=sqlSession();
		Map map=new HashMap();
		map.put("studyName", studyName);
		
		List li=null;
		
		li=sqlSession.selectList(namespace+".getJoinMemberList",map);
		
		sqlSession.close();
		return li;
	}
	
	public RelationVO getMemberInfo(String studyName,String memberid) {
		sqlSession=sqlSession();
		Map map=new HashMap();
		map.put("studyName", studyName);
		map.put("memberid", memberid);
		
		RelationVO memberInfo=sqlSession.selectOne(namespace+".getMemberInfo",map);
		
		sqlSession.close();
		
		
		return memberInfo;
	}
	
	
	
	
}
