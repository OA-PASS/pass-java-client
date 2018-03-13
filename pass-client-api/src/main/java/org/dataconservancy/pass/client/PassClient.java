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
package org.dataconservancy.pass.client;

import java.net.URI;

import java.util.Set;

import org.dataconservancy.pass.model.PassEntity;

/**
 * Interface for interactions with PASS database
 * 
 * TODO: lets see if this satisfies our use cases, if not we can add others. For example, 
 * if we need to filter by multiple fields, we should add additional methods.  
 * Note also that for now we will assume that you can filter by IDs using these find methods.
 * For example, you can use findAllByAttribute to retrieve all Deposits with a specific Repository.id 
 * 
 * @author Karen Hanson
 */
public interface PassClient {

    /**
     * Takes any PassEntity and persists it in the database, returns the URI if successful 
     * or appropriate exception if not. Note that PassEntities that are being created should
     * have null as their ID, the URI will the the ID field when reading the resource back
     * @param modelObj
     * @return URI of new record
     */
    public URI createResource(PassEntity modelObj);
    
    /**
     * Takes any PassEntity, and updates the record matching the ID field.  
     * @param modelObj
     * @return
     */
    public void updateResource(PassEntity modelObj);
    
    /** 
     * Deletes the entity matching the URI provided
     * @param modelObj
     */
    public void deleteResource(URI uri);
    
    /**
     * Retrieves the entity matching the URI provided, populates the  
     * appropriate Java class with its values.
     * @param uri
     * @param modelClass
     * @return
     */
    public <T> PassEntity readResource(URI uri, Class<T> modelClass);
    
    /**
     * Retrieves a SINGLE RECORD by matching the entity type and filtering by the field
     * specified using the value provided. For example, to find the Grant using the 
     * awardNumber:
     * 
     *    String awardNum = "abcdef123";
     *    Grant grant = (Grant) findByAttribute(Grant.class, "awardNumber", awardNum);
     * 
     * @param modelClass
     * @param attribute
     * @param value
     * @return
     */
    public <T> PassEntity findByAttribute(Class<T> modelClass, String attribute, Object value);
    
    
    /**
     * Retrieves ALL MATCHING RECORDS by matching the entity type and filtering by the field
     * specified using the value provided. For example, to find Deposits using a Repository.id:
     * 
     *    URI repositoryId = new URI("https://example.com/fedora/repositories/3");
     *    Set<PassEntity> entities = findByAttribute(Deposit.class, "repository", repositoryId);
     * 
     * Then each item in the set would need to be cast to a Deposit
     * 
     * @param modelClass
     * @param attribute
     * @param value
     * @return
     */
    public <T> Set<PassEntity> findAllByAttribute(Class<T> modelClass, String attribute, Object value);
    
    
}
