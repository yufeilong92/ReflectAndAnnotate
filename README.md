# ReflectAndAnnotate
注解和反射简单实用
```
   public static void injectView(Activity activity) {
           //获取class
	           Class<? extends Activity> cla = activity.getClass();
		           //获取对应class下所有属性
			           Field[] fields = cla.getDeclaredFields();
				           for (Field field : fields) {
					               //判断是否有我们注解
						                   boolean isEsite = field.isAnnotationPresent(AnnotateFindView.class);
								               if (isEsite) {
									                       AnnotateFindView annotation = field.getAnnotation(AnnotateFindView.class);
											                       int idResource = annotation.value();
													                       View view = activity.findViewById(idResource);
															                       field.setAccessible(true);
																	                       try {
																			                           field.set(activity, view);
																						                   } catch (IllegalAccessException e) {
																								                       e.printStackTrace();
																										                       }
																												                   }
																														           }

																															       }

```
