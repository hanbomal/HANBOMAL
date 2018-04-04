package dao;

import org.apache.ibatis.session.SqlSession;

import model.GalleryVO;

public class GalleryDAO extends MybatisConnector {
	private final String namespace = "gall.mybatis";
	private static GalleryDAO instance = new GalleryDAO();

	private GalleryDAO() {	}

	public static GalleryDAO getInstance() {
		return instance;
	}

	SqlSession sqlSession;

	public void addGallery(GalleryVO gallery) {

		sqlSession=sqlSession();
		int num=0;
		num=sqlSession.selectOne(namespace+".getNextNumber");
		gallery.setNum(num);
		sqlSession.insert(namespace+".addGallery",gallery);
		sqlSession.commit();
		sqlSession.close();
		
		
	}
	
	

}
