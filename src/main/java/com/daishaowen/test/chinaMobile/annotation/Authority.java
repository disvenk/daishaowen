package com.daishaowen.test.chinaMobile.annotation;

import java.lang.annotation.*;

/**
 * To support authority verify
 *
 * @author yuanjian
 *
 */

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Authority {

    public enum Name {
        A001_Manage_API("A001"), // API管理
        A002_Operation_Center("A002"), // 运营中心
        A003_Analysis_Center("A003"), // 分析中心
        A004_Monitoring_Diagnosis("A004"), // 监控诊断
        A005_Manage_User("A005"), // 用户管理
        A006_System_Setting("A006"), // 系统设置
        A007_Big_Data("A007"), // 大数据

        B001_API_Designer("B001"), // API设计器
        B002_List_API("B002"), // API列表
        B003_Control_Strategy("B003"), // 策略控制
        B004_Manage_Consumer("B004"), // 消费者管理
        B005_Manage_APIs("B005"), // API目录管理
        B006_Approve_Center("B006"), // 审批中心
        B007_Manage_Promotion("B007"), // 套餐管理
        B008_Manage_Bill_Info("B008"), // 账单管理
        B009_Manage_Partner("B009"), // 合作伙伴管理
        B010_Manage_Notice("B010"), // 公告管理
        B011_Manage_FAQ("B011"), // FAQ管理
        B012_Manage_Process_Info("B012"), // 业务流程
        B013_Switch_API("B013"), // API启停
        B014_Manage_Subscription("B014"), // 订阅管理

        // 分析中心A003
        B032_Quality_Tatistic("B032"), // 服务质量分析
        B033_Partner_Tatistic("B033"), // 合作伙伴分析
        B034_Capacity_Tatistic("B034"), // 能力运营分析
        B035_App_Tatistic("B035"), // 应用统计分析
        B036_Charging_Statistics("B036"), // 计费统计

        B038_Object_Alarm("B038"), // 告警对象
        B037_Info_Alarm("B037"), // 告警记录

        B015_Platform_Statistics("B015"), // 平台统计
        B016_Query_Bussiness_Log("B016"), // 业务交易日志查询
        B017_Topological_Relationship("B017"), // 拓扑关系
        B018_Fault_Conduction_Analysis("B018"), // 故障传导分析
        B019_Manage_Alarm("B019"), // 告警管理

        B020_Manage_Account("B020"), // 账号管理
        B021_Query_Operator_Log("B021"), // 操作日志
        B022_Manage_Tenant("B022"), // 租户管理

        // 系统设置A006
        B027_Manage_PartnerNum_Info("B027"), // 工号管理
        B028_Manage_ConstantInfo("B028"), // 业务字典管理
        B029_Manage_FTP_Address("B029"), // FTP地址管理
        B030_Manage_AdPosition_Carousel("B030"), // 广告轮播图设置
        B031_Manage_AdPosition_HotAbility("B031"), // 热区能力展示设置

        // B023_Manage_Configure("B023"), //配置管理
        B023_Manage_Tag("B023"), // 标签管理
        B024_High_Speed_Query("B024"), // 高速查询
        B025_SQL_Bootstrap_Package("B025");// SQL自助封装

        // A001_Approve_Center("A001"), // 审核中心
        // A002_Manage_Ability("A002"), // 能力启停
        // A003_Analysis("A003"), // 统计报表
        // A004_Manage_Resource("A004"), // 资源管理
        // A005_Manage_Account("A005"), // 账号管理
        // A006_Manage_Partner("A006"), // 合作伙伴管理
        // A007_Manage_Operator_Log("A007"), // 操作日志
        // A008_Manage_Config_Info("A008"), // 配置管理
        // A009_Manage_Process_Info("A009"), // 业务流程管理
        // A010_Manage_Bill_Info("A010"), // 账单管理
        // B001_List_Approving("B001"), // 查看待审核页签
        // B002_List_Approved("B002"), // 查看已审核页签
        // B003_Query_Ability("B003"), // 按能力查询
        // B004_Query_Partner("B004"), // 按合作伙伴查询
        // B005_Ability_Total("B005"), // 能力总开关
        // B006_Query_Bussiness_Log("B006"), // 查看业务日志查询
        // B007_Query_Service_Quality("B007"), // 查看服务质量分析
        // B008_Query_Partner_Analysis("B008"), // 查看合作伙伴分析
        // B009_Query_Operation_Ability("B009"), // 查看能力运营分析
        // B010_Query_Application_Analysis("B010"), // 查看应用统计分析
        // B011_Query_FAQ("B011"), // 查看FAQ
        // B012_Query_Notice("B012"), // 查看公告
        // B013_Query_Ability("B013"), // 查看能力
        // B014_Detail_Operator("B014"), // 运营人员资料
        // B015_Manage_Role("B015"), // 角色管理
        // B016_Detail_Partner("B016"), // 合作伙伴资料
        // B017_Query_Operator_Log("B017"), // 操作日志展示页
        // B018_Manage_ConstantInfo("B018"), // 业务数据管理页
        // B019_Manage_PartnerNum_Info("B019"), // 工号管理页
        // B020_Manage_Process("B020"), // 业务流程管理
        // B021_Process_Monitor("B021"), // 业务流程监控
        // B022_Manage_Partner_Usergroup("B022"), // 用户组管理
        // B023_Query_Subscription("B023"), // 订阅管理
        // B024_Manage_FTP_Address("B024"), // FTP地址管理
        // B025_Manage_AdPosition_Carousel("B025"), // 轮播计划设置
        // B026_Manage_AdPosition_HotAbility("B026"), // 热门能力展示设置
        // B027_Manage_Ad_Lib("B027"), //广告图片库设置
        // B028_Manage_Promotion("B028"), //套餐配置
        // B029_Charging_Statistics("B029"), //计费统计
        // C001_Approve_Sign_List("C001"), // 审批签约申请
        // C002_Approve_QuotaChange_List("C002"), // 审批配额变更申请
        // C003_Approve_FlowChange_List("C003"), // 审批流控变更申请
        // C004_Approve_AbilityInvoke_List("C004"), // 审批流控变更申请
        // C005_AbilitySwitch_Ability("C005"), // 启停权限C005 启停能力级开关
        // C006_AbilitySwitch_Partner("C006"), // 启停权限C006 启停合作伙伴级开关
        // C007_AbilitySwitch_Total("C007"), // 启停权限C007 启停能力总开关
        // C008_FAQ_Add("C008"), // 增加FAQ
        // C009_FAQ_Delete("C009"), // 删除FAQ
        // C010_FAQ_Update("C010"), // 修改FAQ
        // C011_Notice_Add("C011"), // 增加公告
        // C012_Notice_Delete("C012"), // 删除公告
        // C013_Notice_Update("C013"), // 修改公告
        // C014_Create_Ability("C014"), // 新建能力
        // C015_Delete_Ability("C015"), // 删除能力
        // C016_Update_Ability("C016"), // 编辑能力
        // C017_Publish_Ability("C017"), // 发布能力
        // C018_Offline_Ability("C018"), // 下线能力
        // C019_Operate_Create("C019"), // 新建运营人员
        // C020_Operate_Update("C020"), // 修改运营者人员
        // C021_Operate_Delete("C021"), // 删除运营者人员
        // C022_Role_Add("C022"), // 增加角色
        // C023_Role_Delete("C023"), // 删除角色
        // C024_Role_Update("C024"), // 修改角色
        // C025_Partner_Add("C025"), // 增加合作伙伴
        // C026_Partner_Delete("C026"), // 删除合作伙伴
        // C027_Partner_Update("C027"), // 修改合作伙伴
        // C028_ConstantInfo_Add("C028"), // 新增配置信息
        // C029_ConstantInfo_del("C029"), // 删除配置信息
        // C030_ConstantInfo_Update("C030"), // 修改配置信息
        // C031_PartnerNum_Add("C031"), // 新增工号信息
        // C032_PartnerNum_del("C032"), // 删除工号信息
        // C033_PartnerNum_Update("C033"), // 修改工号信息
        // C034_Process_Add("C034"), // 创建业务流程
        // C035_Process_Delete("C035"), // 删除业务流程
        // C036_Process_Update("C036"), // 更新/修改业务流程
        // C037_Process_Query("C037"), // 查询业务流程
        // C038_Process_Monitor_Info_Query("C038"), // 查询监控信息
        // C039_Usergroup_Add("C039"), // 增加用户组
        // C040_Usergroup_Delete("C040"), // 删除用户组
        // C041_Usergroup_Update("C041"), // 更新用户组
        // C042_Usergroup_Query("C042"), // 查询用户组
        // C043_Process_Publish("C043"), // 发布业务流程
        // C044_Process_Offline("C044"), // 下线业务流程
        // C045_Subscription_Add("C045"), // 新增订阅关系
        // C046_Subscription_Delete("C046"), // 删除订阅关系
        // C047_Subscription_Update("C047"), // 更新/修改订阅关系
        // C048_Subscription_Query("C048"), // 查询订阅关系
        // C049_FTP_Address_Add("C049"), // 新建FTP地址
        // C050_FTP_Address_Delete("C050"), // 删除FTP地址
        // C051_FTP_Address_Update("C051"), // 修改FTP地址
        // C052_FTP_Address_Query("C052"), // 查看FTP地址
        // C053_FTPSwitch_Address("C053"), // 启停FTP地址开关
        // C054_PARAM_TAILOR_Add("C054"), // 新建接口参数裁剪
        // C055_PARAM_TAILOR_Update("C055"), // 修改接口参数裁剪
        // C056_PARAM_TAILOR_Query("C056"), // 查询接口参数裁剪
        // C057_Partner_Quota("C057"), // 合作伙伴配额管理
        // C058_Partner_Update_AppFlow("C058"), // 合作伙伴流控申请
        // C059_Export_Business_Log("C059"), // 业务日志导出
        // C060_Export_Partner_Tatistic("C060"), // 统计查询结果导出_按合作伙伴维度
        // C061_Export_App_Tatistic("C061"), // 统计查询结果导出_按应用维度
        // C062_Export_Capacity_Tatistic("C062"), // 统计查询结果导出_按能力维度
        // C063_Export_Quality_Tatistic("C063"), // 统计查询结果导出_服务质量分析
        // C064_Export_Operator_Log("C064"), // 操作日志导出
        // C065_AdPosition_Add("C065"), // 新增轮播计划
        // C066_AdPosition_Update("C066"), // 编辑轮播计划
        // C067_AdPosition_Query("C067"), // 查看轮播计划
        // C068_AdPosition_Delete("C068"), // 删除轮播计划
        // C069_AdPosition_HotAbility_Add("C069"), // 新增能力展示
        // C070_AdPosition_HotAbility_Update("C070"), // 编辑能力展示
        // C071_AdPosition_HotAbility_Query("C071"), // 查看能力展示
        // C072_AdPosition_HotAbility_Delete("C072"), // 删除能力展示
        // C073_Ad_Lib_Add("C073"), //新增广告图片库
        // C074_Ad_Lib_Update("C074"), //编辑广告图片库
        // C075_Ad_Lib_Query("C075"), //查看广告图片库
        // C076_Ad_Lib_Delete("C076"), //删除广告图片库
        // C077_Promotion_Add("C077"), //新增套餐
        // C078_Promotion_Update("C078"), //编辑套餐
        // C079_Promotion_Query("C079"), //查看套餐
        // C080_Promotion_Delete("C080"), //删除套餐
        // C081_Promotion_Status("C081"), //上/下架套餐
        // C082_Charging_Statistics_Export("C082"), //导出计费统计分析
        // C083_Charging_Statistics_Query("C083"), //查看计费统计
        // C084_Partner_Order_Delete("C084") //取消订购套餐

        public String value;

        Name(final String value) {

            this.value = value;
        }
    }

    public Name[] name();
}
