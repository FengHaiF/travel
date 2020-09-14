package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

public interface RouteDao {
    /**
     * 查询数据总记录数
     */
    int getTotalCount(int cid,String rname,int lowPrice,int highPrice,int order);

    /**
     * 按页查询记录
     */
    List<Route> pageQuery(int cid, int start, int pageSize,String rname,int lowPrice,int highPrice,int order);

    Route findOne(int parseInt);
}
