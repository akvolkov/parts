package app.jdbc;

import app.models.Filter;
import app.entities.Part;

import java.util.List;

public interface Repository {
    List<Part> getListOfParts(Filter filter);
}
