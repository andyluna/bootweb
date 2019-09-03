package com.andy.maven;

import org.junit.Test;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.type.StandardAnnotationMetadata;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.lang.annotation.Annotation;
import java.util.Map;

/**
 * @name : springstudy  com.andy.maven
 * @desc :
 * @author: xiangdan
 * @time : 2019-02-25 Monday 14:44
 */
public class MetaDataTest {


	@Test
	public void test1(){
		StandardAnnotationMetadata metadata = new StandardAnnotationMetadata(
				MyJpaRepositoryConfig.class, true);

		Class<? extends Annotation> annotation=  getAnnotation();
		System.out.println(annotation.getName());
		System.out.println(annotation.getClass());
		System.out.println(annotation.getTypeName());
		Map<String, Object> attributes = metadata.getAnnotationAttributes(annotation.getName());

		attributes.forEach((k,v)->{
			System.out.println(k+" = "+v);
		});


		AnnotationAttributes annotationAttributes = new AnnotationAttributes(attributes);

		annotationAttributes.forEach((k,v)->{
			System.out.println(k+" = "+v);
		});

	}

	@EnableJpaRepositories
	public static class MyJpaRepositoryConfig{

	}


	public Class<? extends Annotation> getAnnotation() {
		return EnableJpaRepositories.class;
	}


}
