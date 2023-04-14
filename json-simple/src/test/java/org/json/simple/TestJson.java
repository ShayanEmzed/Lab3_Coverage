/*
 * $Id: Test.java,v 1.1 2006/04/15 14:40:06 platform Exp $
 * Created on 2006-4-15
 */
package org.json.simple;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Test;

public class TestJson {

	@Test
	public void testDecode() throws Exception{
		System.out.println("=======decode=======");

		String s="[0,10]";
		Object obj=JSONValue.parse(s);
		JSONArray array=(JSONArray)obj;
		System.out.println("======the 2nd element of array======");
		System.out.println(array.get(1));
		System.out.println();
		assertEquals("10",array.get(1).toString());
	}

	@Test
	public void testJSONArrayCollection() {
		final ArrayList<String> testList = new ArrayList<String>();
		testList.add("First item");
		testList.add("Second item");
		final JSONArray jsonArray = new JSONArray(testList);

		assertEquals("[\"First item\",\"Second item\"]", jsonArray.toJSONString());
	}

	@Test
	public void testEncodingJSONObject() throws Exception {
		final JSONObject obj = new JSONObject();
		obj.put("Name", "Shayan Mohammadizadeh");
		obj.put("Awake", true);
		StringWriter out = new StringWriter();
		obj.writeJSONString(out);
		String jsonText = out.toString();

		assertEquals("{\"Awake\":true,\"Name\":\"Shayan Mohammadizadeh\"}", jsonText);
	}

	@Test
	public void testErrorHandling() {
		String jsonText = "[98102273}, \"Shayan\"]";
		JSONParser parser = new JSONParser();

		String msg = "";
		try {
			parser.parse(jsonText);
		} catch (ParseException pe) {
			msg = pe.getMessage();
		}

		assertEquals("Unexpected token RIGHT BRACE(}) at position 9.", msg);
	}

	@Test
	public void testCharListJSONString() {
		Character[] testList = {'a', 'b'};
		JSONArray jArr = new JSONArray();
		jArr.add('a');
		jArr.add('b');

		assertEquals(jArr.toJSONString(), JSONArray.toJSONString(testList));
	}
}
