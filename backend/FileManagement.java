package backend;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class FileManagement {
	public static Boolean checkFile(String path) {
		File dir = new File(path);
		return dir.exists();
	}
	
	public static void quickSaveJson(String filePath, JSONObject jsonData) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonData.toString(4)); // toString(4) for pretty-printing
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public static void quickSaveJson(String filePath, JSONArray jsonData) {
        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write(jsonData.toString(4)); // toString(4) for pretty-printing
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static JSONObject readJsonObject(String filePath) {
        try (FileReader fileReader = new FileReader(filePath)) {
            JSONTokener tokener = new JSONTokener(fileReader);
            return new JSONObject(tokener);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
	public static JSONArray readJsonArray(String filePath) {
        JSONArray jsonArray = null;

        try (FileReader fileReader = new FileReader(filePath)) {
            JSONTokener tokener = new JSONTokener(fileReader);
            jsonArray = new JSONArray(tokener);
        } catch (IOException | JSONException e) {
            e.printStackTrace();
            return null;
        }

        return jsonArray;
    }
}
