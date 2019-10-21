package com.daishaowen.test.sousuofujinderen;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.GeoRadiusResponse;
import redis.clients.jedis.GeoUnit;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.geo.GeoRadiusParam;

import java.util.List;

public class NearByServiceForRedis {
    @Autowired
    JedisPool jedisPool;

    /**
     * 添加坐标
     * key 经度  维度  距离
     * return m 表示单位为米*/
    public  Long addReo(PositionInfo coordinate) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //第一个参数可以理解为表名
            return jedis.geoadd("test",coordinate.getLongitude(),coordinate.getLatitude(),coordinate.getKey());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (null != jedis)
                jedis.close();
        }
        return null;
    }

    /*
    * 上报位置
    * */
    public void insertOrUpdatePosition(){
        PositionInfo coordinate=new PositionInfo("1",31.244803,121.483671);
        coordinate.setLatitude(31.244803);  //维度
        coordinate.setLongitude(121.483671); //经度
        coordinate.setKey("1");  //可以作为用户表的id


        //添加经纬度
        PositionInfo coordinate1=new PositionInfo("2",31.244803,121.483671);
        coordinate1.setLatitude(31.245321);  //维度
        coordinate1.setLongitude(121.485015); //经度
        coordinate1.setKey("2");  //可以作为用户表的id

        //添加经纬度
        PositionInfo coordinate2=new PositionInfo("3",31.244803,121.483671);
        coordinate2.setLatitude(31.245456);  //维度
        coordinate2.setLongitude(121.485285); //经度
        coordinate2.setKey("3");  //可以作为用户表的id

        addReo(coordinate);
        addReo(coordinate1);
        addReo(coordinate2);
    }

    public List<GeoRadiusResponse> geoQuery(PositionInfo coordinate) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            //200F GeoUnit.KM表示km
            return jedis.georadius("test",coordinate.getLongitude(),coordinate.getLatitude(),200, GeoUnit.KM, GeoRadiusParam.geoRadiusParam().withDist());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (null != jedis)
                jedis.close();
        }
        return null;
    }

    public void nearByFind(){
       Jedis jedis = jedisPool.getResource();
        //withDist得到距离
        //withCoord得到坐标
        //sortAscending排序
       GeoRadiusParam param =GeoRadiusParam.geoRadiusParam().withDist().withCoord().sortAscending();
       List<GeoRadiusResponse> positionList = jedis.georadiusByMember("u:position","five",1000,GeoUnit.M);

       for(GeoRadiusResponse resp:positionList){
           System.out.println("--->"+resp.getMemberByString()+"离five"+resp.getDistance()+"米,坐标："+resp.getCoordinate());
       }
    }
}
