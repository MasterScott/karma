/*
 *  Horoscope.java
 * 
 *  Created on Oct 25, 2009, 11:51:35 AM
 * 
 *  Copyright (c) 2009 Hippos Development Team. All rights reserved.
 * 
 *  This file is part of Karma.
 * 
 *  Karma is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  Karma is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with Karma.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.taksmind.karma.functions;

import java.util.HashMap;

import com.taksmind.karma.Main;


/**
 *
 * @author tak <tak@taksmind.com>
 */
public class Horoscope extends Function {

    private String message;
    private String channel;
    private String month;
    private int day;
    private String suffix;
    private HashMap<String, String> months = new HashMap<String, String>();
    private HashMap<String, String> zodiac = new HashMap<String, String>();
    
    public Horoscope() {
    	months.put("01", "january");
    	months.put("02", "febuary");
    	months.put("03", "march");
    	months.put("04", "april");
    	months.put("05", "may");
    	months.put("06", "june");
    	months.put("07", "july");
    	months.put("08", "august");
    	months.put("09", "september");
    	months.put("10", "october");
    	months.put("11", "november");
    	months.put("12", "december");
    	
    	zodiac.put("cancer", "http://www.astrology.com.au/astrology/12-signs-of-the-zodiac/cancer.html");
    	zodiac.put("aries", "http://www.astrology.com.au/astrology/12-signs-of-the-zodiac/aries.html");
    	zodiac.put("taurus", "http://www.astrology.com.au/astrology/12-signs-of-the-zodiac/taurus.html");
    	zodiac.put("gemini", "http://www.astrology.com.au/astrology/12-signs-of-the-zodiac/gemini.html");
    	zodiac.put("leo", "http://www.astrology.com.au/astrology/12-signs-of-the-zodiac/leo.html");
    	zodiac.put("libra", "http://www.astrology.com.au/astrology/12-signs-of-the-zodiac/libra.html");
    	zodiac.put("virgo", "http://www.astrology.com.au/astrology/12-signs-of-the-zodiac/virgo.html");
    	zodiac.put("scorpio", "http://www.astrology.com.au/astrology/12-signs-of-the-zodiac/scorpio.html");
    	zodiac.put("sagittarius", "http://www.astrology.com.au/astrology/12-signs-of-the-zodiac/sagittarius.html");
    	zodiac.put("capricorn", "http://www.astrology.com.au/astrology/12-signs-of-the-zodiac/capricorn.html");
    	zodiac.put("aquarius", "http://www.astrology.com.au/astrology/12-signs-of-the-zodiac/aquarius.html");
    	zodiac.put("pisces", "http://www.astrology.com.au/astrology/12-signs-of-the-zodiac/pisces.html");
    }

    @Override
    public void run() {
        /*if there is a message store and check it*/
        if (bot.hasMessage()) {
            message = bot.getMessage();
            channel = bot.getChannel();
        }

        if (message.startsWith("~zodiac")) {
            tokenize(true, 7, message);
            month = (String) tokenParameters.nextElement();
            
            for( String sign : zodiac.keySet() ) {
            	if( sign.equalsIgnoreCase(month) ) { //if the first parameter is a zodiac sign
            		Main.bot.sendMessage(channel, zodiac.get(sign));
            		return;
            	}
            }
            
            day   = Integer.parseInt((String) tokenParameters.nextElement());
            
            if(Integer.toString(day).endsWith("1")) {
            	suffix = "st";
            }
            else if(Integer.toString(day).endsWith("2")) {
            	suffix = "nd";
            }
            else if(Integer.toString(day).endsWith("3")) {
            	suffix = "rd";
            }
            else {
            	suffix = "th";
            }
            
            Main.bot.sendMessage(channel, "http://www.gotohoroscope.com/birthday-horoscopes/" + months.get(month) + "-" + day + suffix + ".html");
        }
    }
}
