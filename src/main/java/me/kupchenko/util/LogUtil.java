package me.kupchenko.util;

import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.springframework.util.StringUtils;

import java.security.MessageDigest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class LogUtil {

    private static final String isEmailRegex = "^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$";
    private static final Pattern isEmailPattern = Pattern.compile(isEmailRegex, Pattern.CASE_INSENSITIVE);

    private static final String hasEmailRegex = "[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}";
    private static final Pattern hasEmailPattern = Pattern.compile(hasEmailRegex, Pattern.CASE_INSENSITIVE);

    public static boolean isEmail(String value) {
        return !StringUtils.isEmpty(value) && isEmailPattern.matcher(value).find();
    }

    public static boolean hasPII(String value) {
        return !StringUtils.isEmpty(value) && hasEmailPattern.matcher(value).find();
    }

    @SneakyThrows
    public static String hashEmail(String string) {
        MessageDigest md = MessageDigest.getInstance("SHA1");
        md.update(string.getBytes());
        byte[] digest = md.digest();
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return "email:" + sb.toString();
    }

    public static String obfuscate(String text) {
        if (StringUtils.isEmpty(text)) return text;

        Matcher matcher = hasEmailPattern.matcher(text);

        if (matcher.find()) {
            StringBuffer s = new StringBuffer();
            do {
                matcher.appendReplacement(s, hashEmail(matcher.group()));
            } while (matcher.find());

            matcher.appendTail(s);
            return s.toString();
        }
        return text;
    }
}
