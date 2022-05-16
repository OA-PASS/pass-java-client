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
package org.dataconservancy.pass.client.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.dataconservancy.pass.model.Grant;
import org.dataconservancy.pass.model.PassEntity;
import org.dataconservancy.pass.model.Submission;
import org.dataconservancy.pass.model.User;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Tests for PassClient.findAllByAttribute
 *
 * @author Karen Hanson
 */
public class FindByAttributeIT extends ClientITBase {

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    /**
     * Ensures that the indexed version of Grant.awardNumber supports case insensitive searching
     *
     * @throws Exception
     */
    @Test
    public void testMixedCaseAwardNumber() throws Exception {

        Grant grant = random(Grant.class, 1);
        final URI grantId = client.createResource(grant);
        createdUris.put(grantId, Grant.class);

        attempt(RETRIES, () -> { // check the record exists before continuing
            final URI uri = client.findByAttribute(Grant.class, "@id", grantId);
            assertEquals(grantId, uri);
        });

        URI foundGrantId1 = client.findByAttribute(Grant.class, "awardNumber", grant.getAwardNumber());
        assertEquals(grantId, foundGrantId1);

        URI foundGrantId2 = client.findByAttribute(Grant.class, "awardNumber", grant.getAwardNumber().toLowerCase());
        assertEquals(grantId, foundGrantId2);

        URI foundGrantId3 = client.findByAttribute(Grant.class, "awardNumber", grant.getAwardNumber().toUpperCase());
        assertEquals(grantId, foundGrantId3);

    }

    /**
     * Ensures that index search can handle special characters including umlauts, double quotes,
     * dashes, and ampersands
     */
    @Test
    public void testSpecialCharacter() {

        User user = new User();
        user.setFirstName("Mary-Ann");
        user.setLastName("Schäfer");
        user.setDisplayName("Mary \"The Shark\" Schäfer");
        user.setAffiliation(Collections.singleton("Lamar & Schäfer Laboratory, Nürnberg"));
        URI userId = client.createResource(user);
        createdUris.put(userId, User.class);

        attempt(RETRIES, () -> { // check the record exists
            final URI uri = client.findByAttribute(User.class, "@id", userId);
            assertEquals(userId, uri);
        });

        URI uri1 = client.findByAttribute(User.class, "firstName", user.getFirstName());
        assertEquals(userId, uri1);

        URI uri2 = client.findByAttribute(User.class, "lastName", user.getLastName());
        assertEquals(userId, uri2);

        URI uri3 = client.findByAttribute(User.class, "displayName", user.getDisplayName());
        assertEquals(userId, uri3);

        URI uri4 = client.findByAttribute(User.class, "affiliation", user.getAffiliation().iterator().next());
        assertEquals(userId, uri4);

    }

    /**
     * Ensures no match found returns null instead of exception
     */
    @Test
    public void testNoMatchFound() {
        Grant grant = random(Grant.class, 1);
        final URI grantId = client.createResource(grant); //create something so it's not empty
        createdUris.put(grantId, Grant.class);

        attempt(RETRIES, () -> {
            final URI uri = client.findByAttribute(Grant.class, "@id", grantId);
            assertEquals(grantId, uri);
        });

        URI matchedId = client.findByAttribute(Grant.class, "awardNumber", "no match");
        assertEquals(null, matchedId);

    }

    /**
     * Confirm that a search on Submission.submitter that has a `mailto:` instead of a `User.id` still matches
     * when used in a findByAttribute
     */
    @Test
    public void testSubmissionWithMailtoUser() throws Exception {
        String emailStr = "mailto:Lesley%20Smith%3Clesleysmith%40example.org%3E";
        Submission submission = random(Submission.class, 1);
        submission.setSubmitter(new URI(emailStr));
        final URI submissionid = client.createResource(submission); //create something so it's not empty
        createdUris.put(submissionid, Submission.class);

        attempt(RETRIES, () -> {
            final URI uri = client.findByAttribute(Submission.class, "@id", submissionid);
            assertEquals(submissionid, uri);
        });

        URI matchedId = client.findByAttribute(Submission.class, "submitter", emailStr);
        assertEquals(submissionid, matchedId);

    }

    /**
     * Ensures that a search on a field of type multi-line array with multi values in it works
     * in this case it uses the repositories list in Submission as an example
     */
    @Test
    public void testMultiRowArraySearch() {
        Submission submission = random(Submission.class, 2); //create random submission where each list has 2 rows
        final URI submissionId = client.createResource(submission);
        createdUris.put(submissionId, Submission.class);

        attempt(RETRIES, () -> {
            final URI uri = client.findByAttribute(Submission.class, "@id", submissionId);
            assertEquals(submissionId, uri);
        });

        URI foundId1 = client.findByAttribute(Submission.class, "repositories", submission.getRepositories().get(0));
        URI foundId2 = client.findByAttribute(Submission.class, "repositories", submission.getRepositories().get(1));

        assertEquals(submissionId, foundId1);
        assertEquals(submissionId, foundId2);

    }

    /**
     * Check findByAttribute doesn't accept PassEntity as a class param
     */
    @Test(expected = IllegalArgumentException.class)
    public void testBadClassParam() {
        try {
            client.findByAttribute(PassEntity.class, "fake", "fake");
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("modelClass cannot be the abstract"));
            assertTrue(ex instanceof RuntimeException);
            throw ex;
        }
        fail("Test should have thrown exception");
    }

    /**
     * Check findByAttribute reject null modelClass as a param
     */
    @Test(expected = IllegalArgumentException.class)
    public void testNullClassParam() {
        try {
            client.findByAttribute(null, "fake", "fake");
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("modelClass cannot be null"));
            assertTrue(ex instanceof RuntimeException);
            throw ex;
        }
        fail("Test should have thrown exception");
    }

    /**
     * Check findByAttribute rejects a value that is a collection
     */
    @Test(expected = IllegalArgumentException.class)
    public void testValueParamAsCollection() {
        try {
            List<URI> coll = new ArrayList<URI>();
            client.findByAttribute(Submission.class, "repositories", coll);
        } catch (Exception ex) {
            assertTrue(ex.getMessage().contains("cannot be a Collection"));
            assertTrue(ex instanceof RuntimeException);
            throw ex;
        }
        fail("Test should have thrown exception");
    }

}
