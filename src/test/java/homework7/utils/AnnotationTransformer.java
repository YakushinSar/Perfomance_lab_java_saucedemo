package homework7.utils;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class AnnotationTransformer implements IAnnotationTransformer {
    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        // Проверка нужна, чтобы игнорировать служебные вызовы TestNG, в которых нет тестового метода, и избежать NullPointerException
        if (testMethod == null) {
            return;
        }

        // Диагностическая строка System.out.println,  нужна только для диагностики. После настройки её можно удалить или
        // закомментировать Она помогает убедиться, что: AnnotationTransformer вообще вызывается, он вызывается именно для нужных тестовых методов (которые упали)
        // System.out.println("✅ AnnotationTransformer вызван для: " + testMethod.getName());

        // Устанавливаем Retry анализатор для каждого тестового метода, Способы подключения Retry по (итоговая таблица которая выше)
        annotation.setRetryAnalyzer(Retry.class);
    }
}
