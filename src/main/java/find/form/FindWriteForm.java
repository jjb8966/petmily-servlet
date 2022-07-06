package find.form;

import java.util.Map;

public class FindWriteForm {

    private int faNumber;
    private int mNumber;
    private String species;
    private String kind;
    private String location;
    private String imgPath;
    private String title;
    private String content;

    public FindWriteForm(int mNumber, String species, String kind, String location, String imgPath, String title, String content) {
        this.mNumber = mNumber;
        this.species = species;
        this.kind = kind;
        this.location = location;
        this.imgPath = imgPath;
        this.title = title;
        this.content = content;
    }

    public FindWriteForm(int faNumber, int mNumber, String species, String kind, String location, String imgPath, String title, String content) {
        this.faNumber = faNumber;
        this.mNumber = mNumber;
        this.species = species;
        this.kind = kind;
        this.location = location;
        this.imgPath = imgPath;
        this.title = title;
        this.content = content;
    }

    public int getFaNumber() {
        return faNumber;
    }

    public int getmNumber() {
        return mNumber;
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

    public String getImgPath() {
        return imgPath;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void validate(Map<String, Boolean> errors) {
        if (title == null || title.trim().isEmpty()) {
            errors.put("title", Boolean.TRUE);
        }
    }
}