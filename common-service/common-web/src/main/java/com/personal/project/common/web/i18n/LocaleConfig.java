package com.personal.project.common.web.i18n;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Configuration
public class LocaleConfig extends AcceptHeaderLocaleResolver {

    private static final Locale DEFAULT_LOCALE = new Locale("vi");

    private static final Map<String, Locale> SUPPORT_LOCALES = Stream.of(
            Locale.ENGLISH,
            DEFAULT_LOCALE
    ).collect(Collectors.toMap(Locale::getLanguage, locale -> locale));

    public LocaleConfig() {
        super();
        setSupportedLocales(new ArrayList<>(SUPPORT_LOCALES.values()));
    }

    @Bean(name = "messageResourceAMC")
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageResource = new ReloadableResourceBundleMessageSource();
        messageResource.setBasenames("classpath:i18n/messages");
        messageResource.setDefaultEncoding("UTF-8");
        messageResource.setCacheSeconds(60);
        messageResource.setDefaultLocale(getDefaultLocale());
        return messageResource;
    }

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String language = request.getHeader(HttpHeaders.ACCEPT_LANGUAGE);
        if (!StringUtils.hasText(language)) {
            return getDefaultLocale();
        }
        List<Locale.LanguageRange> languageRanges = Locale.LanguageRange.parse(language);
        Locale foundLocale = Locale.lookup(languageRanges, getSupportedLocales());
        if (foundLocale == null)
            return getDefaultLocale();
        return foundLocale;
    }

    @Override
    public Locale getDefaultLocale() {
        return DEFAULT_LOCALE;
    }
}