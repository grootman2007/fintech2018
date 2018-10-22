import com.google.gson.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ApiTestInfo {

    public ApiTestInfo () {

    }

    public ArrayList<PersonInfo> GetPersonInfo (int randomCount) throws IOException{
        ArrayList<PersonInfo> personInfos = new ArrayList<PersonInfo>();
        try {
            for (int i = 0; i < randomCount; i++) {
                URL url = new URL("https://randus.org/api.php");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");

                int status = con.getResponseCode();
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                System.out.println(content);

                Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new GsonUTCDateAdapter()).create();
                PersonInfo person = new PersonInfo (gson.fromJson(content.toString(), TestInfoFromApi.class));
                personInfos.add (person);
                System.out.println(gson.fromJson(content.toString(), PersonInfo.class));
            }

        }
        catch (IOException e) {
            if (e.getMessage().equals("randus.org") ) {
                throw new IOException ("No internet!");
            }
        }
        return personInfos;


    }
}

