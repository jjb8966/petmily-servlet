package abandoned_animal.form;

import java.sql.Date;

public class AbandonedAnimalDetailForm {

    private int abNumber;
    private int sNumber;    // 보호소 번호
    private int age;
    private float weight;
    private String name;
    private String species;
    private String kind;
    private String gender;
    private String location;
    private String uniqueness;
    private String description;
    private String imgPath;
    private String animalState;
    private Date admissionDate;
    //    private Blob video;

    public AbandonedAnimalDetailForm(int abNumber, int sNumber, int age, float weight, String name, String species, String kind, String gender, String location, String uniqueness, String description, String imgPath, String animalState, Date admissionDate) {
        this.abNumber = abNumber;
        this.sNumber = sNumber;
        this.age = age;
        this.weight = weight;
        this.name = name;
        this.species = species;
        this.kind = kind;
        this.gender = gender;
        this.location = location;
        this.uniqueness = uniqueness;
        this.description = description;
        this.imgPath = imgPath;
        this.animalState = animalState;
        this.admissionDate = admissionDate;
    }

    public int getAbNumber() {
        return abNumber;
    }

    public void setAbNumber(int abNumber) {
        this.abNumber = abNumber;
    }

    public int getsNumber() {
        return sNumber;
    }

    public void setsNumber(int sNumber) {
        this.sNumber = sNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getUniqueness() {
        return uniqueness;
    }

    public void setUniqueness(String uniqueness) {
        this.uniqueness = uniqueness;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getAnimalState() {
        return animalState;
    }

    public void setAnimalState(String animalState) {
        this.animalState = animalState;
    }
}
