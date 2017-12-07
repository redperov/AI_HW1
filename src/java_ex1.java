import java.io.*;

/**
 * The main class of the program.
 * It receives the user arguments and runs the algorithms.
 */
public class java_ex1 {

    public static void main(String[] args) {

        String filePath = args[0];
        char[][] board;
        String algorithmType;
        int    boardSize;

        //Read data from file.
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            algorithmType = bufferedReader.readLine();
            boardSize = Integer.parseInt(bufferedReader.readLine());
            board = new char[boardSize][boardSize];
            String rowRead;

            //Read board lines.
            for (int row = 0; row < boardSize; row++) {

                rowRead = bufferedReader.readLine();

                //Fill next board line with values.
                for(int column = 0; column < boardSize; column++){

                    board[row][column] = rowRead.charAt(column);
                }
            }

            bufferedReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error: file reading error.");
            e.printStackTrace();
        }

        //TODO this is the way to invoke, add a switch case for the different algorithms
        //Solution solution = searcher.search(searchable);

        //Write the solution into a file.
//        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
//                //TODO change path to output.txt
//                new FileOutputStream("C:\\Users\\Danny\\Desktop\\inputs\\output.txt"), "utf-8"))) {
//
//            StringBuilder stringBuilder = new StringBuilder();
//
//            for (int i = 0; i < 5; i++){
//                stringBuilder.append(i);
//                stringBuilder.append('-');
//            }
//
//            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
//
//            //Write string to file.
//            writer.write(stringBuilder.toString());
//            writer.close();
//
//        } catch (IOException e) {
//            System.out.println("Error: writing to file error.");
//            e.printStackTrace();
//        }

    }
}
