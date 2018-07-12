package belbeze.david.com.timeformatter;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        DurationUtils du = new DurationUtils(appContext, 67092937547l);

        String result = du.format(DurationUtils.EXACT_FORMAT|DurationUtils.MINIFY_UNIT);
        Log.i("david", result);

//        assertEquals("belbeze.david.com.timeformatter.test", appContext.getPackageName());
    }
}
