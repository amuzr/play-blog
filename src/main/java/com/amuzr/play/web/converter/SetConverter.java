package com.amuzr.play.web.converter;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.core.convert.converter.Converter;

import com.amuzr.play.domain.Tag;

public class SetConverter {
	private SetConverter() {}
	
	public static class SetToArrayList implements Converter<Set<Tag>, ArrayList<Tag>> {
		public ArrayList<Tag> convert(Set<Tag> tags) {
			return new ArrayList<Tag>(tags);
		}
	}

}
