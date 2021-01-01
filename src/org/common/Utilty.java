package org.common;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;

/**
 * @author dalin
 */
public class Utilty {
    /**
     * @param input
     * @param len
     * @return
     */
    public static String Substring(String input, int len) {
        if (DataValidator.isNullOrEmpty(input))
            return "";
        if (len >= input.length())
            return input;
        return input.substring(0, len);
    }

    /**
     * @return
     */
    public static Date getNowDateTime() {
        Calendar cal = Calendar.getInstance();
        String now = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE) + " "
                + cal.get(Calendar.HOUR) + ":" + cal.get(Calendar.MINUTE) + ":" + cal.get(Calendar.SECOND);
        return DataConverter.toDate(now, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * @return
     */
    public static Date getNowDate() {
        Calendar cal = Calendar.getInstance();
        String now = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-" + cal.get(Calendar.DATE);
        return DataConverter.toDate(now, "yyyy-MM-dd");
    }

    /**
     *
     */
    public static void writeCookie(HttpServletResponse response, String key, String value) {
        writeCookie(response, key, value, -1);
    }

    public static void writeCookie(HttpServletResponse response, String key, String value, int expirse) {
        Cookie newCookie;
        try {
            newCookie = new Cookie(key, URLEncoder.encode(value, "utf-8"));
            if (expirse > 0)
                expirse = expirse * 60;
            newCookie.setPath("/");
            newCookie.setMaxAge(expirse);//-1是在内存里，关闭浏览器就失效
            response.addCookie(newCookie);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public static String readCookie(HttpServletRequest request, String key) throws UnsupportedEncodingException {
        String value = "";
        Cookie[] ck = request.getCookies();
        if (ck == null)
            return "";
        for (Cookie c : ck) {
            if (c.getName().equals(key)) {
                value = c.getValue();
                break;
            }
        }
        return URLDecoder.decode(value, "utf-8");
    }

    /**
     * Base64加密 cookie
     * @param cleartext
     * @return
     */
    public static String encodeBase64(String cleartext){
        try{
             cleartext = new String(Base64.encodeBase64(cleartext.getBytes("UTF-8")));
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return cleartext;
    }

    /**
     * Base64解密 cookie
     * @param ciphertext
     * @return
     */
    public static String decodeBase64(String ciphertext){
        try{
            ciphertext = new String(Base64.decodeBase64(ciphertext.getBytes()),"UTF-8");
        } catch (UnsupportedEncodingException e){
            e.printStackTrace();
        }
        return ciphertext;
    }
}
