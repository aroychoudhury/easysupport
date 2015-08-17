/* Copyright 2015 Roychoudhury, Abhishek */

package org.abhishek.easysupport.execute.impl;

import org.abhishek.fileanalytics.dto.persist.FileMetadata;
import org.abhishek.fileanalytics.dto.yield.ParseResult;
import org.abhishek.fileanalytics.parse.AbstractParser;
import org.abhishek.fileanalytics.parse.Parser;
import org.abhishek.fileanalytics.parse.helper.VerifyMatchHelper;
import org.abhishek.fileanalytics.parse.impl.CharacterBasedParser;
import org.abhishek.fileanalytics.process.impl.ProcessLogFileContent;
import org.abhishek.fileanalytics.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author abhishek
 * @since 1.0
 */
public class ProcessETMSLogFileContent extends ProcessLogFileContent {
    private static final Logger       logger  = LoggerFactory.getLogger(ProcessETMSLogFileContent.class);
    protected AbstractParser<Boolean> parser  = null;

    /**
     * @param metadata
     * @param startLine
     * @param endLine
     * @author abhishek
     * @since 1.0
     */
    public ProcessETMSLogFileContent(FileMetadata metadata, String match, int startLine, int endLine) {
        super(metadata, startLine, endLine);

        this.parser = new CharacterBasedParser<Boolean>("[<FIRST", "THIRD>]");
        this.parser.setParseHelper(new VerifyMatchHelper(match));
    }

    /**
     * @author abhishek
     * @since 1.0
     * @see org.abhishek.fileanalytics.process.AbstractProcessor#execute(char[])
     */
    @Override
    public boolean execute(char[] lineChars) {
        try {
            ParseResult<Boolean> result = this.parser.parseFragment(lineChars, Parser.DEFAULT);
            if (result.getResult().booleanValue()) {
                this.appendContent(lineChars);
            } else {
                this.appendContent("***********************************************************************\n");
            }
        } catch (Exception e) {
            logger.error(
                "Error in parser execution : " + this.parser.toString(),
                e);

            if (!this.parser.IsContinueOnExc()) {
                this.resetContent();
                this.appendContent(ExceptionUtils.getStackTrace(e));
                return true;
            }
        }
        return false;
    }

    /**
     * @author abhishek
     * @since  1.0
     * @see org.abhishek.fileanalytics.process.AbstractProcessor#initialize()
     */
    @Override
    public void initialize() {
        super.initialize();
        if (this.parser.validate()) {
            this.parser.initialize();
        }
    }

    /**
     * @author abhishek
     * @since 1.0
     * @see org.abhishek.fileanalytics.process.impl.ProcessLogFileContent#destroy()
     */
    @Override
    public void destroy() {
        super.destroy();
        this.parser.destroy();
    }
}