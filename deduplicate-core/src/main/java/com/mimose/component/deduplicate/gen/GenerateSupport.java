package com.mimose.component.deduplicate.gen;

import com.mimose.component.deduplicate.annotations.Deduplicated;
import com.mimose.component.deduplicate.exceptions.GenException;
import com.mimose.component.deduplicate.log.FluentLogger;
import com.mimose.component.deduplicate.utils.MdFive;
import com.mimose.component.deduplicate.utils.StringUtil;

import java.lang.reflect.Method;

import static com.mimose.component.deduplicate.constants.Constant.LINE_SEPARATOR;

/**
 * @author mimose
 * @description provide Arg Value Support (Load specific ArgValueGenerator)
 * @date 2021/3/25
 */
public final class GenerateSupport {
    public GenerateSupport() {}

    private static final FluentLogger LOGGER = FluentLogger.getLogger(GenerateSupport.class);
    private static final String MODULE = "CACHE";

    private static final String DEDUPLICATE_KEY_SALT = "94df064j0fd8-43sd8-912i-1k2pm--12we2-yu9s3";

    /**
     * generate the deduplicate argValue key, for cache and check
     * @param method
     * @param args
     * @return
     */
    public String genArgValueKey(Method method, Object[] args) {
        try {
            StringBuilder argValueKey = new StringBuilder()
                    .append(method.getName())
                    .append(LINE_SEPARATOR)
                    .append(GenerateFactory.ArgValue().singleton().generate(args))
                    .append(LINE_SEPARATOR)
                    .append(DEDUPLICATE_KEY_SALT);
            return MdFive.from(argValueKey.toString());
        } catch (Exception e) {
            LOGGER.error().module(MODULE).message("Generate the deduplicate argValue key error").throwable(e).build();
            throw new GenException("Fail to Generate the Deduplicate ArgValue Key", e);
        }
    }

    /**
     * generate the deduplicate token, for cache and check
     * @param tokenGAid
     * @param tokenGType
     * @param args
     * @return
     */
    public String genToken(String tokenGAid, Deduplicated.TokenGType tokenGType, Object[] args) {
        try {
            return GenerateFactory.Token().by(tokenGType).generate(tokenGAid, args);
        } catch (Exception e) {
            LOGGER.error().module(MODULE).message("Generate the deduplicate token error").throwable(e).build();
            throw new GenException("Fail to Generate the Deduplicate Token", e);
        }
    }

    /**
     * fill the full deduplicate key by argValueKey and token
     * @param argValueKey
     * @param token
     * @return
     */
    public String fillDeduplicateKey(String argValueKey, String token) {
        String deduplicateKey = StringUtil.isNoneBlank(token) ? MdFive.from(argValueKey + LINE_SEPARATOR + token) : argValueKey;
        LOGGER.debug().module(MODULE).message("Generate the deduplicate key: [{}]").args(deduplicateKey).build();
        return deduplicateKey;
    }
}
