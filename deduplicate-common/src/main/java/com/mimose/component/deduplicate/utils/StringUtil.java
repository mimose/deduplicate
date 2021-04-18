package com.mimose.component.deduplicate.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

import static com.mimose.component.deduplicate.constants.Constant.EMPTY_STR;

/**
 * @author mimose
 * @description
 * @date 2021/4/18
 */
public final class StringUtil extends StringUtils {

    public static String optionalOrGetEmpty(Object obj) {
        return Objects.isNull(obj) ? EMPTY_STR : obj.toString();
    }
}
