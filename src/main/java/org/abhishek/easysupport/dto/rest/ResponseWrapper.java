package org.abhishek.easysupport.dto.rest;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_EMPTY)
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id")
public class ResponseWrapper implements Restful {
    private String[] content = new String[0];

    /**
     * @return the content
     * @since 1.0
     * @see String[]
     */
    public String[] getContent() {
        return content;
    }

    /**
     * @param content the content to set
     * @since 1.0
     * @see String[]
     */
    public void setContent(String[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ResponseWrapper [content=" + content.length + "]";
    }
}
