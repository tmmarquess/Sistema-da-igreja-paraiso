package br.com.igrejaparaiso.Igrejaparaiso.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

import br.com.igrejaparaiso.Igrejaparaiso.model.Contatos;

@Service
public class ContatosService {
    Firestore conex = FirestoreClient.getFirestore();
    
    public void cadastrar(Contatos cntt){

        DocumentReference doc = conex.collection("Contatos").document("contatos");

        ApiFuture<WriteResult> writeResult = doc.set(cntt); // salva os dados do contato :)

    }

    public Contatos getContatos() throws InterruptedException, ExecutionException {
        Contatos cntts = null;

        ApiFuture<QuerySnapshot> future = conex.collection("Contatos").get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            cntts = document.toObject(Contatos.class);
        }
        return cntts;
    }
}
