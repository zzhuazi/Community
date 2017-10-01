package com.dgut.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.dgut.interceptor.AdminInterceptor;
import com.dgut.interceptor.UserInterceptor;

/**
 * @ClassName: MyWebMvcConfigurerAdapter
 * @author 闲明苑
 * @date 2017年9月25日 下午8:51:38
 */
@Configuration
public class MyWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

	/**
	 * 静态资源处理
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// 不过滤templates/js、img、fonts文件下的文件
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/templates/css/");
		registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/templates/fonts/");
		registry.addResourceHandler("/img/**").addResourceLocations("classpath:/templates/img/");
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/templates/js/");
		// 后台
		registry.addResourceHandler("/admin/lib/**").addResourceLocations("classpath:/templates/admin/lib/");
		registry.addResourceHandler("/admin/images/**").addResourceLocations("classpath:/templates/admin/images/");
		registry.addResourceHandler("/admin/js/**").addResourceLocations("classpath:/templates/admin/js/");
		registry.addResourceHandler("/admin/stylesheets/**")
				.addResourceLocations("classpath:/templates/admin/stylesheets/");
	}

	/**
	 * 拦截器处理
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 添加404/503等错误处理拦截器
		// ErrorInterceptor errorInterceptor = new ErrorInterceptor();
		// registry.addInterceptor(errorInterceptor);
		registry.addInterceptor(getUserInterceptor());
		registry.addInterceptor(getAdminInterceptor()).addPathPatterns("/admin/**");
	}

	/**
	 * @Description: 不可以再addInterceptors 方法中直接new 一个对象
	 * 否则不能自动装配UserInterceptor的属性@Autowired
	 */
	@Bean
	public UserInterceptor getUserInterceptor() {
		return new UserInterceptor();
	}

	@Bean
	public AdminInterceptor getAdminInterceptor() {
		return new AdminInterceptor();
	}

}
