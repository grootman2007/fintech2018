import java.io.IOException;
import java.util.Date;

public class TestInfoFromApi {
    private String fname;
    private String lname;
    private String patronymic;
    private String city;
    private String street;
    private String gender;
    private Date date;
    private String postcode;
    private int house;
    private int apartment;

    private String phone;
    private String login;
    private String password;
    private String color;
    private String userpic;

    public String getFName () {
        return this.fname;
    }
    public String getLName () {
        return this.lname;
    }
    public String getPatronymic () {
        return this.patronymic;
    }
    public String getCity () {
        return this.city;
    }


    public String getStreet () {
        return this.street;
    }
    public Date getDate () {
        return this.date;
    }
    public String getPostcode () {
        return this.postcode;
    }

    public int getHouse () {
        return this.house;
    }
    public int getApartment () {
        return this.apartment;
    }
    public String getGender () {
        return this.gender;
    }

    public TestInfoFromApi () throws IOException {

    }
}
