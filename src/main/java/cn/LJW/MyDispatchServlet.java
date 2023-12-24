package cn.LJW;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MyDispatchServlet extends DispatcherServlet {
    protected static SqlSessionFactory sqlSessionFactory;
    protected static ApplicationContext applicationContext;

    @Override
    protected void initStrategies(ApplicationContext context) {
        super.initStrategies(context);
        if (applicationContext==null) applicationContext=context;
        try {
            if (sqlSessionFactory==null)
                sqlSessionFactory=new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("Mybatis/MybatisConfig.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("获得MYSQL连接工厂："+sqlSessionFactory+"，上下文："+applicationContext);
    }
}
