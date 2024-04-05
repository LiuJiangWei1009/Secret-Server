package cn.LJW.Controllers;

import cn.LJW.Entities.Net.OnlineSession;
import cn.LJW.Entities.User.User;
import cn.LJW.Entities.User.UserDao;
import cn.LJW.MyDispatchServlet;
import cn.LJW.Utils.ThreadHelper;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.stream.*;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.*;
import java.time.Duration;
import java.util.*;

@Controller
public class AccountManage extends MyDispatchServlet {

    private static Map<String, OnlineSession> sessionMap = new HashMap<>();

    @Autowired
    public StringRedisTemplate stringRedisTemplate;
    @Autowired
    public JedisConnectionFactory jedisConnectionFactory;

    @Autowired
    private ServerSocket serverSocket;

    @RequestMapping(value = "/login")
    @ResponseBody
    public String Login(@CookieValue("firstTime")String firstTimeCookie,
                        @CookieValue("lastTime")String lastTimeCookie,
                        HttpSession session,
                        HttpServletResponse response,
                        String unique,
                        String password) {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserDao userDao = sqlSession.getMapper(UserDao.class);
        User user;
        if (unique.length() < 20) user = userDao.findByName(unique);
        else user = userDao.findByID(unique);
        if (user == null) return getIntJSONString(0);
        if (!user.getPassword().equals(password)) return getIntJSONString(1);

        if (!sessionMap.containsKey(unique)){
            OnlineSession onlineSession = new OnlineSession(firstTimeCookie,session.getId());
            onlineSession.loginTime.add(lastTimeCookie);
            sessionMap.put(unique,onlineSession);
            System.out.println("欢迎你...");
        }else if (firstTimeCookie.equals(sessionMap.get(unique).firstTimeCookie)){
            sessionMap.get(unique).loginTime.add(lastTimeCookie);
            session.setAttribute(lastTimeCookie,password);
            System.out.println("欢迎回来...");
        }else {
            response.addCookie(new Cookie("JSESSIONID",sessionMap.get(unique).sessionID));
            sessionMap.get(unique).firstTimeCookie = firstTimeCookie;
            System.out.println("异地登录...");
        }


        System.out.println("get socket   "+serverSocket);
        new Thread(()->{
            try {
//            ServerSocket serverSocket = new ServerSocket(5786);
                while (true) {
                    System.out.println("wait for socket");
                    Socket socket = serverSocket.accept();
//                    testConnection(socket);
                    System.out.println("get socket  "+socket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();


        System.out.println(getJSONString(user));
        return getJSONString(user);
    }

    public void testConnection(Socket socket){
        new Thread(()->{
            while (true){
                System.out.println(socket.isClosed());
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        ).start();
    }

    @RequestMapping(value = "/register")
    @ResponseBody
    public String Register(String type,User requestUser){
        SqlSession session=sqlSessionFactory.openSession(true);
        UserDao userDao=session.getMapper(UserDao.class);
        if(type.equals("0")){
            User user=userDao.findByName(requestUser.getName());
            if(user==null) return getIntJSONString(0);
            else return getIntJSONString(1);
        }else {
            userDao.registerUser(requestUser);
            userDao.registerActiveData(requestUser.getUserId());
            return getIntJSONString(2);
        }
    }

//    @RequestMapping(value = "/fileUpload")
//    @ResponseBody
//    public void Register(){
//        System.out.println("欢迎回来...");
//    }

    private String getJSONString(User user){
        return new JSONObject(user).toString();
    }

    private String getIntJSONString(int flag){
        return new JSONObject().put("status",flag).toString();
    }
}
