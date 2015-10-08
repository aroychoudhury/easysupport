/* Copyright 2015 Roychoudhury, Abhishek */

package org.abhishek.easysupport.service;

import java.text.ParseException;

import org.abhishek.easysupport.dto.rest.RequestWrapper;
import org.abhishek.easysupport.dto.rest.ResponseWrapper;

/**
 * TODO
 * @author abhishek
 * @since  1.0
 */
public interface LogFileProcessingService {

    void preProcess(RequestWrapper request) throws ParseException;

    ResponseWrapper process(RequestWrapper request) throws ParseException;

}
