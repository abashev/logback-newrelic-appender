logback-newrelic-appender
=========================

[![Build Status](https://travis-ci.org/abashev/logback-newrelic-appender.svg?branch=master)](https://travis-ci.org/abashev/logback-newrelic-appender)

Logback appender for sending error messages directly to NewRelic monitoring. So you don't need to add NewRelic.noticeError() everywhere but just standard log.error() from slf4j will be enough.


How to use it inside your project
---

Add new repository into your Maven configuration

    <repositories>
        <repository>
            <id>logback-newrelic-appender-repository</id>
            <url>http://dl.bintray.com/abashev/logback-newrelic-appender</url>
            <snapshots>
                <enabled>false</enabled>
            </snapshots>
        </repository>
    </repositories>
  
Add new dependency into dependencies list

    <dependency>
        <groupId>com.github.abashev.logback-newrelic-appender</groupId>
        <artifactId>logback-newrelic-appender</artifactId>
        <version>1.0.0</version>
    </dependency>

And from now you could use it inside your logback configuration file like this

    <appender name="newrelic" class="com.github.abashev.logback.NewRelicAppender">
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>ERROR</level>
        </filter>
    </appender>

    <root level="info">
        <appender-ref ref="newrelic" />
    </root>

*Note: It is better to apply messages threshold filter because too many NewRelic messages could break down performance of your application.*


