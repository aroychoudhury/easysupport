/* Copyright 2015 Code Avengers */

package org.abhishek.easysupport.dto.rest;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * All data objects which are to be transferred to the UI/Web Layer has to be
 * wrapped in this class.
 * 
 * @author abhishek
 * @since 1.0
 */
@JsonInclude(Include.NON_EMPTY)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class RestInputWrapper {
    private Restful       data        = null;
    private List<Restful> dataList    = null;
    private String        strData     = null;
    private List<String>  strDataList = null;

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
    public List<Restful> getDataList() {
        return dataList;
    }

    /**
     * @param dataList
     *            the dataList to set
     * @since 1.0
     * @see List
     */
    public void setDataList(List<Restful> dataList) {
        this.dataList = dataList;
    }

    /**
     * @return the strData
     * @since 1.0
     * @see String
     */
    public String getStrData() {
        return strData;
    }

    /**
     * @param strData
     *            the strData to set
     * @since 1.0
     * @see String
     */
    public void setStrData(String strData) {
        this.strData = strData;
    }

    /**
     * @return the strDataList
     * @since 1.0
     * @see List<String>
     */
    public List<String> getStrDataList() {
        return strDataList;
    }

    /**
     * @param strDataList
     *            the strDataList to set
     * @since 1.0
     * @see List<String>
     */
    public void setStrDataList(List<String> strDataList) {
        this.strDataList = strDataList;
    }
}
