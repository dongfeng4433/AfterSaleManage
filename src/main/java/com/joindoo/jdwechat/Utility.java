package com.joindoo.jdwechat;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.MessageDigest;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by zhuqiang1 on 2016/5/28.
 */
public class Utility {
    public static String CHARSET_DEFAULT = "utf-8";
    public final static long TicksOf1Day = 24 * 60 * 60 * 1000;
    public final  static SimpleDateFormat DefaultDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    public final  static SimpleDateFormat DefaultDate = new SimpleDateFormat("MM-dd");
    public final static SimpleDateFormat DefaultDateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public final  static SimpleDateFormat DefaultTimeSpanFormat = new SimpleDateFormat("HH:mm:ss");
    public final  static SimpleDateFormat DefaultTimeSpanFormat1 = new SimpleDateFormat("HH:mm");
    public final  static SimpleDateFormat DefaultYearMonthFormat = new SimpleDateFormat("yyyy-MM");
    public final  static SimpleDateFormat DefaultTimestampFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    public final  static SimpleDateFormat DefaultDateMinuteFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    public final  static SimpleDateFormat DefaultDateMinuteFormatSpan = new SimpleDateFormat("MM-dd HH:mm");
    public final  static SimpleDateFormat DefaultDateMonthDayFormatSpan = new SimpleDateFormat("MM月dd日");
    private static SimpleDateFormat sdfYMDHM = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static SimpleDateFormat sdfYMD = new SimpleDateFormat("yyyy-MM-dd");
    private static final char[] HexChars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
    private final  static char hexDigits[] = {'0', 'n', '1', 'j', '2', 'a', '3', 'q', '4', 's', '5', 'w', '6', 'c', '7', 'e'};
    private static final char[] DEFAULT_SPLIT_CHARS = new char[]{' ', ',', ';'};

    /* ---------------------------------BEGIN----------------------------------------------------------------------------------------------------------------- */

    private static transient int gregorianCutoverYear = 1582;

