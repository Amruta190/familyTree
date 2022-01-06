/*******************************************************************************
* // NortonLifeLock copyright header start
* // //////////////////////
* //
* // PROPRIETARY / CONFIDENTIAL.
* // Use of this product is subject to license terms.
* // Copyright (c) 2020 NortonLifeLock Inc.
* // All rights reserved.
* //
* // /////////////////////////////////////////////////////////////////////////////////////////////////
* // NortonLifeLock copyright header stop
* // //////////////////////
******************************************************************************/
package com.amruta.familytree.protocol;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr353.JSR353Module;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.json.JsonMergePatch;
import javax.json.JsonValue;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class PatchHelper
{

    static private ObjectMapper objectMapper = new ObjectMapper();

    static
    {
        objectMapper.setDefaultPropertyInclusion(Include.NON_NULL);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        objectMapper.configure(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS, false);
        objectMapper.registerModule(new JSR353Module());
    }

    @Autowired
    private final Validator validator;

    /**
     * Performs a JSON Merge Patch operation
     *
     * @param mergePatch
     *            JSON Merge Patch document
     * @param targetBean
     *            object that will be patched
     * @param beanClass
     *            class of the object the will be patched
     * @param <T>
     * @return patched object
     */
    public <T> T mergePatch(JsonMergePatch mergePatch, T targetBean, Class<T> beanClass)
    {
        JsonValue target = objectMapper.convertValue(targetBean, JsonValue.class);
        JsonValue patched = applyMergePatch(mergePatch, target);
        return convertAndValidate(patched, beanClass);
    }

    public <T> T mergePatch(JsonMergePatch mergePatch, T targetBean, TypeReference<T> toValueTypeRef)
    {
        JsonValue target = objectMapper.convertValue(targetBean, JsonValue.class);
        JsonValue patched = applyMergePatch(mergePatch, target);
        return convertAndValidate(patched, toValueTypeRef);
    }

    private JsonValue applyMergePatch(JsonMergePatch mergePatch, JsonValue target)
    {
        return mergePatch.apply(target);
    }

    private <T> T convertAndValidate(JsonValue jsonValue, Class<T> beanClass)
    {
        try
        {
            T bean = objectMapper.convertValue(jsonValue, beanClass);
            validate(bean);
            return bean;
        }
        catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException("Invalid Patach request" + e.getMessage());
        }
    }

    private <T> T convertAndValidate(JsonValue jsonValue, TypeReference<T> tTypeReference)
    {
        try
        {
            T bean = objectMapper.convertValue(jsonValue, tTypeReference);
            validate(bean);
            return bean;
        }
        catch (IllegalArgumentException e)
        {
            throw new IllegalArgumentException("Invalid Patch request" + e.getMessage());
        }
    }

    private <T> void validate(T bean)
    {
        Set<ConstraintViolation<T>> violations = validator.validate(bean);
        if (!violations.isEmpty())
        {
            throw new ConstraintViolationException(violations);
        }
    }
}