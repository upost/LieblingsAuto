package de.androidnewcomer.lieblingsauto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityScenarioRule =
            new ActivityScenarioRule<MainActivity>(MainActivity.class);

    @Test
    public void testStarsClicked() {
        Espresso.onView(ViewMatchers.withId(R.id.star1)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.star1))
                .check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_on)));
        Espresso.onView(ViewMatchers.withId(R.id.star2))
                .check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_off)));
        Espresso.onView(ViewMatchers.withId(R.id.star3))
                .check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_off)));
        Espresso.onView(ViewMatchers.withId(R.id.star4))
                .check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_off)));
        Espresso.onView(ViewMatchers.withId(R.id.star5))
                .check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_off)));

        Espresso.onView(ViewMatchers.withId(R.id.star2)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.star1))
                .check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_on)));
        Espresso.onView(ViewMatchers.withId(R.id.star2))
                .check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_on)));
        Espresso.onView(ViewMatchers.withId(R.id.star3))
                .check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_off)));
        Espresso.onView(ViewMatchers.withId(R.id.star4)).check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_off)));
        Espresso.onView(ViewMatchers.withId(R.id.star5)).check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_off)));
        Espresso.onView(ViewMatchers.withId(R.id.star3)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.star1)).check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_on)));
        Espresso.onView(ViewMatchers.withId(R.id.star2)).check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_on)));
        Espresso.onView(ViewMatchers.withId(R.id.star3)).check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_on)));
        Espresso.onView(ViewMatchers.withId(R.id.star4)).check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_off)));
        Espresso.onView(ViewMatchers.withId(R.id.star5)).check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_off)));
        Espresso.onView(ViewMatchers.withId(R.id.star4)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.star1)).check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_on)));
        Espresso.onView(ViewMatchers.withId(R.id.star2)).check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_on)));
        Espresso.onView(ViewMatchers.withId(R.id.star3)).check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_on)));
        Espresso.onView(ViewMatchers.withId(R.id.star4)).check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_on)));
        Espresso.onView(ViewMatchers.withId(R.id.star5)).check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_off)));
        Espresso.onView(ViewMatchers.withId(R.id.star5)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.star1)).check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_on)));
        Espresso.onView(ViewMatchers.withId(R.id.star2)).check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_on)));
        Espresso.onView(ViewMatchers.withId(R.id.star3)).check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_on)));
        Espresso.onView(ViewMatchers.withId(R.id.star4)).check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_on)));
        Espresso.onView(ViewMatchers.withId(R.id.star5)).check(ViewAssertions.matches(withImageDrawable(android.R.drawable.star_on)));


    }

    public static Matcher<View> withImageDrawable(final int resourceId) {
        return new BoundedMatcher<View, ImageView>(ImageView.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has image drawable resource " + resourceId);
            }

            @Override
            public boolean matchesSafely(ImageView imageView) {
                return sameBitmap(imageView.getContext(), imageView.getDrawable(), resourceId);
            }
        };
    }

    private static boolean sameBitmap(Context context, Drawable drawable, int resourceId) {
        Drawable otherDrawable = context.getResources().getDrawable(resourceId);
        if (drawable == null || otherDrawable == null) {
            return false;
        }
        if (drawable instanceof StateListDrawable && otherDrawable instanceof StateListDrawable) {
            drawable = drawable.getCurrent();
            otherDrawable = otherDrawable.getCurrent();
        }
        if (drawable instanceof BitmapDrawable) {
            Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
            Bitmap otherBitmap = ((BitmapDrawable) otherDrawable).getBitmap();
            return bitmap.sameAs(otherBitmap);
        }
        return false;
    }
}