package com.cloudtruss.peopledb.repository;

import com.cloudtruss.peopledb.annotation.SQL;
import com.cloudtruss.peopledb.model.Address;
import com.cloudtruss.peopledb.model.CrudOperation;
import com.cloudtruss.peopledb.model.Region;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class personRepository extends CrudRepository<Person> {
    private AddressRepository addressRepository = null;

    public static final String SAVE_PERSON_SQL = """
            INSERT INTO PEOPLE 
            (FIRST_NAME, LAST_NAME, DOB, SALARY, EMAIL, HOME_ADDRESS, WORK_ADDRESS, PARENT_ID) 
            VALUES(?, ?, ?, ?, ?, ?, ?, ?)""";

    public static final String FIND_BY_ID_SQL = """
            SELECT 
            PARENT.ID AS PARENT_ID, PARENT.FIRST_NAME AS PARENT_FIRST_NAME, PARENT.LAST_NAME AS PARENT_LAST_NAME, PARENT.DOB AS PARENT_DOB, PARENT.SALARY AS PARENT_SALARY, PARENT.EMAIL AS PARENT_EMAIL, PARENT.HOME_ADDRESS AS PARENT_HOME_ADDRESS,
            CHILD.ID AS CHILD_ID, CHILD.FIRST_NAME AS CHILD_FIRST_NAME, CHILD.LAST_NAME AS CHILD_LAST_NAME, CHILD.DOB AS CHILD_DOB, CHILD.SALARY AS CHILD_SALARY, CHILD.EMAIL AS CHILD_EMAIL, CHILD.HOME_ADDRESS AS CHILD_HOME_ADDRESS,
            HOME.ID AS HOME_ID, HOME.STREET_ADDRESS AS HOME_STREET_ADDRESS, HOME.ADDRESS2 AS HOME_ADDRESS2, HOME.CITY AS HOME_CITY, HOME.STATE AS HOME_STATE, HOME.POSTCODE AS HOME_POSTCODE, HOME.COUNTY AS HOME_COUNTY , HOME.REGION AS HOME_REGION, HOME.COUNTRY AS HOME_COUNTRY,
            WORK.ID AS WORK_ID, WORK.STREET_ADDRESS AS WORK_STREET_ADDRESS, WORK.ADDRESS2 AS WORK_ADDRESS2, WORK.CITY AS WORK_CITY, WORK.STATE AS WORK_STATE, WORK.POSTCODE AS WORK_POSTCODE, WORK.COUNTY AS WORK_COUNTY , WORK.REGION AS WORK_REGION, WORK.COUNTRY AS WORK_COUNTRY
            FROM PEOPLE AS PARENT
            LEFT OUTER JOIN PEOPLE AS CHILD ON PARENT.ID = CHILD.PARENT_ID
            LEFT OUTER JOIN ADDRESSES AS HOME ON PARENT.HOME_ADDRESS = HOME.ID
            LEFT OUTER JOIN ADDRESSES AS WORK ON PARENT.WORK_ADDRESS = WORK.ID
            WHERE PARENT.ID=?
            """;

    public static final String FIND_ALL_SQL = """
            SELECT 
            PARENT.ID AS PARENT_ID, PARENT.FIRST_NAME AS PARENT_FIRST_NAME, PARENT.LAST_NAME AS PARENT_LAST_NAME, PARENT.DOB AS PARENT_DOB, PARENT.SALARY AS PARENT_SALARY, PARENT.EMAIL AS PARENT_EMAIL, PARENT.HOME_ADDRESS AS PARENT_HOME_ADDRESS
            FROM PEOPLE AS PARENT 
            FETCH FIRST 100 ROWS ONLY
            """;
    public static final String COUNT_SQL = "SELECT COUNT(*) FROM PEOPLE";
    public static final String DELETE_SQL = "DELETE FROM PEOPLE WHERE ID=?";
    public static final String DELETE_IN_SQL = "DELETE FROM PEOPLE WHERE ID IN (:ids)";
    public static final String UPDATE_SQL = "UPDATE PEOPLE SET FIRST_NAME=?, LAST_NAME=?, DOB=?, SALARY=? WHERE ID=?";

    private static Map<String, Integer> aliasColumnIndexMap = new HashMap<>();

    public personRepository(Connection connection) {
        super(connection);
        addressRepository = new AddressRepository(connection);
    }

    @Override
    @SQL(value = SAVE_PERSON_SQL, operationType = CrudOperation.SAVE)
    void mapForSave(Person entity, PreparedStatement preparedStatement) throws SQLException {
        Address savedAddress = null;
        preparedStatement.setString(1, entity.getFirstName());
        preparedStatement.setString(2, entity.getLastName());
        preparedStatement.setTimestamp(3, Timestamp.valueOf(getNormalizedLocalDateTime(entity.getBirthDate())));
        preparedStatement.setBigDecimal(4, entity.getSalary());
        preparedStatement.setString(5, entity.getEmail());
        associateAddressWithPerson(preparedStatement, entity.getHomeAddress(), 6);
        associateAddressWithPerson(preparedStatement, entity.getWorkAddress(), 7);
        associateChildWithPerson(entity, preparedStatement);
    }

    private static void associateChildWithPerson(Person entity, PreparedStatement preparedStatement) throws SQLException {
        Optional<Person> parent = entity.getParent();
        if(parent.isPresent()) {
            preparedStatement.setLong(8, parent.get().getId());
        } else {
            preparedStatement.setObject(8, null);
        }
    }

    @Override
    protected void postSave(Person entity, Long id) {
        entity.getChildren().stream().forEach(this::save);
    }

    private void associateAddressWithPerson(PreparedStatement preparedStatement, Optional<Address> address, int parameterIndex) throws SQLException {
        Address savedAddress;
        if (address.isPresent()) {
            savedAddress = addressRepository.save(address.get()); // Gets the Address from Person and Saves it
            preparedStatement.setLong(parameterIndex, savedAddress.id());
        } else {
            preparedStatement.setObject(parameterIndex, null);
        }
    }

    @Override
    @SQL(value = UPDATE_SQL, operationType = CrudOperation.UPDATE)
    void mapForUpdate(Person entity, PreparedStatement ps) throws SQLException {
        ps.setString(1, entity.getFirstName());
        ps.setString(2, entity.getLastName());
        ps.setTimestamp(3, Timestamp.valueOf(getNormalizedLocalDateTime(entity.getBirthDate())));
        ps.setBigDecimal(4, entity.getSalary());
    }

    // The reflection API will see this method annotation as:
    //@MultiSQL (
    // @SQL(value = FIND_ALL_SQL, operationType = CrudOperation.FIND_ALL),
    // @SQL(value = FIND_ALL_SQL, operationType = CrudOperation.FIND_ALL)
    // )
    @Override
    @SQL(value = FIND_ALL_SQL, operationType = CrudOperation.FIND_ALL)
    @SQL(value = FIND_BY_ID_SQL, operationType = CrudOperation.FIND_BY_ID)
    @SQL(value = COUNT_SQL, operationType = CrudOperation.COUNT)
    @SQL(value = DELETE_SQL, operationType = CrudOperation.DELETE_ONE)
    @SQL(value = DELETE_IN_SQL, operationType = CrudOperation.DELETE_MANY)
     Person extractEntityFromResultSet(ResultSet rs) throws SQLException {
        Person finalParent = null;
        do {
            Person currentParent = extractPersonFromResultSet(rs, "PARENT_").get();
            if(finalParent == null) {
                finalParent = currentParent;
            }
            if(!finalParent.equals(currentParent)) {
                rs.previous(); // Return cursor position back to previous row so that it can re-read current parent
                break;
            }

            Optional<Person> child = extractPersonFromResultSet(rs, "CHILD_");

            Address homeAddress = extractAddress(rs, "HOME_");
            Address workAddress = extractAddress(rs, "WORK_");

            //Optional<Address> homeAddress = addressRepository.findById(homeAddressId);
            finalParent.setHomeAddress(homeAddress);
            finalParent.setWorkAddress(workAddress);
            child.ifPresent(finalParent::addChild);
        } while (rs.next()); // get to next row to see if
        return finalParent ;
    }

    private Optional<Person> extractPersonFromResultSet(ResultSet rs, String aliasPrefix) throws SQLException {
        Long personId = getValueByAlias(aliasPrefix + "ID", rs, Long.class);
        if(personId == null) {
            return Optional.empty();
        }
        String firstName = getValueByAlias(aliasPrefix + "FIRST_NAME", rs, String.class);
        String lastName = getValueByAlias(aliasPrefix + "LAST_NAME", rs, String.class);
        ZonedDateTime dob = ZonedDateTime.of(getValueByAlias(aliasPrefix + "DOB", rs, Timestamp.class).toLocalDateTime(), ZoneId.of("+0"));
        BigDecimal salary = getValueByAlias(aliasPrefix + "SALARY", rs, BigDecimal.class);
        Person person = new Person(personId, firstName, lastName, dob, salary);
        return Optional.of(person);
    }



    private  Address extractAddress(ResultSet rs, String aliasPrefix) throws SQLException {
        if (getValueByAlias(aliasPrefix + "ID", rs, Long.class) == null) {
            return null;
        }
        Long addrId = getValueByAlias(aliasPrefix + "ID", rs, Long.class);
        //Long addressId = rs.getLong("HOME_ID");
        String streetAddress = getValueByAlias(aliasPrefix + "STREET_ADDRESS", rs, String.class);
        String address2 = getValueByAlias(aliasPrefix + "ADDRESS2", rs, String.class);
        String city = getValueByAlias(aliasPrefix + "CITY", rs, String.class);
        String state = getValueByAlias(aliasPrefix + "STATE", rs, String.class);
        String county = getValueByAlias(aliasPrefix + "COUNTY", rs, String.class);
        String postcode = getValueByAlias(aliasPrefix + "POSTCODE", rs, String.class);
        Region region = Region.valueOf(getValueByAlias(aliasPrefix + "REGION", rs, String.class).toUpperCase());
        String country = getValueByAlias(aliasPrefix + "COUNTRY", rs, String.class);
        return new Address(addrId, streetAddress, address2, city, state, postcode, country, county, region);
    }

    // In order for the method to be generic is you will have to do this <T> T
    // Long.class or String.class --- A generic classType
    // Return sth if found, else if you can't find anything, throw an exception
    private  <T> T getValueByAlias(String alias, ResultSet resultSet, Class<T> clazz) throws SQLException {
        int columnCount = resultSet.getMetaData().getColumnCount();
        int foundIdx = getIndexForAlias(alias, resultSet, columnCount);
        return foundIdx == 0 ? null : clazz.cast(resultSet.getObject(foundIdx));
    }

    // Caching
    private int getIndexForAlias(String alias, ResultSet resultSet, int columnCount) throws SQLException {
        Integer foundIdx = aliasColumnIndexMap.getOrDefault(alias, 0);
        if (foundIdx == 0) {
            for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
                if (alias.equals(resultSet.getMetaData().getColumnLabel(columnIndex))) {
                    foundIdx = columnIndex;
                    aliasColumnIndexMap.put(alias, foundIdx);
                    break;
                }
            }
        }
        return foundIdx;
    }

    private static LocalDateTime getNormalizedLocalDateTime(ZonedDateTime birthDate) {
        // The date time should be normalized or standardized to GMT-0 using the withZoneSameIntstant() method
        // so that irrespective of the time zone, a standardized timestamp is saved in the DB
        return birthDate.withZoneSameInstant(ZoneId.of("+0")).toLocalDateTime();
    }
}
