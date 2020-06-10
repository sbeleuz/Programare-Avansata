package com;

import app.LocaleExplore;

import java.util.Locale;

public class SetLocale implements Command {

    @Override
    public void execute(String... params) {
        Locale locale = new Locale(params[0], params[1]);
        Locale.setDefault(locale);
        LocaleExplore.setResourceBundle(locale);
        System.out.println(LocaleExplore.message("locale.set", params[0]));
    }
}
