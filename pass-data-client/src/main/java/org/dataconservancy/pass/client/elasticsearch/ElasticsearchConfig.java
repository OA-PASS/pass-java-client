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
package org.dataconservancy.pass.client.elasticsearch;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import org.dataconservancy.pass.client.util.ConfigUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Holds information and methods required to configure Fedora.
 *
 * @author Karen Hanson
 */
public class ElasticsearchConfig {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticsearchConfig.class);

    private static final String INDEXER_URL_KEY = "pass.elasticsearch.url";
    private static final String DEFAULT_INDEXER_URL = "http://localhost:9200";

    private static final String INDICES_KEY = "pass.elasticsearch.indices";
    private static final String DEFAULT_INDICES = "pass";

    private static final String INDEXER_LIMIT_KEY = "pass.elasticsearch.limit";
    private static final Integer DEFAULT_INDEXER_LIMIT = 200;

    private ElasticsearchConfig() {
    }

    /**
     * Get indexer URL(s), defaults to DEFAULT_INDEXER_URL if one not set
     *
     * @return host URLs.
     */
    public static Set<URL> getIndexerHostUrl() {
        Set<URL> urls = new HashSet<URL>();

        String sUrls = ConfigUtil.getSystemProperty(INDEXER_URL_KEY, DEFAULT_INDEXER_URL);
        String[] arrUrl = sUrls.split(",");

        try {
            for (String sUrl : arrUrl) {
                urls.add(new URL(sUrl));
            }
            LOG.debug("Using indexer host URL(s): {}", sUrls);
        } catch (MalformedURLException e) {
            throw new RuntimeException("Indexer host path contains invalid URL:" + sUrls);
        }
        return urls;
    }

    /**
     * Get indexes to search, defaults to DEFAULT_INDEXES if not set
     *
     * @return indices
     */

    public static String[] getIndices() {
        String sIndices = ConfigUtil.getSystemProperty(INDICES_KEY, DEFAULT_INDICES);
        String[] arrIndices = sIndices.split(",");
        LOG.debug("Using index array of {} ", arrIndices.toString());
        return arrIndices;
    }

    /**
     * Get indexer limit setting, defaults to DEFAULT_INDEXER_LIMIT if environment variable not set
     *
     * @return indexer limit.
     */
    public static Integer getIndexerLimit() {
        Integer limit = DEFAULT_INDEXER_LIMIT;

        try {
            String sLimit = ConfigUtil.getSystemProperty(INDEXER_LIMIT_KEY, DEFAULT_INDEXER_LIMIT.toString());
            limit = Integer.parseInt(sLimit);
            if (limit < 0) {
                limit = DEFAULT_INDEXER_LIMIT;
                LOG.warn(
                    "Index record limit environment variable was a negative integer, using default limit of " + limit);
            }
        } catch (Exception e) {
            limit = DEFAULT_INDEXER_LIMIT;
            LOG.warn("Limit environment variable could not be converted to an Integer, using default limit of " + limit,
                     e);
        }

        LOG.debug("Using indexer limit of: {}", limit);
        return limit;
    }

}
