//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//
//public class Example {
//    public static void main(String[] args) throws IOException {
//        Gson gson = new GsonBuilder()
//                .setPrettyPrinting()
//                .create();
//
//        Test testEnt = new Test(
//                0,
//                "Alexey",
//                "Drug dealer"
//        );
//
//        String json = gson.toJson(testEnt);
//
//        Files.writeString(Paths.get("testFile.json"), json);
//
//        var resultJson = Files.readString(Paths.get("testFile.json"));
//
//        Test resultEnt = new Gson().fromJson(resultJson, Test.class);
//
//        System.out.println(resultEnt);
//    }
//}
