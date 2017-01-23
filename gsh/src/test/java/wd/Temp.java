package wd;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


public class Temp {
	public static void main(String[] args) throws ParseException {
		DateFormat utcFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SS");
		utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));

		Date date = utcFormat.parse("2017-01-22T08:13:13.29");
		System.out.println(date.toLocaleString());
	}
}


