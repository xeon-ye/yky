package com.deloitte.platform.common.web.feign;


import feign.RequestTemplate;
import feign.codec.EncodeException;
import feign.codec.Encoder;
import feign.form.FormEncoder;
import feign.form.spring.SpringMultipartEncodedDataProcessor;
import lombok.val;
import org.springframework.web.multipart.MultipartFile;

import java.lang.reflect.Type;
import java.util.*;

public class SpringMultipartEncoder extends FormEncoder {

	private final Encoder delegate;

	public SpringMultipartEncoder() {
		this(new FeignSpringFormEncoder());
	}

	public SpringMultipartEncoder(Encoder delegate) {
		this.delegate = delegate;
	}

	
	@Override
	public void encode(Object object, Type bodyType, RequestTemplate template) throws EncodeException {
		if (object instanceof MultipartFile) {
			MultipartFile file = (MultipartFile) object;
			Map<String, Object> data = Collections.singletonMap(file.getName(), object);
			new SpringMultipartEncodedDataProcessor().process(data, template);
		}
		else if (bodyType.equals(MultipartFile[].class)) {
			val file = (MultipartFile[]) object;
			if(file != null) {
				val data = Collections.singletonMap(file.length == 0 ? "" : file[0].getName(), object);
				delegate.encode(data, Encoder.MAP_STRING_WILDCARD, template);
			}
		}
		else {
			delegate.encode(object, bodyType, template);
			return;
		}

	}

}
