package cn.itcast.travel.service;

import cn.itcast.travel.domain.User;

public interface UserService {

    public boolean regist(User user);

    boolean activete(String activeCode);

    User login(User user);
}
