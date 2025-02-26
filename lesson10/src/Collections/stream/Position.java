package Collections.stream;

public enum Position {
    DEVELOPER(true), TECH_LEAD(true), MANAGER(false), RECRUITER(false);
private final boolean isIT;

    public boolean isIT() {
        return isIT;
    }

    Position(boolean isIT) {
        this.isIT = isIT;


    }
}
