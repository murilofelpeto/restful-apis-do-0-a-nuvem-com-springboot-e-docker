package br.com.murilo.converter;

import java.util.ArrayList;
import java.util.List;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class DozerConverter {

	private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();
	
	public static <O, T> T parseObject(O origin, Class<T> target) {
		return mapper.map(origin, target);
	}
	
	public static <O, T> List<T> parseListObjects(List<O> origin, Class<T> target) {
		List<T> targetObjects = new ArrayList<T>();
		
		for (Object o : origin) {
			targetObjects.add(mapper.map(o, target));
		}		
		return targetObjects;
	}
}
