package com.andy.maven.primary.repository;

import com.andy.maven.primary.entity.Car;
import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;

/**
 * @name : springstudy  com.andy.maven.primary.entity.${CLASS_NAME}
 * @desc :
 * @author: xiangdan
 * @time : 2019-02-19 Tuesday 18:51
 */
public interface CarRepository extends JpaRepositoryImplementation<Car,Integer > {

}
