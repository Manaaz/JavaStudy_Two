package messenger.chatClient.models;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileManagement {

    private File historyFile;

    public FileManagement(String username) throws IOException {
        String chatHistoryFile = String.format("chat\\src\\main\\resources\\projectHistory\\%s%s.txt", "history_", username);

        historyFile = new File(chatHistoryFile);
        if (!historyFile.exists()) {
            try {
                historyFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public void historyAppendMessage(String chat) {

        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(historyFile))) {
            bufferedWriter.write(chat);
        } catch (IOException e) {
            // Exception handling
        }

    }

    public String readingFileHistory() throws IOException {
        String chat = "";

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(historyFile))) {
            String line = bufferedReader.readLine();
            while(line != null) {
                chat += line + System.lineSeparator();
                line = bufferedReader.readLine();
            }
        } catch (FileNotFoundException e) {
            // Exception handling
        } catch (IOException e) {
            // Exception handling
        }
        return chat;
    }

}
