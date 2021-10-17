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
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;

import org.springframework.stereotype.Service;

import br.com.igrejaparaiso.Igrejaparaiso.model.LinkDoCulto;

@Service
public class LinkService {
    Firestore conex = FirestoreClient.getFirestore();

    public void cadastrar(LinkDoCulto link) {

        DocumentReference doc = conex.collection("links").document(); // cria um ID aleatório
        link.setId(doc.getId()); // bota esse ID no link

        ApiFuture<WriteResult> writeResult = doc.set(link); // salva os dados do link :)
    }

    public ArrayList<LinkDoCulto> getAllLinks() throws InterruptedException, ExecutionException {
        ArrayList<LinkDoCulto> lista = new ArrayList<LinkDoCulto>();

        ApiFuture<QuerySnapshot> future = conex.collection("links").orderBy("data").get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            LinkDoCulto adic = document.toObject(LinkDoCulto.class);
            lista.add(adic);
        }
        return lista;
    }

    public LinkDoCulto getLinkById(String id) throws InterruptedException, ExecutionException {
        LinkDoCulto link = new LinkDoCulto();
        
        CollectionReference links = conex.collection("links");

        Query query = links.whereEqualTo("id", id);
        // retrieve query results asynchronously using query.get()
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        for (DocumentSnapshot document : querySnapshot){
            link = document.toObject(LinkDoCulto.class);
        }

        return link;
    }

    public void editar(LinkDoCulto link) {

        DocumentReference doc = conex.collection("links").document(link.getId()); // resgata o doc pelo ID

        ApiFuture<WriteResult> writeResult = doc.set(link); // salva os dados do link :)
    }

    public void apagar(String id){
        ApiFuture<WriteResult> writeResult = conex.collection("links").document(id).delete();
    }
}
