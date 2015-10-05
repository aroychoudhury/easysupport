/* Copyright 2015 Roychoudhury, Abhishek */

package org.abhishek.easysupport.service.impl;

import java.io.File;
import java.text.ParseException;

import javax.transaction.Transactional;

import org.abhishek.easysupport.convert.impl.FileWrapperConverter;
import org.abhishek.easysupport.dto.rest.FileWrapper;
import org.abhishek.easysupport.dto.rest.RequestWrapper;
import org.abhishek.easysupport.dto.rest.ResponseWrapper;
import org.abhishek.easysupport.execute.impl.ProcessETMSLogFileContent;
import org.abhishek.easysupport.service.LogFileProcessingService;
import org.abhishek.fileanalytics.dto.persist.FileMetadata;
import org.abhishek.fileanalytics.persist.Persister;
import org.abhishek.fileanalytics.persist.impl.FileMetaSerializingPersister;
import org.abhishek.fileanalytics.preprocess.AbstractPreProcessor;
import org.abhishek.fileanalytics.preprocess.impl.PreProcessLogFileContent;
import org.abhishek.fileanalytics.process.AbstractProcessor;
import org.abhishek.fileanalytics.utils.DateUtils;
import org.abhishek.fileanalytics.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author abhishek
 * @since 1.0
 */
@Transactional
@Service("LogProcessServices")
public class LogFileProcessingServiceImpl implements LogFileProcessingService {
    private static final Logger logger = LoggerFactory.getLogger(LogFileProcessingServiceImpl.class);

    /**
     * @author abhishek
     * @since  1.0
     * @see org.abhishek.easysupport.service.LogFileProcessingService#preProcess(org.abhishek.easysupport.dto.rest.RequestWrapper)
     */
    @Override
    public void preProcess(RequestWrapper reqWrapper) throws ParseException {
        logger.info("Processing started for LogFileProcessingServiceImpl.preProcess");
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

        AbstractPreProcessor<FileMetadata> preProcessor = new PreProcessLogFileContent(metadata);
        preProcessor.initialize();
        if (preProcessor.validate()) {
            preProcessor.preprocess();
            metadata = preProcessor.getContent();
            preProcessor.destroy();
        }

        Persister<String, FileMetadata> persister = new FileMetaSerializingPersister();
        persister.persist(FileUtils.serilizedFileName(fileWrapper.getName(), metadata.getLastModified()), metadata);
    }

    /**
     * @author abhishek
     * @since  1.0
     * @see org.abhishek.easysupport.service.LogFileProcessingService#process(org.abhishek.easysupport.dto.rest.RequestWrapper)
     */
    @Override
    public ResponseWrapper process(RequestWrapper reqWrapper) throws ParseException {
        logger.info("Processing started for LogFileProcessingServiceImpl.process");
        ResponseWrapper respWrapper = new ResponseWrapper();

        String fileLoc = reqWrapper.getFilePath() + File.separator + reqWrapper.getFileName();
        logger.info("fileLoc : {}", fileLoc);

        FileWrapper fileWrapper = FileUtils.fileMeta(fileLoc, new FileWrapperConverter());
        logger.debug("{}", fileWrapper);

        Persister<String, FileMetadata> persister = new FileMetaSerializingPersister();
        FileMetadata metadata = persister.retrieve(FileUtils.serilizedFileName(
            fileWrapper.getName(),
            fileWrapper.getModifiedStr().replaceAll("[^0-9.]", "")));
        logger.debug("{}", metadata);

        if (null == metadata) {
            new IllegalStateException("Files cannot be processed without pre-processing");
        }

        AbstractProcessor<String[]> processor = new ProcessETMSLogFileContent(
            metadata,
            reqWrapper.getPermission(),
            reqWrapper.getFromLineNumber(),
            reqWrapper.getToLineNumber());
        processor.initialize();
        if (processor.validate()) {
            processor.process();
            respWrapper.setContent(processor.getContent());
            processor.destroy();
        }

        return respWrapper;
    }
}
