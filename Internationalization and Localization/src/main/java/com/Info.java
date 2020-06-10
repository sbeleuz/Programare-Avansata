package com;

import app.LocaleExplore;

import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;

public class Info implements Command {

    @Override
    public void execute(String... params) {
        Locale locale;
        if (params.length < 2) { // get info about current locale
            System.out.println(LocaleExplore.message("info", "default"));
            locale = Locale.getDefault();
        } else { // get info about specific locale
            System.out.println(LocaleExplore.message("info", params[0]));
            locale = new Locale(params[0], params[1]);
        }
        info(locale);
    }

    private static void info(Locale locale) {
        System.out.println("Country: " + locale.getDisplayCountry() + "\t" + locale.getDisplayLanguage(locale));
        System.out.println("Language: " + locale.getDisplayLanguage() + "\t" + locale.getDisplayLanguage(locale));

        Currency currency = Currency.getInstance(locale);
        System.out.println("Currency: " + currency.getSymbol() + "\t" + currency.getDisplayName());

        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols(locale);
        System.out.println("Week Days: " + Arrays.toString(dateFormatSymbols.getWeekdays()));
        System.out.println("Months: " + Arrays.toString(dateFormatSymbols.getMonths()));

        LocalDateTime today = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        DateTimeFormatter formatterLocale = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).withLocale(locale);
        System.out.println("Today: " + today.format(formatter) + "\t" + today.format(formatterLocale));
    }
}
