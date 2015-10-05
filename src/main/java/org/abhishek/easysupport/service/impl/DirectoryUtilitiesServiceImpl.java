package org.abhishek.easysupport.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.abhishek.easysupport.convert.impl.FileWrappersArrayConverter;
import org.abhishek.easysupport.dto.rest.Restful;
import org.abhishek.easysupport.service.DirectoryUtilitiesService;
import org.abhishek.fileanalytics.convert.impl.BasicFilesArrayConverter;
import org.abhishek.fileanalytics.utils.FileUtils;
import org.springframework.stereotype.Service;

/**
 * TODO
 * 
 * @author abhishek
 * @since 1.0
 */
@Transactional
@Service("DirectoryServices")
public class DirectoryUtilitiesServiceImpl implements DirectoryUtilitiesService {
    /*
     * @see org.abhishek.easysupport.service.FileUtilitiesService#getFiles(java
     * .lang.String)
     */
    @Override
    public List<Restful> getContents(String directoryPath) {
        return FileUtils.listContents(
            directoryPath,
            new FileWrappersArrayConverter());
    }

    /*
     * @see org.abhishek.easysupport.service.FileUtilitiesService#getFilesSimple
     * (java.lang.String)
     */
    @Override
    public List<String> getContentsBasic(String directoryPath) {
        return FileUtils.listContents(
            directoryPath,
            new BasicFilesArrayConverter());
    }

    /*
     * @see org.abhishek.easysupport.service.FileUtilitiesService#clearDirectory
     * (java.lang.String)
     */
    @Override
    public boolean clear(String directoryPath) {
        return (null != FileUtils.cleanDirectory(directoryPath)) ? true : false;
    }

    /*
     * @see
     * org.abhishek.easysupport.service.FileUtilitiesService#removeDirectory
     * (java.lang.String)
     */
    @Override
    public boolean delete(String directoryPath) {
        FileUtils.deleteDirectory(directoryPath);
        return true;
    }
}
