## Email Configuration
To have the application send out emails for any logging error events like exceptions and bad code, you need to include a `logback-email-config.properties` in this directory (/resources).

*Note:* To enable SSL or TLS, you need to specify the word "true".


### Example `logback-email-config.properties`

```markdown
application.logback.smtp.from=bugs@moesounds.com
application.logback.smtp.to=devs@moesounds.com
application.logback.smtp.host=smtp.gmail.com
application.logback.smtp.port=465
application.logback.smtp.username=coolUserName
application.logback.smtp.password=secretPassword
application.logback.smtp.ssl=true
application.logback.smtp.tls=false
```

**Note:** Make sure to include all the properties in the example or else exceptions occur.