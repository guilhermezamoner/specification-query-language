package br.com.specification.common;

public class SearchCriteria {

    private String key;
    private SearchOperation operation;
    private Object value;

    public SearchCriteria() {
    }

    public SearchCriteria(String key, SearchOperation operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
    }

    public Object getValue() {
        return this.value;
    }

    public SearchOperation getOperation() {
        return this.operation;
    }

    public String getKey() {
        return this.key;
    }

    @Override
    public String toString() {
        return "SearchCriteria{" +
                "key='" + key + '\'' +
                ", operation=" + operation +
                ", value=" + value +
                '}';
    }
}
