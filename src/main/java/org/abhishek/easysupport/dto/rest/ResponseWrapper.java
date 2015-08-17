package org.abhishek.easysupport.dto.rest;

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
