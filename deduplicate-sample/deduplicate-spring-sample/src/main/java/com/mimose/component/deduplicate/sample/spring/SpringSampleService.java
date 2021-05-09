package com.mimose.component.deduplicate.sample.spring;

import com.mimose.component.deduplicate.annotations.Deduplicated;
import com.mimose.component.deduplicate.log.FluentLogger;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mimose
 * @description
 * @date 2021/3/26
 */
@Service
public class SpringSampleService {
    private static final FluentLogger LOGGER = FluentLogger.getLogger(SpringSampleService.class);

    /**
     * duplicate check by argument
     * @param id
     * @return
     */
    @Deduplicated(ttl = 3)
    public String getOne(Integer id) {
        LOGGER.debug().module("SPRING").message("getOne, id: {}").args(id).build();
        return "success:" + id;
    }

    /**
     * duplicate check by request's header and arguments
     * @param request
     * @param id
     * @return
     */
    @Deduplicated(ttl = 3)
    public String getOne(HttpServletRequest request, Integer id) {
        LOGGER.debug().module("SPRING").message("getOne, id: {}").args(id).build();
        return "success:" + id;
    }
}
