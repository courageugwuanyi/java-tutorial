package com.cloudtruss.peopledb.repository;

import com.cloudtruss.peopledb.annotation.Id;
import com.cloudtruss.peopledb.annotation.MultiSQL;
import com.cloudtruss.peopledb.annotation.SQL;
import com.cloudtruss.peopledb.exception.DataException;
import com.cloudtruss.peopledb.exception.UnableToSaveException;
import com.cloudtruss.peopledb.model.CrudOperation;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;

// <T extends Entity>, Generic T will be a class that extends the Entity Interface
abstract class CrudRepository<T> {

    protected Connection connection;
    private PreparedStatement savePS;
    private PreparedStatement findByIdPS;
    private PreparedStatement updatePS;

    public CrudRepository(Connection connection) {
        try {
            this.connection = connection;
            savePS = connection.prepareStatement(getSqlByAnnotation(CrudOperation.SAVE, this::getSaveSql), Statement.RETURN_GENERATED_KEYS);
            findByIdPS = connection.prepareStatement(getSqlByAnnotation(CrudOperation.FIND_BY_ID, this::getFindByIdSql));
        } catch (SQLException e) {
            throw new DataException("Unable to create prepared statements for CrudRepository", e);
        }

    }

    public T save(T entity) throws UnableToSaveException {
        // if
        try {
            // connection.createStatement().execute(SAVE_PERSON_SQL); This will make us hardcode the values of the insert statement and expose us to SQL injection
            // Frank', 'Jackson', '1950-02-02'); select * from people;  // This is SQL injection. The prepared statement prevents it.
            // if you are not passing any parameter, then you can use createStatement(); e.g Select * from people;
            mapForSave(entity, savePS);
            savePS.executeUpdate();
            // ResultSet is like a 2D arrays (spreadsheet) with rows and columns
            ResultSet rs = savePS.getGeneratedKeys(); // This is the generatedId of the Person that is saved
            while (rs.next()) {
                Long id = rs.getLong(1);
                setIdByAnnotation(entity, id); // Set the Id onto the Person
                postSave(entity, id);
                //System.out.println(entity);
            }
            //System.out.printf("Records affected: %s%n", recordsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new UnableToSaveException("Unable to save person: " + entity);
        }
        return entity;
    }

    public Optional<T> findById(Long id) {
        T entity = null;

        try {
            findByIdPS.setLong(1, id);
            ResultSet rs = findByIdPS.executeQuery(); // rs -> rows and columns
            while (rs.next()) {
                entity = extractEntityFromResultSet(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.ofNullable(entity); // Use optional instead of try-catch or returning null if id is not found.
    }

    public List<T> findAll() {
        List<T> entity = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement(getSqlByAnnotation(CrudOperation.FIND_ALL, this::getFindAllSql),
                    ResultSet.TYPE_SCROLL_INSENSITIVE, // Make a table scrollable to move back and forth and read the records as it is.
                    ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                 entity.add(extractEntityFromResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    public long count() {
        long count = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(getSqlByAnnotation(CrudOperation.COUNT, this::getCountSql));
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getLong(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return count;
    }

    public void delete(T entity) {
        PreparedStatement ps = null;
        try {
            ps = connection.prepareStatement(getSqlByAnnotation(CrudOperation.DELETE_ONE, this::getDeleteSql));
            ps.setLong(1, getIdByAnnotation(entity));
            int affectedRecordCount = ps.executeUpdate();
            System.out.println(affectedRecordCount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setIdByAnnotation(T entity, Long id) {
        Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .forEach(field -> {
                    field.setAccessible(true);
                    try {
                        field.set(entity, id);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Unable to set field " + field.getName());
                    }
                });
    }

    private Long getIdByAnnotation(T entity) {
        return Arrays.stream(entity.getClass().getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .map(field -> {
                    field.setAccessible(true); // Access a private field and read its value.
                    Long id = null;
                    try {
                        id = (long) field.get(entity);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    return id;
                })
                .findFirst().orElseThrow(() -> new RuntimeException("Unable to find id")); // In real time, we should create our own exception rather than using Generic RTException

    }

    public void delete(T... entities) { // Person[] entities
        try {
            Statement stmt = connection.createStatement();

            String ids = Arrays.stream(entities)
                    // this::findIdByAnnotation
                    .map(entity -> getIdByAnnotation(entity))
                    .map(String::valueOf)
                    .collect(joining(","));

            int affectedRecordCount = stmt.executeUpdate(getSqlByAnnotation(CrudOperation.DELETE_MANY, this::getDeleteInSql).replace(":ids", ids));
            System.out.println(affectedRecordCount);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (T entity : entities) {
            delete(entity);
        }
    }

    public void update(T entity) {
        try {
            updatePS = connection.prepareStatement(getSqlByAnnotation(CrudOperation.UPDATE, this::getUpdateSql));
            mapForUpdate(entity, updatePS);
            updatePS.setLong(5, getIdByAnnotation(entity));
            updatePS.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String getSqlByAnnotation(CrudOperation operationType, Supplier<String> sqlGetter){
        Stream<SQL> multiSqlStream = Arrays.stream(this.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(MultiSQL.class))
                .map(method -> method.getAnnotation(MultiSQL.class).value())
                .flatMap(msql -> Arrays.stream(msql));

        Stream<SQL> sqlStream = Arrays.stream(this.getClass().getDeclaredMethods())
                .filter(method -> method.isAnnotationPresent(SQL.class))
                .map(method -> method.getAnnotation(SQL.class));

        return Stream.concat(multiSqlStream, sqlStream)
                .filter(annotation -> annotation.operationType().equals(operationType))
                .map(SQL::value)
                .findFirst().orElseGet(sqlGetter);
    }

    protected void postSave(T entity, Long id)  {}

    /**
     *
     * @return Should return a SQL String like: "DELETE FROM PEOPLE WHERE ID IN (:ids)"
     * Be sure to include the '(:ids)' named parameter and call it 'ids'
     */
    protected String getDeleteInSql() {throw new RuntimeException("SQL not defined");};

    /**
     *
     * @return Returns a String that reperesents the SQL needed to retrieve one entity.
     * The SQL must contain one SQL parameter, i.e., "?", that will bind to the entity's ID.
     */
    protected String getFindByIdSql() { throw new RuntimeException("SQL not defined");};

    protected String getDeleteSql() { throw new RuntimeException("SQL not defined");};

    protected String getCountSql() { throw new RuntimeException("SQL not defined");};

    protected String getUpdateSql() {throw new RuntimeException("SQL not defined"); }

    protected String getFindAllSql() { throw new RuntimeException("SQL not defined");};

    abstract T extractEntityFromResultSet(ResultSet rs) throws SQLException;

    abstract void mapForSave(T entity, PreparedStatement preparedStatement) throws SQLException;

    abstract void mapForUpdate(T entity, PreparedStatement ps) throws SQLException;

    String getSaveSql() {
        return "";
    }
}
