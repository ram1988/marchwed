package com.premram.marchwed.adapters;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;

import com.premram.marchwed.R;

public class GalleryPageAdapter extends PagerAdapter{
	  
	  private FragmentActivity activity;
	  int NumberOfPages = 4;
	  
	 
	  
	  int[] res = { 
	    R.drawable.eng1,
	    R.drawable.eng2,
	    R.drawable.eng3,
	    R.drawable.eng4,
	   };
	

	  public GalleryPageAdapter(FragmentActivity activity) {
		  this.activity = activity;
	  }
	  
	  @Override
	  public int getCount() {
	   return NumberOfPages;
	  }

	  @Override
	  public boolean isViewFromObject(View view, Object object) {
	   return view == object;
	  }

	  @Override
	  public Object instantiateItem(ViewGroup container, int position) {
	   
		  Log.i("ViewPagerAdapter00000", "Control reached here"); 
	      
		  
	      ImageView imageView = new ImageView(activity);
	      imageView.setImageResource(res[position]);
	      LayoutParams imageParams = new LayoutParams(
	        LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
	      imageView.setLayoutParams(imageParams);
	      
	      //To add toast to show the scroll info
	      
	     
	      
	      ((ViewPager) container).addView(imageView, 0);
	      return imageView;
	   }

	  @Override
	  public void destroyItem(ViewGroup container, int position, Object object) {
		  ((ViewPager) container).removeView((ImageView)object);
	  }

	}
