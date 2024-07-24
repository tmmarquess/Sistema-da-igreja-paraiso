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

import br.com.igrejaparaiso.Igrejaparaiso.model.Agenda;

@Service
public class AgendaService {
    Firestore conex = FirestoreClient.getFirestore(); // gera uma conexão a qual irá fazer todo o CRUD

    public void cadastrar(Agenda ev) {

        DocumentReference doc = conex.collection("Agenda").document(); // cria um ID aleatório
        ev.setId(doc.getId()); // bota esse ID no link

        ApiFuture<WriteResult> writeResult = doc.set(ev); // salva os dados do link :)
    }

    public ArrayList<ArrayList<Agenda>> getAllEvs() throws InterruptedException, ExecutionException{
        ArrayList<ArrayList<Agenda>> lista = new ArrayList<>();

        List<String> dias =  List.of("Segunda","Terça","Quarta","Quinta","Sexta","Sabado","Domingo");

        CollectionReference evs = conex.collection("Agenda");

        List<QueryDocumentSnapshot> querySnapshot;
        ArrayList<Agenda> eventos = new ArrayList<>();

        for(int i = 0; i<7;i++){
            Query query = evs.whereEqualTo("diaSemana", dias.get(i));
            querySnapshot = query.get().get().getDocuments();

            eventos = new ArrayList<>();
            for (DocumentSnapshot document : querySnapshot) {
                eventos.add(document.toObject(Agenda.class));
            }
            lista.add(eventos);
        }

        return lista;
    }

    public void editar(Agenda ev) {

        DocumentReference doc = conex.collection("Agenda").document(ev.getId()); // cria um ID aleatório

        ApiFuture<WriteResult> writeResult = doc.set(ev); // salva os dados do link :)
    }

    public void apagar(String id) {

        ApiFuture<WriteResult> doc = conex.collection("Agenda").document(id).delete();

    }

}
