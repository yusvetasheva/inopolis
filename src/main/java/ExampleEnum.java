public enum ExampleEnum {

    PERSON1 ("A", "a"),

    PERSON2("B","b");

    String firstName;
    String secondName;


    private ExampleEnum (String firstName, String secondName){
        this.firstName = firstName;
        this.secondName = secondName;
    }

}
