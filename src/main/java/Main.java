public class Main {

    public static void main(String[] args) {
        //Порядковый номер с 0
        System.out.println(ExampleEnum.PERSON1.ordinal());

        System.out.println(ExampleEnum.PERSON1);

        MyEnum someName = MyEnum.NAME1; // Переменная должна быть типа MyEnum

        switch (someName) {
            case NAME1:
                System.out.println("case 1");
                break;
            case NAME2:
                System.out.println("case 2");
                break;
            default:
                System.out.println("finally case");
        }

        int b = 0;
        int a = switch (b) {
            case 1 -> 11;
            case 0 -> 23;
            default -> 0;
        };
        
        System.out.println(a);

    }
}
