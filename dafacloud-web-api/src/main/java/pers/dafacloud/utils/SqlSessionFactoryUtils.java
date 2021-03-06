package pers.dafacloud.utils;


import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class SqlSessionFactoryUtils {

    private static SqlSessionFactory sqlSessionFactory;
    private static final Class CLASS_LOCK = SqlSessionFactoryUtils.class;

    /**
     * 私有化构造
     */
    private SqlSessionFactoryUtils() {}

    /**
     * 单实例对象
     */
    public static SqlSessionFactory initSqlSessionFactory() {
        String resource = "SqlMapConfig.xml";
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        synchronized (CLASS_LOCK) {
            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            }
        }
        return sqlSessionFactory;
    }

    public static SqlSession openSqlSession() {
        if (sqlSessionFactory == null) {
            initSqlSessionFactory();
        }
        //return sqlSessionFactory.openSession();//不会自动提交add,update等
        return sqlSessionFactory.openSession(true);//自动提交
    }

}

