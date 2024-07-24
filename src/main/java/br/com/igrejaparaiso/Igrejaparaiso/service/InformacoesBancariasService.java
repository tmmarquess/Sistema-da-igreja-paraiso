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

import br.com.igrejaparaiso.Igrejaparaiso.model.InformacoesBancarias;

@Service
public class InformacoesBancariasService {
    Firestore conex = FirestoreClient.getFirestore();

    public void cadastrar(InformacoesBancarias inf){

        DocumentReference doc = conex.collection("InformacoesBancarias").document("informacoes");
        inf.setId(doc.getId()); // bota esse ID no link

        ApiFuture<WriteResult> writeResult = doc.set(inf); // salva os dados do link :)
    }

    public InformacoesBancarias getInformacoes() throws InterruptedException, ExecutionException {
        InformacoesBancarias inf = null;

        ApiFuture<QuerySnapshot> future = conex.collection("InformacoesBancarias").get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            inf = document.toObject(InformacoesBancarias.class);
        }
        return inf;
    }
}
