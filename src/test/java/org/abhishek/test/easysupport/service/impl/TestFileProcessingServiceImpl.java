/* Copyright 2015 Roychoudhury, Abhishek */

package org.abhishek.test.easysupport.service.impl;

import java.io.File;
import java.text.ParseException;

import org.abhishek.easysupport.dto.rest.RequestWrapper;
import org.abhishek.easysupport.dto.rest.ResponseWrapper;
import org.abhishek.easysupport.service.impl.FileProcessingServiceImpl;
import org.junit.Before;
import org.junit.Test;

/**
 * @author abhishek
 * @since 1.0
 */
public class TestFileProcessingServiceImpl {

    /**
     * @throws java.lang.Exception
     * @author abhishek
     * @since 1.0
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * Test method for
     * {@link org.abhishek.easysupport.service.impl.FileProcessingServiceImpl#preProcess(org.abhishek.easysupport.dto.rest.RequestWrapper)}
     * .
     * 
     * @throws ParseException
     */
    @Test
    public void testPreProcess() throws ParseException {
        System.out.println((new File(".")).getAbsolutePath());
        RequestWrapper requestWrapper = new RequestWrapper();
        requestWrapper.setFileName("etmsapp.1.log");
        requestWrapper.setFilePath("./src/test/resources");
        requestWrapper.setUserId("ABHISHEK");
        requestWrapper.setFromLineNumber(1);
        requestWrapper.setToLineNumber(11);
        requestWrapper.setPermission("<FIRST>1</FIRST><SECOND>3</SECOND><THIRD>9</THIRD>");

        FileProcessingServiceImpl service = new FileProcessingServiceImpl();
        service.preProcess(requestWrapper);
    }

    /**
     * Test method for
     * {@link org.abhishek.easysupport.service.impl.FileProcessingServiceImpl#process(org.abhishek.easysupport.dto.rest.RequestWrapper)}
     * .
     * @throws ParseException 
     */
    @Test
    public void testProcess() throws ParseException {
        RequestWrapper requestWrapper = new RequestWrapper();
        requestWrapper.setFileName("etmsapp.1.log");
        requestWrapper.setFilePath("./src/test/resources");
        requestWrapper.setUserId("ABHISHEK");
        requestWrapper.setFromLineNumber(2);
        requestWrapper.setToLineNumber(21);
        requestWrapper.setPermission("<FIRST>1</FIRST><SECOND>6</SECOND><THIRD>9</THIRD>");

        FileProcessingServiceImpl service = new FileProcessingServiceImpl();
        ResponseWrapper responseWrapper = service.process(requestWrapper);
        if (null != responseWrapper.getContent()) {
            String[] contents = responseWrapper.getContent();
            for (String content : contents) {
                System.out.print(content);
            }
        }
    }

}
