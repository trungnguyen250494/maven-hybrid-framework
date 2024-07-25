package testdata.jsonData;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import commons.GlobalConstants;

import java.io.File;
import java.io.IOException;

public class UserInfo {
    public static UserInfo getUserInfo(String fileName){
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.readValue(new File(GlobalConstants.RESOURCE_PATH + fileName),UserInfo.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @JsonProperty("firstname")
    String firstName;

    @JsonProperty("lastname")
    String lastName;

    @JsonProperty("email")
    String email;

    @JsonProperty("password")
    String password;

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
