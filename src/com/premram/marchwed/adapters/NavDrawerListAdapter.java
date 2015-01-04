package com.premram.marchwed.adapters;

import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.premram.marchwed.R;

public class NavDrawerListAdapter extends BaseAdapter {
	
	private FragmentActivity activity;
	
	private int[] image_icons = new int[] {R.drawable.invitation_icon,
								R.drawable.engagement_icon,
								R.drawable.itinerary_icon,
								R.drawable.map_icon
								};
	private String[] titles;
	
	public NavDrawerListAdapter(FragmentActivity activity, String[] titles) {
		this.activity = activity;
		this.titles = titles;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return image_icons.length;
	}

	@Override
	public Object getItem(int pos) {
		// TODO Auto-generated method stub
		return titles[pos];
	}

	@Override
	public long getItemId(int pos) {
		// TODO Auto-generated method stub
		return pos;
	}

	@Override
	public View getView(int position, View view1, ViewGroup viewGrp) {
		
		if (view1 == null) {
            LayoutInflater mInflater = (LayoutInflater)
            		activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view1 = mInflater.inflate(R.layout.fragment_main, null);
        }
		
		Log.i("NavDisplay---->","Position--->"+view1);
		
		ImageView iconView = (ImageView)view1.findViewById(R.id.icon);
		TextView txtView = (TextView)view1.findViewById(R.id.section_label);
		
		iconView.setImageResource(image_icons[position]);
		txtView.setText(titles[position]);
		
		Log.i("NavDisplay---->","ControlsSet");
		
		
		return view1;
	}
}
