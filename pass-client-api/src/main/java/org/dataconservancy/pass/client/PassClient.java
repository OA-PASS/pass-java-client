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

import java.util.Map;
import java.util.Set;

import org.dataconservancy.pass.model.PassEntity;

/**
 * Interface for interactions with PASS database
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
    public <T extends PassEntity> T readResource(URI uri, Class<T> modelClass);
    
    /**
     * Retrieves URI for a SINGLE RECORD by matching the entity type and filtering by the field
     * specified using the value provided. 
     * For example, to find the Grant using the 
     * awardNumber:
     * 
     *    String awardNum = "abcdef123";
     *    URI grantId = findByAttribute(Grant.class, "awardNumber", awardNum);
     * 
     * If >1 records are found, a RuntimeException will be thrown. If no records are found it will return null.
     * 
     * @param modelClass
     * @param attribute
     * @param value
     * @return
     */
    public <T extends PassEntity> URI findByAttribute(Class<T> modelClass, String attribute, Object value);
    
    
    /**
     * Retrieves URIs for MULTIPLE MATCHING RECORDS by matching the entity type and filtering by the field
     * specified using the value provided. For example, to find Deposits using a Repository.id:
     * 
     *    URI repositoryId = new URI("https://example.com/fedora/repositories/3");
     *    Set<URI> entityUris = findByAttribute(Deposit.class, "repository", repositoryId);
     *    
     * By default this will return a maximum of 3000 matching records, unless the pass.elasticsearch.limit
     * environment variable is set. If there are no matches, it will return an empty list.
     * 
     * @param modelClass
     * @param attribute
     * @param value
     * @return
     */
    public <T extends PassEntity> Set<URI> findAllByAttribute(Class<T> modelClass, String attribute, Object value);
    
    
    /**
     * Retrieves URIs for MULTIPLE MATCHING RECORDS by matching the entity type and filtering by the field
     * specified using the value provided.  For example, to find Deposits using a Repository.id:
     * 
     *    URI repositoryId = new URI("https://example.com/fedora/repositories/3");
     *    Set<URI> entityUris = findByAttribute(Deposit.class, "repository", repositoryId);
     *    
     * The number of records will be limited by limit provided, and the offset will be applied to the default 
     * sorting. If there are no matches, it will return an empty list. This will override the limit env variable
     * 
     * @param modelClass
     * @param attribute
     * @param value
     * @param limit
     * @param offset
     * @return
     */
    public <T extends PassEntity> Set<URI> findAllByAttribute(Class<T> modelClass, String attribute, Object value, int limit, int offset);
    
    
    /**
     * Retrieves URIs for MULTIPLE MATCHING RECORDS by matching the entity type and filtering by the attributes
     * and values specified. For example, to find a Submission using a GrantId and DOI:
     * 
     *    Map<String, Object> map = new HashMap<String, Object>();
     *    URI grantId = new URI("https://example.com/fedora/grants/3");
     *    String doi = "10.001/12345abc";
     *    map.put("grants", grantId)
     *    map.put("doi", doi);
     *    Set<URI> entityUris = findByAttribute(Submission.class, map);
     *    
     * By default this will return a maximum of 3000 matching records, unless the pass.elasticsearch.limit
     * environment variable is set. If there are no matches, it will return an empty list.
     * 
     * @param modelClass
     * @param attribute
     * @param value
     * @return
     */
    public <T extends PassEntity> Set<URI> findAllByAttributes(Class<T> modelClass, Map<String, Object> attributeValuesMap);
    
    
    /**
     * Retrieves URIs for MULTIPLE MATCHING RECORDS by matching the entity type and filtering by the attributes
     * and values specified. For example, to find a Submission using a GrantId and DOI:
     * 
     *    Map<String, Object> map = new HashMap<String, Object>();
     *    URI grantId = new URI("https://example.com/fedora/grants/3");
     *    String doi = "10.001/12345abc";
     *    map.put("grants", grantId)
     *    map.put("doi", doi);
     *    Set<URI> entityUris = findByAttribute(Submission.class, map);
     *    
     * The number of records will be limited by limit provided, and the offset will be applied to the default 
     * sorting. If there are no matches, it will return an empty list. This will override the limit env variable
     * 
     * @param modelClass
     * @param attribute
     * @param value
     * @param limit
     * @param offset
     * @return
     */
    public <T extends PassEntity> Set<URI> findAllByAttributes(Class<T> modelClass, Map<String, Object> attributeValuesMap, int limit, int offset);

    
}
