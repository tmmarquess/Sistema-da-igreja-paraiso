package br.com.igrejaparaiso.Igrejaparaiso.service;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.springframework.stereotype.Service;

import br.com.igrejaparaiso.Igrejaparaiso.model.Membro;
import br.com.igrejaparaiso.Igrejaparaiso.model.MembroGoogle;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class MembroGoogleService {
    Firestore conex = FirestoreClient.getFirestore();

    public void cadastrar(Membro membro) {

        DocumentReference doc = conex.collection("Membros").document(); // cria um ID aleatório
        membro.setId(doc.getId()); // bota esse ID no membro

        ApiFuture<WriteResult> writeResult = doc.set(membro); // salva os dados do membro :)
    }

    public ArrayList<Membro> getAllMembros() throws InterruptedException, ExecutionException {
        ArrayList<Membro> lista = new ArrayList<Membro>();

        ApiFuture<QuerySnapshot> future = conex.collection("Membros").orderBy("nome").get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            Membro adic = document.toObject(Membro.class);
            lista.add(adic);
        }
        return lista;
    }

    public Membro getMembroById(String id) throws InterruptedException, ExecutionException {
        Membro membro = new Membro();
        
        CollectionReference membros = conex.collection("Membros");

        Query query = membros.whereEqualTo("id", id);
        // retrieve query results asynchronously using query.get()
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        for (DocumentSnapshot document : querySnapshot){
            membro = document.toObject(Membro.class);
        }

        return membro;
    }

    public void editar(Membro membro) {

        DocumentReference doc = conex.collection("Membros").document(membro.getId()); // resgata o doc pelo ID

        ApiFuture<WriteResult> writeResult = doc.set(membro); // salva os dados do membro :)
    }

    public void apagar(String id){
        ApiFuture<WriteResult> writeResult = conex.collection("Membros").document(id).delete();
    }

    public Membro login(Membro membro) throws InterruptedException, ExecutionException{
        
        CollectionReference membros = conex.collection("Membros");

        Query query = membros.whereEqualTo("email", membro.getEmail()).whereEqualTo("senha", membro.getSenha());
        // retrieve query results asynchronously using query.get()
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        Membro resultado = null;
        for (DocumentSnapshot document : querySnapshot){
            resultado = document.toObject(Membro.class);
        }

        return resultado;
    }
}