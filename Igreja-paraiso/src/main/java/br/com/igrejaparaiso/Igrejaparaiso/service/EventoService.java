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

import br.com.igrejaparaiso.Igrejaparaiso.model.Evento;

@Service
public class EventoService {
    Firestore conex = FirestoreClient.getFirestore();

    public void cadastrar(Evento evento) {

        DocumentReference doc = conex.collection("Eventos").document(); // cria um ID aleatório
        evento.setId(doc.getId()); // bota esse ID no evento

        ApiFuture<WriteResult> writeResult = doc.set(evento); // salva os dados do membro :)
    }

    public ArrayList<Evento> getAllEventos() throws InterruptedException, ExecutionException {
        ArrayList<Evento> lista = new ArrayList<Evento>();

        ApiFuture<QuerySnapshot> future = conex.collection("Eventos").orderBy("nome").get();

        List<QueryDocumentSnapshot> documents = future.get().getDocuments();
        for (DocumentSnapshot document : documents) {
            Evento adic = document.toObject(Evento.class);
            lista.add(adic);
        }
        return lista;
    }

    public Evento getEventoById(String id) throws InterruptedException, ExecutionException {
        Evento evento = new Evento();
        
        CollectionReference eventos = conex.collection("Eventos");

        Query query = eventos.whereEqualTo("id", id);
        // retrieve query results asynchronously using query.get()
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        for (DocumentSnapshot document : querySnapshot){
            evento = document.toObject(Evento.class);
        }

        return evento;
    }

    public void editar(Evento evento) {

        DocumentReference doc = conex.collection("Eventos").document(evento.getId()); // resgata o doc pelo ID

        ApiFuture<WriteResult> writeResult = doc.set(evento); // salva os dados do membro :)
    }

    public void apagar(String id){
        ApiFuture<WriteResult> writeResult = conex.collection("Eventos").document(id).delete();
    }
}
