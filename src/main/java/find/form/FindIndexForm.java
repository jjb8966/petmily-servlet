package find.form;

import java.sql.Date;

public class FindIndexForm {

    private int faNumber;
    private String name;
    private String species;
    private String kind;
    private String location;
    private String animalState;
    private String imgPath;
    private Date wrTime;
    private String title;

    public FindIndexForm(int faNumber, String name, String species, String kind, String location, String animalState, String imgPath,
                         Date wrTime, String title) {
        this.faNumber = faNumber;
        this.name = name;
        this.species = species;
        this.kind = kind;
        this.location = location;
        this.animalState = animalState;
        this.imgPath = imgPath;
        this.wrTime = wrTime;
        this.title = title;
    }

    public int getFaNumber() {
        return faNumber;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public String getKind() {
        return kind;
    }

    public String getLocation() {
        return location;
    }

    public String getAnimalState() {
        return animalState;
    }

    public String getImgPath() {
        return imgPath;
    }

    public Date getWrTime() {
        return wrTime;
    }

    public String getTitle() {
        return title;
    }
}