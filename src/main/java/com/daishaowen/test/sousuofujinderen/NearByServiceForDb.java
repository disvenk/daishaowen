package com.daishaowen.test.sousuofujinderen;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import java.util.List;
import java.util.Map;

public class NearByServiceForDb {

//    位置上报;
    public String positionReport(String userName,Double lng,Double lat){
//        List<PositionInfo> positionInfo = positionMapper.findPos(userName);
        List<PositionInfo> positionInfo =null;
        PositionInfo position = new PositionInfo(userName,lng,lat);
        int row=0;
        if(positionInfo!=null && positionInfo.size()>0){
//            row=positionMapper.updatePosition(position);
        }else {
//            row=positionMapper.savePosition(position);
        }

        if(row>0)
            return "{code:200,msg:\"位置上报成功\"}";
        return "{code:200,msg:\"位置上报失败\"}";
    }

    //查询用户指定范围内的其他用户信息
    public String findNeayBy(String userName,int radius){
        //根据用户名，从数据库查询自己的位置
        //实际业务场景，自己的位置缓存
//        List<PositionInfo>  positionInfo = positionMapper.findPositionInfo(userName);
        List<PositionInfo> positionInfo =null;
        if(positionInfo!=null && !positionInfo.isEmpty()){
            PositionInfo position = positionInfo.get(0);
//            List<Map<String,Object>> nearByList= positionMapper.findNearBy(position.getLongitude(),position.getLatitude(),radius);
            List<Map<String,Object>> nearByList= null;
            JSONArray array = JSONArray.parseArray(JSON.toJSONString(nearByList));
            return array.toJSONString();
        }
        return "{code:200,msg:\"没有查询到数据\"}";
    }
}
