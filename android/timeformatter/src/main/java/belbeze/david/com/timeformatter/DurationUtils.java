package belbeze.david.com.timeformatter;

import android.content.Context;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class allows to create a right format for time duration
 */

public class DurationUtils {

    public static final char SHOW_MILLISECOND       = 0X0001;
    public static final char SHOW_SECOND            = 0X0002;
    public static final char SHOW_MINUTE            = 0X0004;
    public static final char SHOW_HOUR              = 0X0008;
    public static final char SHOW_DAY               = 0X0010;
    public static final char SHOW_MONTH             = 0X0020;
    public static final char SHOW_YEAR              = 0X0040;

    public static final char EXACT_FORMAT           = 0x007f;
    public static final char APPROXIMATE_FORMAT     = 0x0000;

    public static final char SHOW_ZERO_VALUES       = 0x0100;
    public static final char MINIFY_UNIT            = 0x1000;

    public static final char _MILLISECOND         = 'S';
    public static final char _SECOND              = 's';
    public static final char _MINUTE              = 'm';
    public static final char _HOUR                = 'h';
    public static final char _DAY                 = 'd';
    public static final char _MONTH               = 'M';
    public static final char _YEAR                = 'y';

    public static final String UNIT = "%#%";
    public static final String MIN_UNIT = "%_#%";

    private Context mContext;

    private long duration;

    private int[] translatedDuration;

    public DurationUtils(Context context) {
        this(context, 0);
    }

    public DurationUtils(Context context, long duration) {
        this.duration = duration;
        mContext = context;

        initialize();
    }

