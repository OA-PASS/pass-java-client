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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.InputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Test;

/**
 * Model has been annotated with JSON tags. These tests do a simple check to ensure the
 * Jackson integration is functional and the equals / hashcode functions work
 *
 * @author Karen Hanson
 */
public class JournalModelTests {

    /**
     * Simple verification that JSON file can be converted to Journal model
     *
     * @throws Exception
     */
    @Test
    public void testJournalFromJsonConversion() throws Exception {

        InputStream json = JournalModelTests.class.getResourceAsStream("/journal.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Journal journal = objectMapper.readValue(json, Journal.class);

        assertEquals(TestValues.JOURNAL_ID_1, journal.getId().toString());
        assertEquals(TestValues.JOURNAL_NAME, journal.getJournalName());
        assertEquals(TestValues.JOURNAL_ISSN_1, journal.getIssns().get(0));
        assertEquals(TestValues.JOURNAL_ISSN_2, journal.getIssns().get(1));
        assertEquals(TestValues.PUBLISHER_ID_1, journal.getPublisher().toString());
        assertEquals(TestValues.JOURNAL_NLMTA, journal.getNlmta());
        assertEquals(TestValues.JOURNAL_PMCPARTICIPATION, journal.getPmcParticipation().name());
    }

    /**
     * Simple verification that Journal model can be converted to JSON
     *
     * @throws Exception
     */
    @Test
    public void testJournalToJsonConversion() throws Exception {

        Journal journal = createJournal();
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonJournal = objectMapper.writeValueAsString(journal);

        JSONObject root = new JSONObject(jsonJournal);

        assertEquals(root.getString("@id"), TestValues.JOURNAL_ID_1);
        assertEquals(root.getString("@type"), "Journal");
        assertEquals(root.getString("journalName"), TestValues.JOURNAL_NAME);
        assertEquals(root.getJSONArray("issns").get(0), TestValues.JOURNAL_ISSN_1);
        assertEquals(root.getJSONArray("issns").get(1), TestValues.JOURNAL_ISSN_2);
        assertEquals(root.getString("publisher"), TestValues.PUBLISHER_ID_1);
        assertEquals(root.getString("nlmta"), TestValues.JOURNAL_NLMTA);
        assertEquals(root.getString("pmcParticipation"), TestValues.JOURNAL_PMCPARTICIPATION);
    }

    /**
     * Creates two identical Journals and checks the equals and hashcodes match.
     * Modifies one field on one of the journals and verifies they no longer are
     * equal or have matching hashcodes.
     *
     * @throws Exception
     */
    @Test
    public void testJournalEqualsAndHashCode() throws Exception {

        Journal journal1 = createJournal();
        Journal journal2 = createJournal();

        assertEquals(journal1, journal2);
        journal1.setJournalName("different");
        assertTrue(!journal1.equals(journal2));

        assertTrue(journal1.hashCode() != journal2.hashCode());
        journal1 = journal2;
        assertEquals(journal1.hashCode(), journal2.hashCode());

    }

    /**
     * Test copy constructor creates a valid duplicate that is not the same object
     *
     * @throws Exception
     */
    @Test
    public void testJournalCopyConstructor() throws Exception {
        Journal journal = createJournal();
        List<String> issnsOrig = new ArrayList<String>(
            Arrays.asList(TestValues.JOURNAL_ISSN_1, TestValues.JOURNAL_ISSN_2));
        journal.setIssns(issnsOrig);
        Journal journalCopy = new Journal(journal);

        assertEquals(journal, journalCopy);

        journalCopy.setPmcParticipation(PmcParticipation.A);
        assertEquals(PmcParticipation.valueOf(TestValues.JOURNAL_PMCPARTICIPATION), journal.getPmcParticipation());
        assertEquals(PmcParticipation.A, journalCopy.getPmcParticipation());

        List<String> issnsNew = new ArrayList<String>(Arrays.asList("9876-1234"));
        journalCopy.setIssns(issnsNew);
        assertEquals(issnsOrig, journal.getIssns());
        assertEquals(issnsNew, journalCopy.getIssns());
    }

    private Journal createJournal() throws Exception {
        Journal journal = new Journal();
        journal.setId(new URI(TestValues.JOURNAL_ID_1));
        journal.setJournalName(TestValues.JOURNAL_NAME);
        List<String> issns = new ArrayList<String>();
        issns.add(TestValues.JOURNAL_ISSN_1);
        issns.add(TestValues.JOURNAL_ISSN_2);
        journal.setIssns(issns);
        journal.setPublisher(new URI(TestValues.PUBLISHER_ID_1));
        journal.setNlmta(TestValues.JOURNAL_NLMTA);
        journal.setPmcParticipation(PmcParticipation.valueOf(TestValues.JOURNAL_PMCPARTICIPATION));
        return journal;
    }

}
