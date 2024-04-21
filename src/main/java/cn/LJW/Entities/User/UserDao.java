package cn.LJW.Entities.User;

public interface UserDao {

    User findByID(String userID);
    User findByName(String name);

    void registerUser(User user);
    void registerActiveData(String userID);
}
