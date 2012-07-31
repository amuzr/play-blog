package com.amuzr.play.web.converter;

import org.springframework.core.convert.converter.Converter;

import com.amuzr.play.domain.Pager;

public class PagerConverter {
	private PagerConverter() {}
	
	public static class IntegerToPager implements Converter<Integer, Pager> {
		public Pager convert(Integer i) {
			Pager pager = new Pager();
			pager.setCurPage(i);
			return pager;
		}
	}

}
