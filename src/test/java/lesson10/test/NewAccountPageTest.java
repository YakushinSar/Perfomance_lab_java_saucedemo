package lesson10.test;


import lesson10.dto.Account;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class NewAccountPageTest extends BaseTest {

    //      создаем экземпляр Account account и вводим входные параметры, если используется lombok то все делается аналогично
    Account account = new Account("lesson9/test", "+123456", "+1234567", "avito.ru", "lesson9/test", "Investor", "Banking");

    @Test
    public void checkAddNewAccount() throws InterruptedException {
//      Fluent/Chain с цепочкой, видна очередность вызова методов без вызова несколько раз переменной
        loginPage.open();
        loginPage.login("will", "will");
        newAccountPage.open();
        sleep(2000);

        newAccountPage.addNewAccount(account);
        sleep(2000);

//        newAccountPage.clickSave();  // вызов метода сохранения
//        sleep(2000);
    }
}
