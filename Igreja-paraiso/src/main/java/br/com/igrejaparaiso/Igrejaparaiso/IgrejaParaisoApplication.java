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
	
	private int maxUploadSizeInMb = 10 * 1024 * 1024;
	public static void main(String[] args) throws IOException {
		
		//carregando arquivo com as chaves de permissão de manipulação  do Firebase
		FileInputStream serviceAccount = new FileInputStream("src/main/resources/serviceAccountKey.json");

		//criando conexão com o banco de dados da aplicação no Firebase
		FirebaseOptions options;
		options = new FirebaseOptions.Builder().setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://sistema-da-igreja-paraiso-default-rtdb.firebaseio.com").build();

		//verificando se as configurações ja foram inicializadas
		boolean hasBeenInitialized = false;
		List<FirebaseApp> firebaseApps = FirebaseApp.getApps();
		FirebaseApp finestayApp = null;
		for (FirebaseApp app : firebaseApps) {
			if (app.getName().equals(FirebaseApp.DEFAULT_APP_NAME)) {
				hasBeenInitialized = true;
				finestayApp = app;
			}
		}
		//se não foram, inicializa as configurações
		if (!hasBeenInitialized) {
			finestayApp = FirebaseApp.initializeApp(options);
		}		

		SpringApplication.run(IgrejaParaisoApplication.class, args);
	}
}
