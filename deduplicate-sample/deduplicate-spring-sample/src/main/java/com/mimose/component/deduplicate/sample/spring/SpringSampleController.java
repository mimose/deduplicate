package com.mimose.component.deduplicate.sample.spring;

import com.mimose.component.deduplicate.exceptions.DuplicateException;
import com.mimose.component.deduplicate.log.FluentLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author mimose
 * @description
 * @date 2021/5/9
 */
@RestController
@RequestMapping("/spring/sample")
public class SpringSampleController {
    private static final FluentLogger LOGGER = FluentLogger.getLogger(SpringSampleController.class);

    @Autowired
    private SpringSampleService springSampleService;

    @GetMapping("/getOne/{id}")
    public String getOne(@PathVariable Integer id) {
        try {
            return springSampleService.getOne(id);
        } catch (Exception e) {
            if(e instanceof DuplicateException) {
                LOGGER.error().module("SPRING").message(e.getMessage()).args(id).throwable(e).build();
            }
            return "error:" + id;
        }
    }

    @GetMapping("/getOne/request/{id}")
    public String getOne(HttpServletRequest request, @PathVariable Integer id) {
        try {
            return springSampleService.getOne(request, id);
        } catch (Exception e) {
            if(e instanceof DuplicateException) {
                LOGGER.error().module("SPRING").message(e.getMessage()).args(id).throwable(e).build();
            }
            return "error:" + id;
        }
    }

}
