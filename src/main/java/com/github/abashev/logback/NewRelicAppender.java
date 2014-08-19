package com.github.abashev.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.core.AppenderBase;

import java.util.HashMap;
import java.util.Map;

import static com.newrelic.api.agent.NewRelic.noticeError;

/**
 * Logback appender for sending error messages to NewRelic monitoring.
 *
 * @author <A href="mailto:alexey at abashev dot ru">Alexey Abashev</A>
 */
public class NewRelicAppender extends AppenderBase<ILoggingEvent> {
    private static final String MESSAGE_PARAM = "message";

    @Override
    protected void append(ILoggingEvent event) {
        final IThrowableProxy throwableProxy = event.getThrowableProxy();
        final Map<String, String> mdc = event.getMDCPropertyMap();

        if ((throwableProxy != null) && (throwableProxy instanceof ThrowableProxy)) {
            ThrowableProxy proxy = (ThrowableProxy) throwableProxy;

            if (proxy.getThrowable() != null) {
                Map<String, String> params = new HashMap<>(mdc);

                if (!mdc.containsKey(MESSAGE_PARAM)) {
                    params.put(MESSAGE_PARAM, event.getFormattedMessage());
                }

                noticeError(proxy.getThrowable(), params);

                return;
            }
        }

        noticeError(event.getFormattedMessage(), mdc);
    }
}
