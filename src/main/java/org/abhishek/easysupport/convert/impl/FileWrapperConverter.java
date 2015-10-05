package org.abhishek.easysupport.convert.impl;

import java.io.File;

import org.abhishek.easysupport.dto.rest.FileWrapper;
import org.abhishek.fileanalytics.constants.FileTypes;
import org.abhishek.fileanalytics.convert.Converter;
import org.abhishek.fileanalytics.utils.DateUtils;
import org.abhishek.fileanalytics.utils.FileUtils;

public class FileWrapperConverter implements Converter<FileWrapper, File> {

    @Override
    public FileWrapper convert(File file) {
        String fileName = file.getName();
        FileWrapper convFile = new FileWrapper();
        convFile.setName(FileUtils.extractFileName(fileName));
        convFile.setExtn(FileUtils.extractFileExtension(fileName));
        convFile.setPath(file.getAbsolutePath());
        convFile.setModifiedStr(DateUtils.format(file.lastModified()));
        convFile.setPreProcessed(FileUtils.fileExists(FileUtils.serilizedFileName(fileName, convFile.getModifiedStr())));
        if (file.isDirectory()) {
            convFile.setType(FileTypes.DIRECTORY);
        } else if (file.isFile()) {
            convFile.setType(FileTypes.FILE);
        }
        return convFile;
    }

}
