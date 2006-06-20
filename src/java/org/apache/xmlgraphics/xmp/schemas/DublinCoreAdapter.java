/*
 * Copyright 2006 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/* $Id$ */

package org.apache.xmlgraphics.xmp.schemas;

import java.util.Date;

import org.apache.xmlgraphics.xmp.Metadata;
import org.apache.xmlgraphics.xmp.XMPSchemaAdapter;
import org.apache.xmlgraphics.xmp.XMPSchemaRegistry;

/**
 * Schema adapter implementation for the Dublin Core schema.
 * <p>
 * Note: In Adobe's XMP specification dc:subject is defined as "bag Text", but in PDF/A-1 it is
 * defined as "Text". Here it is implemented as "bag Text". 
 */
public class DublinCoreAdapter extends XMPSchemaAdapter {

    /**
     * Constructs a new adapter for Dublin Core around the given metadata object.
     * @param meta the underlying metadata
     */
    public DublinCoreAdapter(Metadata meta) {
        super(meta, XMPSchemaRegistry.getInstance().getSchema(DublinCoreSchema.NAMESPACE));
    }
    
    /**
     * Adds a new entry to the list of creators (authors of the resource).
     * @param value the new value
     */
    public void addCreator(String value) {
        addStringToSeq("creator", value);
    }
    
    /** @return a String array of all creators */
    public String[] getCreators() {
        return getStringArray("creator");
    }
    
    /**
     * Adds a new entry to the list of dates indicating points in time something interesting
     * happened to the resource.
     * @param value the date value 
     */
    public void addDate(Date value) {
        addDateToSeq("date", value);
    }
    
    /**
     * Adds a new entry to the list of subjects (descriptive phrases or keywords that
     * specify the topic of the content of the resource).
     * @param value the new value
     */
    public void addSubject(String value) {
        addStringToBag("subject", value);
    }
    
    /** @return a String array of all subjects */
    public String[] getSubjects() {
        return getStringArray("subject");
    }
    
    /**
     * Sets the title of the resource (in the default language).
     * @param value the new value
     */
    public void setTitle(String value) {
        setTitle(null, value);
    }
    
    /**
     * Sets the title of the resource.
     * @param lang the language of the value ("x-default" or null for the default language)
     * @param value the new value
     */
    public void setTitle(String lang, String value) {
        setLangAlt("title", lang, value);
    }
    
    /** @return the title of the resource (in the default language) */
    public String getTitle() {
        return getTitle(null);
    }
    
    /**
     * Returns the title of the resource in a language-dependant way.
     * @param lang the language ("x-default" or null for the default language)
     * @return the language-dependent value.
     */
    public String getTitle(String lang) {
        return getLangAlt(lang, "title");
    }
    
}