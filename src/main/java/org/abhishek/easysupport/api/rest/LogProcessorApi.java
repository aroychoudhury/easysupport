/* Copyright 2015 Roychoudhury, Abhishek */

package org.abhishek.easysupport.api.rest;

import org.abhishek.easysupport.dto.rest.RestInputWrapper;
import org.abhishek.easysupport.dto.rest.RestOutputWrapper;

/**
 * TODO
 * @author abhishek
 * @since  1.0
 */
public interface LogProcessorApi {

    RestOutputWrapper viewDirectory(RestInputWrapper inputWrapper);

    RestOutputWrapper viewFile(RestInputWrapper inputWrapper);

    RestOutputWrapper preProcess(RestInputWrapper inputWrapper);

    RestOutputWrapper verifyPreProcess(RestInputWrapper inputWrapper);

    RestOutputWrapper process(RestInputWrapper inputWrapper);

    RestOutputWrapper healthCheck();

}
