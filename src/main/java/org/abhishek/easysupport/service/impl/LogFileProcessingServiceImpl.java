/* Copyright 2015 Roychoudhury, Abhishek */

package org.abhishek.easysupport.service.impl;

import java.io.File;
import java.text.ParseException;

import org.abhishek.easysupport.convert.impl.FileWrapperConverter;
import org.abhishek.easysupport.dto.rest.FileWrapper;
import org.abhishek.easysupport.dto.rest.RequestWrapper;
import org.abhishek.easysupport.dto.rest.ResponseWrapper;
import org.abhishek.easysupport.execute.impl.ProcessETMSLogFileContent;
import org.abhishek.easysupport.service.LogFileProcessingService;
import org.abhishek.fileanalytics.dto.persist.FileMetadata;
import org.abhishek.fileanalytics.orchestrate.Orchestrater;
import org.abhishek.fileanalytics.persist.impl.FileMetaSerializingPersister;
import org.abhishek.fileanalytics.preprocess.impl.PreProcessLogFileContent;
import org.abhishek.fileanalytics.utils.DateUtils;
import org.abhishek.fileanalytics.utils.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author abhishek
 * @since 1.0
 */
@Service("LogProcessServices")
public class LogFileProcessingServiceImpl implements LogFileProcessingService {
    private static final Logger logger = LoggerFactory.getLogger(LogFileProcessingServiceImpl.class);

    @Autowired
    private Orchestrater orchestrater;

    /**
     * @author abhishek
     * @since  1.0
     * @see org.abhishek.easysupport.service.LogFileProcessingService#preProcess(org.abhishek.easysupport.dto.rest.RequestWrapper)
     */
    @Override
    public void preProcess(RequestWrapper request) throws ParseException {
        logger.info("Processing started for LogFileProcessingServiceImpl.preProcess");
        String fileLoc = request.getFilePath() + File.separator + request.getFileName();
        logger.info("fileLoc : {}", fileLoc);

        FileWrapper fileWrapper = FileUtils.fileMeta(fileLoc, new FileWrapperConverter());
        logger.debug("{}", fileWrapper);

        FileMetadata metadata = new FileMetadata.Builder(
            DateUtils.parse(fileWrapper.getModifiedStr()),
            fileWrapper.getName(),
            fileWrapper.getExtn(),
            request.getFilePath()).build();
        logger.debug("{}", metadata);

        // start pre-process
        orchestrater.preProcessFile(
            metadata,
            FileUtils.serilizedFileName(fileWrapper.getName(), metadata.getLastModified()),
            new FileMetaSerializingPersister(),
            new PreProcessLogFileContent(metadata));
    }

    /**
     * @author abhishek
     * @since  1.0
     * @see org.abhishek.easysupport.service.LogFileProcessingService#process(org.abhishek.easysupport.dto.rest.RequestWrapper)
     */
    @Override
    public ResponseWrapper process(RequestWrapper request) throws ParseException {
        logger.info("Processing started for LogFileProcessingServiceImpl.process");
        ResponseWrapper respWrapper = new ResponseWrapper();

        String fileLoc = request.getFilePath() + File.separator + request.getFileName();
        logger.info("fileLoc : {}", fileLoc);

        FileWrapper fileWrapper = FileUtils.fileMeta(fileLoc, new FileWrapperConverter());
        logger.debug("{}", fileWrapper);

        // start processing
        respWrapper.setContent(orchestrater.processFile(
            FileUtils.serilizedFileName(fileWrapper.getName(), fileWrapper.getModifiedStr().replaceAll("[^0-9.]", "")),
            new FileMetaSerializingPersister(),
            new ProcessETMSLogFileContent(request.getPermission(), request.getFromLineNumber(), request.getToLineNumber())));

        return respWrapper;
    }
}
