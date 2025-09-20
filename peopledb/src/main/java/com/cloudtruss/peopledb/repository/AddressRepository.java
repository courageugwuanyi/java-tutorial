package com.cloudtruss.peopledb.repository;

import com.cloudtruss.peopledb.annotation.SQL;
import com.cloudtruss.peopledb.model.Address;
import com.cloudtruss.peopledb.model.CrudOperation;
import com.cloudtruss.peopledb.model.Region;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressRepository  extends CrudRepository<Address> {

    public AddressRepository(Connection connection) {
        super(connection);
    }

    @Override
    @SQL(operationType = CrudOperation.FIND_BY_ID, value = """
            SELECT ID, STREET_ADDRESS, ADDRESS2, CITY, STATE, POSTCODE, COUNTY, REGION, COUNTRY
            FROM ADDRESSES
            WHERE ID = ?
            """)
    Address extractEntityFromResultSet(ResultSet rs) throws SQLException {
        long id = rs.getLong("ID");
        String streetAddress = rs.getString("STREET_ADDRESS");
        String address2 = rs.getString("ADDRESS2");
        String city = rs.getString("CITY");
        String state = rs.getString("STATE");
        String county = rs.getString("COUNTY");
        String postcode = rs.getString("POSTCODE");
        Region region = Region.valueOf(rs.getString("REGION").toUpperCase());
        String country = rs.getString("COUNTRY");
        return new Address(id, streetAddress, address2, city, state, postcode, country, county, region);
    }

    @Override
    @SQL(operationType = CrudOperation.SAVE, value = """
            INSERT INTO ADDRESSES (STREET_ADDRESS, ADDRESS2, CITY, STATE, POSTCODE, COUNTY, REGION, COUNTRY)
             VALUES(?, ?, ?, ?, ?, ?, ?, ?)""")
    void mapForSave(Address entity, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, entity.streetAddress());
        preparedStatement.setString(2, entity.address2());
        preparedStatement.setString(3, entity.city());
        preparedStatement.setString(4, entity.state());
        preparedStatement.setString(5, entity.postcode());
        preparedStatement.setString(6, entity.country());
        preparedStatement.setString(7, entity.region().toString());
        preparedStatement.setString(8, entity.country());
    }

    @Override
    void mapForUpdate(Address entity, PreparedStatement ps) throws SQLException {

    }

    @Override
    String getSaveSql() {
        return super.getSaveSql();
    }
}
