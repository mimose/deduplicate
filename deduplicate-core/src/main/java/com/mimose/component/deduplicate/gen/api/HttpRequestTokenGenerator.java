package com.mimose.component.deduplicate.gen.api;

import com.mimose.component.deduplicate.utils.StringUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

import static com.mimose.component.deduplicate.constants.Constant.EMPTY_STR;

/**
 * @author mimose
 * @description provide specific HttpRequestTokenGenerator
 * @date 2021/4/17
 */
public class HttpRequestTokenGenerator implements Generator {

    @Override
    public String generate(Object... args) {
        if(Objects.isNull(args)) {
            return EMPTY_STR;
        }
        String tokenGAid = StringUtil.optionalOrGetEmpty(args[0]);
        return searchInArgs(1, tokenGAid, args);
    }

    private String searchInArgs(int startIndex, String tokenGAid, Object[] args) {
        for (; startIndex < args.length; startIndex++) {
            if(args[startIndex] instanceof Object[]) {
                String token = searchInArgs(0, tokenGAid, (Object[]) args[startIndex]);
                if(StringUtil.isNoneBlank(token)) {
                    return token;
                }
            } else {
                if(args[startIndex] instanceof HttpServletRequest) {
                    final HttpServletRequest request = (HttpServletRequest) args[startIndex];
                    return request.getHeader(tokenGAid);
                }
            }
        }
        return EMPTY_STR;
    }
}
