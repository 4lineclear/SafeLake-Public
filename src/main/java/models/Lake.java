package models;

/**
 * Constants for each lake that will be included
 */
public enum Lake {
    Superior,
    Ontario,
    Huron,
    Erie;

    public static Lake getLake(String lake) {
        return switch (lake) {
            case "Lake Superior" -> Superior;
            case "Lake Ontario" -> Ontario;
            case "Lake Huron" -> Huron;
            case "Lake Erie" -> Erie;

            default -> throw new IllegalStateException("Unexpected value: " + lake);

        };

    }
}

