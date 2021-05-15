package com.mimose.component.deduplicate.starter;

import com.mimose.component.deduplicate.exceptions.LoaderException;
import com.mimose.component.deduplicate.log.FluentLogger;
import com.mimose.component.deduplicate.utils.Loader;

import java.util.List;
import java.util.Objects;

/**
 * @author mimose
 * @description component starter
 * @date 2021/5/15
 */
public final class DeduplicateStarter {
    private static final FluentLogger LOGGER = FluentLogger.getLogger(DeduplicateStarter.class);
    private static final String MODULE = "DEDUPLICATE_STARTER";

    public static void start() {
        List<IPartStarter> partStarter = null;
        try {
            partStarter = Loader.loadAll(IPartStarter.class, DeduplicateStarter.class.getClassLoader());
        } catch (LoaderException e) {
            LOGGER.error().module(MODULE).message("load All starter services fail, will exit System").throwable(e).build();
            System.exit(0);
        }
        // do all starter - start
        if(Objects.nonNull(partStarter) && partStarter.size() > 0) {
            partStarter.stream().forEach(IPartStarter::start);
        }
    }
}
