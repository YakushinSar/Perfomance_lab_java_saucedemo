package lesson5JUnit5.tests;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;

import java.util.Optional;

public class CustomTestWatcher implements TestWatcher {
    @Override
    public void testSuccessful(ExtensionContext context) {
        System.out.println("✅ УСПЕХ: " + context.getDisplayName());
        // Здесь можно, например, очистить данные после успешного теста
    }

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        System.out.println("❌ ПАДЕНИЕ: " + context.getDisplayName());
        System.out.println("Причина: " + cause.getMessage());
        // Здесь можно сделать скриншот WebDriver'a или прикрепить лог
    }

    @Override
    public void testDisabled(ExtensionContext context, Optional<String> reason) {
        System.out.println("⏩ ПРОПУЩЕН: " + context.getDisplayName() +
                ". Причина: " + reason.orElse("не указана"));
    }

    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        System.out.println("⚠️ ПРЕРВАН: " + context.getDisplayName());
    }
}
