package com.yc.tmwk.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtil {

    private static final Logger log = LoggerFactory.getLogger(DateFormatUtil.class);

    /**
     *
     * @param format "yy-mm-dd"
     * @param date  new Date() java.util.Date
     * @return such as 2018-01-01
     */
    public static String format(String format, Date date){
        String str = "";
        if(date != null){
            try{
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYY-mm-dd");
                str = simpleDateFormat.format(date);
            }catch (Exception e){
                log.error(e.toString());
            }finally {
                return str;
            }
        }
        return str;
    }
}
