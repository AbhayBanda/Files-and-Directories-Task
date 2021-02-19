import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
class Task {
    public static void main(String[] args) throws IOException {
    
        File MainFolder = new File("./Main");
        File[] SubFolders = MainFolder.listFiles();
        ArrayList<String[]> finalResult = new ArrayList<>();
        finalResult.add(new String[]{"Folder Name","File Name", "Size (In Bytes)"});
        finalResult.add(new String[]{"Main","", ""});
        System.out.println("Main Folder - " + "Main");
        for(File SubFolder : SubFolders)
        {
            File[] files = SubFolder.listFiles();
            System.out.print(SubFolder.getName() + " -");
            for(File file: files)
            {
                System.out.print("  "+file.getName());
                finalResult.add(new String[]{SubFolder.getName(),file.getName(), Long.toString(file.length())});
            }
            System.out.println();
            CSV c = new CSV();
            c.Data_to_CSV(finalResult);
        }
            
    }
}

class CSV {
    public String convertToCSV(String[] data) {
        return Stream.of(data).collect(Collectors.joining(","));
    }
    public void Data_to_CSV(ArrayList<String[]> dataLines) throws IOException {
        File csvOutputFile = new File("./Result.CSV");
        try (PrintWriter pw = new PrintWriter(csvOutputFile)) {
            dataLines.stream()
              .map(this::convertToCSV)
              .forEach(pw::println);
        }
        
    }
}