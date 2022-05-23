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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Describes a Repository. A Repository is the target of a Deposit.
 *
 * @author Karen Hanson
 */

public class Repository extends PassEntity {

    /**
     * Name of repository e.g. "PubMed Central"
     */
    private String name;

    /**
     * Several sentence description of repository
     */
    private String description;

    /**
     * URL to the homepage of the repository so that PASS users can view the platform before deciding whether to
     * participate in it
     */
    private URI url;

    /**
     * The legal text that a submitter must agree to in order to submit a publication to this repository
     */
    private String agreementText;

    /**
     * Stringified JSON representing a form template to be loaded by the front-end when this Repository is selected
     */
    private String formSchema;

    /**
     * Type of integration PASS has with the Repository
     */
    private IntegrationType integrationType;

    /**
     * Key that is unique to this {@code Repository} instance.  Used to reference the {@code Repository} when its URI
     * is not available (e.g. prior to the creation of a {@code Repository} resource in Fedora).
     */
    private String repositoryKey;

    /**
     * URLs that link to JSON schema documents describing the repository's metadata requirements
     */
    private List<URI> schemas = new ArrayList<>();

    /**
     * Repository constructor
     */
    public Repository() {
    }

    ;

    /**
     * Copy constructor, this will copy the values of the object provided into the new object
     *
     * @param repository the repository to copy
     */
    public Repository(Repository repository) {
        super(repository);
        this.name = repository.name;
        this.description = repository.description;
        this.url = repository.url;
        this.agreementText = repository.agreementText;
        this.formSchema = repository.formSchema;
        this.integrationType = repository.integrationType;
        this.repositoryKey = repository.repositoryKey;
        this.schemas = new ArrayList<>(repository.schemas);
    }

    /**
     * Possible deposit statuses. Note that some repositories may not go through every status.
     */
    public enum IntegrationType {
        /**
         * PASS can make Deposits to this Repository, and will received updates about its status
         */
        @JsonProperty("full")
        FULL("full"),
        /**
         * PASS can make Deposits to this Repository but will not automatically receive updates about its status
         */
        @JsonProperty("one-way")
        ONE_WAY("one-way"),
        /**
         * A deposit cannot automatically be made to this Repository from PASS, only a web link can be created.
         */
        @JsonProperty("web-link")
        WEB_LINK("web-link");

        private static final Map<String, IntegrationType> map = new HashMap<>(values().length, 1);

        static {
            for (IntegrationType d : values()) {
                map.put(d.value, d);
            }
        }

        private String value;

        private IntegrationType(String value) {
            this.value = value;
        }

        /**
         * Parse the integration type.
         *
         * @param integrationType String serialized integration type
         * @return parsed integration type.
         */
        public static IntegrationType of(String integrationType) {
            IntegrationType result = map.get(integrationType);
            if (result == null) {
                throw new IllegalArgumentException("Invalid Integration Type: " + integrationType);
            }
            return result;
        }

        @Override
        public String toString() {
            return this.value;
        }

    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the url
     */
    public URI getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(URI url) {
        this.url = url;
    }

    /**
     * @return the agreement text
     */
    public String getAgreementText() {
        return agreementText;
    }

    /**
     * @param agreementText the agreement text to set
     */
    public void setAgreementText(String agreementText) {
        this.agreementText = agreementText;
    }

    /**
     * @return the formSchema
     */
    public String getFormSchema() {
        return formSchema;
    }

    /**
     * @param formSchema the form schema (typically, a stringified JSON blob)
     */
    public void setFormSchema(String formSchema) {
        this.formSchema = formSchema;
    }

    /**
     * @return the integrationType
     */
    public IntegrationType getIntegrationType() {
        return integrationType;
    }

    /**
     * @param integrationType the integrationType to set
     */
    public void setIntegrationType(IntegrationType integrationType) {
        this.integrationType = integrationType;
    }

    /**
     * Key that is unique to this {@code Repository} instance.  Used to look up the {@code Repository} when its URI
     * is not available (e.g. prior to the creation of a {@code Repository} resource in Fedora).
     *
     * @return a String unique to this {@code Repository} within PASS, may be {@code null}
     */
    public String getRepositoryKey() {
        return repositoryKey;
    }

    /**
     * Key that is unique to this {@code Repository} instance.  Used to look up the {@code Repository} when its URI
     * is not available (e.g. prior to the creation of a {@code Repository} resource in Fedora).
     *
     * @param repositoryKey a String unique to this {@code Repository} within PASS
     */
    public void setRepositoryKey(String repositoryKey) {
        this.repositoryKey = repositoryKey;
    }

    /**
     * @return URLs that link to JSON schema documents describing the repository's metadata requirements
     */
    public List<URI> getSchemas() {
        return schemas;
    }

    /**
     * @param schemas URLs that link to JSON schema documents describing the repository's metadata requirements
     */
    public void setSchemas(List<URI> schemas) {
        this.schemas = schemas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }

        Repository that = (Repository) o;

        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (description != null ? !description.equals(that.description) : that.description != null) {
            return false;
        }
        if (url != null ? !url.equals(that.url) : that.url != null) {
            return false;
        }
        if (formSchema != null ? !formSchema.equals(that.formSchema) : that.formSchema != null) {
            return false;
        }
        if (integrationType != null ? !integrationType.equals(that.integrationType) : that.integrationType != null) {
            return false;
        }
        if (agreementText != null ? !agreementText.equals(that.agreementText) : that.agreementText != null) {
            return false;
        }
        if (repositoryKey != null ? !repositoryKey.equals(that.repositoryKey) : that.repositoryKey != null) {
            return false;
        }
        if (schemas != null ? !schemas.equals(that.schemas) : that.schemas != null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (url != null ? url.hashCode() : 0);
        result = 31 * result + (formSchema != null ? formSchema.hashCode() : 0);
        result = 31 * result + (integrationType != null ? integrationType.hashCode() : 0);
        result = 31 * result + (agreementText != null ? agreementText.hashCode() : 0);
        result = 31 * result + (repositoryKey != null ? repositoryKey.hashCode() : 0);
        result = 31 * result + (schemas != null ? schemas.hashCode() : 0);
        return result;
    }

}
