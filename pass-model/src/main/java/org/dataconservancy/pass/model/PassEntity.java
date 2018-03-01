/*
 * Copyright 2018 Johns Hopkins University
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.dataconservancy.pass.model;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author Karen Hanson
 * @version $Id$
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class PassEntity {
    
    /** Unique Deposit URI **/
    @JsonProperty("@id")
    protected URI id;
    
    @JsonProperty("@context")
    protected String context = null;
    

    /**
     * @return the id
     */
    public URI getId() {
        return id;
    }

    
    /**
     * @param id the id to set
     */
    public void setId(URI id) {
        this.id = id;
    }

    /**
     * @return the type
     */
    public abstract String getType();

    
    /**
     * @param type the type to set
     */
    public abstract void setType(String type);


    /**
     * @return the context
     */
    public String getContext() {
        return context;
    }

    
    /**
     * @param context the context to set
     */
    public void setContext(String context) {
        this.context = context;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PassEntity that = (PassEntity) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (context != null ? !context.equals(that.context) : that.context != null) return false;
        return true;
    }

    
    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (context != null ? context.hashCode() : 0);
        return result;
    }
}
