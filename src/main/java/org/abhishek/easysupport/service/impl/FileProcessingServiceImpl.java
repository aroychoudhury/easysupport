/* Copyright 2015 Roychoudhury, Abhishek */

package org.abhishek.easysupport.service.impl;

import java.io.File;
import java.text.ParseException;

import org.abhishek.easysupport.convert.impl.FileWrapperConverter;
import org.abhishek.easysupport.dto.rest.FileWrapper;
import org.abhishek.easysupport.dto.rest.RequestWrapper;
import org.abhishek.easysupport.dto.rest.ResponseWrapper;
import org.abhishek.easysupport.execute.impl.ProcessETMSLogFileContent;
import org.abhishek.fileanalytics.dto.persist.FileMetadata;
import org.abhishek.fileanalytics.persist.impl.FileMetaSerializingPersister;
import org.abhishek.fileanalytics.process.impl.PreProcessLogFileContent;
import org.abhishek.fileanalytics.utils.DateUtils;
import org.abhishek.fileanalytics.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author abhishek
 * @since 1.0
 */
public class FileProcessingServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(FileProcessingServiceImpl.class);

    public void preProcess(RequestWrapper reqWrapper) throws ParseException {
        logger.info("Processing started for FileProcessingServiceImpl.preProcess");
        String fileLoc = reqWrapper.getFilePath() + File.separator + reqWrapper.getFileName();
        logger.info("fileLoc : {}", fileLoc);

        FileWrapper fileWrapper = FileUtils.fileMeta(fileLoc, new FileWrapperConverter());
        logger.debug("{}", fileWrapper);

        FileMetadata metadata = new FileMetadata.Builder(
            DateUtils.parse(fileWrapper.getModifiedStr()),
            fileWrapper.getName(),
            fileWrapper.getExtn(),
            reqWrapper.getFilePath()).build();
        logger.debug("{}", metadata);

        PreProcessLogFileContent preProcessor = new PreProcessLogFileContent(metadata);
        preProcessor.initialize();
        preProcessor.process();
        preProcessor.destroy();

        FileMetaSerializingPersister persister = new FileMetaSerializingPersister();
        persister.persist(FileUtils.serilizedFileName(fileWrapper.getName()), metadata);
    }

    public ResponseWrapper process(RequestWrapper reqWrapper) throws ParseException {
        logger.info("Processing started for FileProcessingServiceImpl.process");
        ResponseWrapper respWrapper = new ResponseWrapper();

        String fileLoc = reqWrapper.getFilePath() + File.separator + reqWrapper.getFileName();
        logger.info("fileLoc : {}", fileLoc);

        FileWrapper fileWrapper = FileUtils.fileMeta(fileLoc, new FileWrapperConverter());
        logger.debug("{}", fileWrapper);

        FileMetaSerializingPersister persister = new FileMetaSerializingPersister();
        FileMetadata metadata = persister.retrieve(fileWrapper.getName());
        logger.debug("{}", metadata);

        if (null == metadata) {
            new IllegalStateException("Files cannot be processed without pre-processing");
        }

        ProcessETMSLogFileContent processor = new ProcessETMSLogFileContent(
            metadata,
            reqWrapper.getPermission(),
            reqWrapper.getFromLineNumber(),
            reqWrapper.getToLineNumber());
        processor.initialize();
        processor.process();
        respWrapper.setContent(processor.getContent());
        processor.destroy();

        return respWrapper;
    }
}
