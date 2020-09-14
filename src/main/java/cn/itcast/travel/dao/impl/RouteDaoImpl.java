package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;

import cn.itcast.travel.domain.Route;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class RouteDaoImpl implements RouteDao {

    private JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public int getTotalCount(int cid, String rname, int lowPrice, int highPrice, int order) {
        String sql="select count(*) from tab_route  WHERE 1=1 ";
        StringBuilder sb=new StringBuilder(sql);
        List params=new ArrayList();
        if(cid!=0){
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if(rname!=null&&rname.length()>0&&!"null".equals(rname)){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }

        if(highPrice!=-1){
            sb.append(" and price < ?");
            params.add(highPrice);
        }
        if(lowPrice!=-1){
            sb.append(" and price > ?");
            params.add(lowPrice);

        }
        if(order==1){
            sb.append(" order by count desc ");
        }
        sql=sb.toString();

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, params.toArray());
        return count;

    }

    @Override
    public List<Route> pageQuery(int cid, int start, int pageSize, String rname, int lowPrice, int highPrice, int order) {
        String sql="select * from tab_route  WHERE 1=1 ";
        StringBuilder sb=new StringBuilder(sql);
        List params=new ArrayList();
        if(cid!=0){
            sb.append(" and cid = ? ");
            params.add(cid);
        }
        if(rname!=null&&rname.length()>0&&!"null".equals(rname)){
            sb.append(" and rname like ? ");
            params.add("%"+rname+"%");
        }
        if(highPrice!=-1){
            sb.append(" and price < ?");
            params.add(highPrice);
        }
        if(lowPrice!=-1){
            sb.append(" and price > ?");
            params.add(lowPrice);

        }
        if(order==1){
            sb.append(" order by count desc ");
        }else if(order==2) {
            sb.append(" order by rdate desc ");
        }else if(order==3){
            sb.append(" order by isThemeTour  desc ");
        }
        sb.append(" limit ? , ? ");
        params.add(start);
        params.add(pageSize);
        sql=sb.toString();


        System.out.println(sql);
        System.out.println(params);

        List<Route> list=jdbcTemplate.query(sql,new BeanPropertyRowMapper<Route>(Route.class),params.toArray());
        System.out.println("RouteDaoImp的："+list);
        return list ;


    }

    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid = ?";
        return jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Route>(Route.class),rid);

    }
}
