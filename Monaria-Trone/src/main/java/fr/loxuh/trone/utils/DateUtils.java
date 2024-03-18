package fr.loxuh.trone.utils;

public class DateUtils
{
    public static String getDate(final long millis) {
        final long millisInDay = 86400000L;
        final long millisInHour = 3600000L;
        final long millisInMinute = 60000L;
        final long millisInSecond = 1000L;
        final long days = millis / millisInDay;
        final long daysDivisionResidueMillis = millis - days * millisInDay;
        final long hours = daysDivisionResidueMillis / millisInHour;
        final long hoursDivisionResidueMillis = daysDivisionResidueMillis - hours * millisInHour;
        final long minutes = hoursDivisionResidueMillis / millisInMinute;
        final long minutesDivisionResidueMillis = hoursDivisionResidueMillis - minutes * millisInMinute;
        final long seconds = minutesDivisionResidueMillis / millisInSecond;
        String date = "";
        if (days > 0L) {
            date = days + ((days > 1L) ? " jours " : " jour ");
        }
        if (hours > 0L) {
            date = date + hours + ((hours > 1L) ? " heures " : " heure ");
        }
        if (minutes > 0L) {
            date = date + minutes + ((minutes > 1L) ? " minutes " : " minute ");
        }
        return date + seconds + ((seconds > 1L) ? " secondes" : " seconde");
    }
}

