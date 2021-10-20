package br.com.igrejaparaiso.Igrejaparaiso.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

import br.com.igrejaparaiso.Igrejaparaiso.model.Comprovante;

@Service
public class ComprovanteService {
    Firestore conex = FirestoreClient.getFirestore(); // gera uma conexão a qual irá fazer todo o CRUD

    public void cadastrar(Comprovante comp) {

        DocumentReference doc = conex.collection("Comprovantes").document(); // cria um ID aleatório
        comp.setId(doc.getId()); // bota esse ID no evento

        ApiFuture<WriteResult> writeResult = doc.set(comp); // salva os dados do Comprovante :)
    }

    public ArrayList<Comprovante> getComprovantesByMembroId(String id) throws InterruptedException, ExecutionException{
        ArrayList<Comprovante> lista = new ArrayList<Comprovante>();
        
        CollectionReference eventos = conex.collection("Comprovantes");

        Query query = eventos.whereEqualTo("idMembro", id);
        // retrieve query results asynchronously using query.get()
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        for (DocumentSnapshot document : querySnapshot){
            lista.add(document.toObject(Comprovante.class));
        }
        if(lista.isEmpty()){
            return null;
        }else{
            return lista;
        }
    }
    
}
