import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

class PersonInfo {
    private String fname;
    private String lname;
    private String patronymic;
    private String city;
    private String country;
    private String region;
    private String sex;
    private String street;
    private Date date;
    private int index;
    private String inn;
    private int house;
    private int apartment;

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
    public String getCountry () {
        return this.country;
    }
    public String getRegion () {
        return this.region;
    }
    public String getSex () {
        return this.sex;
    }
    public String getStreet () {
        return this.street;
    }
    public Date getDate () {
        return this.date;
    }
    public int getIndex () {
        return this.index;
    }
    public String getInn () {
        return this.inn;
    }
    public int getHouse () {
        return this.house;
    }
    public int getApartment () {
        return this.apartment;
    }

    public PersonInfo () throws IOException {
        setSex ();
        setName ();
        setFamilyName ();
        setPatronymic ();
        setCity();
        setRegion();
        setCountry ();
        setStreet();
        setIndex ();
        setHouse ();
        setApartment ();
        setDate();
        setInn ();
        System.out.println("ssssssssssssssssssssssssssssss");
    }
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PersonInfo that = (PersonInfo) o;

        if (fname != that.fname) return false;
        if (lname != that.lname) return false;
        if (patronymic != that.patronymic) return false;
        if (city != that.city) return false;
        if (country != that.country) return false;
        if (region != that.region) return false;
        if (sex != that.sex) return false;
        if (street != that.street) return false;
        if (date != that.date) return false;
        if (index != that.index) return false;
        if (inn != that.inn) return false;
        if (house != that.house) return false;
        if (apartment != that.apartment) return false;

        return true;
    }


    public PersonInfo (TestInfoFromApi testInfoFromApi) throws IOException {
        if (testInfoFromApi.getGender() == "w") {
            this.sex = "Ж";
        } else {
            this.sex = "М";
        }
        this.fname = testInfoFromApi.getFName();
        this.lname = testInfoFromApi.getLName();
        this.patronymic = testInfoFromApi.getPatronymic();
        this.index = Integer.parseInt(testInfoFromApi.getPostcode());
        this.city = testInfoFromApi.getCity();
        this.street = testInfoFromApi.getStreet();
        this.house = testInfoFromApi.getHouse();
        this.apartment = testInfoFromApi.getApartment();

        setRegion();
        setCountry ();
        setDate();
        setInn ();

    }

    private void setSex () {
        int rand = getRandomNumber (2);
        if (rand < 1) {
            sex = "М";
        } else {
            sex = "Ж";
        }
    }

    private void setName () {
        ArrayList<String> names;
        if (sex == "М") {
            names =  getFile ("male_names.txt");
        } else if (sex == "Ж") {
            names =  getFile ("female_names.txt");
        } else {
            throw new EmptyStackException();
        }

        int rand = getRandomNumber (names.size());
        fname = names.get(rand);
    }

    private void setFamilyName () {
        ArrayList<String> names;
        if (sex == "М") {
            names =  getFile ("male_fname.txt");
        } else if (sex == "Ж") {
            names =  getFile ("female_fname.txt");
        } else {
            throw new EmptyStackException();
        }

        int rand = getRandomNumber (names.size());
        lname = names.get(rand);
    }

    private void setPatronymic () {
        ArrayList<String> names;
        if (sex == "М") {
            names =  getFile ("male_patronymic.txt");
        } else if (sex == "Ж") {
            names =  getFile ("female_patronymic.txt");
        } else {
            throw new EmptyStackException();
        }

        int rand = getRandomNumber (names.size());
        patronymic = names.get(rand);
    }

    private void setCity () {
        ArrayList<String> names;
        names =  getFile ("cities.txt");

        int rand = getRandomNumber (names.size());
        city = names.get(rand);
    }

    private void setRegion () {
        ArrayList<String> names;
        names =  getFile ("regions.txt");

        int rand = getRandomNumber (names.size());
        region = names.get(rand);
    }

    private void setCountry () {
        ArrayList<String> names;
        names =  getFile ("countries.txt");

        int rand = getRandomNumber (names.size());
        country = names.get(rand);
    }

    private void setStreet () {
        ArrayList<String> names;
        names =  getFile ("streets.txt");

        int rand = getRandomNumber (names.size());
        street = names.get(rand);
    }

    private void setIndex () {

        index = getRandomNumber (999999 - 100000) + 10000;
    }

    private void setHouse () {

        house = getRandomNumber (100) + 1;
    }

    private void setApartment () {

        apartment = getRandomNumber (100) + 1;
    }

    private void setDate () {

        this.date = new Date(getRandomNumber (118), getRandomNumber (12), getRandomNumber (31));
    }

    private void setInn () {
        inn = "";
        ArrayList<Integer> names = new ArrayList<Integer>();
        names.add (7);
        names.add (7);

        names.add (getRandomNumber (10));
        names.add (getRandomNumber (10));

        names.add (getRandomNumber (10));
        names.add (getRandomNumber (10));
        names.add (getRandomNumber (10));
        names.add (getRandomNumber (10));
        names.add (getRandomNumber (10));
        names.add (getRandomNumber (10));

        int n11 = (
                    (7*names.get(0) + 2*names.get(1) + 4*names.get(2)
                    + 10*names.get(3) + 3*names.get(4) + 5*names.get(5)
                    + 9*names.get(6) + 4*names.get(7) + 6*names.get(8) + 8*names.get(9)
                ) % 11) % 10;
        names.add (n11);

        int n12 = (
                    (3*names.get(0) + 7*names.get(1) + 2*names.get(2)
                    + 4*names.get(3) + 10*names.get(4) + 3*names.get(5)
                    + 5*names.get(6) + 9*names.get(7) + 4*names.get(8)
                    + 6*names.get(9) + 8*names.get(10)
                ) % 11) % 10;
        names.add (n12);

        for (int i = 0; i < names.size(); i++) {
            inn += names.get(i);
        }

    }
    private int getRandomNumber (int max) {
        int randomInt = 0;
        Random randomGenerator = new Random();
        randomInt = randomGenerator.nextInt(max);
        return randomInt;
    }

    private ArrayList<String> getFile (String fileName) {

        ArrayList<String> result = new ArrayList<String>();
        try {

            //Get file from resources folder
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                result.add(line);
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result;

    }
    public String toString() {
        return "SimplePojo{" +
                "firstName='" + fname + '\'' +
                ", lastName='" + lname + '\'' +
                ", age=" + date +
                ", gender=" + sex +
                '}';
    }
}