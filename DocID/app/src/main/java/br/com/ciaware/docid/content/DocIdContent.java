package br.com.ciaware.docid.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p/>
 */
public class DocIdContent {

    /**
     * An array of sample (dummy) items.
     */
    public static List<DocIdItem> ITEMS = new ArrayList<DocIdItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static Map<String, DocIdItem> ITEM_MAP = new HashMap<String, DocIdItem>();


    static {
        // Add some sample items.

        DocIdItem cn = new DocIdItem("CN", "Certidão de Nascimento", "Documento emitido por um cartório mediante apresentação da declaração de nascido vivo.");
        addItem(cn);

        DocIdItem cns = new DocIdItem("CNS", "Cartão Nacional de Saúde", "Registro do cidadão junto ao Ministério da Saúde.");
        addItem(cns);

        DocIdItem cpf = new DocIdItem("CPF", "Cadastro de Pessoa Fisica", "Registro civil federal da pessoa física.");
        addItem(cpf);

        DocIdItem dnv = new DocIdItem("DNV", "Declaraçao de Nascido Vivo", "Documento emitido pela maternidade para obtenção da certidão de nascimento.");
        addItem(dnv);

        DocIdItem passaporte = new DocIdItem("Passaporte", "Passaporte", "Documento de identificaçao pessoal em pais estrangeiro.");
        addItem(passaporte);

    }

    private static void addItem(DocIdItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    private static DocIdItem createDocIdItem(int position) {
        return new DocIdItem(String.valueOf(position), "Item " + position, makeDetails(position));
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

    /**
     * A dummy item representing a piece of content.
     */
    public static class DocIdItem {
        public String id;
        public String content;
        public String details;


        public DocIdItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {

            String result = content;
            if (!content.toLowerCase().equals(id.toLowerCase())) {
                result += " (" + id + ")";
            }

            return content;
        }

    }
}
