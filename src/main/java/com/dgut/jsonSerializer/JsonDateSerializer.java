package com.dgut.jsonSerializer;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @ClassName: JsonDateSerializer
 * @Description: jackson 时间格式序列化器
 * @author 闲明苑
 * @date 2017年9月29日 下午1:56:38
 */
public class JsonDateSerializer extends JsonSerializer<Date> {

	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public void serialize(Date date, JsonGenerator generator, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		String value = dateFormat.format(date);
		generator.writeString(value);
	}

}
