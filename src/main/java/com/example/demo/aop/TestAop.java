package com.example.demo.aop;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.aspectj.lang.ProceedingJoinPoint;

//@Aspect
//@Component
public class TestAop {

	// @Pointcut("execution(* com.example.demo.controller.TestController.test(..))")
	// public void sayings() {
	// }
	//

	@SuppressWarnings("unchecked")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		System.out.println("注解类型环绕通知..环绕前");
		// 获得所传参数
		Object[] objs = pjp.getArgs();

		Map<String, Object> map = new HashMap<String, Object>();

		for (Object object : objs) {
			if (object instanceof Map) {
				System.out.println("当前参数是map");
				map = (Map<String, Object>) object;
			}

		}

		System.out.println("获得所传参数--" + objs);
		Object obj = pjp.proceed();// 执行方法
		System.out.println("注解类型环绕通知..环绕后");

		Set<String> set = map.keySet();
		for (String key : set) {
			System.out.println("key==" + key);
			System.out.println("value==" + map.get(key));
			// 如果存在key==x 且 key==0
			if ("x".equals(key) && "0".equals(map.get(key))) {
				return obj;
				// 如果存在key==x 且 key==1
			} else if ("x".equals(key) && "1".equals(map.get(key))) {
				obj = "返回值返改后的值..." + obj;
				return obj;
			}
		}
		return obj;
	}
}
