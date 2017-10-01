package com.dgut.service.exception;

/**
 * @ClassName: ServiceException
 * @Description: com.dgut.service所有类抛出的运行时异常类的父类
 * @author 闲明苑
 * @date 2017年9月25日 上午9:39:07
 */
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceException(String message) {
		super(message);
	}

}
