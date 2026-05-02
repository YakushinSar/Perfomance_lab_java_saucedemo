package lesson5TestNG.junit5;

public class ParallelTestJUnit5 {
    /*
Параллельный запуск. В папке resourses cоздать файл junit-platform.properties и в нем настроить параллельный запуск.

junit.jupiter.execution.parallel.enabled = true // означает что тесты можно запускать параллельно.
junit.jupiter.execution.parallel.mode.default = concurrent // можно параллелить методы, concurrent - параллельно, same_thread - последовательно
junit.jupiter.execution.parallel.mode.classes.default = concurrent// можно параллелить классы, concurrent - параллельно, same_thread - последовательно
# (опционально) Ограничить количество потоков можно фиксированным числом, например 4
junit.jupiter.execution.parallel.config.strategy = fixed // фиксация потоков, fixed - фиксированное количество, dynamic - динамическое (по числу ядер)
junit.jupiter.execution.parallel.config.fixed.parallelism = 4 // указано что потока будет 4

# Как проверить:
@Test
public void testParallel() {
    System.out.println("Поток: " + Thread.currentThread().getName());
}
Если все тесты вывели main → параллелизация не работает
Если разные имена (worker-1, worker-2) → ✅ параллелизация работает
     */
}
