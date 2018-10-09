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

/**
 * Constants used in test data
 *
 * @author Karen Hanson
 * @version $Id$
 */
public class TestValues {

    /** A test value */
    public static final String CONTRIBUTOR_ID_1 = "https://example.org/fedora/contributors/1";

    /** A test value */
    public static final String DEPOSIT_ID_1 = "https://example.org/fedora/deposits/1";

    /** A test value */
    public static final String DEPOSIT_ID_2 = "https://example.org/fedora/deposits/2";

    /** A test value */
    public static final String FILE_ID_1 = "https://example.org/fedora/files/1";

    /** A test value */
    public static final String FUNDER_ID_1 = "https://example.org/fedora/funders/1";

    /** A test value */
    public static final String FUNDER_ID_2 = "https://example.org/fedora/funders/2";

    /** A test value */
    public static final String GRANT_ID_1 = "https://example.org/fedora/grants/1";

    /** A test value */
    public static final String GRANT_ID_2 = "https://example.org/fedora/grants/2";

    /** A test value */
    public static final String JOURNAL_ID_1 = "https://example.org/fedora/journals/1";

    /** A test value */
    public static final String JOURNAL_ID_2 = "https://example.org/fedora/journals/2";

    /** A test value */
    public static final String INSTITUTION_ID_1 = "https://example.org/fedora/institutions/1";

    /** A test value */
    public static final String POLICY_ID_1 = "https://example.org/fedora/policies/1";

    /** A test value */
    public static final String PUBLICATION_ID_1 = "https://example.org/fedora/publications/1";

    /** A test value */
    public static final String PUBLISHER_ID_1 = "https://example.org/fedora/publishers/1";

    /** A test value */
    public static final String REPOSITORY_ID_1 = "https://example.org/fedora/repositories/1";

    /** A test value */
    public static final String REPOSITORY_ID_2 = "https://example.org/fedora/repositories/2";

    /** A test value */
    public static final String REPOSITORYCOPY_ID_1 = "https://example.org/fedora/repositoryCopies/1";

    /** A test value */
    public static final String SUBMISSION_ID_1 = "https://example.org/fedora/submissions/1";

    /** A test value */
    public static final String SUBMISSION_ID_2 = "https://example.org/fedora/submissions/2";

    /** A test value */
    public static final String USER_ID_1 = "https://example.org/fedora/users/1";

    /** A test value */
    public static final String USER_ID_2 = "https://example.org/fedora/users/2";

    /** A test value */
    public static final String USER_ID_3 = "https://example.org/fedora/users/3";

    /** A test value */
    public static final String CONTRIBUTOR_ROLE_1 = "first-author";

    /** A test value */
    public static final String CONTRIBUTOR_ROLE_2 = "author";

    /** A test value */
    public static final String DEPOSIT_STATUS = "submitted";

    /** A test value */
    public static final String DEPOSIT_STATUSREF = "http://depositstatusref.example/abc";

    /** A test value */
    public static final String FILE_NAME = "article.pdf";

    /** A test value */
    public static final String FILE_URI = "https://someplace.dl/a/b/c/article.pdf";

    /** A test value */
    public static final String FILE_DESCRIPTION = "The file is an article";

    /** A test value */
    public static final String FILE_ROLE = "manuscript";

    /** A test value */
    public static final String FILE_MIMETYPE = "application/pdf";

    /** A test value */
    public static final String FUNDER_NAME = "Funder A";

    /** A test value */
    public static final String FUNDER_URL = "https://nih.gov";

    /** A test value */
    public static final String FUNDER_LOCALKEY = "A12345";

    /** A test value */
    public static final String GRANT_AWARD_NUMBER = "RH1234CDE";

    /** A test value */
    public static final String GRANT_STATUS = "active";

    /** A test value */
    public static final String GRANT_LOCALKEY = "ABC123";

    /** A test value */
    public static final String GRANT_PROJECT_NAME = "Project A";

    /** A test value */
    public static final String GRANT_AWARD_DATE_STR = "2018-01-01T00:00:00.000Z";

