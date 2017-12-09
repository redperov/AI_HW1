import java.io.*;

/**
 * The main class of the program.
 * It performs the requested search according to the input in the file.
 */
public class java_ex1 {

    public static void main(String[] args) {
        final String inputFilePath = "input.txt";
        final String outputFilePath = "output.txt";
        Searchable<Position> searchable    = null;
        Searcher<Position>   searcher      = null;
        Solution             solution      = null;
        String               algorithmType = "";
        int                  boardSize     = 0;

        //Read data from file.
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath));
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
                break;
            case "A*":
                searcher = new AStarSearcher(10 * boardSize * boardSize);
                break;
            default:
                break;
        }

        //Perform the requested search.
        solution = searcher.search(searchable);

        //Write the solution into a file.
        writeSolutionToFile(solution.toString(), outputFilePath);
    }

    public static void writeSolutionToFile(String solution, String filePath) {

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filePath), "utf-8"))) {

            //Write string to file.
            writer.write(solution);
            writer.close();

        } catch (IOException e) {
            System.out.println("Error: writing to file error.");
            e.printStackTrace();
        }

    }
}
