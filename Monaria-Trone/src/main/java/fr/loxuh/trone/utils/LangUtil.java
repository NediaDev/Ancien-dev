package fr.loxuh.trone.utils;

import java.util.regex.*;

public class LangUtil
{
    public static String get(final String message, final Object... objects) {
        final StringBuffer stringBuffer = new StringBuffer();
        final Matcher matcher = Pattern.compile("%(.*?)%").matcher(message);
        int i = 0;
        while (matcher.find()) {
            matcher.appendReplacement(stringBuffer, String.valueOf(objects[i]));
            ++i;
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    public static String getWithVariables(String message, final Object... objects) {
        String variable;
        Object object;
        for (int i = 0; i < objects.length; object = objects[++i], message = message.replace(variable, String.valueOf(object)), ++i) {
            variable = (String)objects[i];
        }
        return message;
    }
}

