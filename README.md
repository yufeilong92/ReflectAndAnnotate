# ReflectAndAnnotate
注解和反射简单实用
##资源注解
```
@IntDef({MainActivity.RESOUCES_ONE,MainActivity.RESOUCES_TWO})
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.SOURCE)
public @interface AnnotateValue {

}

private fun setResouce() {
        setResourceValue(RESOUCES_ONE)
}

    //标志注解
 fun setResourceValue(@AnnotateValue id: Int) {

}
```
```
    companion object {
        const val RESOUCES_ONE = 1;
        const val RESOUCES_TWO = 2;
    }
```


##注解使用
```
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface AnnotateFindView {
    @IdRes int value();
}

```
##反射
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
```
    public static void injectViewBackgroundOnly(Activity activity, @ColorInt int colorResource, @IdRes int id) {
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
                if (idResource == id) {
                    view.setBackgroundColor(colorResource);
                    field.setAccessible(true);
                    try {
                        field.set(activity, view);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
```
#Activity 融合注解和反射
```
@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ActivityValue {
    String value() default "";
}

```
```
    public static void injntActivity(Activity activity) {
        Class<? extends Activity> cla = activity.getClass();

        Intent intent = activity.getIntent();
        Bundle extras = intent.getExtras();

        if (extras == null) {
            return;
        }
        Field[] fields = cla.getDeclaredFields();

        for (Field field : fields) {
            boolean isExsit = field.isAnnotationPresent(ActivityValue.class);
            if (isExsit) {
                ActivityValue annotation = field.getAnnotation(ActivityValue.class);
                String key = TextUtils.isEmpty(annotation.value()) ? field.getName() : annotation.value();
                Object obj = extras.get(key);
                Class<?> type = field.getType().getComponentType();
                if (field.getType().isArray() &&null!=type&& Parcelable.class.isAssignableFrom(type)) {
                    Object[] objs = (Object[]) obj;
                    Class<? extends Object[]> clas = (Class<? extends Object[]>) field.getType();
                    obj = Arrays.copyOf(objs, objs.length, clas);
                }

                field.setAccessible(true);
                try {
                    field.set(activity, obj);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }

    }
```
常规调整
```
        mainActivity.btnStartIntent.setOnClickListener {
            val vo = UserData().apply {
                age = 2
                name = "小马哥"
                cla = "一年级二班"
            }
            val intent = Intent(this, ContentActivity::class.java).apply {
                putExtra("name", "小明")
                putExtra("age", 1)
                putExtra("vo", vo)
            }
            startActivity(intent)
        }
```
对应Activity 使用
```
    @ActivityValue
    var name: String? = null;

    @ActivityValue
    var age: Int? = null;

    @ActivityValue(value = "vo")
    var dataVo: UserData? = null;
    lateinit var contentBinding: ActivityContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        contentBinding = ActivityContentBinding.inflate(layoutInflater)
        setContentView(contentBinding.root)
        setContent()
    }

    private fun setContent() {
        ActivityReflectUtils.injntActivity(this)
        contentBinding.tvContent.text = toString()

    }

    override fun toString(): String {
        return "ContentActivity(name=$name, age=$age, dataVo=$dataVo)"
    }

```
