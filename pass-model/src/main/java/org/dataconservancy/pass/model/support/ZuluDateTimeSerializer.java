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
package org.dataconservancy.pass.model.support;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.joda.time.DateTime;

/**
 * Serializes date into JSON as zulu date format
 * @author Karen Hanson
 */
public class ZuluDateTimeSerializer extends JsonSerializer<DateTime> {

    private static final String datePattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void serialize(DateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        // TODO Auto-generated method stub
        String dt = value.toString(datePattern);
        gen.writeString(dt);        
    }

}
