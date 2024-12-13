package com.hana4.board.utill;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestUtils {
	public static String toJsonString(final Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