    /** 闰年中每月天数 */
    private static final int[] DAYS_P_MONTH_LY= {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /** 非闰年中每月天数 */
    private static final int[] DAYS_P_MONTH_CY= {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /** 代表数组里的年、月、日 */
    private static final int Y = 0, M = 1, D = 2;

    /**
     * 将代表日期的字符串分割为代表年月日的整形数组
     * @param date
     * @return
     */
    public static int[] splitYMD(String date){
        date = date.replace("-", "");
        int[] ymd = {0, 0, 0};
        ymd[Y] = Integer.parseInt(date.substring(0, 4));
        ymd[M] = Integer.parseInt(date.substring(4, 6));
        ymd[D] = Integer.parseInt(date.substring(6, 8));
        return ymd;
    }

    /**
     * 检查传入的参数代表的年份是否为闰年
     * @param year
     * @return
     */
    public static boolean isLeapYear(int year) {
        return year >= gregorianCutoverYear ?
                ((year%4 == 0) && ((year%100 != 0) || (year%400 == 0))) : (year%4 == 0);
    }

    /**
     * 日期加1天
     * @param year
     * @param month
     * @param day
     * @return
     */
    private static int[] addOneDay(int year, int month, int day){
        if(isLeapYear( year )){
            day++;
            if( day > DAYS_P_MONTH_LY[month -1 ] ){
                month++;
                if(month > 12){
                    year++;
                    month = 1;
                }
                day = 1;
            }
        }else{
            day++;
            if( day > DAYS_P_MONTH_CY[month -1 ] ){
                month++;
                if(month > 12){
                    year++;
                    month = 1;
                }
                day = 1;
            }
        }
        int[] ymd = {year, month, day};
        return ymd;
    }

    /**
     * 将不足两位的月份或日期补足为两位
     * @param decimal
     * @return
     */
    public static String formatMonthDay(int decimal){
        DecimalFormat df = new DecimalFormat("00");
        return df.format( decimal );
    }

    /**
     * 将不足四位的年份补足为四位
     * @param decimal
     * @return
     */
    public static String formatYear(int decimal){
        DecimalFormat df = new DecimalFormat("0000");
        return df.format( decimal );
    }

    /**
     * 计算两个日期之间相隔的天数
     * @param begin
     * @param end
     * @return
     * @throws ParseException
     */
    public static long countDay(String begin,String end){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date beginDate , endDate;
        long day = 0;
        try {
            beginDate= format.parse(begin);
            endDate=  format.parse(end);
            day=(endDate.getTime()-beginDate.getTime())/(24*60*60*1000);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return day;
    }

    /**
     * 以循环的方式计算日期
     * @param beginDate endDate
     * @return
     */
    public static List<String> getEveryday(String beginDate , String endDate){
        long days = countDay(beginDate, endDate);
        int[] ymd = splitYMD( beginDate );
        List<String> everyDays = new ArrayList<String>();
        everyDays.add(beginDate);
        for(int i = 0; i < days; i++){
            ymd = addOneDay(ymd[Y], ymd[M], ymd[D]);
            everyDays.add(formatYear(ymd[Y])+"-"+formatMonthDay(ymd[M])+"-"+formatMonthDay(ymd[D]));
        }
        return everyDays;
    }
    /* ---------------------------------END----------------------------------------------------------------------------------------------------------------- */

    public static Object getPropertyValue(Object obj, String filed) {
        try {
            Class clazz = obj.getClass();
           // PropertyDescriptor pd = new PropertyDescriptor(filed, clazz);
            String getMethodName = "get"+filed; //get方法
            Method getMethod = clazz.getMethod(getMethodName, new Class[] {});
           // Method getMethod = pd.getReadMethod();//获得get方法

            if (getMethod != null) {
                Object o = getMethod.invoke(filed,new Object[]{});//执行get方法返回一个Object
                System.out.println(o);
                return o;
            }
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isNullOrEmpty(String s){
        if(s==null||s==""||s.equals(""))return true;
        return false;
    }
    public static boolean isNullOrEmpty(Object o){
        if(o==null)return true;
        return false;
    }
    public static boolean isNull(Date dt){
        if(dt==null)return true;
        return false;
    }
    /**
     * 注意为了速度和处理方便，本方法最多处理10个参数
     */
    public static String String_Format(String format, Object... args) {
        if (format == null) return "";
        StringBuilder sb = new StringBuilder(format.length());
        int size = format.length();
        Object o;
        char ch, ch1, ch2;
        StringBuilder temp = new StringBuilder(10);
        int i = 0, index;
        while (i < size) {
            ch = format.charAt(i);
            if (ch == '{') {
                i++;
                ch1 = format.charAt(i);
                if (ch1 == '{') {
                    sb.append('{');
                } else if (ch1 >= '0' && ch1 <= '9') {
                    index = ch1 - '0';
                    i++;
                    ch2 = format.charAt(i);
                    if (ch2 != '}') return "*INCORRECT_FORMAT*";
                    if (args.length > index) {
                        o = args[index];
                        if (o != null) sb.append(Utility.toString(o, false));
                    }
                } else {
                    return "*INCORRECT_FORMAT*";
                }
            } else sb.append(ch);
            i++;
        }
        return sb.toString();
    }
    /**
     * 将一个对象转化成String类型，为了保持一致，所以canNull参数，但是实际上该参数无效
     *
     * @param o
     * @param canNull 本参数无效
     * @return
     */
    public static String toString(Object o, boolean canNull) {
        if (o == null) return null;
        if (o instanceof Date) {
            Date date = (Date) o;
            if (date.getTime() % Utility.TicksOf1Day == 0)
                return Utility.formatDateTime(date, true);
            return Utility.formatDateTime(date, false);
        }
        return o.toString();
    }

    /** 判断某一天，是否在days数组中，用途：判断某天是否是休息或上班
     * datetime:某一天
     * days:节假日或调休日集合数组
     *
     */
    public static boolean isCalendarInDays(Calendar datetime, ArrayList<String> days){
        //java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd");
        if (days.size() < 1)		return false;
        Calendar date1 = Calendar.getInstance();
        Date d = null;
        try {
            for (String s : days) {
                d = sdfYMD.parse(s);
                date1.setTimeInMillis(d.getTime());
                if(date1.get(Calendar.YEAR) == datetime.get(Calendar.YEAR) && date1.get(Calendar.MONTH) == datetime.get(Calendar.MONTH) &&
                        date1.get(Calendar.DAY_OF_MONTH) == datetime.get(Calendar.DAY_OF_MONTH)) {
                    return true;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /*
     * 判断某个时刻是否工作日（工作时间：工作日9:00-12:00,14:00-18:00）
    * datetime:某个时间
    * holiday:周一到周五，但是是节假日的集合数组；
    * workday:周六或周日，但是是工作日的集合数组。
    */
    public static boolean isWorkingDay(Calendar datetime, ArrayList<String> holiday, ArrayList<String> workday){
        boolean flag = false;
        //如果是星期六或星期天
        if(datetime.get(Calendar.DAY_OF_WEEK) == 1 || datetime.get(Calendar.DAY_OF_WEEK) == 7){
            //虽然是星期六或星期天，但是是工作日 isWorkday()
            flag = false;
            if(isCalendarInDays(datetime,workday)){
                flag = true;
            }
        }
        else{
            flag = true;
            //如果是周一到周五，但是是节假日 isHoliday()
            if(isCalendarInDays(datetime,holiday)) {
                flag = false;
            }
        }
        return flag;
    }

    /*
     *  判断某个时刻是否工作时间（工作时间：工作日9:00-12:00,14:00-18:00）
    * datetime:某个时间
    * holiday:周一到周五，但是是节假日的集合数组；
    * workday:周六或周日，但是是工作日的集合数组。
    */
    public static boolean isWorkingTime(Calendar datetime, ArrayList<String> holiday, ArrayList<String> workday){

        boolean flag = isWorkingDay(datetime,holiday,workday);
        if(flag){
            Calendar date1 = Calendar.getInstance();
            date1.set(datetime.get(Calendar.YEAR),datetime.get(Calendar.MONTH),datetime.get(Calendar.DAY_OF_MONTH),9,0);
            Calendar date2 = Calendar.getInstance();
            date2.set(datetime.get(Calendar.YEAR),datetime.get(Calendar.MONTH),datetime.get(Calendar.DAY_OF_MONTH),12,0);
            Calendar date3 = Calendar.getInstance();
            date3.set(datetime.get(Calendar.YEAR),datetime.get(Calendar.MONTH),datetime.get(Calendar.DAY_OF_MONTH),14,0);
            Calendar date4 = Calendar.getInstance();
            date4.set(datetime.get(Calendar.YEAR),datetime.get(Calendar.MONTH),datetime.get(Calendar.DAY_OF_MONTH),18,0);
            if ((datetime.before(date2) && datetime.after(date1)) || datetime.before(date4) && datetime.after(date3))		return true;
            return false;
        }
        return false;

    }

	/*
	 * 根据起止时间计算工作日，按一天7工作小时计，扣周一到周五的休息日，加上周六周日的上班日
	 * startDateTime:起始日期
	 * endDateTime:结束日期
	 * holiday:周一到周五的休息日集合数组
	 * workday:周六或周日的上班日集合数组
	 * return:工作日，按一天7个工作小时计
	 */

    public static float getWorkDays(String startDateTime, String endDateTime, ArrayList<String> holiday, ArrayList<String> workday){
        int result = 0;
        Date sdatetime;
        Date edatetime;
        try {
            sdatetime = sdfYMDHM.parse(startDateTime.trim());
            edatetime = sdfYMDHM.parse(endDateTime.trim() );
            Calendar date1 = Calendar.getInstance();
            Calendar date2 = Calendar.getInstance();

            date1.setTimeInMillis(sdatetime.getTime());
            date2.setTimeInMillis(edatetime.getTime());

            while (date1.before(date2)) {
//				while(!isWorkingDay(date1,holiday,workday)){
//					date1.add(Calendar.DAY_OF_YEAR,1);
//					date1.set(Calendar.HOUR,9);
//				}
                date1.add(Calendar.MINUTE, 5);

                if(isWorkingTime(date1,holiday,workday)){
                    result += 5;
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result/60.0f/7;
    }

    /*
     *
     */
    public static boolean dayBetweenDays(Calendar date, ArrayList<String> days) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (days.size() < 1)
            return false;
        Date d1 = null, d2 = null;
        Calendar date1 = Calendar.getInstance();
        Calendar date2 = Calendar.getInstance();
        try {
            for (String s : days) {
                d1 = sdf.parse(s + " 0:00:00");
                d2 = sdf.parse(s + " 23:59:59");

                date1.setTimeInMillis(d1.getTime());
                date2.setTimeInMillis(d2.getTime());
                if (date.before(date2) && date.after(date1))
                    return true;

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     *
     * 计算起、止时间的工作日 h 星期一至五：休假日 w 星期六或星期天工作日
     */
    public static float getDutyDays(ArrayList<String> holidays, ArrayList<String> workdays, String start, String end) {
        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // SimpleDateFormat sdf = new SimpleDateFormat(format);

        Date sdate;
        Date edate;
        try {
            sdate = sdf.parse(start.trim() + ":00");
            edate = sdf.parse(end.trim() + ":00");
            Calendar date1 = Calendar.getInstance();
            Calendar date2 = Calendar.getInstance();

            date1.setTimeInMillis(sdate.getTime());
            date2.setTimeInMillis(edate.getTime());

            while (date1.before(date2)) {
                date1.add(Calendar.MINUTE, 10);
                if (date1.get(Calendar.DAY_OF_WEEK) != 1 && date1.get(Calendar.DAY_OF_WEEK) != 7) {
                    result += 10;
                    if (dayBetweenDays(date1, holidays)) {// 虽然是星期一到星期五，但是并非工作日
                        result -= 10;
                    }
                } else {
                    if (dayBetweenDays(date1,workdays))// 虽然是周六或周日，但是是工作日
                        result += 10;
                }
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }
        // System.out.println(result);
        float f = result / 60f / 24;
        return f;
    }
    public static String formatDateTime(Date dateTime, boolean justDate) {
        if (isNull(dateTime)) return null;
        if (justDate) return DefaultDateFormat.format(dateTime);
        return DefaultDateTimeFormat.format(dateTime);
    }
    public static String formatTime(Time dateTime) {
        if (isNull(dateTime)) return null;
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        String dateString = formatter.format(dateTime);
        return dateString;
    }
    public static String formatDateTime(Date dateTime, boolean justDate, boolean noNULL) {
        if (isNull(dateTime) && noNULL) return "";
        if (dateTime == null) return null;
        if (justDate) return DefaultDateFormat.format(dateTime);
        return DefaultDateTimeFormat.format(dateTime);
    }
    public static void getWorkHoliday(List<String> p_holidays, List<String> p_workdays){
        File file = new File("./holiday.txt");// Text文件
        BufferedReader br = null;
        if(p_holidays==null)p_holidays = new ArrayList<String>();
        if(p_workdays==null)p_workdays = new ArrayList<String>();
        try {
            br = new BufferedReader(new FileReader(file));

            String day = "";

            while ((day = br.readLine()) != null) {
                p_holidays.add(day);
            }
            br.close();
            file = new File("./workday.txt");// Text文件
            br = new BufferedReader(new FileReader(file));
            while ((day = br.readLine()) != null) {
                p_workdays.add(day);
            }
            // br.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 格式化ids，用于sql条件查询in
     * @param arr 数组
     * @param isAddQuotationMark 是否添加引号
     * @param splitStr 分隔符
     * @return
     */
    public static String formatIds(String[] arr,boolean isAddQuotationMark,String splitStr){
        if(arr==null)return null;
        StringBuilder ids= new StringBuilder();
        for(String s:arr){
            if(isAddQuotationMark){
                ids.append("'").append(s).append("'").append(splitStr);
            }else
                ids.append(s).append(splitStr);
        }
        if(ids.substring(ids.length()-splitStr.length()).equals(splitStr))
            return ids.substring(0,ids.length()-splitStr.length());
        return ids.toString();
    }
    public static String getSqlFielStatement(String type, Object o){
        String result="";
        if(type.equals("datetime") || type.equals("date")){
            if(o==null){
                result="null";
            }else {
                result="'"+ formatDateTime((Date)o,false)+"'";
            }
        } if(type=="time"){
            if(o==null){
                result="null";
            }else {
                result="'"+ formatTime((Time) o)+"'";
            }
        }else if(type=="string") {
            if(o==null){
                result="null";
            }else{
                String s=o.toString();
                if(s.indexOf("'")>=0){
                    result="'"+s.replaceAll("'","\\\\'")+"'";
                }else if(s.indexOf("\\")>=0){
                    result="'"+s.replaceAll("\\\\","")+"'";
                }else
                    result="'"+s+"'";
            }
        }else{

        }
        return result;
    }
    public static boolean isFileExists(String path){
        File file =new File(path);
        return file.exists();
    }
    /**
     * emoji表情替换
     *
     * @param source 原字符串
     * @param slipStr emoji表情替换成的字符串
     * @return 过滤后的字符串
     */
    public static String filterEmoji(String source, String slipStr) {
        if(!isNullOrEmpty(source)){
            return source.replaceAll("[\\ud800\\udc00-\\udbff\\udfff\\ud800-\\udfff]", slipStr);
        }else{
            return source;
        }
    }

    /**
     * 检测文件是否存在
     * @param filePath
     * @return
     */
    public static boolean File_Exists(String filePath) {
        if(Utility.isNullOrEmpty(filePath))return false;
        return new File(filePath).exists();
    }

    public static boolean File_Mkdir(String filePath){
        return new File(filePath).mkdir();
    }
    public static boolean File_Mkdirs(String filePath){
        return new File(filePath).mkdirs();
    }
    public static boolean File_Move(File file, String parentPath) throws Exception
    {
        //boolean r=File_Copy(file, parentPath);
        //file.delete();
        //return r;
        File parentFile=new File(parentPath);

        //FileUtils.moveFileToDirectory(file, new File(parentPath), !parentFile.exists());
        //boolean r = File_Copy(file, targetFile);
        //file.delete();
        return true;
    }
    public static boolean File_Move(File file, File targetFile) throws Exception {
        if(targetFile.exists()) targetFile.delete();
        try {
            //FileUtils.copyFile(file, targetFile);
            boolean r = File_Copy(file, targetFile);
        }
        catch (Exception error){
        }    finally {
            file.delete();
        }
        //boolean r = File_Copy(file, targetFile);
        //file.delete();
        return true;
    }

    /**
     * 复制文件到目标的目录下，保持文件名相同
     * @param file
     * @param parentPath
     * @return
     * @throws Exception
     */
    public static Boolean File_Copy(File file, String parentPath) throws Exception {
        String name = file.getName();
        String targetPath = Path_Combine(parentPath, name);
        File newFile = new File(targetPath);
        return File_Copy(file, newFile);
    }

    /**
     * 复制文件到目标文件路径，如果目标存在，则删除掉再新建
     * @param file
     * @param targetFile
     * @return
     * @throws Exception
     */
    public static boolean File_Copy(File file, File targetFile) throws Exception {
        boolean flag = false;
        if (file.exists()) {
            FileInputStream inputStream = new FileInputStream(file);
            return File_WriteAllBytes(targetFile, inputStream);
        }
        return flag;
    }

    /**
     *
     * @param targetFile
     * @param sourceStream
     * @return
     * @throws Exception
     */
    public static boolean File_WriteAllBytes(File targetFile, InputStream sourceStream) throws Exception
    {
        boolean flag = false;
        File newDir = targetFile.getParentFile();
        if (newDir.isDirectory()==false&&newDir.exists())
            newDir.delete();
        if (!newDir.exists()) {
            newDir.mkdirs();
            newDir.setExecutable(true);
            newDir.setReadable(true);
            newDir.setWritable(true);
        }
        //String name = file.getName();
        //File newFile = new File(targetPath);
        if (targetFile.exists()) targetFile.delete();
        //targetFile.createNewFile();
        FileOutputStream stream = new FileOutputStream(targetFile, false);
        try {
            InputStream inputStream = sourceStream;
            try {
                byte[] buffer = new byte[20480];
                int len;
                len = inputStream.read(buffer, 0, buffer.length);
                while (len > 0) {
                    stream.write(buffer, 0, len);
                    len = inputStream.read(buffer, 0, buffer.length);
                }
            } finally {
                inputStream.close();
            }
        } finally {
            stream.close();
        }
        return flag;
    }
    /**
     * write file
     *
     * @param fname
     * @param data
     * @return
     */
    public static Boolean File_WriteAllText(String fname, String data) {
        boolean result = false;
        File file = new File(fname);
        BufferedWriter bw = null;
        try {
            file.setExecutable(true);
            if (!file.exists()) {
                file.createNewFile();
            }
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), Utility.CHARSET_DEFAULT));
            bw.write(data);
            bw.flush();
            result = true;
        } catch (IOException e) {
        } finally {
            if (null != bw) {
                try {
                    bw.close();
                } catch (IOException e) {
                }
            }
        }
        return result;
    }
    public static Boolean File_WriteAppendText(String fname, String data){
        boolean result = false;
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(fname, true);
            try {
                writer.write(data);
            }
            finally {
                writer.close();
                writer=null;
            }
            result=true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * read file
     *
     * @param path
     * @return
     */
    public static String File_ReadAllText(String path) {
        return File_ReadAllText(new File(path));
    }

    /**
     * read file
     *
     * @param file
     * @return
     */
    public static String File_ReadAllText(File file) {
        StringBuffer content = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
            try {
                String line = null;
                while ((line = br.readLine()) != null) {
                    content.append(line);
                }
            } finally {
                br.close();
            }
        } catch (FileNotFoundException e) {
        } catch (UnsupportedEncodingException e) {
        } catch (IOException e) {
        } finally {
            br=null;
        }
        return content.toString();
    }

    public static boolean File_Delete(String filePath) {
        File file = new File(filePath);
        if (file.isFile() && file.exists())
            return file.delete();
        return false;
    }

    public static boolean File_Delete(String[] searchPatterns, String parentDirectory){
        File file=new File(parentDirectory);
        return File_Delete(searchPatterns, file);
    }

    public static boolean File_Delete(final String[] searchPattern, File parentDirectory) {
        File[] files = parentDirectory.listFiles(new FileFilter() {
            @Override
            public boolean accept(File pathname) {
                if (!pathname.isFile()) return false;
                if (searchPattern == null || searchPattern.length == 0) return true;
                String filename = pathname.toString();
                for (String sP : searchPattern) {
                    if (filename.contains(sP)) return true;
                }
                return false;
            }
        });
        if (files != null && files.length > 0)
            for (int i = 0; i < files.length; i++) {
                files[i].delete();
            }
        return true;
    }

    public static boolean Directory_Delete(String filePath, boolean force)
    {
        File file=new File(filePath);
        return Directory_Delete(file, force);
    }

    public static boolean Directory_Delete(File file, boolean force) {
        if (!file.isDirectory()||!file.exists()) return false;
        File[] childFiles = file.listFiles();
        if (childFiles == null || childFiles.length == 0) {
            file.delete();
            return true;
        }
        if (force) {
            for (int i = 0; i < childFiles.length; i++) {
                Directory_Delete(childFiles[i], force);
            }

            file.delete();
            return true;
        }
        return false;
    }

    /**
     * 检测目录是否存在, 如果是文件也代表不存在
     * @param dirPath
     * @return
     */
    public static boolean Directory_Exists(String dirPath) {
        File dir= new File(dirPath);
        if (dir.isDirectory()==false) return false;
        return dir.exists();
    }
    public static String Path_GetFileName(String path) {
        if (path == null || Utility.isNullOrEmpty(path)) return null;
        int index = path.lastIndexOf("/");
        if (index >= 0) path = path.substring(index + 1);
        else {
            index = path.lastIndexOf("\\");
            if (index >= 0) path = path.substring(index + 1);
        }
        index = path.lastIndexOf(".");
        if (index >= 0) path = path.substring(0, index);
        return path;
    }

    /**
     * 获取文件的后缀名
     * @param path
     * @return
     */
    public static String Path_GetExtension(String path) {
        if (path == null || Utility.isNullOrEmpty(path)) return null;
        int index = path.lastIndexOf(".");
        if (index >= 0) path = path.substring(index + 1);
        return "";
    }

    /**
     * 合并两段路径
     * @param left
     * @param right
     * @return
     */
    public static String Path_Combine(String left, String right) {
        if (Utility.isNullOrEmpty(left)) return right;
        if (Utility.isNullOrEmpty(right)) return left;
        char ch1 = left.charAt(left.length() - 1),
                ch2 = right.charAt(0),
                ch = File.separatorChar;
        if (ch1 == ch2 && ch2 == ch) return left + right.substring(1);
        if (ch1 == ch || ch2 == ch) return left + right;
        return left + ch + right;
    }

    public static String Url_Combine(String left, String right){
        String url=Path_Combine(left,right);
        url=url.replace(File.separatorChar,'/');
        url=url.replaceAll("//","/");
        if((url.indexOf("http://")<0)&&
                (url.indexOf("http:/")==0)){
            url=url.replaceFirst("http:/","http://");
        }
        return url;
    }
    /**
     * 转化回日期类型，请注意不是所有的格式都能够进行转化
     * @param text
     * @return
     */
    public static Date parseDate(String text) {
        if (Utility.isNullOrEmpty(text)) return null;
        int len=text.length();
        int i=0;
        int dashCount=0;
        int pamaCount=0;
        int spaceCount=0;
        StringBuilder stringBuffer=new StringBuilder(len);
        for(i=0;i<len;i++){
            char ch=text.charAt(i);
            if(ch=='\t'||ch=='\r'||ch=='\n') continue;
            if(ch==' '&&stringBuffer.length()<=0) continue;
            if(ch==' '&&dashCount<2) continue;
            if(ch=='/'){
                dashCount++;
                stringBuffer.append('-');
            }
            else if(ch=='.'&&dashCount<2){
                dashCount++;
                stringBuffer.append('-');
            }
            else {
                stringBuffer.append(ch);
                if (ch == ':') pamaCount++;
                else if (ch == '-') dashCount++;
            }
        }
        text=stringBuffer.toString();
        if(pamaCount<=0){
            try{
                return DefaultDateFormat.parse(text);
            }
            catch (Exception error) {
            }
            try{
                return DefaultDate.parse(text);
            }
            catch (Exception error) {
            }
            try{
                return DefaultYearMonthFormat.parse(text);
            }
            catch (Exception error) {
            }
            try{
                return DefaultTimestampFormat.parse(text);
            }
            catch (Exception error) {
            }
        }
        try {
            return DefaultDateTimeFormat.parse(text);
        } catch (Exception error) {
        }
        try {
            return DefaultDateMinuteFormat.parse(text);
        } catch (Exception error) {
        }
        try {
            return DefaultDateMinuteFormatSpan.parse(text);
        } catch (Exception error) {
        }
        return null;
    }

    /**
     * 格式化一个数字
     *
     * @param num
     * @param pad
     * @return
     */
    public static String formatPadNumber(int num, int pad) {
        return String.format("%0" + pad + "d", num);
    }

    public static String createUniqueId() {
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    }

    public static String getDirection(Integer angle) {
        String direct = "";
        if (angle >= 0 && angle <= 360) {
            Integer index = angle / 90;
            Integer mod = angle % 90;
            index = (index == 4) ? 0 : index;

            String[] strArray1 = {"正北", "正东", "正南", "正西", "正北"};
            String[] strArray2 = {"北", "东", "南", "西", "北"};
            if (mod == 0) {
                direct = strArray1[index];
            } else if (mod > 0) {
                direct = strArray2[index] + "偏" + strArray2[index + 1] + mod + "度";
            }
        }
        return direct;
    }


    public static void doCompress(File file, String fileName, ZipOutputStream out) throws IOException {
        if (file.exists()) {
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(file);
            out.putNextEntry(new ZipEntry(fileName));
            int len = 0;
            // 读取文件的内容,打包到zip文件
            while ((len = fis.read(buffer)) > 0) {
                out.write(buffer, 0, len);
            }
            out.flush();
            out.closeEntry();
            fis.close();
        }
    }
    public static String getFileExtension(String path,boolean bhd) {
        int index = path.lastIndexOf(".");
        if(index >= 0) {
            String ext = path.substring(index+1);
            if(bhd)ext=path.substring(index);
            return ext.indexOf(47) >= 0?null:ext;
        } else {
            return null;
        }
    }
    public static String getFileNameExtension(String fileName) {
        if(isNullOrEmpty(fileName))return fileName;
        String prefix=fileName.substring(fileName.lastIndexOf("."));
        int num=prefix.length();//得到后缀名长度
       return fileName.substring(0, fileName.length()-num);//得到文件名。去掉了后缀
    }
    public static String createRfc2231HeaderValue(String filename) {
        StringBuilder builder = new StringBuilder("attachment; filename*=UTF-8\'\'");

        try {
            byte[] error = filename.getBytes("UTF-8");
            byte[] arr$ = error;
            int len$ = error.length;

            for(int i$ = 0; i$ < len$; ++i$) {
                byte b = arr$[i$];
                if(isByteValidHeaderValueCharacter(b)) {
                    builder.append((char)b);
                } else {
                    addByteToStringBuilder(b, builder);
                }
            }
        } catch (Exception var7) {
            var7.printStackTrace();
        }

        return builder.toString();
    }
    private static boolean isByteValidHeaderValueCharacter(byte b) {
        if(48 <= b && b <= 57) {
            return true;
        } else if(97 <= b && b <= 122) {
            return true;
        } else if(65 <= b && b <= 90) {
            return true;
        } else {
            switch(b) {
                case 33:
                case 36:
                case 38:
                case 43:
                case 45:
                case 46:
                case 58:
                case 95:
                case 126:
                    return true;
                default:
                    return false;
            }
        }
    }
    private static void addByteToStringBuilder(byte b, StringBuilder builder) {
        builder.append('%');
        int i = b;
        if(b < 0) {
            i = 256 + b;
        }

        addHexDigitToStringBuilder(i / 16, builder);
        addHexDigitToStringBuilder(i % 16, builder);
    }
    private static void addHexDigitToStringBuilder(int digit, StringBuilder builder) {
        builder.append(HexChars[digit]);
    }
    public static boolean createFileIfNotExists(String path) throws IOException {
        if(!File_Exists(path)){
            File file=new File(path);
            String dir=file.getParent();
            File directory=new File(dir);
            directory.mkdirs();
            file.createNewFile();
        }
        return true;
    }

    /**
     * 获取md5字符串
     *
     * @param str
     * @return
     */
    public static String getMD5String(String str) {
        try {
            byte[] strTemp = str.getBytes();
            MessageDigest mdTemp = MessageDigest.getInstance("MD5");
            mdTemp.update(strTemp);
            byte[] md = mdTemp.digest();
            int j = md.length;
            char schar[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                schar[k++] = hexDigits[byte0 >>> 4 & 0xf];
                schar[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(schar);
        } catch (Exception e) {
            return null;
        }
    }

    public static String[] getSeparatedItems(String text, char sp) {
        return isNullOrEmpty(text) ? new String[0] : getSeparatedItems(text, new char[]{sp});
    }
    public static String[] getSeparatedItems(String text, char[] sp) {
        if (isNullOrEmpty(text)) {
            return new String[0];
        } else {
            if (sp == null || sp.length <= 0) {
                sp = DEFAULT_SPLIT_CHARS;
            }

            List<String> list = new ArrayList(10);
            StringBuffer sb = new StringBuffer();
            int size = text.length();
            boolean has = false;

            for(int i = 0; i < size; ++i) {
                has = false;

                for(int j = 0; j < sp.length; ++j) {
                    if (text.charAt(i) == sp[j]) {
                        has = true;
                        break;
                    }
                }

                if (has) {
                    if (sb.length() > 0) {
                        list.add(sb.toString());
                        sb.setLength(0);
                    }
                } else {
                    sb.append(text.charAt(i));
                }
            }

            if (sb.length() > 0) {
                list.add(sb.toString());
            }

            String[] ss = new String[list.size()];

            for(int i = 0; i < ss.length; ++i) {
                ss[i] = (String)list.get(i);
            }

            return ss;
        }
    }

    public static boolean Stream_Copy(InputStream in, OutputStream out) {
        byte[] bytes = new byte['ꀀ'];

        try {
            boolean var3 = false;

            int len;
            do {
                len = in.read(bytes, 0, bytes.length);
                if (len > 0) {
                    out.write(bytes, 0, len);
                }
            } while(len > 0);
        } catch (Exception var5) {
            return false;
        }

        try {
            out.flush();
        } catch (Exception var4) {
            ;
        }

        return true;
    }

    /**
     * 获得指定文件的byte数组
     */
    private byte[] getBytes(String filePath){
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
    public static byte[] getBytes(File file){
        byte[] buffer = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
    /**
     * 根据byte数组，生成文件
     */
    public static void getFile(byte[] bfile, String filePath,String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if(!dir.exists()&&dir.isDirectory()){//判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(filePath+"\\"+fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bfile);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
