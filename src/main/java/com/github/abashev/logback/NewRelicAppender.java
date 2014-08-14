package com.github.abashev.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxy;
import ch.qos.logback.core.AppenderBase;

import static com.newrelic.api.agent.NewRelic.noticeError;

/**
 * Logback appender for sending error messages to NewRelic monitoring.
 *
 * @author <A href="mailto:alexey at abashev dot ru">Alexey Abashev</A>
 */
public class NewRelicAppender extends AppenderBase<ILoggingEvent> {
    @Override
    protected void append(ILoggingEvent eventObject) {
        final IThrowableProxy throwableProxy = eventObject.getThrowableProxy();

        if ((throwableProxy != null) && (throwableProxy instanceof ThrowableProxy)) {
            ThrowableProxy proxy = (ThrowableProxy) throwableProxy;

            if (proxy.getThrowable() != null) {
                noticeError(proxy.getThrowable(), eventObject.getMDCPropertyMap());

                return;
            }
        }

        noticeError(eventObject.getFormattedMessage(), eventObject.getMDCPropertyMap());
    }
}
