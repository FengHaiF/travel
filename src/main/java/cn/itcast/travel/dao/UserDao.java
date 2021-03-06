package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

public interface UserDao {
    User findByUsername(String username);
    public void save(User user);

    User findBycode(String activeCode);

    void updateStatus(User user);

    User findByUsernameAndPassword(String username, String password);
}
