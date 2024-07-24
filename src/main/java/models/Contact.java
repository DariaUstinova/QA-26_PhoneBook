package models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@ToString

public class   Contact {

    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private String description;

}
