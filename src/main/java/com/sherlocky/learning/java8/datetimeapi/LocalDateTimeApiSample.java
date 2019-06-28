package com.sherlocky.learning.java8.datetimeapi;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Java 8通过发布新的Date-Time API (JSR 310)来进一步加强对日期与时间的处理。
 *
 * 在旧版的 Java 中，日期时间 API 存在诸多问题:
 * - 1.非线程安全 − java.util.Date 是非线程安全的，所有的日期类都是可变的，这是Java日期类最大的问题之一。
 * - 2.设计很差 − Java的日期/时间类的定义并不一致，在java.util和java.sql的包中都有日期类，此外用于格式化和解析的类在java.text包中定义。java.util.Date同时包含日期和时间，而java.sql.Date仅包含日期，将其纳入java.sql包并不合理。另外这两个类都有相同的名字，这本身就是一个非常糟糕的设计。
 * - 3.时区处理麻烦 − 日期类并不提供国际化，没有时区支持，因此Java引入了java.util.Calendar和java.util.TimeZone类，但他们同样存在上述所有的问题。
 *
 * Java 8 在 java.time 包下提供了很多新的 API。以下为两个比较重要的 API：
 *
 * - 1.Local(本地) − 简化了日期时间的处理，没有时区的问题。
 * - 2.Zoned(时区) − 通过制定的时区处理日期时间。
 *
 * 新的java.time包涵盖了所有处理日期，时间，日期/时间，时区，时刻（instants），过程（during）与时钟（clock）的操作。
 *
 * @author: zhangcx
 * @date: 2018/11/15 16:30
 */
public class LocalDateTimeApiSample {

    public static void main(String[] args) {
        // 1.本地化日期时间 API： LocalDate/LocalTime 和 LocalDateTime 类可以在处理时区不是必须的情况下使用
        // 1.1 获取当前的日期时间
        System.out.println("======1.1 获取当前的日期时间======");
        LocalDateTime currentTime = LocalDateTime.now();
        System.out.println(currentTime);

        LocalDate date1 = currentTime.toLocalDate();
        System.out.println(date1);
        int year = currentTime.getYear();
        Month month0 = currentTime.getMonth();
        int month = currentTime.getMonthValue();
        int day = currentTime.getDayOfMonth();
        int hour = currentTime.getHour();
        int minute = currentTime.getMinute();
        int second = currentTime.getSecond();
        System.out.println(year + "年" + month + "月" + day + "日 " + hour + "时" + minute + "分" + second + "秒");
        System.out.println(month0.getValue());

        // 1.2 修改日期时间
        System.out.println("======1.2 修改日期时间======");
        LocalDateTime date2 = currentTime.withDayOfMonth(10).withYear(2019);
        System.out.println("date2: " + date2);

        // 1.3 指定初始化日期时间
        System.out.println("======1.3 指定初始化日期时间======");
        // 25 december 2014
        LocalDate date3 = LocalDate.of(2014, 12, 25);
        System.out.println("date3: " + date3);
        LocalDate date3_ = LocalDate.of(2014, Month.DECEMBER, 25);
        System.out.println("date3_: " + date3_);
        // 11: 30
        LocalTime time1 = LocalTime.of(11, 30 , 0);
        System.out.println("time1: " + time1);
        LocalTime time2 = LocalTime.of(11, 30 , 12);
        System.out.println("time2: " + time2);
        // 解析字符串 11:30:25
        LocalTime time3 = LocalTime.parse("11:30:25");
        System.out.println("time3: "+time3);

        // 2.使用时区的日期时间API (需要考虑到时区的时候使用)
        // 2.1 获取当前时间日期
        System.out.println("======2.1 获取当前日期时间======");
        ZoneId currentZone = ZoneId.systemDefault();
        System.out.println("当前时区: " + currentZone);
        ZoneId zid = ZoneId.of("Europe/Paris");
        System.out.println("zoneId: " + zid);

        // parse的字符串必须表示有效的日期时间，并使用DateTimeFormatter.ISO_ZONED_DATE_TIME进行解析
        ZonedDateTime date1_ = ZonedDateTime.parse("2018-11-16T09:35:39+05:30[Asia/Shanghai]");
        System.out.println("date1_: " + date1_);
        // 等价于
        ZonedDateTime date2_ = ZonedDateTime.parse("2018-11-16T09:35:39+05:30[Asia/Shanghai]", DateTimeFormatter.ISO_ZONED_DATE_TIME);
        System.out.println("date2_: " + date2_);

        // 3.Instant 用于表示一个时间戳，可以精确到纳秒（Nano-Second）
        /**
         * Instant.ofEpochSecond()
         * 第一个参数为秒
         * 第二个参数为纳秒，上面的代码表示从 1970-01-01 00:00:00 开始后两分钟（120秒）的10万纳秒的时刻。
         */
        // 1970-01-01T00:02:00.000100Z
        Instant instant = Instant.ofEpochSecond(120, 100000);
        System.out.println("======3 Instant.ofEpochSecond() 和 Instant.now() ======");
        System.out.println(instant);
        System.out.println(Instant.now());

        // 4.Duration 内部实现与Instant类似，也是包含两部分：seconds表示秒，nanos表示纳秒。
        /**
         * 两者的区别是 Instant 用于表示一个时间戳（或者说是一个时间点），
         * 而Duration表示一个时间段，所以Duration类中不包含now()静态方法。可以通过 Duration.between() 方法创建 Duration 对象
         */
        LocalDateTime from = LocalDateTime.of(2019, Month.MAY, 1, 8, 0, 0);    // 2019-05-01 08:00:00
        LocalDateTime to = LocalDateTime.of(2019, Month.JUNE, 30, 10, 59, 59); // 2019-06-30 23:59:59
        Duration duration = Duration.between(from, to);     // 表示从 2019-05-01 08:00:00 到 2019-06-30 23:59:59 这段时间

        long days = duration.toDays();              // 这段时间的总天数
        long hours = duration.toHours();            // 这段时间的小时数
        long minutes = duration.toMinutes();        // 这段时间的分钟数
        long seconds = duration.getSeconds();       // 这段时间的秒数
        long milliSeconds = duration.toMillis();    // 这段时间的毫秒数
        long nanoSeconds = duration.toNanos();      // 这段时间的纳秒数
        System.out.println("======4 Duration.betweent() ======");
        System.out.println(String.format("总天数:%s,小时数：%s，分钟数：%s，秒数：%s，毫秒数：%s，纳秒数：%s。",
                days, hours, minutes, seconds, milliSeconds, nanoSeconds));
    }
}
