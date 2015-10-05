/* Copyright 2015 Roychoudhury, Abhishek */

package org.abhishek.easysupport.api.rest.impl;

import org.abhishek.easysupport.dto.rest.RequestWrapper;
import org.abhishek.easysupport.dto.rest.RestOutputWrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 * 
 * @author abhishek
 * @since 1.0
 */
@RestController
public class TestApiImpl {
    @RequestMapping(value = "/another-health-check", method = RequestMethod.GET)
    @ResponseBody
    public RestOutputWrapper healthCheck() {
        RequestWrapper requestWrapper = new RequestWrapper();
        requestWrapper.setFileName("etmsapp.1.log");
        requestWrapper.setFilePath("./src/test/resources");
        requestWrapper.setUserId("ABHISHEK");
        requestWrapper.setFromLineNumber(1);
        requestWrapper.setToLineNumber(11);
        requestWrapper.setPermission("<FIRST>1</FIRST><SECOND>3</SECOND><THIRD>9</THIRD>");

        RestOutputWrapper outputWrapper = new RestOutputWrapper();
        outputWrapper.setData(requestWrapper);
        return outputWrapper;
    }

}
