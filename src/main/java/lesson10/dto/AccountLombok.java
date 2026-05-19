package lesson10.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class AccountLombok {
    private String name;
    private String phone;
    private String fax;
    private String website;
    private String street;
    private String type;
    private String industry;

}
