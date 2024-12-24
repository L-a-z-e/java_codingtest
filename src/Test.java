public class Test {
    public static void main(String[] args) {
        System.out.println(getExcelColumnName(701));
    }

    private static String getExcelColumnName(int colIndex) {
        StringBuilder columnName = new StringBuilder();

        while (colIndex >= 0) {
            int remainder = colIndex % 26;
            char letter = (char) ('A' + remainder);
            columnName.insert(0, letter);
            colIndex = (colIndex / 26) - 1;

            if (colIndex < 0) {
                break;
            }
        }

        return columnName.toString();
    }
}
