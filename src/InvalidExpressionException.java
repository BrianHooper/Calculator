class InvalidExpressionException extends Exception {
    public InvalidExpressionException() {
        super();
    }
    public InvalidExpressionException(String errorMessage) {
        super(errorMessage);
    }
}