    /** A test value */
    public static final String GRANT_START_DATE_STR = "2018-04-01T00:00:00.000Z";

    /** A test value */
    public static final String GRANT_END_DATE_STR = "2020-04-30T00:00:00.000Z";

    /** A test value */
    public static final String JOURNAL_NAME = "Test Journal";

    /** A test value */
    public static final String JOURNAL_ISSN_1 = "1234-5678";

    /** A test value */
    public static final String JOURNAL_ISSN_2 = "5678-1234";

    /** A test value */
    public static final String JOURNAL_NLMTA = "TJ";

    /** A test value */
    public static final String JOURNAL_PMCPARTICIPATION = "B";

    /** A test value */
    public static final String POLICY_TITLE = "Policy A";

    /** A test value */
    public static final String POLICY_DESCRIPTION = "You must submit to any OA repo";

    /** A test value */
    public static final String POLICY_URL = "https://somefunder.org/policy";

    /** A test value */
    public static final String PUBLICATION_TITLE = "Some article";

    /** A test value */
    public static final String PUBLICATION_ABSTRACT = "An article about something";

    /** A test value */
    public static final String PUBLICATION_PMID = "12345678";

    /** A test value */
    public static final String PUBLICATION_DOI = "10.0101/1234abcd";

    /** A test value */
    public static final String PUBLICATION_VOLUME = "5";

    /** A test value */
    public static final String PUBLICATION_ISSUE = "123";

    /** A test value */
    public static final String PUBLISHER_NAME = "Publisher A";

    /** A test value */
    public static final String PUBLISHER_PMCPARTICIPATION = "A";

    /** A test value */
    public static final String REPOSITORY_NAME = "Repository A";

    /** A test value */
    public static final String REPOSITORY_DESCRIPTION = "An OA repository run by funder A";

    /** A test value */
    public static final String REPOSITORY_URL = "https://repo-example.org/";

    /** A test value */
    // TODO: verify format of formSchema field
    public static final String REPOSITORY_FORMSCHEMA = "{\"customFieldName\": \"String\"}";

    /** A test value */
    public static final String REPOSITORY_INTEGRATION_TYPE = "web-link";

    /** A test value */
    public static final String REPOSITORYCOPY_STATUS = "accepted";

    /** A test value */
    public static final String REPOSITORYCOPY_EXTERNALID_1 = "PMC12345";

    /** A test value */
    public static final String REPOSITORYCOPY_EXTERNALID_2 = "NIHMS1234";

    /** A test value */
    public static final String REPOSITORYCOPY_ACCESSURL = "https://www.ncbi.nlm.nih.gov/pmc/articles/PMC12345/";

    /** A test value */
    public static final String SUBMISSION_STATUS = "in-progress";

    /** A test value */
    public static final String SUBMISSION_DATE_STR = "2018-01-05T12:12:12.000Z";

    /** A test value */
    public static final String SUBMISSION_SOURCE = "other";

    /** A test value */
    public static final Boolean SUBMISSION_SUBMITTED = true;

    /** A test value */
    public static final String SUBMISSION_METADATA = "{\"customFieldName\": \"value\"}";

    /** A test value */
    public static final String USER_NAME = "am12345";

    /** A test value */
    public static final String USER_FIRST_NAME = "June";

    /** A test value */
    public static final String USER_MIDDLE_NAME = "Marie";

    /** A test value */
    public static final String USER_LAST_NAME = "Smith";

    /** A test value */
    public static final String USER_DISPLAY_NAME = "June Smith";

    /** A test value */
    public static final String USER_EMAIL = "js@example.com";

    /** A test value */
    public static final String USER_INSTITUTIONAL_ID = "jms001";

    /** A test value */
    public static final String USER_LOCALKEY = "abcdef";

    /** A test value */
    public static final String USER_ORCID_ID = "https://orcid.org/0000-1111-2222-3333";

    /** A test value */
    public static final String USER_AFFILIATION = "Johns Hopkins University";

    /** A test value */
    public static final String USER_ROLE_1 = "admin";

    /** A test value */
    public static final String USER_ROLE_2 = "submitter";

}
