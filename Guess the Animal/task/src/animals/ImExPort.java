package animals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class ImExPort {
    public static void exportBinTree(BinaryTree binaryTree, String type) {
        String fileName = "animals" + (Locale.getDefault().getLanguage().equals("eo") ? "_eo" : "") + "." + type;
        ObjectMapper objectMapper;

        switch (type) {
            case "xml":
                objectMapper = new XmlMapper();
                break;
            case "yaml":
                objectMapper = new YAMLMapper();
                break;
            default:
                objectMapper = new JsonMapper();
                break;
        }

        try {
            File file = new File(fileName);
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(fileName), binaryTree);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static BinaryTree importBinTree(String type) {
        String fileName = "animals" + (Locale.getDefault().getLanguage().equals("eo") ? "_eo" : "") + "." + type;
        ObjectMapper objectMapper;

        switch (type) {
            case "xml":
                objectMapper = new XmlMapper();
                break;
            case "yaml":
                objectMapper = new YAMLMapper();
                break;
            default:
                objectMapper = new JsonMapper();
                break;
        }

        try {
            return objectMapper.readValue(new File(fileName), BinaryTree.class);
        } catch (IOException e) {
            return new BinaryTree();
        }
    }

}
