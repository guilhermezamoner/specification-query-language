package br.com.specification.common;

public enum SearchOperation {

    EQUALS,
    NEGATE,
    GREATER_THAN,
    LESS_THAN,
    STARTS_WITH,
    CONTAINS;

    public static SearchOperation getOperation(final String input) {
        return switch (input) {
            case "=" -> EQUALS;
            case "!" -> NEGATE;
            case ">" -> GREATER_THAN;
            case "<" -> LESS_THAN;
            case "^" -> STARTS_WITH;
            case "~" -> CONTAINS;
            default -> null;
        };
    }

}
