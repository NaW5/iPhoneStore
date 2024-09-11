package helper;

public class ExtractString {

    public String catLinkAnh(String filePath) {
        String delimiter = "\\\\";
        String[] splitPath = filePath.split(delimiter);

        if (splitPath.length > 3) {
            StringBuilder extractedString = new StringBuilder();
            for (int i = 5; i < splitPath.length; i++) {
                extractedString.append(splitPath[i]);
                extractedString.append("\\"); // Add back the delimiters
            }
            extractedString.deleteCharAt(extractedString.length() - 1); // Remove the last delimiter
            return extractedString.toString();
        } else {
            System.out.println("Invalid file path format");
            return null; // Or throw an exception for invalid paths
        }
    }
    public static void main(String[] args) {
        // Test cases with different file paths
        String filePath1 = "C:\\Users\\Smile\\eclipse-workspace\\MobileStore\\Image\\11\\11\\den.png";

        ExtractString extractString = new ExtractString();

        String imageLink1 = extractString.catLinkAnh(filePath1);

        System.out.println("Extracted link for filePath1: " + imageLink1);
    }
}

