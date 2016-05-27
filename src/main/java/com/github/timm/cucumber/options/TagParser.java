package com.github.timm.cucumber.options;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.Arrays.asList;
import static org.apache.commons.lang.StringUtils.join;

public class TagParser {

    private static final String QUOTE = Pattern.quote("\"");
    private static final String NOT_QUOTE_CHARS = "[^" + QUOTE + "]*?";
    private static final String COMMA = ",";

    public static String parseTags(final List<String> tags) {

        if (tags.isEmpty()) {
            return "";
        }

        return '"' + join(tags, "\",\"") + '"';

    }

    public static List<List<String>> splitQuotedTagsIntoParts(final String quotedTags) {

        final String TAG_GROUP = "(" + QUOTE + NOT_QUOTE_CHARS + QUOTE + ")";
        final Matcher matcher = Pattern.compile(TAG_GROUP).matcher(quotedTags);
        final List<List<String>> allTags = new ArrayList<List<String>>();

        while (matcher.find()) {

            final String tags = matcher.group(1);

            allTags.add(asList(tags.replaceAll("\\\"", "").split(",")));

        }

        return allTags;
    }

}
