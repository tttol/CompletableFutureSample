public class Entity {
    private String yearAndModel;

    private String seq;

    public Entity(String yearAndModel, String seq) {
        this.yearAndModel = yearAndModel;
        this.seq = seq;
    }

    public String getYearAndModel() {
        return yearAndModel;
    }

    public String getSeq() {
        return seq;
    }

    public void setYearAndModel(String yearAndModel) {
        this.yearAndModel = yearAndModel;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }

}
