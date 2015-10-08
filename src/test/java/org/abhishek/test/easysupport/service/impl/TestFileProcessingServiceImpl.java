/* Copyright 2015 Roychoudhury, Abhishek */

package org.abhishek.test.easysupport.service.impl;

import java.io.File;
import java.text.ParseException;

import org.abhishek.easysupport.dto.rest.RequestWrapper;
import org.abhishek.easysupport.dto.rest.ResponseWrapper;
import org.abhishek.easysupport.service.LogFileProcessingService;
import org.abhishek.easysupport.spring.config.SpringRootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author abhishek
 * @since 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
    SpringRootConfig.class
})
public class TestFileProcessingServiceImpl {
    @Autowired
    @Qualifier("LogProcessServices")
    private LogFileProcessingService logProcessServices = null;

    /**
     * Test method for
     * {@link org.abhishek.easysupport.service.impl.LogFileProcessingService#preProcess(org.abhishek.easysupport.dto.rest.RequestWrapper)}
     * .
     * 
     * @throws ParseException
     */
    @Test
    public void testPreProcess() throws ParseException {
        System.out.println((new File(".")).getAbsolutePath());
        RequestWrapper request = new RequestWrapper();
        request.setFileName("etmsapp.1.log");
        request.setFilePath("./src/test/resources");
        request.setUserId("ABHISHEK");
        request.setFromLineNumber(1);
        request.setToLineNumber(11);
        request.setPermission("<FIRST>1</FIRST><SECOND>3</SECOND><THIRD>9</THIRD>");

        logProcessServices.preProcess(request);
    }

    /**
     * Test method for
     * {@link org.abhishek.easysupport.service.impl.LogFileProcessingService#process(org.abhishek.easysupport.dto.rest.RequestWrapper)}
     * .
     * 
     * @throws ParseException
     */
    @Test
    public void testProcess() throws ParseException {
        RequestWrapper request = new RequestWrapper();
        request.setFileName("etmsapp.1.log");
        request.setFilePath("./src/test/resources");
        request.setUserId("ABHISHEK");
        request.setFromLineNumber(2);
        request.setToLineNumber(21);
        request.setPermission("<FIRST>1</FIRST><SECOND>6</SECOND><THIRD>9</THIRD>");

        ResponseWrapper response = logProcessServices.process(request);
        if (null != response.getContent()) {
            String[] contents = response.getContent();
            for (String content : contents) {
                System.out.print(content);
            }
        }
    }

}
