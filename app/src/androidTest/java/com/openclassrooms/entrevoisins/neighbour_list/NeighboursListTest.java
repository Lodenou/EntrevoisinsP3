
package com.openclassrooms.entrevoisins.neighbour_list;

import android.content.res.Resources;
import android.support.design.widget.FloatingActionButton;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.openclassrooms.entrevoisins.R;
import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.service.DummyNeighbourGenerator;
import com.openclassrooms.entrevoisins.ui.neighbour_list.ListNeighbourActivity;
import com.openclassrooms.entrevoisins.utils.DeleteViewAction;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressBack;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.typeTextIntoFocusedView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.openclassrooms.entrevoisins.utils.RecyclerViewItemCountAssertion.withItemCount;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;



/**
 * Test class for list of neighbours
 */
@RunWith(AndroidJUnit4.class)
public class NeighboursListTest {

    // This is fixed
   // private static int ITEMS_COUNT = 12;


    private ListNeighbourActivity mActivity;

    @Rule
    public ActivityTestRule<ListNeighbourActivity> mActivityRule =
            new ActivityTestRule(ListNeighbourActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    /**
     * We ensure that our recyclerview is displaying at least on item
     */
    @Test
    public void myNeighboursList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(withId(R.id.list_neighbours))
                .check(matches(hasMinimumChildCount(1)));
    }

    /**
     * When we delete an item, the item is no more shown
     */
    @Test
    public void myNeighboursList_deleteAction_shouldRemoveItem() {
        int ITEMS_COUNT = DI.getNeighbourApiService().getNeighbours().size();
        // Given : We remove the element at position 2
        onView(Matchers.allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(withId(R.id.list_neighbours))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(Matchers.allOf(withId(R.id.list_neighbours), isDisplayed())).check(withItemCount(ITEMS_COUNT -1));
    }

    @Test
    // Lors ce que l'on clique sur le bouton add neighbour dans la liste, on ajoute bien un nouveau neighbour
    public void addNeighbourList() {
        int ITEMS_COUNT = DI.getNeighbourApiService().getNeighbours().size();
        onView(withId(R.id.list_neighbours)).check((withItemCount(ITEMS_COUNT)));

        onView(withId(R.id.fab_add)).perform(click());

        onView(withId(R.id.list_neighbours)).check((withItemCount(ITEMS_COUNT+1)));
    }

    @Test

    //L'écran détail du neighbour s'affiche lors ce que l'on clique sur un neighbour dans la liste
    public void DetailScreenWork() {

            ViewInteraction recyclerView = onView(
                    Matchers.allOf(withId(R.id.list_neighbours),
                            withParent(withId(R.id.container))));
            recyclerView.perform(actionOnItemAtPosition(0, click()));

        onView(withId(R.id.textView_detail_a_propos ))
                .check(matches(withText("À propos de moi")));

        
    }

    @Test
    // Test pour vérifier que l'user name dans le détail est bien mis automatiquement
    public void UserNameInDetailIsOk() {

        ViewInteraction recyclerView = onView(
                Matchers.allOf(withId(R.id.list_neighbours),
                        withParent(withId(R.id.container))));
        recyclerView.perform(actionOnItemAtPosition(0, click()));
        onView((withId(R.id.toolbar))).check(matches(new ToolbatMatcher("Caroline")));


    }

    // Quelques changements pour que UserNameInDetailIsOk marche
    public class ToolbatMatcher <T extends Toolbar> extends TypeSafeMatcher <T> {

        private String expextedTitle;

        public ToolbatMatcher(String expextedTitle) {
            this.expextedTitle = expextedTitle;
        }

        @Override
        protected boolean matchesSafely(Toolbar item) {
            return item.getTitle().equals(expextedTitle);
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("test");
        }
    }

}