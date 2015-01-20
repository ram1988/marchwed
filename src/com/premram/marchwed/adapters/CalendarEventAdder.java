package com.premram.marchwed.adapters;

import java.util.Calendar;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.CalendarContract;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Reminders;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class CalendarEventAdder {
	
	public static void addReminders(Context ctxt) {
		 	SharedPreferences p = PreferenceManager.getDefaultSharedPreferences(ctxt);
	    	boolean firstRun = p.getBoolean("preference_first_run", true);
	    	if(firstRun) {
	    		//Adding reminders to the calendar        	
	    		ContentResolver cr = ctxt.getContentResolver();
				createReceptionEventReminder(1,cr);
				createWeddingEventReminder(1,cr);
	        	p.edit().putBoolean("preference_first_run", false).commit();
	        	Log.i("MainActivityCalendarAdd--","first run");
	    	} else {
	    		Log.i("MainActivityCalendarAdd--","non-first run");
	    	}
	}
	
	/*public static void addReminders(FragmentActivity activity) {

			String[] projection = 
			      new String[]{
			            Calendars._ID, 
			            Calendars.NAME, 
			            Calendars.ACCOUNT_NAME, 
			            Calendars.ACCOUNT_TYPE};
			
			ContentResolver cr = activity.getContentResolver();
			
			Cursor calCursor = 
			      cr.query(Calendars.CONTENT_URI, 
			                  projection, 
			                  Calendars.VISIBLE + " = 1", 
			                  null, 
			                  Calendars._ID + " ASC");
			if (calCursor.moveToFirst()) {
			   do {
			      long id = calCursor.getLong(0);
			      String displayName = calCursor.getString(1);
			      
			      Log.i("RamCal************",id+"----"+displayName+"---"+calCursor.getString(3));
			      //createReceptionEventReminder(id,cr);
			      //createWeddingEventReminder(id,cr);
			   } while (calCursor.moveToNext());
			}
	}*/
	
	/*private void createLocalCalendar() {
		ContentValues values = new ContentValues();
		values.put(
		      Calendars.ACCOUNT_NAME, );
		values.put(
		      Calendars.ACCOUNT_TYPE, 
		      CalendarContract.ACCOUNT_TYPE_LOCAL);
		values.put(
		      Calendars.NAME, 
		      "GrokkingAndroid Calendar");
		values.put(
		      Calendars.CALENDAR_DISPLAY_NAME, 
		      "GrokkingAndroid Calendar");
		values.put(
		      Calendars.CALENDAR_COLOR, 
		      0xffff0000);
		values.put(
		      Calendars.CALENDAR_ACCESS_LEVEL, 
		      Calendars.CAL_ACCESS_OWNER);
		values.put(
		      Calendars.OWNER_ACCOUNT, 
		      "some.account@googlemail.com");
		values.put(
		      Calendars.CALENDAR_TIME_ZONE, 
		      "Europe/Berlin");
		values.put(
		      Calendars.SYNC_EVENTS, 
		      1);
		Uri.Builder builder = 
		      CalendarContract.Calendars.CONTENT_URI.buildUpon(); 
		builder.appendQueryParameter(
		      Calendars.ACCOUNT_NAME, 
		      "com.grokkingandroid");
		builder.appendQueryParameter(
		      Calendars.ACCOUNT_TYPE, 
		      CalendarContract.ACCOUNT_TYPE_LOCAL);
		builder.appendQueryParameter(
		      CalendarContract.CALLER_IS_SYNCADAPTER, 
		      "true");
		Uri uri = 
		      getContentResolver().insert(builder.build(), values);
	}*/
	
	private static void createReceptionEventReminder(long calID,ContentResolver cr) {
		
		Log.i("RamCal---","Adding Reception event");
		
		long startMillis = 0; 
		long endMillis = 0;     
		Calendar beginTime = Calendar.getInstance();
		beginTime.set(2015, 2, 7, 19, 30);
		
		startMillis = beginTime.getTimeInMillis();
		Calendar endTime = Calendar.getInstance();
		endTime.set(2015, 2, 7, 21, 30);
		
		endMillis = endTime.getTimeInMillis();
		
		ContentValues values = new ContentValues();
		values.put(Events.DTSTART, startMillis);
		values.put(Events.DTEND, endMillis);
		values.put(Events.TITLE, "Reception - Ram Narayan with Prem Prakasini");
		values.put(Events.DESCRIPTION, "Ram Narayan and Prem Prakasini's Wedding Reception");
		values.put(Events.CALENDAR_ID, calID);
		values.put(Events.HAS_ALARM, 1);
		values.put(Events.EVENT_TIMEZONE, "Asia/Calcutta");
		//values.put(Events.MAX_REMINDERS, 5);

		Uri uri = cr.insert(Events.CONTENT_URI, values);
		
		long eventID = Long.parseLong(uri.getLastPathSegment());
		
		Log.i("RamCal---","event added"+eventID);
		
		addReminder(eventID,cr);
	}
	
	private static void createWeddingEventReminder(long calID, ContentResolver cr) {
		
		Log.i("RamCal---","Adding Wedding event");
		
		long startMillis = 0; 
		long endMillis = 0;     
		Calendar beginTime = Calendar.getInstance();
		beginTime.set(2015, 2, 8, 7, 30);
		
		startMillis = beginTime.getTimeInMillis();
		Calendar endTime = Calendar.getInstance();
		endTime.set(2015, 2, 8, 11, 30);
		
		endMillis = endTime.getTimeInMillis();
		
		ContentValues values = new ContentValues();
		values.put(Events.DTSTART, startMillis);
		values.put(Events.DTEND, endMillis);
		values.put(Events.TITLE, "Muhurtham - Ram Narayan ties the knot to Prem Prakasini");
		values.put(Events.DESCRIPTION, "Ram Narayan and Prem Prakasini's Muhurtham");
		values.put(Events.CALENDAR_ID, calID);
		values.put(Events.HAS_ALARM, 1);
		values.put(Events.EVENT_TIMEZONE, "Asia/Calcutta");
		//values.put(Events.MAX_REMINDERS, 5);

		Uri uri = cr.insert(Events.CONTENT_URI, values);
		
		long eventID = Long.parseLong(uri.getLastPathSegment());
		
		Log.i("RamCal---","event added"+eventID);
		
		addReminder(eventID,cr);
	}
	
	
	private static void addReminder(long eventID, ContentResolver cr) {
		ContentValues values = new ContentValues();
		values.put(Reminders.MINUTES, 120);
		values.put(Reminders.EVENT_ID, eventID);
		values.put(Reminders.METHOD, Reminders.METHOD_ALERT);
		cr.insert(Reminders.CONTENT_URI, values);
	}
}
