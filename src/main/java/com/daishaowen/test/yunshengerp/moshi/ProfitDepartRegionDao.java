//package com.daishaowen.test.yunshengerp.moshi;
//
//import com.alibaba.fastjson.JSONObject;
//import com.daishaowen.test.yunshengerp.javacommon.base.BaseEntity;
//import com.daishaowen.test.yunshengerp.javacommon.base.BaseIbatis3Dao;
//import org.springframework.dao.DataAccessException;
//import org.springframework.stereotype.Repository;
//
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * [部门(地区)报表数据访问对象]
// *
// * @author disvenk[代绍文]
// * @version [版本, 2019-04-19]
// */
//@SuppressWarnings("unchecked")
//@Repository
//public class ProfitDepartRegionDao extends BaseIbatis3Dao<BaseEntity, Long> {
//
//    @Override
//    public String getIbatisMapperNamesapce() {
//        return "ProfitDepartRegion";
//    }
//
//    //使用该方法时，vo要继承BaseEntity
//    @Override
//    public void saveOrUpdate(BaseEntity entity) throws DataAccessException {
//    }
//
//    /**
//     * [创建部门(地区)报表表头]
//     *
//     * @author disvenk[代绍文]
//     * @version [版本, 2019-04-19]
//     */
//    public void createProfitDepartRegionTitle( ){
//       getSqlSessionTemplate().selectOne("ProfitDepartRegion.createProfitDepartRegionTitle", null);
//    }
//
//    /**
//     * [获取部门(地区)报表表头]
//     *
//     * @author disvenk[代绍文]
//     * @version [版本, 2019-04-19]
//     */
//    public String getProfitDepartRegionTitle(){
//        Map<String,Object> map = new HashMap<>();
//        map.put("time_stamp",System.currentTimeMillis());
//        String title = (String)getSqlSessionTemplate().selectOne("ProfitDepartRegion.getProfitDepartRegionTitle", map);
//        return title;
//    }
//}
