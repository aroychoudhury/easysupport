/* Copyright 2015 Roychoudhury, Abhishek */

package org.abhishek.easysupport.api.rest.impl;

import org.abhishek.easysupport.api.rest.LogProcessorApi;
import org.abhishek.easysupport.dto.rest.RequestWrapper;
import org.abhishek.easysupport.dto.rest.RestInputWrapper;
import org.abhishek.easysupport.dto.rest.RestOutputWrapper;
import org.abhishek.easysupport.service.DirectoryUtilitiesService;
import org.abhishek.easysupport.service.FileUtilitiesService;
import org.abhishek.easysupport.service.LogFileProcessingService;
import org.abhishek.fileanalytics.utils.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
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
public class LogProcessorApiImpl implements LogProcessorApi {
    @Autowired
    @Qualifier("DirectoryServices")
    private DirectoryUtilitiesService directoryServices  = null;

    @Autowired
    @Qualifier("FileServices")
    private FileUtilitiesService      fileServices       = null;

    @Autowired
    @Qualifier("LogProcessServices")
    private LogFileProcessingService  logProcessServices = null;

    /**
     * @author abhishek
     * @since 1.0
     * @see org.abhishek.easysupport.api.rest.LogProcessorApi#viewDirectory(org.abhishek.easysupport.dto.rest.RestOutputWrapper)
     */
    @Override
    @RequestMapping(value = "/view-directory", method = RequestMethod.POST)
    @ResponseBody
    public RestOutputWrapper viewDirectory(@RequestBody RestInputWrapper inputWrapper) {
        RestOutputWrapper outputWrapper = new RestOutputWrapper();
        try {
            outputWrapper.setDataList(directoryServices.getContents(inputWrapper.getStrData()));
        } catch(Exception exc) {
            outputWrapper.setError("VWDIR", exc.getMessage());
            outputWrapper.setErrStack(ExceptionUtils.getStackTrace(exc));
        }
        return outputWrapper;
    }

    /**
     * @author abhishek
     * @since 1.0
     * @see org.abhishek.easysupport.api.rest.LogProcessorApi#viewFile(org.abhishek.easysupport.dto.rest.RestOutputWrapper)
     */
    @Override
    @RequestMapping(value = "/view-file", method = RequestMethod.POST)
    @ResponseBody
    public RestOutputWrapper viewFile(RestInputWrapper inputWrapper) {
        RestOutputWrapper outputWrapper = new RestOutputWrapper();
        try {
            outputWrapper.setData(fileServices.get(inputWrapper.getStrData()));
        } catch(Exception exc) {
            outputWrapper.setError("VWFILE", exc.getMessage());
            outputWrapper.setErrStack(ExceptionUtils.getStackTrace(exc));
        }
        return outputWrapper;
    }

    /**
     * @author abhishek
     * @since 1.0
     * @see org.abhishek.easysupport.api.rest.LogProcessorApi#preProcess(org.abhishek.easysupport.dto.rest.RestOutputWrapper)
     */
    @Override
    @RequestMapping(value = "/preprocess", method = RequestMethod.POST)
    @ResponseBody
    public RestOutputWrapper preProcess(RestInputWrapper inputWrapper) {
        RestOutputWrapper outputWrapper = new RestOutputWrapper();
        try {
            if (!(inputWrapper.getData() instanceof RequestWrapper)) {
                throw new IllegalArgumentException("Instance mismatch : " + inputWrapper.getData().getClass().getSimpleName() + ".class");
            }
            RequestWrapper requestWrapper = (RequestWrapper) inputWrapper.getData();
            logProcessServices.preProcess(requestWrapper);
            outputWrapper.setData(requestWrapper); // Send back the same data for success
        } catch(Exception exc) {
            outputWrapper.setError("PREPROC", exc.getMessage());
            outputWrapper.setErrStack(ExceptionUtils.getStackTrace(exc));
        }
        return outputWrapper;
    }

    /**
     * @author abhishek
     * @since 1.0
     * @see org.abhishek.easysupport.api.rest.LogProcessorApi#verifyPreProcess(org.abhishek.easysupport.dto.rest.RestOutputWrapper)
     */
    @Override
    @RequestMapping(value = "/verify-preprocess", method = RequestMethod.POST)
    @ResponseBody
    public RestOutputWrapper verifyPreProcess(RestInputWrapper inputWrapper) {
        RestOutputWrapper outputWrapper = new RestOutputWrapper();
        try {
            outputWrapper.setData(fileServices.get(inputWrapper.getStrData()));
        } catch(Exception exc) {
            outputWrapper.setError("VERPREPROC", exc.getMessage());
            outputWrapper.setErrStack(ExceptionUtils.getStackTrace(exc));
        }
        return outputWrapper;
    }

    /**
     * @author abhishek
     * @since 1.0
     * @see org.abhishek.easysupport.api.rest.LogProcessorApi#process(org.abhishek.easysupport.dto.rest.RestOutputWrapper)
     */
    @Override
    @RequestMapping(value = "/process", method = RequestMethod.POST)
    @ResponseBody
    public RestOutputWrapper process(RestInputWrapper inputWrapper) {
        RestOutputWrapper outputWrapper = new RestOutputWrapper();
        try {
            if (!(inputWrapper.getData() instanceof RequestWrapper)) {
                throw new IllegalArgumentException("Instance mismatch : " + inputWrapper.getData().getClass().getSimpleName() + ".class");
            }
            RequestWrapper requestWrapper = (RequestWrapper) inputWrapper.getData();
            outputWrapper.setData(logProcessServices.process(requestWrapper));
        } catch(Exception exc) {
            outputWrapper.setError("PROCESS", exc.getMessage());
            outputWrapper.setErrStack(ExceptionUtils.getStackTrace(exc));
        }
        return outputWrapper;
    }

    /**
     * @author abhishek
     * @since 1.0
     * @see org.abhishek.easysupport.api.rest.LogProcessorApi#process(org.abhishek.easysupport.dto.rest.RestOutputWrapper)
     */
    @Override
    @RequestMapping(value = "/heath-check", method = RequestMethod.GET)
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
