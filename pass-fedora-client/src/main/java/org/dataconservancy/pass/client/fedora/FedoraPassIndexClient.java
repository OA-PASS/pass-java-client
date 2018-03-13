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
package org.dataconservancy.pass.client.fedora;

import java.util.HashSet;
import java.util.Set;

import org.dataconservancy.pass.model.PassEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Karen Hanson
 */
public class FedoraPassIndexClient {


    private static final Logger LOG = LoggerFactory.getLogger(FedoraPassIndexClient.class);

    /**
     * @see org.dataconservancy.pass.client.PassClient#findByAttribute(Class, String, Object)
     */
    public <T> PassEntity findByAttribute(Class<T> modelClass, String attribute, Object value) {
        if (modelClass==null) {throw new IllegalArgumentException("modelClass cannot be null");}
        if (attribute==null || attribute.length()==0) {throw new IllegalArgumentException("attribute cannot be null");}
        if (value==null) {throw new IllegalArgumentException("value cannot be null");}
        
        PassEntity passEntity = null;
        try {
            passEntity = (PassEntity) modelClass.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            throw new RuntimeException("modelClass provided could not be converted to a new PassEntity object");
        } 
        
        LOG.debug("Searching for {} where {} is \"{}\"", modelClass.getSimpleName(), attribute, value.toString());
        
        //TODO: do stuff
        
        return passEntity;
    }

    /**
     * @see org.dataconservancy.pass.client.PassClient#findAllByAttribute(Class, String, Object)
     */
    public <T> Set<PassEntity> findAllByAttribute(Class<T> modelClass, String attribute, Object value) {
        if (modelClass==null) {throw new IllegalArgumentException("modelClass cannot be null");}
        if (attribute==null || attribute.length()==0) {throw new IllegalArgumentException("attribute cannot be null");}
        if (value==null) {throw new IllegalArgumentException("value cannot be null");}
        
        Set<PassEntity> passEntities = new HashSet<PassEntity>();
        
        //TODO: do stuff

        LOG.debug("Searching for {} where {} is \"{}\"", modelClass.getSimpleName(), attribute, value.toString());
        
        return passEntities;
    }

}
