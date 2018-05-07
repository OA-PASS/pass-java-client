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


import java.io.InputStream;

import java.net.URI;

import org.fcrepo.client.FcrepoClient;
import org.fcrepo.client.PatchBuilder;

import static org.fcrepo.client.FedoraHeaderConstants.PREFER;

/**
 * The org.fcrepo.client.PatchBuilder class does not currently have several features needed
 * to perform a PATCH operation in which you can define the content type and add the "prefer lenient"
 * header. This extends the PatchBuilder to include these for use on FedoraPassCrudClient.updateResource()
 * @author Karen Hanson
 */
public class PatchBuilderExtension extends PatchBuilder {

    /**
     * Instantiate builder
     * 
     * @param uri uri of the resource this request is being made to
     * @param client the client
     */
    public PatchBuilderExtension(final URI uri, final FcrepoClient client) {
        super(uri, client);
    }

    @Override
    public PatchBuilderExtension body(final InputStream stream, final String contentType) {
        return (PatchBuilderExtension) super.body(stream, contentType);
    }

    /**
     * Set the prefer header for this request to lenient handling, to indicate that server-managed triples will not
     * be included in the request body.
     *
     * @return this builder
     */
    public PatchBuilderExtension preferLenient() {
        request.setHeader(PREFER, "handling=lenient; received=\"minimal\"");
        return this;
    }
}
