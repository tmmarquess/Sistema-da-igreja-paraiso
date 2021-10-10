package br.com.igrejaparaiso.Igrejaparaiso;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IgrejaParaisoApplication {

	public static void main(String[] args) throws IOException {

		FileInputStream serviceAccount = new FileInputStream("src/main/resources/serviceAccountKey.json");

		FirebaseOptions options;
		options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://sistema-da-igreja-paraiso-default-rtdb.firebaseio.com").build();

		boolean hasBeenInitialized = false;
		List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
		FirebaseApp finestayApp = null;
		for (FirebaseApp app : firebaseApps) {
			if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
				hasBeenInitialized = true;
				finestayApp = app;
			}
		}

		if (!hasBeenInitialized) {
			finestayApp = FirebaseApp.initializeApp(options);
		}

		SpringApplication.run(IgrejaParaisoApplication.class, args);
	}

}
