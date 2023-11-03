package shop;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

public class UserServise {

    public User findUserByName(String name) {
        try {
            String textRepository = readJsonTextFromFile("src/main/resources/userRepo.json");
            JSONArray jsonArrayUsers = new JSONArray(textRepository);
            JSONObject jsonObjectUser = returnSearch(jsonArrayUsers, name);
            User user = new User(jsonObjectUser.getString("name"), jsonObjectUser.getString("email"));
            return user;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        User user = new User("", "");
        return user;
    }

    public String readJsonTextFromFile(String path) {
        String jsonText = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = null;
            StringBuilder stringBuilder = new StringBuilder();
            String ls = System.getProperty("line.separator");

            try {
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append(ls);
                }

                return stringBuilder.toString();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            } finally {
                reader.close();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return jsonText;
    }

    public JSONObject returnSearch(JSONArray array, String searchValue) {
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = null;
            try {
                obj = array.getJSONObject(i);
                if (obj.getString("name").equals(searchValue)) {
                    return obj;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        JSONObject result = new JSONObject("");
        return result;
    }

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public String readJsonText(String url) throws IOException {
        String jsonText = "";
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            jsonText = readAll(rd);
        } finally {
            is.close();
        }
        return jsonText;
    }
}
