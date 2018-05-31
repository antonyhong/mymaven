public class SubB extends  AbsC {
    @Override
    void printUUid() {
        System.out.println(SubB.unId);
    }

    public static void main(String[] args) {
        SubB subB = new SubB();
        subB.printUUid();

        SubA subA = new SubA();
        subA.printUUid();
    }
}
