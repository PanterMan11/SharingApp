package backend;

import java.security.SecureRandom;
import java.util.Base64;

import org.json.JSONArray;
import org.json.JSONObject;

enum error { 
	SUCCESS,
	ID_ALREADY_EXISTS,
	NAME_ALREADY_EXISTS,
	PASSWORD_OR_ID_WRONG,
	TOKEN_ERROR
}

public class Account {
	private static final SecureRandom secureRandom = new SecureRandom();
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder(); 
	
	public static error login(int id, String password) {
		JSONArray accounts = FileManagement.readJsonArray("accounts.json");
		for (int i = 0; i < accounts.length(); i++) {
			JSONObject account = accounts.getJSONObject(i);
			if (account.getInt("id") == id && account.getString("password") == password) {
				return error.SUCCESS;
			}
		}

		return error.PASSWORD_OR_ID_WRONG;
	}
	
	public static String generateNewToken() {
	    byte[] randomBytes = new byte[24];
	    secureRandom.nextBytes(randomBytes);
	    return base64Encoder.encodeToString(randomBytes);
	}
	
	public static error register(int id, String displayName, String password) {
		//TODO: ADD SECURITY
		
		String token = generateNewToken();
		if (!FileManagement.checkFile("accounts.json")) {
			JSONObject output = new JSONObject();
			output.put("id", id);
			output.put("displayName", displayName);
			output.put("password", password);
			output.put("token", token);
			
			JSONArray array = new JSONArray();
			array.put(output);
			
			FileManagement.quickSaveJson("accounts.json", array);
		} else {
			 JSONArray accounts = FileManagement.readJsonArray("accounts.json");

			 for (int i = 0; i < accounts.length(); i++) {
				 JSONObject account = accounts.getJSONObject(i);
				 
				 int existingId = account.getInt("id");
				 String existingDisplayName = account.getString("displayName");
				 String existingToken = account.getString("token");

				 if (existingId == id)
					 return error.ID_ALREADY_EXISTS;

				 if (existingDisplayName.equals(displayName))
					 return error.NAME_ALREADY_EXISTS;
				 
				 if (existingToken == token)
					 return error.TOKEN_ERROR;
			 }
			 
			 JSONObject newUser = new JSONObject();
			 newUser.put("id", id);
			 newUser.put("displayName", displayName);
			 newUser.put("password", password);
			 newUser.put("token", token);
			 
			 accounts.put(newUser);
			 FileManagement.quickSaveJson("accounts.json", accounts);
		}
		
		return error.SUCCESS;
	}
	
	public static JSONObject getAccountInfo(String token) {
		JSONArray accounts = FileManagement.readJsonArray("accounts.json");
		 for (int i = 0; i < accounts.length(); i++) {
			 JSONObject account = accounts.getJSONObject(i);
			 if (account.getString("token") == token)
				 return account;
		 }
		
		return null;
	}
}
