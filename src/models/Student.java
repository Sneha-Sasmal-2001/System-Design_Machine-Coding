package models;

public class Student extends User{
    private String class_num;
    private String section;


    public Student(String id, String name, String phone, String class_num, String section){
        super(id,name,phone);
        this.class_num = class_num;
        this.section = section;
    }

    public String getClass_num(){
        return class_num;
    }

    public String getSection(){
        return section;
    }
}
