
package com.trans.agent.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * The Class JsonUtil.
 */
public class JsonUtil {
	/** The instance. */
    private volatile static JsonUtil instance;
    private ObjectMapper mapper = null;

    /**
	 * Gets the single instance of JsonUtil.
	 *
	 * @return single instance of JsonUtil
	 */

    public static JsonUtil inst() {
		if(instance == null) {
			synchronized (JsonUtil.class) {
				if(instance == null) {
					instance = new JsonUtil();
				}
			}
		}
		return instance;
	}

    public JsonUtil() {
    	mapper = new ObjectMapper();
    }

	/**
	 * To json object.
	 *
	 * @param response
	 *            the response
	 * @return the JSON object
	 */
	public JSONObject toJsonObject(String response) {
		JSONObject obj = null;
		try {
			obj = JSONObject.fromObject(response);
		} catch (JSONException e) {
			throw new JSONException(e);
		}
		return obj;
	}

	/**
	 * Gets the json string.
	 *
	 * @param obj the obj
	 * @param key the key
	 * @return the json string
	 */
	public String getJsonString(JSONObject obj, String key) {
		String result = null;
		try {
			if (obj.has(key)) {
					result = obj.getString(key);
			}
		} catch (JSONException e) {
			throw new JSONException(e);
		}
		return result;
	}

	public String getJsonString(Object obj){
//		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			throw new JSONException(e);
		}
	}

	public <T> T getJsonReadValue(String paramString, Class<T> paramClass){
//		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(paramString, paramClass);
		} catch (IOException e) {
			throw new JSONException(e);
		}
	}

	public <T> T getJsonReadValueWeak(String paramString, Class<T> paramClass){
//		ObjectMapper mapper = new ObjectMapper();
		try {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(paramString, paramClass);
		} catch (IOException e) {
			throw new JSONException(e);
		} finally {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		}
	}

	public <T> T getJsonReadValue(String paramString, TypeReference<?> paramTypeReference){
//		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(paramString, paramTypeReference);
		} catch (IOException e) {
			throw new JSONException(e);
		}
	}

	public <T> T getJsonStringToReadValue(Object obj, Class<T> paramClass){
		String json = getJsonString(obj);
		return getJsonReadValue(json, paramClass);
	}
	/**
	 * Gets the json object.
	 *
	 * @param obj the obj
	 * @param key the key
	 * @return the json object
	 */
	public JSONObject getJsonObject(JSONObject obj, String key) {
		JSONObject result = null;
		try {
			if (obj.has(key)) {
				result = obj.getJSONObject(key);
			}
		} catch (JSONException e) {
			throw new JSONException(e);
		}
		return result;
	}

	/**
	 * Gets the json long.
	 *
	 * @param obj the obj
	 * @param key the key
	 * @return the json long
	 */
	public long getJsonLong(JSONObject obj, String key) {
		long result = 0;
		try {
			if (obj.has(key)) {
				result = obj.getLong(key);
			}
		} catch (JSONException e) {
			throw new JSONException(e);
		}

		return result;
	}
}
