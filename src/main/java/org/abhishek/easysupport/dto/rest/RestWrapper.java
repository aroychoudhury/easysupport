/* Copyright 2015 Code Avengers */

package org.abhishek.easysupport.dto.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * All data objects which are to be transferred to the UI/Web Layer has to be
 * wrapped in this class.
 * 
 * @author abhishek
 * @since 1.0
 */
@JsonInclude(Include.NON_EMPTY)
public class RestWrapper {
    private Restful data     = null;
    @SuppressWarnings("rawtypes")
    private List    dataList = null;
    private String  errCd    = null;
    private String  errMsg   = null;
    private String  errStack = null;

    /**
     * @return the data
     * @since 1.0
     * @see org.codeavengers.common.dto.Restful
     */
    public Restful getData() {
        return data;
    }

    /**
     * @param data
     *            the data to set
     * @since 1.0
     * @see Restful
     */
    public void setData(Restful data) {
        this.data = data;
    }

    /**
     * @return the dataList
     * @since 1.0
     * @see List
     */
    @SuppressWarnings("rawtypes")
    public List getDataList() {
        return dataList;
    }

    /**
     * @param dataList
     *            the dataList to set
     * @since 1.0
     * @see List
     */
    @SuppressWarnings("rawtypes")
    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    /**
     * @return the errCd
     * @since 1.0
     * @see String
     */
    public String getErrCd() {
        return errCd;
    }

    /**
     * @return the errMsg
     * @since 1.0
     * @see String
     */
    public String getErrMsg() {
        return errMsg;
    }

    /**
     * @param errCd
     *            the errCd to set
     * @param errMsg
     *            the errMsg to set
     * @since 1.0
     * @see String
     */
    public void setError(String errCd,
        String errMsg) {
        this.errCd = errCd;
        this.errMsg = errMsg;
    }

    /**
     * @return the errStack
     * @since 1.0
     * @see String
     */
    public String getErrStack() {
        return errStack;
    }

    /**
     * @param errStack
     *            the errStack to set
     * @since 1.0
     * @see String
     */
    public void setErrStack(String errStack) {
        this.errStack = errStack;
    }
}
