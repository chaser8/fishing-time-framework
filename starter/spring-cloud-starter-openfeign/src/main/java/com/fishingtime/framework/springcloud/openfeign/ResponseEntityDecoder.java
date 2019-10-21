package com.fishingtime.framework.springcloud.openfeign;

import feign.FeignException;
import feign.Response;
import feign.codec.Decoder;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * @program:
 * @description:
 * @author:
 * @create: 2019-05-31 13:46
 **/
public class ResponseEntityDecoder extends org.springframework.cloud.openfeign.support.ResponseEntityDecoder {
    public ResponseEntityDecoder(Decoder decoder) {
        super(decoder);
    }

    @Override
    public Object decode(Response response, Type type) throws IOException, FeignException {

        return super.decode(response, type);
    }
}
