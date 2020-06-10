package com;

import app.LocaleExplore;

import java.util.Locale;

public class DisplayLocales implements Command {

    @Override
    public void execute(String... params) {
        System.out.println(LocaleExplore.message("locales"));
        Locale[] availableLocales = Locale.getAvailableLocales();
        for (Locale locale : availableLocales)
            System.out.println(locale.getDisplayCountry() + "\t" + locale.getDisplayLanguage(locale));
    }
}
