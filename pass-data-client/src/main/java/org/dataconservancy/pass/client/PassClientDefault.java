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

import java.io.InputStream;
import java.net.URI;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

import org.dataconservancy.pass.client.elasticsearch.ElasticsearchPassClient;
import org.dataconservancy.pass.client.fedora.FedoraPassCrudClient;
import org.dataconservancy.pass.model.PassEntity;

/**
 * Creates instances of objects needed to perform PassClient requirements, and redirects to appropriate
 * service (Index client or CRUD client)
 *
 * @author Karen Hanson
 */
public class PassClientDefault implements PassClient {

    /**
     * Client that interacts with Fedora repo to carry out CRUD operations
     */
    private FedoraPassCrudClient crudClient;

    /**
     * Client that interacts with Index repo to do lookups and searches
     */
    private ElasticsearchPassClient indexClient;

    /**
     * Create a default pass client, with default configuration.
     */
    public PassClientDefault() {
        crudClient = new FedoraPassCrudClient();
        indexClient = new ElasticsearchPassClient();
    }

    /**
     * Sets option to overwrite (PUT) when updating instead of the default PATCH.
     *
     * @param overwriteOnUpdate - set to true to use PUT as update type
     * @return
     */
    public PassClientDefault overWriteOnUpdate(boolean overwriteOnUpdate) {
        this.crudClient.overwriteOnUpdate(overwriteOnUpdate);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URI createResource(PassEntity modelObj) {
        return crudClient.createResource(modelObj);
    }

    @Override
    public <T extends PassEntity> T createAndReadResource(T modelObj, Class<T> modelClass) {
        return crudClient.createAndReadResource(modelObj, modelClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateResource(PassEntity modelObj) {
        crudClient.updateResource(modelObj);
    }

    @Override
    public <T extends PassEntity> T updateAndReadResource(T modelObj, Class<T> modelClass) {
        return crudClient.updateAndReadResource(modelObj, modelClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteResource(URI modelObj) {
        crudClient.deleteResource(modelObj);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends PassEntity> T readResource(URI uri, Class<T> modelClass) {
        return crudClient.readResource(uri, modelClass);
    }

    @Override
    public Map<String, Collection<URI>> getIncoming(URI passEntity) {
        return crudClient.getIncoming(passEntity);
    }

    @Override
    public URI upload(URI entityUri, InputStream content) {
        return upload(entityUri, content, Collections.emptyMap());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URI upload(URI entityUri, InputStream content, Map<String, ?> params) {
        return crudClient.upload(entityUri, content, params);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends PassEntity> URI findByAttribute(Class<T> modelClass, String attribute, Object value) {
        return indexClient.findByAttribute(modelClass, attribute, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends PassEntity> Set<URI> findAllByAttribute(Class<T> modelClass, String attribute, Object value) {
        return indexClient.findAllByAttribute(modelClass, attribute, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends PassEntity> Set<URI> findAllByAttribute(Class<T> modelClass, String attribute, Object value,
                                                              int limit, int offset) {
        return indexClient.findAllByAttribute(modelClass, attribute, value, limit, offset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends PassEntity> Set<URI> findAllByAttributes(Class<T> modelClass,
                                                               Map<String, Object> valueAttributesMap) {
        return indexClient.findAllByAttributes(modelClass, valueAttributesMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends PassEntity> Set<URI> findAllByAttributes(Class<T> modelClass,
                                                               Map<String, Object> valueAttributesMap, int limit,
                                                               int offset) {
        return indexClient.findAllByAttributes(modelClass, valueAttributesMap, limit, offset);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends PassEntity> int processAllEntities(Consumer<URI> processor, Class<T> modelClass) {
        return crudClient.processAllEntities(processor, modelClass);
    }
}
