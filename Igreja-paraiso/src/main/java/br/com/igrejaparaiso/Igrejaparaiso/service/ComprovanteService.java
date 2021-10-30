package br.com.igrejaparaiso.Igrejaparaiso.service;

import java.util.ArrayList;
import java.util.Collections;
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

    public Comprovante getComprovanteById(String id) throws InterruptedException, ExecutionException{
        Comprovante comp = new Comprovante();
        comp.setId(null);
        
        CollectionReference eventos = conex.collection("Comprovantes");

        Query query = eventos.whereEqualTo("id", id);
        // retrieve query results asynchronously using query.get()
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        for (DocumentSnapshot document : querySnapshot){
            comp = document.toObject(Comprovante.class);
        }
        if(comp.getId() == null){
            return null;
        }else{
            return comp;
        }
    }

    public void apagar(String id){
        //Faz referência à coleção 'Membros', resgata o 'documento' pelo Id e apaga ele
        ApiFuture<WriteResult> writeResult = conex.collection("Comprovantes").document(id).delete();
    }

    public ArrayList<Comprovante> getAllComprovantes() throws InterruptedException, ExecutionException {
        //gera um ArrayList para Armazenar todos os membros resgatados
        ArrayList<Comprovante> lista = new ArrayList<Comprovante>();

        //busca no Banco de dados todos os 'documentos' da coleção 'Membros' e põe em ordem alfabética
        ApiFuture<QuerySnapshot> future = conex.collection("Comprovantes").orderBy("data").get();

        //recebe uma lista dos 'documentos' de membros resgatados 
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        //transforma cada 'documento' dessa lista em uma instância da classe Membro e adiciona á ArrayList
        for (DocumentSnapshot document : documents) {
            Comprovante adic = document.toObject(Comprovante.class);
            lista.add(adic);
        }

        Collections.reverse(lista);

        return lista;
    }

    public ArrayList<Comprovante> getComprovantesByMes(String mes) throws InterruptedException, ExecutionException{
        ArrayList<Comprovante> comp = new ArrayList<Comprovante>();
        
        for(Comprovante teste : getAllComprovantes()){
            if(teste.getData().startsWith(mes)){
                comp.add(teste);
            }
        }
        if(comp.isEmpty()){
            return null;
        }else{
            return comp;
        }
    }
    
}
