package me.kupchenko.logs;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import lombok.extern.slf4j.Slf4j;
import me.kupchenko.util.LogUtil;
import org.slf4j.Marker;

import java.util.Arrays;
import java.util.Objects;

import static me.kupchenko.util.LogUtil.obfuscate;

@Slf4j
public class LogObfuscationFilter extends TurboFilter {

    public LogObfuscationFilter() {
        log.warn("LogObfuscationFilter enabled");
    }

    @Override
    public FilterReply decide(Marker marker, Logger logger, Level level, String format, Object[] params, Throwable t) {
        if (LogUtil.hasPII(format) || paramsHasPii(params)){
            logger.log(marker, logger.getName(), Level.toLocationAwareLoggerInteger(level), obfuscate(format), obfuscateParams(params), t);
            return FilterReply.DENY;
        }

        return FilterReply.NEUTRAL;
    }

    private boolean paramsHasPii(Object[] params){
        return params != null && Arrays.stream(params)
                .filter(Objects::nonNull)
                .map(Objects::toString)
                .anyMatch(LogUtil::hasPII);
    }

    private Object[] obfuscateParams(Object[] params) {
        if (params == null) return null;
        return Arrays.stream(params)
                .map(Objects::toString)
                .map(LogUtil::obfuscate)
                .toArray();
    }

}
