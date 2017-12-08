import java.io.*;

/**
 * The main class of the program.
 * It receives the user arguments and runs the algorithms.
 */
public class java_ex1 {

    public static void main(String[] args) {

        String               filePath      = args[0];
        Searchable<Position> searchable    = null;
        Searcher<Position>   searcher      = null;
        Solution             solution      = null;
        String               algorithmType = "";
        int                  boardSize     = 0;

        //Read data from file.
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            algorithmType = bufferedReader.readLine();
            boardSize = Integer.parseInt(bufferedReader.readLine());
            searchable = new Board(boardSize);
            String rowRead;

            //Read board lines.
            for (int row = 0; row < boardSize; row++) {

                rowRead = bufferedReader.readLine();

                //Fill next board line with values.
                for (int column = 0; column < boardSize; column++) {

                    //Create the string for the board.
                    String value = Integer.toString(row) + " " + Integer.toString(column) + " " +
                            rowRead.charAt(column);

                    //Sets the road type in location (x,y) at the board.
                    //NOTE: this is not used to create a State or the game world in advance,
                    // it just sets values in the matrix, which will be used to create states.
                    searchable.addValue(value);
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


        switch (algorithmType) {

            case "IDS":
                searcher = new IDSSearcher(boardSize * boardSize);
                solution = searcher.search(searchable);
                break;
            case "A*":
                //TODO implement
            default:
                break;
        }

        System.out.println(solution.toString());
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
