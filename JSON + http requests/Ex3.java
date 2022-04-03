import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Ex3 {
    @Test
    public void saveToFile() throws FileNotFoundException {

        JsonArray linkuri = new JsonArray();

        JsonObject link1 = new JsonObject();
        JsonObject link2 = new JsonObject();

        link1.addProperty("url", (String) null);
        link1.addProperty("label", "Previous");
        link1.addProperty("active", false);

        link2.addProperty("url", "https://");
        link2.addProperty("label", "1");
        link2.addProperty("active", true);

        linkuri.add(link1);
        linkuri.add(link2);

        JsonObject data = new JsonObject();
        data.addProperty("address", "Street");
        data.addProperty("city", "1");
        data.addProperty("active", 1);


        JsonObject dePrintat = new JsonObject();
        dePrintat.addProperty("activity", "Practice");
        dePrintat.addProperty("type", "education");
        dePrintat.add("links", linkuri);
        dePrintat.add("data", data);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();     //for pretty printing
        System.out.println(gson.toJson(dePrintat));


        PrintWriter w = new PrintWriter("out.json");        // create .json output file
        w.write(gson.toJson(dePrintat));

        w.close();
        w.flush();
        }
}


