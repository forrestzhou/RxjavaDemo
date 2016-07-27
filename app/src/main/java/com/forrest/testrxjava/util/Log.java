/*
Log.java
Copyright (C) 2011  Belledonne Communications, Grenoble, France

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
*/
package com.forrest.testrxjava.util;



/**
 * Convenient wrapper for Android logs.
 */
public final class Log {

	public static final String TAG = "TestRxjava";

	public static final boolean LOG_ENABLED= true;

	public static void i(String tag,String msg) {
		if (LOG_ENABLED) {
			android.util.Log.i(tag, msg);
		}
	}
	public static void v(String tag,String msg) {
		if (LOG_ENABLED) {
			android.util.Log.v(tag, msg);
		}
	}
	public static void d(String tag,String msg) {
		if (LOG_ENABLED) {
			android.util.Log.v(tag, msg);
		}
	}
	
	public static void w(String tag,String msg) {
		if (LOG_ENABLED) {
			android.util.Log.w(tag, msg);
		}
	}
	public static void e(String tag,String msg) {
		if (LOG_ENABLED) {
			android.util.Log.e(tag, msg);
		}
	}
	public static void e(String tag,String msg,Throwable t) {
		if (LOG_ENABLED) {
			android.util.Log.e(tag, msg, t);
		}
	}
	public static void e(String tag,Throwable t) {
		if (LOG_ENABLED) {
			android.util.Log.e(tag, t.getLocalizedMessage(), t);
		}
	}

	/**
	 * @throws RuntimeException always throw after logging the error message.
	 */
	public static void f(Object...objects) {
		if (LOG_ENABLED) {
			android.util.Log.e(TAG, toString(objects));
			throw new RuntimeException("Fatal error : " + toString(objects));
		}
	}
	/**
	 * @throws RuntimeException always throw after logging the error message.
	 */
	public static void f(Throwable t, Object...objects) {
		if (LOG_ENABLED) {
			android.util.Log.e(TAG, toString(objects), t);
			throw new RuntimeException("Fatal error : " + toString(objects), t);
		}
	}

	private static String toString(Object...objects) {
		StringBuilder sb = new StringBuilder();
		for (Object o : objects) {
			sb.append(o);
		}
		return sb.toString();
	}
}
