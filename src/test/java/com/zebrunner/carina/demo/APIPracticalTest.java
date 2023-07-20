package com.zebrunner.carina.demo;

import com.zebrunner.carina.api.apitools.validation.JsonCompareKeywords;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.core.registrar.tag.Priority;
import com.zebrunner.carina.core.registrar.tag.TestPriority;
import com.zebrunner.carina.demo.api.*;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;
import java.lang.invoke.MethodHandles;

public class APIPracticalTest implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test()
    @MethodOwner(owner = "apidemo")
    public void testCreateTodos() throws Exception {
        LOGGER.info("POST REQUEST");
        PostTodosMethod api = new PostTodosMethod();
        api.setProperties("api/todos/todos.properties");
        //call to endpoint
        api.callAPIExpectSuccess();
        //validate the response
        api.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "apidemo")
    public void testCreateTodosMissingSomeFields() throws Exception {
        PostTodosMethod api = new PostTodosMethod();
        api.setProperties("api/todos/todos.properties");
        //removing optional property
        api.getProperties().remove("title");
        //call to endpoint
        api.callAPIExpectSuccess();
        //validate the response
        api.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "apidemo")
    public void testGetTodos() {
        GetTodosMethod getTodosMethod = new GetTodosMethod();
        //call to endpoint
        getTodosMethod.callAPIExpectSuccess();
        //validate the response
        getTodosMethod.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getTodosMethod.validateResponseAgainstSchema("api/todos/_get/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "apidemo")
    @TestPriority(Priority.P1)
    public void testDeleteTodos() {
        DeleteTodosMethod deleteTodosMethod = new DeleteTodosMethod();
        deleteTodosMethod.setProperties("api/todos/todos.properties");
        //call to endpoint
        deleteTodosMethod.callAPIExpectSuccess();
        //validate the response
        deleteTodosMethod.validateResponse();
    }
}
