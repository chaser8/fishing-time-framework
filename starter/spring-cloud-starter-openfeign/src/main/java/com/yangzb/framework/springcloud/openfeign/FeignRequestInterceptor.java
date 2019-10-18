package com.yangzb.framework.springcloud.openfeign;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yangzb.framework.common.web.Constants;
import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;

/**
 * 处理feign不支持get pojo问题
 *
 * @program: bigdata-dev1
 * @description:
 * @author: yzb
 * @create: 2019-05-27 14:24
 **/
@Slf4j
public class FeignRequestInterceptor implements RequestInterceptor {

    public FeignRequestInterceptor(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private ObjectMapper objectMapper;

    @Override
    public void apply(RequestTemplate template) {
        //内部调用不需要返回openapi格式
        Map<String, List<String>> openapiHeader = new HashMap<>();
        List<String> headerValue = new ArrayList<String>();
        headerValue.add("false");
        template.header(Constants.HTTP_REQUEST_HEADER_OPENAPI, headerValue);

        // feign 不支持 GET 方法传 POJO, json body转query
        if (template.method().equals("GET") && template.requestBody() != null && template.requestBody().asBytes() != null) {
            try {
                JsonNode jsonNode = objectMapper.readTree(template.requestBody().asBytes());
                Request.Body body = Request.Body.empty();
                template.body(body);
                Map<String, Collection<String>> queries = new HashMap<>();
                buildQuery(jsonNode, "", queries);
                template.queries(queries);
            } catch (IOException e) {
                log.error("", e);
            }
        }
    }

    /**
     * 一个递归方法
     *
     * @param jsonNode
     * @param path
     * @param queries
     */
    private void buildQuery(JsonNode jsonNode, String path, Map<String, Collection<String>> queries) {
        // 叶子节点
        if (!jsonNode.isContainerNode()) {
            if (jsonNode.isNull()) {
                return;
            }
            Collection<String> values = queries.get(path);
            if (null == values) {
                values = new ArrayList<>();
                queries.put(path, values);
            }
            values.add(jsonNode.asText());
            return;
        }
        // 数组节点
        if (jsonNode.isArray()) {
            Iterator<JsonNode> it = jsonNode.elements();
            while (it.hasNext()) {
                buildQuery(it.next(), path, queries);
            }
        } else {
            Iterator<Map.Entry<String, JsonNode>> it = jsonNode.fields();
            while (it.hasNext()) {
                Map.Entry<String, JsonNode> entry = it.next();
                if (StringUtils.hasText(path)) {
                    buildQuery(entry.getValue(), path + "." + entry.getKey(), queries);
                } else {  // 根节点
                    buildQuery(entry.getValue(), entry.getKey(), queries);
                }
            }
        }
    }
}