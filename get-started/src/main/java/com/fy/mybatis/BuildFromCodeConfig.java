package com.fy.mybatis;

import com.fy.mybatis.entity.Student;
import com.fy.mybatis.mapper.StudentMapper;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.datasource.pooled.PooledDataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class BuildFromCodeConfig {

    public static void main(String[] args) throws IOException {
        Properties properties = Resources.getResourceAsProperties("jdbc.properties");
        DataSourceFactory dataSourceFactory = new PooledDataSourceFactory();
        dataSourceFactory.setProperties(properties);
        DataSource dataSource = dataSourceFactory.getDataSource();
        TransactionFactory transactionFactory = new JdbcTransactionFactory();
        Environment environment = new Environment("development", transactionFactory, dataSource);
        Configuration configuration = new Configuration(environment);
        configuration.addMapper(StudentMapper.class);
        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder().build(configuration);
        try(SqlSession sqlSession = sessionFactory.openSession()){
            StudentMapper mapper = sqlSession.getMapper(StudentMapper.class);
            System.out.println(mapper.listStudent());
            List<Student> students = sqlSession.selectList("listStudent");
            System.out.println(students);
        }
    }
}
