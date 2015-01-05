package com.premram.marchwed;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.premram.marchwed.adapters.GalleryPageAdapter;
import com.premram.marchwed.adapters.NavDrawerListAdapter;
import com.premram.marchwed.map.MapRenderer;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Fragment used for managing interactions for and presentation of a navigation drawer.
 * See the <a href="https://developer.android.com/design/patterns/navigation-drawer.html#Interaction">
 * design guidelines</a> for a complete explanation of the behaviors implemented here.
 */
public class NavigationDrawerFragment extends Fragment {

    /**
     * Remember the position of the selected item.
     */
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    /**
     * Per the design guidelines, you should show the drawer on launch until the user manually
     * expands it. This shared preference tracks this.
     */
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

    /**
     * A pointer to the current callbacks instance (the Activity).
     */
    private NavigationDrawerCallbacks mCallbacks;

    /**
     * Helper component that ties the action bar to the navigation drawer.
     */
    private ActionBarDrawerToggle mDrawerToggle;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerListView;
    private View mFragmentContainerView;

    private int mCurrentSelectedPosition = 0;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;
    
    private Integer[][] toggleMenuStates;
    private MapRenderer mapRenderer;
    private ViewPager viewPager;
    private GalleryPageAdapter pageAdapter;
    private WebView rsvpWebView;

	public int position;
    
