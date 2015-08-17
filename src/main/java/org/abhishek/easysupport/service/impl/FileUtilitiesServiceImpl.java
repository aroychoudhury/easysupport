/**
 * 
 */
package org.abhishek.easysupport.service.impl;

import org.abhishek.easysupport.convert.impl.FileWrapperConverter;
import org.abhishek.easysupport.dto.rest.FileWrapper;
import org.abhishek.easysupport.service.FileUtilitiesService;
import org.abhishek.fileanalytics.convert.impl.BasicFileConverter;
import org.abhishek.fileanalytics.utils.FileUtils;

/**
 * @author abhishek
 *
 */
public class FileUtilitiesServiceImpl implements FileUtilitiesService {

    /*
     * @see
     * org.abhishek.easysupport.service.FileUtilitiesService#get(java.lang
     * .String)
     */
    @Override
    public FileWrapper get(String filePath) {
        return FileUtils.fileMeta(filePath, new FileWrapperConverter());
    }

    /*
     * @see
     * org.abhishek.easysupport.service.FileUtilitiesService#getBasic(java
     * .lang.String)
     */
    @Override
    public String getBasic(String filePath) {
        return FileUtils.fileMeta(filePath, new BasicFileConverter());
    }

    /*
     * @see
     * org.abhishek.easysupport.service.FileUtilitiesService#delete(java.lang
     * .String)
     */
    @Override
    public boolean delete(String filePath) {
        FileUtils.deleteFile(filePath);
        return true;
    }

}
