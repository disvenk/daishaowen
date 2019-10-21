/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT China Mobile (SuZhou) Software Technology Co.,Ltd. 2016
 *
 * The copyright to the computer program(s) herein is the property of
 * CMSS Co.,Ltd. The programs may be used and/or copied only with written
 * permission from CMSS Co.,Ltd. or in accordance with the terms and conditions
 * stipulated in the agreement/contract under which the program(s) have been
 * supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.daishaowen.test.chinaMobile.dto;

/**
 *
 */
public class RequestHeaderDto {

    /**
     * 接入渠道
     */
    private String inModeCode;

    /**
     * 受理员工
     */
    private String tradeStaffId;

    /**
     * 受理部门
     */
    private String tradeDepartId;

    /**
     * 受理业务区
     */
    private String tradeCityCode;

    /**
     * 受理地市
     */
    private String tradeEparchyCode;

    public String getInModeCode() {

        return inModeCode;
    }

    public void setInModeCode(final String inModeCode) {

        this.inModeCode = inModeCode;
    }

    public String getTradeStaffId() {

        return tradeStaffId;
    }

    public void setTradeStaffId(final String tradeStaffId) {

        this.tradeStaffId = tradeStaffId;
    }

    public String getTradeDepartId() {

        return tradeDepartId;
    }

    public void setTradeDepartId(final String tradeDepartId) {

        this.tradeDepartId = tradeDepartId;
    }

    public String getTradeCityCode() {

        return tradeCityCode;
    }

    public void setTradeCityCode(final String tradeCityCode) {

        this.tradeCityCode = tradeCityCode;
    }

    public String getTradeEparchyCode() {

        return tradeEparchyCode;
    }

    public void setTradeEparchyCode(final String tradeEparchyCode) {

        this.tradeEparchyCode = tradeEparchyCode;
    }

}
