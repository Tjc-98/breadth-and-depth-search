/**
 * Tools that is needed to run the program.
 */

package se.kth;

import java.nio.file.*;

class Utilities {
    private Utilities() {}

    /**
     * Read the database from the dat file.
     * @param file the file name.
     * @return the string holding the file information.
     */
    static String readDatabase(String file) {
        Path databasePath = Paths.get(file);
        try {
            String data = Files.readString(databasePath);
            return data;
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

    /**
     * Separate the data into multiple input.
     * @param database string holding the information from the database.
     * @return string array the data separated.
     */
    static String[] getInput(String database) {
        database = database.replaceAll("\r\n", " ");
        String[] data = database.split(" ");
        return data;
    }

    /**
     * Takes a different inputs and distributed the inputs into different indices.
     * @param data the string array of the data separated.
     * @return ordered array symbol table that holds the data as keys and their indices as values.
     */
    static ST<String,Integer> indexDatabase(String[] data) {
        ST<String, Integer> dataIndex = new ST<>();
        int index = 0;
        for(int i = 0; i < data.length; i++) {
            if(!dataIndex.contains(data[i])) {
                dataIndex.put(data[i], index++);
            }
        }
        return dataIndex;
    }

    /**
     * Reverse the keys as values and vise versa.
     * @param indexTable the table holding the data with the indices.
     * @return ordered array symbol table with that is a reverse of the original one.
     */
    static ST<Integer, String> reverseTable(ST<String, Integer> indexTable) {
        ST<Integer, String> table = new ST<>();
        for(String key: indexTable.keys()) {
            table.put(indexTable.get(key), key);
        }
        return table;
    }

    /**
     * Translate the indices back to symbols.
     * @param thePath the result of the search algorithm.
     * @param indexTable ordered array symbol table holding the data and their indices.
     * @return string holding the translation of indices into strings.
     */
    static String translatePath(Queue<Integer> thePath,ST<Integer, String> reverseTable) {
        StringBuilder st = new StringBuilder();
        for(int i : thePath) {
            st.append("-");
            st.append(reverseTable.get(i));
        }
        return st.toString().replaceFirst("-","");
    }
}
