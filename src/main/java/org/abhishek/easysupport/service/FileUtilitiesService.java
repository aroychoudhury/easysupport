package org.abhishek.easysupport.service;

import org.abhishek.easysupport.dto.rest.FileWrapper;

public interface FileUtilitiesService {

    FileWrapper get(String filePath);

    String getBasic(String filePath);

    boolean delete(String filePath);

}