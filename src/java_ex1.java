import java.io.*;

/**
 * The main class of the program.
 * It performs the requested search according to the input in the text file.
 */
public class java_ex1 {

    public static void main(String[] args) {

        //Input file path.
        final String inputFilePath = "input.txt";

        //Output file path.
        final String outputFilePath = "output.txt";

        //Searchable interface.
        Searchable<Position> searchable = null;

        //Searcher interface.
        Searcher<Position> searcher = null;

        //Stores the solution.
        Solution solution = null;

        //Algorithm type.
        String algorithmType = "";

        //Board size.
        int boardSize = 0;

        //Read data from file.
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFilePath));

            //The first line holds the algorithm type.
            algorithmType = bufferedReader.readLine();

            //The second line holds the board size.
            boardSize = Integer.parseInt(bufferedReader.readLine());

            //Create a new searchable object.
            searchable = new Board(boardSize);
            String rowRead;

            //Read lines containing board values..
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


        //Create the searcher algorithm according to the file input.
        switch (algorithmType) {

            //IDS algorithm.
            case "IDS":
                searcher = new IDSSearcher(boardSize * boardSize);
                break;

            //A* algorithm.
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

    /**
     * Writes the search solution into a text file.
     *
     * @param solution solution's string representation.
     * @param filePath output file path
     */
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
