package x;

public class ClassUnderTestTemplate {

    public int someMethod(int i, int j) {

        if (i == j) {
            return -1;
        } else {
            if (i>j) {
                return i;
            } else {
                return j;
            }
        }
    }

}
