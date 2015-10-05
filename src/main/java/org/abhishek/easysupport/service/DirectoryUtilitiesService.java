package org.abhishek.easysupport.service;

import java.util.List;

import org.abhishek.easysupport.dto.rest.Restful;

public interface DirectoryUtilitiesService {

    List<Restful> getContents(String directoryPath);

    List<String> getContentsBasic(String directoryPath);

    boolean clear(String directoryPath);

    boolean delete(String directoryPath);

}