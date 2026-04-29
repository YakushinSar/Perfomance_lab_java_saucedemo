package lesson5;

public class SuiteTestNG {
    /*
TestNG xml suite file.Функциональность:
1. Разделение тестов на группы с возможностью запуска отдельно. Допустим: SmokeTest.xml,RegressionTest.xml
2. Возможность запуска в параллели.
3. Возможность запуска из командной строки.

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
//количество потоков
<suite thread-count="2"
       name="Sauce demo"
//     тип
//распараллеливания parallel (методы, классы, тесты) 5 сущностей
       parallel="classes">

//  это тестовые наборы, их может быть несколько
    <test name="Поток 1">
//  тут запустятся только тесты помеченные include groups = "smoke", если include то кроме этих тестов
        <groups>
            <run>
                <include name="smoke"/>
            </run>
        </groups>
        <classes>
//        сюда в кавычки ставятся тестовые классы которые надо запускать
            <class name=""/>
            <parameter name="password" value="password"></parameter>
            <parameter name="email" value="test@mailinator.com"></parameter>
        </classes>
    </test>

    <test name="Поток 1">
        <classes>
            <class name=""/>
            <class name=""/>
        </classes>
    </test>
</suite>

# Считывание параметров извне
1. @Parameters - дает возможность считать переменные из .xml файла
2. @Optional - подставляет дефолтное значение в случае если данные не будут найдены в xml

@Parameters({"email", "password"})
@Test(groups = {"slow"})
public void test4(@Optional("1")
        String p1,
        @Optional("string")
        String p2) {
    System.out.println(p1 + " " + p2);
}

3. <parameter name=”” value=””> -передает переменную со значением внутрь тестов
<test name="test 1">
<!-- <groups>
    <run>
     <include name="Писал Юра" />
    </run>
   </groups>-->
   <classes>
    <class name="testng.TestNGExampleTest"/>
    <parameter name="password" value="password"></parameter>
    <parameter name="email" value="test@mailinator.com"></parameter>
   </classes>
</test> <!-- Test -->


     */
}
