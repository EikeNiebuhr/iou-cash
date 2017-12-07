package iou.api.util;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONMapper<Type>
{
	public String objectToJSON(Type object) throws IOException
	{
		return new ObjectMapper().writeValueAsString(object);
	}
	
	public Type jsonToObject(String json, Class<Type> typeParameterClass) throws IOException
	{
		return new ObjectMapper().readValue(json, typeParameterClass);
	}
}
