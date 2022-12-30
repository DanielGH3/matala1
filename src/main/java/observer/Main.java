package observer;

public class Main {
    public static void main(String[] args) {
        GroupAdmin admin = new GroupAdmin();
        ConcreteMember member = new ConcreteMember();

        admin.register(member);
        admin.append("hello");

        if(member.getValue().equals("hello")){
            System.out.println("Hello");
        }
    }
}
