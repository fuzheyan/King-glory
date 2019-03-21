//package com.db.sys.dao.impl;
//
//import java.util.Map;
//
//import org.apache.ibatis.session.SqlSession;
//import org.apache.ibatis.session.SqlSessionFactory;
//
//import com.db.sys.dao.SysLogDao;
////1.此类要交给spring管理
////2.此类中基于mybatis API 操作数据库数据
//public class SysLogDaoImpl implements SysLogDao {
//    private SqlSessionFactory sqlSessionFactory;
//    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
//    	this.sqlSessionFactory = sqlSessionFactory;
//	}
//	@Override
//	public Map<String, Object> findById(Integer id) {
//		//1.获取sqlSession对象
//		SqlSession session=
//		sqlSessionFactory.openSession();
//		//2.执行查询操作
//		//2.1方式1
//		//String statement="com.db.sys.dao.SysLogDao.findById";
//	    //Map<String,Object> map=session.selectOne(statement, id);
//		//2.2方式2
//		SysLogDao dao=session.getMapper(SysLogDao.class);
//		Map<String,Object> map=dao.findById(9);
//		//3.释放资源
//	    session.close();
//		return map;
//	}
//}
