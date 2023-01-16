package dev.jeremidigitallab.learn.dto;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SimpleResponse {

	private Integer status;
	private String message;
	private Map<String, Object> payload;

	public static ResponseEntity<SimpleResponse> ok(Map<String, Object> payload) {
		return ResponseEntity
				.status(HttpStatus.OK.value())
				.body(SimpleResponse.builder()
						.status(HttpStatus.OK.value())
						.message(HttpStatus.OK.name())
						.payload(payload)
						.build());
	}

	public static ResponseEntity<SimpleResponse> created(Map<String, Object> payload) {
		return ResponseEntity
				.status(HttpStatus.CREATED.value())
				.body(SimpleResponse.builder()
						.status(HttpStatus.CREATED.value())
						.message(HttpStatus.CREATED.name())
						.payload(payload)
						.build());
	}
}
