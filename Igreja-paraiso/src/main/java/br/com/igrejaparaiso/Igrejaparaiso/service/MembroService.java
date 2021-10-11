package br.com.igrejaparaiso.Igrejaparaiso.service;

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

import br.com.igrejaparaiso.Igrejaparaiso.model.Membro;

import java.util.ArrayList;
import java.util.*;
import java.util.concurrent.ExecutionException;

@Service
public class MembroService {
    Firestore conex = FirestoreClient.getFirestore(); // gera uma conexão a qual irá fazer todo o CRUD

    public void cadastrar(Membro membro) {

        // cria um ID aleatório a partir da coleção "Membros" do banco de dados
        DocumentReference doc = conex.collection("Membros").document(); 

        // bota esse ID aleatório como ID do membro
        membro.setId(doc.getId());

        // salva os dados do membro :)
        ApiFuture<WriteResult> writeResult = doc.set(membro); // salva os dados do membro :)
    }

    public ArrayList<Membro> getAllMembros() throws InterruptedException, ExecutionException {
        //gera um ArrayList para Armazenar todos os membros resgatados
        ArrayList<Membro> lista = new ArrayList<Membro>();

        //busca no Banco de dados todos os 'documentos' da coleção 'Membros' e põe em ordem alfabética
        ApiFuture<QuerySnapshot> future = conex.collection("Membros").orderBy("nome").get();

        //recebe uma lista dos 'documentos' de membros resgatados 
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        //transforma cada 'documento' dessa lista em uma instância da classe Membro e adiciona á ArrayList
        for (DocumentSnapshot document : documents) {
            Membro adic = document.toObject(Membro.class);
            lista.add(adic);
        }
        return lista;
    }

    public Membro getMembroById(String id) throws InterruptedException, ExecutionException {
        Membro membro = new Membro();
        
        //faz referência á coleção 'Membros' do Banco de dados
        CollectionReference membros = conex.collection("Membros");

        //pesquisa todos o membro a partir da id recebida por parâmetro
        Query query = membros.whereEqualTo("id", id);

        //recebe uma lista dos 'documentos' de membros resgatados (no caso só resgatou 1 membro)
        List<QueryDocumentSnapshot> querySnapshot = query.get().get().getDocuments();

        ////transforma o documento em uma instância da classe Membro
        for (DocumentSnapshot document : querySnapshot){
            membro = document.toObject(Membro.class);
        }

        return membro;
    }

    public void editar(Membro membro) {

        //faz referência á coleção 'Membros' e resgata o 'documento' a partir da Id do membro
        DocumentReference doc = conex.collection("Membros").document(membro.getId()); // resgata o doc pelo ID

        //substitui os dados antigos pelos novos registrados na instância recebida por parâmetro
        ApiFuture<WriteResult> writeResult = doc.set(membro); // salva os dados do membro :)
    }

    public void apagar(String id){
        //Faz referência à coleção 'Membros', resgata o 'documento' pelo Id e apaga ele
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