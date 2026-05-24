package lesson10.dto;

public class Account {

    //   в класс DTO помещаем только поля, геттеры и конструктор
    //    тут будут храниться поля для метода в котором они будут использоваться
    private String name;
    private String phone;
    private String fax;
    private String website;
    private String street;
    private String type;
    private String industry;

    public Account(String name, String phone, String fax, String website, String street, String type, String industry) {
        this.name = name;
        this.phone = phone;
        this.fax = fax;
        this.website = website;
        this.street = street;
        this.type = type;
        this.industry = industry;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getFax() {
        return fax;
    }

    public String getWebsite() {
        return website;
    }

    public String getStreet() {
        return street;
    }

    public String getType() {
        return type;
    }

    public String getIndustry() {
        return industry;
    }
}