    public NavigationDrawerFragment() {
    	
    	toggleMenuStates = new Integer[5][];
    	
    	//ToggleStateOrder - Map, Photos, About us, Invitation
    	toggleMenuStates[0] = new Integer[]{View.GONE,View.GONE,View.VISIBLE,View.GONE,View.GONE};
    	toggleMenuStates[1] = new Integer[]{View.GONE,View.GONE,View.GONE,View.VISIBLE,View.GONE};
    	toggleMenuStates[2] = new Integer[]{View.GONE,View.VISIBLE,View.GONE,View.GONE,View.GONE};
    	toggleMenuStates[3] = new Integer[]{View.VISIBLE,View.GONE,View.GONE,View.GONE,View.GONE};
    	toggleMenuStates[4] = new Integer[]{View.GONE,View.GONE,View.GONE,View.GONE,View.VISIBLE};
    }

    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Read in the flag indicating whether or not the user has demonstrated awareness of the
        // drawer. See PREF_USER_LEARNED_DRAWER for details.
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }

        // Select either the default item (0) or the last selected item.
        selectItem(mCurrentSelectedPosition);
    }

    @Override
    public void onActivityCreated (Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Indicate that this fragment would like to influence the set of actions in the action bar.
        setHasOptionsMenu(true);
    }
    
    private void initializeView() {
    	TextView invitationView = (TextView) getActivity().findViewById(R.id.invitation);
    	
    	StringBuffer sb = new StringBuffer();
    	sb.append("<p style='align:left'>There were two hearts living in different places in Chennai.");
    	sb.append(" These two hearts had not known each other for long time that it would unite forever.</p>");
    	sb.append("<p align=left>Time arrived for bondage. Two hearts web crawled the matrimony and found each other. </p>");
    	sb.append("<p align=left>Two hearts were engaged on <font color=blue>September 7, 2014.</font></p>");
    	sb.append("<p align=left>Now, It's the time for the hearts to live unitedly and love each other forever.</p>");
    	sb.append("<p align=left>Okay!!! Whom these hearts belong to?. It's none other than us</p>");
    	sb.append("<p></p>");
    	sb.append("<p style='align:left'>			<i><font color=blue>Ram Narayan.M</font></i> and <i><font color=blue>Prem Prakasini.G</font></i>			</p>");
    	sb.append("<p></p>");
    	sb.append("<p align=left>To make this auspicious occassion of unison of two hearts in grand level with our friends and relatives, ");
    	sb.append(" We cordially invite you all for our wedding. </p>");
    	sb.append("<p align=left>Wedding grandness is not just about rich decorations, gifts and variety food. </p>");
    	sb.append("<p align=left>It's about the presence of PEOPLE. </p>");
    	sb.append("<p></p>");
    	sb.append("<p align=left>So, again We invite you all to make our Wedding, a Great Gala Wedding happening in </p>");
    	sb.append("<p><u>Wedding Venue</u>:</p>");
    	sb.append("<p>AIOBEU SWASTIKA - L.BALASUBRAMANIAN HALL<br/>");
    	sb.append("38/10, 7th Street, Dr.Radhakrishnan Salai (Opp. Children Garden School),<br/>");
    	sb.append("Mylapore, Chennai - 600 004</p>");
    	//Wedding Venue goes here
    	
    	invitationView.setText(Html.fromHtml(sb.toString()));
    	invitationView.setMovementMethod(new ScrollingMovementMethod());
    	
    	//TableLayout txtView = (TableLayout) getActivity().findViewById(R.id.about_us);
    	//txtView.setVisibility(View.GONE);
    	
    	/*sb = new StringBuffer();
    	
    	sb.append("<table>");
    	sb.append("<tr><td>Wedding Reception</td> <td>7:00 pm-9:00 pm, Saturday, March 7, 2015</td></tr>");
    	sb.append("<tr><td>Muhurtham</td> <td> 9:00 am-10:30 am, Sunday, March 8, 2015</td></tr>");
    	sb.append("<tr><td>Scintillating Music Programme</u>");
    	sb.append("Rendered by <i><font color=blue>Sri.T.V.K. Vikaasa Ramdas</font> </i>(Disciple of Padma Shree Mandolin U.Srinivas)</td></tr>");
    	sb.append("<tr><td>Dinner</td><td> 7:00 pm onwards</td></tr>");
    	sb.append("</table>");
    	
    	txtView.setText(Html.fromHtml(sb.toString()));
    	*/
    	
    	mapRenderer = new MapRenderer(getActivity(),getFragmentManager());
    	//mapRenderer.setVisible(View.GONE);
    	mapRenderer.setLongitude(80.269322);
    	mapRenderer.setLatitude(13.045942);
    	mapRenderer.setLocTitle("AIOBEU SWASTIKA - L.BALASUBRAMANIAN HALL");
    	mapRenderer.setLocSnippet("My Wedding Venue");
    	
    	/*
    	 * Map rendered at first in order to reduce the interaction with the google map server.
    	 * Just that the layout is being hidden/displayed at each time
    	 */
    	mapRenderer.renderMap();
    	
    	viewPager = (ViewPager)getActivity().findViewById(R.id.myviewpager);
    	pageAdapter = new GalleryPageAdapter(getActivity());
    	viewPager.setAdapter(pageAdapter);

    	rsvpWebView = (WebView) getActivity().findViewById(R.id.webview);
    	rsvpWebView.loadUrl("http://premram.rsvpify.com/");
    	
    	toggleMenuStates(0);    	
    }

    
    private void toggleMenuStates(int position) {
    	FragmentActivity fragActivity = getActivity();
        TextView invitationView = (TextView) fragActivity.findViewById(R.id.invitation);
        TableLayout txtView = (TableLayout) fragActivity.findViewById(R.id.about_us);
       
        Integer[] toggleStates = toggleMenuStates[position];
        mapRenderer.setVisible(toggleStates[0]);
    	txtView.setVisibility(toggleStates[1]);
    	invitationView.setVisibility(toggleStates[2]);
    	viewPager.setVisibility(toggleStates[3]);
    	rsvpWebView.setVisibility(toggleStates[4]);
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
    	
    	    	
        mDrawerListView = (ListView) inflater.inflate(
                R.layout.fragment_navigation_drawer, container, false);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
        	
        	
        	
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(position);          
                toggleMenuStates(position);
                
                Log.i("ItemClickee", position+" is selected**************");      
            }
        });
        
        String[] titles = new String[]{
                getString(R.string.title_section1),
                getString(R.string.title_section4),
                getString(R.string.title_section2),
                getString(R.string.title_section3),
                getString(R.string.title_section5),
        };
        
       /* mDrawerListView.setAdapter(new ArrayAdapter<String>(
                getActionBar().getThemedContext(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1,
                new String[]{
                        getString(R.string.title_section1),
                        getString(R.string.title_section4),
                        getString(R.string.title_section2),
                        getString(R.string.title_section3),
                }));
                */
        mDrawerListView.setAdapter(new NavDrawerListAdapter(getActivity(),titles));
        mDrawerListView.setItemChecked(mCurrentSelectedPosition, true);
        return mDrawerListView;
    }

    public boolean isDrawerOpen() {
        return mDrawerLayout != null && mDrawerLayout.isDrawerOpen(mFragmentContainerView);
    }

    /**
     * Users of this fragment must call this method to set up the navigation drawer interactions.
     *
     * @param fragmentId   The android:id of this fragment in its activity's layout.
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     */
    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        // set up the drawer's list view with items and click listener

        ActionBar actionBar = getActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the navigation drawer and the action bar app icon.
        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(),                    /* host Activity */
                mDrawerLayout,                    /* DrawerLayout object */
                R.drawable.ic_drawer,             /* nav drawer image to replace 'Up' caret */
                R.string.navigation_drawer_open,  /* "open drawer" description for accessibility */
                R.string.navigation_drawer_close  /* "close drawer" description for accessibility */
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }

                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) {
                    return;
                }

                if (!mUserLearnedDrawer) {
                    // The user manually opened the drawer; store this flag to prevent auto-showing
                    // the navigation drawer automatically in the future.
                    mUserLearnedDrawer = true;
                    SharedPreferences sp = PreferenceManager
                            .getDefaultSharedPreferences(getActivity());
                    sp.edit().putBoolean(PREF_USER_LEARNED_DRAWER, true).apply();
                }

                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }
        };

        // If the user hasn't 'learned' about the drawer, open it to introduce them to the drawer,
        // per the navigation drawer design guidelines.
        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }

        // Defer code dependent on restoration of previous instance state.
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        initializeView();
    	
    }

    private void selectItem(int position) {
        mCurrentSelectedPosition = position;
        if (mDrawerListView != null) {
            mDrawerListView.setItemChecked(position, true);
        }
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawer(mFragmentContainerView);
        }
        if (mCallbacks != null) {
            mCallbacks.onNavigationDrawerItemSelected(position);
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallbacks = (NavigationDrawerCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement NavigationDrawerCallbacks.");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Forward the new configuration the drawer toggle component.
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // If the drawer is open, show the global app actions in the action bar. See also
        // showGlobalContextActionBar, which controls the top-left area of the action bar.
        if (mDrawerLayout != null && isDrawerOpen()) {
            inflater.inflate(R.menu.global, menu);
            showGlobalContextActionBar();
        }
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

       /* if (item.getItemId() == R.id.action_example) {
            //Toast.makeText(getActivity(), "Example action.", Toast.LENGTH_SHORT).show();
            return true;
        }
*/
        return super.onOptionsItemSelected(item);
    }

    /**
     * Per the navigation drawer design guidelines, updates the action bar to show the global app
     * 'context', rather than just what's in the current screen.
     */
    private void showGlobalContextActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setTitle(R.string.app_name);
    }

    private ActionBar getActionBar() {
        return ((ActionBarActivity) getActivity()).getSupportActionBar();
    }

    /**
     * Callbacks interface that all activities using this fragment must implement.
     */
    public static interface NavigationDrawerCallbacks {
        /**
         * Called when an item in the navigation drawer is selected.
         */
        void onNavigationDrawerItemSelected(int position);
    }
    

}
