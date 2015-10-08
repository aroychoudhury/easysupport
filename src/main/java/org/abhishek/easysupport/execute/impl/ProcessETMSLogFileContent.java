/* Copyright 2015 Roychoudhury, Abhishek */

package org.abhishek.easysupport.execute.impl;

import org.abhishek.fileanalytics.dto.yield.ParseResult;
import org.abhishek.fileanalytics.parse.AbstractFragmentParser;
import org.abhishek.fileanalytics.parse.FragmentParser;
import org.abhishek.fileanalytics.parse.helper.VerifyMatchHelper;
import org.abhishek.fileanalytics.parse.impl.CharacterBasedParser;
import org.abhishek.fileanalytics.process.impl.ProcessLogFileContent;
import org.abhishek.fileanalytics.utils.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This class extends the basic Log Processing capabilities and customizes it
 * for ETMS log structure.
 * 
 * It is also designed tol be extendable and hence could be extended to further
 * customize the processing.
 * 
 * @author abhishek
 * @since 1.0
 */
public class ProcessETMSLogFileContent extends ProcessLogFileContent {
    private static final Logger       logger         = LoggerFactory.getLogger(ProcessETMSLogFileContent.class);

    /* Class variables */
    protected String                  defaultDisplay = "";
    protected AbstractFragmentParser<Boolean> parser         = null;

    /**
     * @param metadata
     * @param startLine
     * @param endLine
     * @author abhishek
     * @since 1.0
     */
    public ProcessETMSLogFileContent(String match,
        int startLine,
        int endLine) {
        super(startLine, endLine);

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
            ParseResult<Boolean> result = this.parser.parseFragment(
                lineChars,
                FragmentParser.DEFAULT);
            if (result.getResult().booleanValue()) {
                this.appendContent(lineChars);
            } else {
                this.appendContent(this.defaultDisplay);
            }
        } catch (Exception e) {
            logger.error(
                "Error in parser execution : " + this.parser.toString(),
                e);

            if (!this.parser.IsContinueOnExc()) {
                this.resetContent();
                this.appendContent(ExceptionUtils.getStackTrace(e));
                return false;
            }
        }
        return true;
    }

    /**
     * @author abhishek
     * @since 1.0
     * @see org.abhishek.fileanalytics.process.AbstractProcessor#initialize()
     */
    @Override
    public void initialize() {
        StringBuilder tmpBuilder = new StringBuilder("");
        for (int idx = 0; idx < metadata.getMaxLineLength(); idx++) {
            tmpBuilder.append('*');
        }
        tmpBuilder.append('\n');
        this.defaultDisplay = tmpBuilder.toString();

        if (this.parser.validate()) {
            this.parser.initialize();
        }
        super.initialize();
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
