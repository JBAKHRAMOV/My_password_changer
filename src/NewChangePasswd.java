
public class NewChangePasswd {
    private static final int MAX_SIZE = 126;
    private static final int MIN_SIZE = 33;
    private static int PASSWD_SIZE;
    private static String CURRENT_WORD;

    public String changePassword(String str) {

        StringBuilder newPasswd = new StringBuilder();

        createCurrentWord(str);

        for (int i = 0; i < str.length(); i++) {

            char c = str.charAt(i);
            int a = getLogicalInt(c) + getLogicalInt(CURRENT_WORD.charAt(c + 3)) + getLogicalInt(CURRENT_WORD.charAt(c + 5));

            newPasswd.append((char) getAnyChar(a));
        }

        return newPasswd.toString();
    }

    private int getLogicalInt(char c) {
        int sum = 0;
        int c1 = getSum(c);
        int size = PASSWD_SIZE - c1;

        for (int i = 0; i < c / 2; i++) {
            int temp = getSum(c1) + getSum(size);
            size -= temp;
            c1 = getSum(c1);
            sum += temp;
        }

        return sum;
    }

    private int getSum(int c) {

        int sum = 0, c1 = c;

        while (c1 != 0) {
            sum += c1 % 10;
            c1 /= 10;
        }

        return sum;
    }


    private int getAnyChar(int c) {

        int count = 0;

        while (MAX_SIZE < c || MIN_SIZE > c) {

            if (MAX_SIZE < c) {
                c /= getSum(c);
            } else {
                c *= getSum(c);
            }

            if (count == 10) {

                while (MAX_SIZE < c || MIN_SIZE > c) {

                    if (MAX_SIZE < c) {
                        c -= getSum(c);
                    } else {
                        c += getSum(c);
                    }

                }

                return c;

            }

            count++;

        }

        return c;
    }

    private void createCurrentWord(String str) {

        StringBuilder builder = new StringBuilder();

        int b = str.length() == 1 ? MAX_SIZE * 2 : MAX_SIZE;

        builder.append(str.repeat(b));

        CURRENT_WORD = builder.toString();
        PASSWD_SIZE = builder.length();

    }
}
