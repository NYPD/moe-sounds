package com.moesounds.configuration.typeHandlers;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import com.moesounds.domain.enums.MappedEnum;
import com.moesounds.domain.enums.MediaType;

/**
 * MyBatis type handler to handle mapping Java enums to their proper database fields.
 * This type handler does not have to contain a clause for every enum type,
 * but only for enum types where the value in the database column is not identical to the name of the enum constant.
 */
@MappedTypes({MediaType.class})
public class EnumTypeHandler<E extends MappedEnum> extends BaseTypeHandler<E> {
	
	/** Reverse-lookup map for quickly finding enum constants by their mapped values. */
	private final Map<String, E> enumsByMappedValue = new HashMap<String, E>();
	
	/**
	 * Creates a new handler for a specific class of enum.
	 * This will be invoked by MyBatis when attempting to handle an enum it hasn't seen before.
	 * @param type The class of the enum this type handler will handle.
	 */
	public EnumTypeHandler(Class<E> type) {
		
		if(!type.isEnum()) throw new IllegalArgumentException("Can not have a enum type handler on a non-enum class: " + type.getName());
		
		for(E enumConstant : type.getEnumConstants())
			enumsByMappedValue.put(enumConstant.getMappedValue(), enumConstant);
	}

	@Override
	public E getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		String value = cs.getString(columnIndex);
		return enumsByMappedValue.get(value);
	}

	@Override
	public E getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		String value = rs.getString(columnIndex);
		return enumsByMappedValue.get(value);
	}

	@Override
	public E getNullableResult(ResultSet rs, String columnName) throws SQLException {
		String value = rs.getString(columnName);
		E result = enumsByMappedValue.get(value);
		return result;
	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType) throws SQLException {
		String value = parameter.getMappedValue();
		ps.setString(i, value);
	}
}