package homework5_TestNG.tests;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass,
                          Constructor testConstructor, Method testMethod) {
        // Устанавливаем Retry анализатор для каждого тестового метода
        annotation.setRetryAnalyzer(Retry.class);
    }
}