    /**
     * Initialize each units of duration time
     * Example: 67092940547ms -> 2 years 1 month 15 days 12 hours 55 minutes 37 seconds 3547 milliseconds
     */
    private void initialize() {
        int[] dividers = new int[] {
                1000,   // ms in 1 s
                60,     // s in 1 min
                60,     // min in 1 h
                24,     // h in 1 day
        };

        translatedDuration = new int[7];

        long tmp = duration;
        int ind = 0;

        while (tmp != 0) {
            if (ind < 4) {
                translatedDuration[ind] = (int) (tmp % dividers[ind]);      // get the number of the current unit
                tmp /= dividers[ind];                                       // update to calculate for next unit

                ind++;
            } else if (ind == 4) {
                // calculate day, month
                translatedDuration[5] = 0;

                int year = (int) (tmp % 365);
                int[] mYear = new int[]{31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

                int mInd = 0;
                int mDay = mYear[mInd];
                while (year >= mDay) {
                    year -= mDay;
                    // update the month
                    translatedDuration[5]++;

                    mInd++;
                    mDay = mYear[mInd];
                }

                // set day
                translatedDuration[ind] = year;

                // set year
                translatedDuration[6] = (int) (tmp / 365);
                tmp = 0;
            }
        }
    }

    /**
     * Create the pattern from the given frag
     * @param flag the flag with the right options
     * @return the pattern which match with the given flag
     */
    private String getPattern(int flag) {
        String pattern = "";
        String unit = ((flag & MINIFY_UNIT) == MINIFY_UNIT) ? MIN_UNIT : UNIT;

        boolean showZero = (flag & SHOW_ZERO_VALUES) == SHOW_ZERO_VALUES;

        // set year
        if ((flag & SHOW_YEAR) == SHOW_YEAR
                && (translatedDuration[6] != 0 || (translatedDuration[6] == 0 && showZero))) {
            pattern = pattern.concat(" y " + unit);
        }

        // set month
        if ((flag & SHOW_MONTH) == SHOW_MONTH
                && (translatedDuration[5] != 0 || (translatedDuration[5] == 0 && showZero))) {
            pattern = pattern.concat(" M " + unit);
        }

        // set day
        if ((flag & SHOW_DAY) == SHOW_DAY
                && (translatedDuration[4] != 0 || (translatedDuration[4] == 0 && showZero))) {
            pattern = pattern.concat(" d " + unit);
        }

        // set hour
        if ((flag & SHOW_HOUR) == SHOW_HOUR
                && (translatedDuration[3] != 0 || (translatedDuration[3] == 0 && showZero))) {
            pattern = pattern.concat(" h " + unit);
        }

        // set minute
        if ((flag & SHOW_MINUTE) == SHOW_MINUTE
                && (translatedDuration[2] != 0 || (translatedDuration[2] == 0 && showZero))) {
            pattern = pattern.concat(" m " + unit);
        }

        // set second
        if ((flag & SHOW_SECOND) == SHOW_SECOND
                && (translatedDuration[1] != 0 || (translatedDuration[1] == 0 && showZero))) {
            pattern = pattern.concat(" s " + unit);
        }

        // set millisecond
        if ((flag & SHOW_MILLISECOND) == SHOW_MILLISECOND
                && (translatedDuration[0] != 0 || (translatedDuration[0] == 0 && showZero))) {
            pattern = pattern.concat(" S " + unit);
        }

        pattern = pattern.trim();
        if (pattern.isEmpty()) {
            pattern = "s " + unit;
        }

        return pattern.trim();
    }

    /**
     * format the time from the sequence that is match with the evolute pattern
     * @param sequence the evolute pattern
     * @param item the item of time {@link DurationUtils#_YEAR}, ...
     * @param position the position of the value
     * @return the sequence updated
     */
    private String formatSubSequence(String sequence, char item, int position) {
        String subSequence = sequence;

        Pattern regex = Pattern.compile(String.format("(%s)+\\s*(%s|%s)", item, UNIT, MIN_UNIT));
        Matcher matcher = regex.matcher(sequence);
        if (matcher.find()) {
            String seq = matcher.group(0);
            String[] parts = regex.split(sequence);

            int value = translatedDuration[position];

            seq = seq.replaceAll(item + "+", String.valueOf(value));
            seq = seq.replaceAll(UNIT + "|" + MIN_UNIT, getUnit(item, matcher.group(2), value));

            int len = parts.length;
            if (len == 0) {
                subSequence = seq;
            } else if (len == 1) {
                subSequence = parts[0].concat(seq);
            } else {
                subSequence = parts[0];
                for (int i = 1; i < len; i++) {
                    subSequence = subSequence.concat(seq + parts[i]);
                }
            }
        }

        return subSequence;
    }

    private String getUnit(char item, String type, int value) {
        String unit = "";

        if (item == _YEAR || item == _MONTH || item == _DAY) {
            type = type.isEmpty() ? "" : UNIT;
        }
        if (type.equals(UNIT)) {
            unit = getContext().getString(getStringId(item, value > 1));
        } else if (type.equals(MIN_UNIT)) {
            unit = getContext().getString(getMinifyStringId(item));
        }

        return unit;
    }

    private int getStringId(char item, boolean plural) {
        if (item == _YEAR && plural) {
            return R.string.years;
        } else if (item == _YEAR) {
            return R.string.year;
        } else if (item == _MONTH && plural) {
            return R.string.months;
        } else if (item == _MONTH) {
            return R.string.month;
        } else if (item == _DAY && plural) {
            return R.string.days;
        } else if (item == _DAY) {
            return R.string.day;
        } else if (item == _HOUR && plural) {
            return R.string.hours;
        } else if (item == _HOUR) {
            return R.string.hour;
        } else if (item == _MINUTE && plural) {
            return R.string.minutes;
        } else if (item == _MINUTE) {
            return R.string.minute;
        } else if (item == _SECOND && plural) {
            return R.string.seconds;
        } else if (item == _SECOND) {
            return R.string.second;
        } else if (item == _MILLISECOND && plural) {
            return R.string.milliseconds;
        } else return R.string.millisecond;
    }

    private int getMinifyStringId(char item) {
        if (item == _HOUR) {
            return R.string.hour_min;
        } else if (item == _MINUTE) {
            return R.string.minute_min;
        } else if (item == _SECOND) {
            return R.string.second_min;
        } else return R.string.millisecond_min;
    }

    public void setDuration(long duration){
        this.duration = duration;
        initialize();
    }

    /**
     * Return the Context is currently associated with.
     * @return the application context
     */
    public Context getContext() {
        return mContext;
    }

    /**
     * Format the duration as the best
     * @return the duration formatted
     */
    public String format() {
        return format(false);
    }

    /**
     * Format the duration as the best
     * @return the duration formatted
     */
    public String format(boolean minify) {
        int flag = APPROXIMATE_FORMAT;

        if (minify) {
            flag = MINIFY_UNIT;
        }

        return format(flag);
    }

    public String format(int flag) {
        int realFlag = 0x0000;

        boolean minify = (flag & MINIFY_UNIT) == MINIFY_UNIT;
        // remove MINIFY_UNIT flag
        flag = (flag & ~MINIFY_UNIT);

        if (flag == APPROXIMATE_FORMAT) {
            // create the best flag
            char[] flags = new char[]{
                    SHOW_MILLISECOND,
                    SHOW_SECOND,
                    SHOW_MINUTE,
                    SHOW_HOUR,
                    SHOW_DAY,
                    SHOW_MONTH,
                    SHOW_YEAR,
            };

            boolean loop = true;
            int ind = 6;
            while (ind >= 0 && loop) {
                if (translatedDuration[ind] != 0) {
                    loop = false;
                    realFlag |= flags[ind];
                    if (ind > 1 && translatedDuration[ind - 1] != 0) {
                        realFlag |= flags[ind - 1];
                    }
                } else {
                    ind--;
                }
            }
        } else {
            realFlag = flag;
        }

        if (minify) {
            realFlag |= MINIFY_UNIT;
        }

        // create the pattern and return it
        return format(getPattern(realFlag));
    }

    /**
     *
     * @param pattern The pattern to use to create the duration format.<br />
     *                Warning: {@link DurationUtils#UNIT} is used to select the right string unit (example : 60 min, min is set as %UNIT%).<br />
     *                To escape {@link DurationUtils#UNIT} use "%" char before
     * @return the duration formatted
     */
    public String format(String pattern) {
        // set year
        pattern = formatSubSequence(pattern, _YEAR, 6);
        pattern = formatSubSequence(pattern, _MONTH, 5);
        pattern = formatSubSequence(pattern, _DAY, 4);
        pattern = formatSubSequence(pattern, _HOUR, 3);
        pattern = formatSubSequence(pattern, _MINUTE, 2);
        pattern = formatSubSequence(pattern, _SECOND, 1);
        pattern = formatSubSequence(pattern, _MILLISECOND, 0);

        return pattern;
    }

}
