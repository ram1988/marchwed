package com.premram.marchwed.adapters;

import java.util.Calendar;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Reminders;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class CalendarEventAdder {

	public static void addReminder(FragmentActivity activity) {
		
		Log.i("RamCal---","Adding event and reminder");
		
		ContentResolver cr = activity.getContentResolver();
		long eventID = createReceptionEvent(cr);
	
		ContentValues values = new ContentValues();
		values.put(Reminders.MINUTES, 120);
		values.put(Reminders.EVENT_ID, eventID);
		values.put(Reminders.METHOD, Reminders.METHOD_ALERT);
		Uri uri = cr.insert(Reminders.CONTENT_URI, values);
		
		cr = activity.getContentResolver();
		eventID = createWeddingEvent(cr);
	
		values = new ContentValues();
		values.put(Reminders.MINUTES, 120);
		values.put(Reminders.EVENT_ID, eventID);
		values.put(Reminders.METHOD, Reminders.METHOD_ALERT);
		uri = cr.insert(Reminders.CONTENT_URI, values);
	
		Log.i("RamCal---","reminder added");
	}
	
	private static long createReceptionEvent(ContentResolver cr) {
		
		Log.i("RamCal---","Adding Reception event");
		
		long calID = 3;
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
		
		return eventID;
	}
	
	private static long createWeddingEvent(ContentResolver cr) {
		
		Log.i("RamCal---","Adding Wedding event");
		
		long calID = 3;
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
		
		return eventID;
	}
}
