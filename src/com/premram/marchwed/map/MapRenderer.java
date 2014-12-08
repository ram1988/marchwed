package com.premram.marchwed.map;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.premram.marchwed.R;

public class MapRenderer {
	
	private FragmentActivity fragActivity;
	private FragmentManager fragMgr;
	private double lat;
	private double longi;
	private String locTitle;
	private String locSnippet;
	private int visible;
	private FrameLayout layout;
	
	
	
	public MapRenderer(FragmentActivity fragActivity, FragmentManager fragMgr) {
		this.fragActivity = fragActivity;
		this.fragMgr = fragMgr;
		this.layout = (FrameLayout) fragActivity.findViewById(R.id.map_container);
	}

	public void renderMap()  {
		LatLng mdm = new LatLng(lat, longi);
        	    	
    	SupportMapFragment mapFrag = (SupportMapFragment) fragMgr.findFragmentById(R.id.map);
    	
    	GoogleMap map = mapFrag.getMap();
    	
    	map.addMarker(new MarkerOptions()
    		        .position(mdm)
    		        .title(locTitle)
    		        .snippet(locSnippet)
    		        .icon(BitmapDescriptorFactory
    		            .fromResource(R.drawable.ic_locator)));

    	map.moveCamera(CameraUpdateFactory.newLatLngZoom(mdm, 215));

    	map.animateCamera(CameraUpdateFactory.zoomTo(16), 2000, null);
	}
	
	
	public int getVisible() {
		return visible;
	}

	public void setVisible(int visible) {
		this.visible = visible;
		layout.setVisibility(visible);
	}

	
	public double getLatitude() {
		return lat;
	}

	public void setLatitude(double lat) {
		this.lat = lat;
	}

	public double getLongitude() {
		return longi;
	}

	public void setLongitude(double longi) {
		this.longi = longi;
	}

	public String getLocTitle() {
		return locTitle;
	}

	public void setLocTitle(String locTitle) {
		this.locTitle = locTitle;
	}

	public String getLocSnippet() {
		return locSnippet;
	}

	public void setLocSnippet(String locSnippet) {
		this.locSnippet = locSnippet;
	}
}
