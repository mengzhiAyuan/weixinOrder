package com.mengzhiayuan.naruto.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * @author ：mengzhiayuan
 * @description：TODO
 * @date ：2021/7/22 19:05
 */
//统一所有时间格式 穿给前端的时间是毫秒数 long  而数据库时间格式为 ：date2021-07-21 15:09:08

public class DateShiftLong extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(date.getTime());
    }
}
