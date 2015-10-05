package org.abhishek.easysupport.dto.rest;

import org.abhishek.fileanalytics.constants.FileTypes;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class FileWrapper implements Restful {
    private String    path         = null;
    private String    name         = null;
    private String    extn         = null;
    private String    modifiedStr  = null;
    private boolean   preProcessed = false;
    private FileTypes type         = null;

    /**
     * @return the path
     * @since 1.0
     * @see String
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     *            the path to set
     * @since 1.0
     * @see String
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return the name
     * @since 1.0
     * @see String
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     * @since 1.0
     * @see String
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the extn
     * @since 1.0
     * @see String
     */
    public String getExtn() {
        return extn;
    }

    /**
     * @param extn
     *            the extn to set
     * @since 1.0
     * @see String
     */
    public void setExtn(String extn) {
        this.extn = extn;
    }

    /**
     * @return the modifiedStr
     * @since 1.0
     * @see String
     */
    public String getModifiedStr() {
        return modifiedStr;
    }

    /**
     * @param modifiedStr
     *            the modifiedStr to set
     * @since 1.0
     * @see String
     */
    public void setModifiedStr(String modifiedStr) {
        this.modifiedStr = modifiedStr;
    }

    /**
     * @return the preProcessed
     * @since 1.0
     * @see boolean
     */
    public boolean isPreProcessed() {
        return preProcessed;
    }

    /**
     * @param preProcessed the preProcessed to set
     * @since 1.0
     * @see boolean
     */
    public void setPreProcessed(boolean preProcessed) {
        this.preProcessed = preProcessed;
    }

    /**
     * @return the type
     * @since 1.0
     * @see FileTypes
     */
    public FileTypes getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     * @since 1.0
     * @see FileTypes
     */
    public void setType(FileTypes type) {
        this.type = type;
    }

    /**
     * @author abhishek
     * @since 1.0
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "FileWrapper [" + path + " - " + name + extn + " - " + type.type() + " ( " + modifiedStr + " ) " + "]";
    }
}
