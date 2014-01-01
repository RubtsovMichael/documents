package rubtsov.documents.data.model.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mike on 16.12.13.
 */
public enum DocumentType {

    INCOMING("Входящий"),
    OUTGOING("Исходящий");

    private String displayName;

    private DocumentType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static Map<String, String> getAsMap() {
        HashMap<String, String> docTypes = new HashMap<>();
        for (DocumentType documentType : DocumentType.values()) {
            docTypes.put(documentType.name(), documentType.getDisplayName());
        }
        return docTypes;
    }
}
