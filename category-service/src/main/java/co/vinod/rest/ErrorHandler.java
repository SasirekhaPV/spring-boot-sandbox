package co.vinod.rest;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {

	@ExceptionHandler
	public ResponseEntity<Map<String, Object>> error(Exception ex) {
		Map<String, Object> map = new LinkedHashMap<String, Object>();

		if (ex != null) {
			map.put("message", ex.getMessage());
		} else {
			map.put("message", "Something went wrong");
		}
		map.put("timestamp", new Date().toString());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(map);
	}
}
