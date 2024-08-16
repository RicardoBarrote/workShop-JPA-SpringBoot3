package project.workshop.services.exceptions;

public class ResourcerNotFound extends RuntimeException {
    public ResourcerNotFound(Integer id) {
        super("Identifier not found: " + id);
    }
}
