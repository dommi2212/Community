package de.janhektor.community.tests;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.google.common.reflect.ClassPath;
import com.google.common.reflect.ClassPath.ClassInfo;

import de.janhektor.community.Main;

public class TestManager {

	private static TestManager instance = null;
	
	private List<Class<?>> testClasses = new ArrayList<>();
	
	private TestManager() {
		ClassPath cp;
		try {
			cp = ClassPath.from(TestManager.class.getClassLoader());
		} catch (IOException e) {
			e.printStackTrace();
			return;
		}
		for (ClassInfo classInfo : cp.getTopLevelClasses(TestManager.class.getPackage().getName())) {
			Class<?> clazz;
			try {
				clazz = Class.forName(classInfo.getName());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
				continue;
			}
			Annotation[] annotations = clazz.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation instanceof Test) {
					this.testClasses.add(clazz);
					break;
				}
			}
		}
	}
	
	public void initTests() {
		Main.getInstance().getLogger().info("[Test] Initializing tests ... (" + testClasses.size() + " classes)");
		for (Class<?> testClass : this.testClasses) {
			Object testObject;
			try {
				testObject = testClass.newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
				continue;
			}
			for (Method method : testClass.getMethods()) {
				Annotation[] annotations = method.getDeclaredAnnotations();
				for (Annotation annotation : annotations) {
					if (!(annotation instanceof TestMethod)) {
						continue;
					}
					try {
						method.invoke(testObject);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
						e1.printStackTrace();
					}
				}
			}
			Main.getInstance().getLogger().info("[Test] " + testClass.getName() + " initialized!");
		}
	}
	
	public static TestManager getInstance() {
		if(TestManager.instance == null) {
			TestManager.instance = new TestManager();
		}
		return TestManager.instance;
	}
	
}
