package com.mimose.component.deduplicate.gen;

import com.mimose.component.deduplicate.exceptions.ActionException;
import com.mimose.component.deduplicate.exceptions.GenException;
import com.mimose.component.deduplicate.exceptions.LoaderException;
import com.mimose.component.deduplicate.gen.api.ArgValueGenerator;
import com.mimose.component.deduplicate.utils.Assert;
import com.mimose.component.deduplicate.utils.Loader;
import com.mimose.component.deduplicate.utils.MdFive;
import lombok.NoArgsConstructor;

import java.lang.reflect.Method;

/**
 * @author mimose
 * @description provide Arg Value Support (Load specific ArgValueGenerator)
 * @date 2021/3/25
 */
@NoArgsConstructor
public final class ArgValueSupport {

    private static final ArgValueGenerator argValueGenerator;

    private static final String DEDUPLICATE_KEY_SALT = "94df064j0fd8-43sd8-912i-1k2pm--12we2-yu9s3";

    static {
        try {
            argValueGenerator = Loader.load(ArgValueGenerator.class, ArgValueSupport.class.getClassLoader());
        } catch (LoaderException e) {
            throw new ActionException("Can't support the ArgValue Generator, missing ServiceLoader");
        }
        Assert.notNull(argValueGenerator, "Can't support the ArgValue Generator, Load ArgValueGenerator result is null");
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
                    .append(argValueGenerator.generate(args))
                    .append(DEDUPLICATE_KEY_SALT);
            return MdFive.from(fullKey.toString());
        } catch (Exception e) {
            throw new GenException("Fail to Generate the Deduplicate Key", e);
        }
    }
}
