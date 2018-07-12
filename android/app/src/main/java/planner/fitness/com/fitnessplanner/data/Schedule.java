package planner.fitness.com.fitnessplanner.data;

public class Schedule {

    private String name;

    private long duration;
    private long time;

    private String place;

    private String conceptSrc;

    private boolean book;

    public Schedule(String name, long duration, long time, String place, String conceptSrc, boolean book){
        this.name = name;
        this.duration = duration;
        this.time = time;

        this.place = place;
        this.conceptSrc = conceptSrc;
        this.book = book;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getConceptSrc() {
        return conceptSrc;
    }

    public void setConceptSrc(String conceptSrc) {
        this.conceptSrc = conceptSrc;
    }

    public boolean isBook() {
        return book;
    }

    public void setBook(boolean book) {
        this.book = book;
    }
}
