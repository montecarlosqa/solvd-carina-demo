package com.zebrunner.carina.demo.api;

import com.zebrunner.carina.api.AbstractApiMethodV2;
import com.zebrunner.carina.api.annotation.Endpoint;
import com.zebrunner.carina.api.annotation.RequestTemplatePath;
import com.zebrunner.carina.api.annotation.ResponseTemplatePath;
import com.zebrunner.carina.api.annotation.SuccessfulHttpStatus;
import com.zebrunner.carina.api.http.HttpMethodType;
import com.zebrunner.carina.api.http.HttpResponseStatusType;
import com.zebrunner.carina.utils.config.Configuration;

@Endpoint(url = "${base_url}/todos", methodType = HttpMethodType.POST)
@RequestTemplatePath(path = "api/todos/_post/rq.json")
@ResponseTemplatePath(path = "api/todos/_post/rs.json")
@SuccessfulHttpStatus(status = HttpResponseStatusType.CREATED_201)
public class PostTodosMethod extends AbstractApiMethodV2 {

    public PostTodosMethod() {
        super("api/todos/_post/rq.json", "api/todos/_post/rs.json", "api/todos/todos.properties");
        replaceUrlPlaceholder("base_url", Configuration.getRequired("api_url"));
    }
}
