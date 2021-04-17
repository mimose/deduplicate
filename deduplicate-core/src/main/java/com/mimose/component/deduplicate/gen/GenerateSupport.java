package com.mimose.component.deduplicate.gen;

import com.mimose.component.deduplicate.annotations.Deduplicated;
import com.mimose.component.deduplicate.constants.Constant;
import com.mimose.component.deduplicate.exceptions.GenException;
import com.mimose.component.deduplicate.gen.api.ArgValueGenerator;
import com.mimose.component.deduplicate.gen.api.Generator;
import com.mimose.component.deduplicate.gen.api.HttpRequestTokenGenerator;
import com.mimose.component.deduplicate.log.FluentLogger;
import com.mimose.component.deduplicate.utils.MdFive;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * @author mimose
 * @description provide Arg Value Support (Load specific ArgValueGenerator)
 * @date 2021/3/25
 */
@NoArgsConstructor
public final class GenerateSupport {
    private static final FluentLogger LOGGER = FluentLogger.getLogger(GenerateSupport.class);
    private static final String MODULE = "CACHE";

    private static final Generator argValueGenerator;

    private static final HttpRequestTokenGenerator tokenGenerator;

    private static final String DEDUPLICATE_KEY_SALT = "94df064j0fd8-43sd8-912i-1k2pm--12we2-yu9s3";

    static {
        argValueGenerator = new ArgValueGenerator();
        tokenGenerator = new HttpRequestTokenGenerator();
    }

    /**
     * generate the deduplicate key, for cache and check
     * @param method
     * @param args
     * @return
     */
    public String genArgValueKey(Method method, Object[] args) {
        try {
            StringBuilder fullKey = new StringBuilder()
                    .append(method.getName())
                    .append(Constant.LINE_SEPARATOR)
                    .append(argValueGenerator.generate(args))
                    .append(Constant.LINE_SEPARATOR)
                    .append(DEDUPLICATE_KEY_SALT);
            final String output = MdFive.from(fullKey.toString());
            LOGGER.debug().module(MODULE).message("Generate the deduplicate key, output: [{}]").args(output).build();
            return output;
        } catch (Exception e) {
            LOGGER.error().module(MODULE).message("Generate the deduplicate key error").throwable(e).build();
            throw new GenException("Fail to Generate the Deduplicate Key", e);
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
            // TODO
            return tokenGenerator.generate(args);
        } catch (Exception e) {
            LOGGER.error().module(MODULE).message("Generate the deduplicate token error").throwable(e).build();
            throw new GenException("Fail to Generate the Deduplicate Token", e);
        }
    }
}
