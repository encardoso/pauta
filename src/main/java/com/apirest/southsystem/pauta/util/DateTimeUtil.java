package com.apirest.southsystem.pauta.util;

import java.time.LocalDateTime;
import java.time.ZoneId;

public class DateTimeUtil {
	
	private DateTimeUtil() {
		// TODO Auto-generated constructor stub
	}
	
	public static LocalDateTime getCurrentLocalDateTime() {
		LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
		return now;
	}
	
	
//	public static void main(String[] args) {
//		LocalDateTime ldt = LocalDateTime.now();
//		ZoneId zoneId = ZoneId.of("America/Sao_Paulo");
//		ZonedDateTime zdt = ZonedDateTime.of(ldt, zoneId);
//		System.out.println(ldt);
//		System.out.println(zdt.toString());
//		System.out.println(getCurrentLocalDateTime());
//	}

}
