package org.abhishek.easysupport.service;

import java.util.List;

import org.abhishek.easysupport.dto.rest.FileWrapper;

public interface DirectoryUtilitiesService {

    List<FileWrapper> getContents(String directoryPath);

    List<String> getContentsBasic(String directoryPath);

    boolean clear(String directoryPath);

    boolean delete(String directoryPath);

}