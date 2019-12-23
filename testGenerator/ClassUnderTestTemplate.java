package x;

public class ClassUnderTestTemplate {

    public int someMethod(int i, int j) {

        wasteSomeTimeToSimulateRealLifeTestDuration();

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

    private void wasteSomeTimeToSimulateRealLifeTestDuration() {
        try {
            Thread.sleep(Math.round(Math.random()*100));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
