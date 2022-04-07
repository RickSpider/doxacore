package com.doxacore.dialect;

import java.sql.Types;

import org.hibernate.dialect.PostgreSQL10Dialect;
import org.hibernate.type.StandardBasicTypes;

public class PostgresSqlCustomDialect extends PostgreSQL10Dialect {
	
	public PostgresSqlCustomDialect() {
		super();
		
		registerHibernateType(Types.BIGINT, StandardBasicTypes.LONG.getName());
	}

}
