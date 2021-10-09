package br.com.igrejaparaiso.Igrejaparaiso.service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;

@Service
public class FBInitialize {

    @PostConstruct
    public void initialize() {
        try {

            FileInputStream serviceAccount = new FileInputStream("serviceAccountKey.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://sistema-da-igreja-paraiso-default-rtdb.firebaseio.com").build();

            FirebaseApp.initializeApp(options);

            DatabaseReference ref = FirebaseDatabase.getInstance().getReference("restricted_access/secret_document");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Object document = dataSnapshot.getValue();
                    System.out.println(document);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}